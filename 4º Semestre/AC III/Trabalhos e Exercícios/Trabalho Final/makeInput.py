import graficos as graph

f = open('/home/geovane/Downloads/canneal_cacheline_stats.txt', 'r')
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

        listX.append(x)
        listY.append(y)

    try:
        sort(listX, listY)

        # print(listX)
        # print(listY)
        graph.makeGraph(listX, listY, param, tag)
    except ValueError:
        pass
