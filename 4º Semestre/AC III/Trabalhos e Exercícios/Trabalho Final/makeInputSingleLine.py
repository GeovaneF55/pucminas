import math
import graficos as graph

from optparse import OptionParser

parser = OptionParser()
parser.add_option('-c', '--canneal', action='store_true',
                  help='Use canneal program stats')
(options, args) = parser.parse_args()

f = None

if options and options.canneal:
    f = open('/home/geovane/Downloads/stats_to_use_canneal.txt', 'r')
else:
    f = open('/home/geovane/Downloads/stats_to_use_blackscholes.txt', 'r')

data = f.read().split('Tag: ')

def sort(listX, listY):
    length = len(listX)

    for i in range(length):
        for j in range(length):
            if float(listX[i]) < float(listX[j]):
                temp = listX[i]
                listX[i] = listX[j]
                listX[j] = temp

                temp = listY[i]
                listY[i] = listY[j]
                listY[j] = temp

for line in data:
    if len(line) == 0:
        continue

    (tag, diff, param, *var) = line.split('\n')
    param = param.split(': ')[1].replace(', ', '_')

    listX = []
    listY = []

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

        x = math.log(float(x), 2)
        listX.append(x)
        listY.append(y)

    try:
        sort(listX, listY)
        graph.makeGraphSingleLine(listX, listY, param, tag)
    except ValueError:
        pass
