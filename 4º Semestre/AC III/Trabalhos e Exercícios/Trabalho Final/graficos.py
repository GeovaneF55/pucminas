import plotly as py
import plotly.graph_objs as go

import numpy as np

# Gráfico de Uma linha
def makeGraphSingleLine(x, y, name, tag):

    trace = go.Scatter(
        x = x,
        y = y,
        mode = "lines+markers"
    )

    data = [trace]
    layout = go.Layout(
        title=tag,
        showlegend=False
    )
    fig = go.Figure(data=data, layout=layout)
    py.offline.plot(fig, filename=name + ".html")
    input()

# Gráfico de múltiplas linhas
def makeGraphMultipleLines(listX, listY, name, tag, listDescr):
    traces = []
    for i in range(len(listX)):
        trace = go.Scatter(
            x = listX[i],
            y = listY[i],
            mode = "lines+markers",
            name = listDescr[i]
        )
        traces.append(trace)

    data = traces
    layout = go.Layout(
        title=tag,
        showlegend=True
    )
    fig = go.Figure(data=data, layout=layout)
    py.offline.plot(fig, filename=name + ".html")
    input()

if __name__ == "__main__":
    listX = []
    listY = []
    name = input()
    size = int(input())
    for i in range(size):
        line = input()
        (x, y) = line.split(',')
        listX.append(int(x))
        listY.append(int(y))

    makeGraphSingleLine(listX, listY, name)
