import re
import math
import graficos as graph

from optparse import OptionParser

def sort(listX, listY):
    length = len(listX)

    for i in range(length):
        for j in range(length):
            if int(listX[i]) < int(listX[j]):
                temp = listX[i]
                listX[i] = listX[j]
                listX[j] = temp

                temp = listY[i]
                listY[i] = listY[j]
                listY[j] = temp

parser = OptionParser()
parser.add_option('-f', '--file', help='Stats file')
#parser.add_option('-c', '--canneal', action='store_true',
#                  help='Use canneal program stats')
(options, args) = parser.parse_args()

f = None
print(options.file)
f = open(options.file, 'r')

#if options and options.canneal:
#    f = open('/home/geovane/Downloads/stats_to_use_canneal.txt', 'r')
#else:
#    f = open('/home/geovane/Downloads/stats_to_use_blackscholes.txt', 'r')

translate = {
    'l1d_assoc=': 'Assoc. cache L1 Dados = ',
    'l1d_size=': 'Cache L1 Dados = ',
    'l1i_assoc=': 'Assoc. cache L1 Instr. = ',
    'l1i_size=': 'Cache L1 Instr. = ',
    'l2_assoc=': 'Assoc. cache L2 = ',
    'l2_size=': 'Cache L2 = ',
    'num_cpus=': 'Qtd. CPUs = ',
    'cacheline_size=': 'Tamanho do bloco = '
}

listX = []
listY = []
listDescr = []

attr = f.readline()[:-1]
data = f.read().split('Tag: ')

for line in data:
    if len(line) == 0:
        continue

    (tag, diff, param, *var) = line.split('\n')

    descr = param.split(attr)[1]
    descr = descr.split(',')[0]
    descr = attr + descr;

    for key, value in translate.items():
        if key in descr:
            descr = descr.replace(key, value)

    listDescr.append(descr)

    param = param.split(': ')[1].replace(', ', '_')

    traceY = []
    traceX = []

    for coord in var:
        if len(coord) == 0:
            continue

        (x, y) = coord.split(': ')
        (leg, x) = x.split(' ')

        x = x.split('kB')[0]

        if 'size=' in x:
            index = x.index('=')
            x = x[index + 1:]

        leg = leg.strip()

        x = math.log(int(x), 2)
        traceX.append(x)
        traceY.append(y)

    try:
        sort(traceX, traceY)
        listX.append(traceX)
        listY.append(traceY)
    except ValueError:
        pass

tag = tag.split('system.')[1]
if 'cpu.' in tag:
    tag = tag.split('cpu.')[1]
tag = tag.split('::total')[0]
tag = re.sub('[._]', ' ', tag)
graph.makeGraphMultipleLines(listX, listY, param, tag, listDescr)
