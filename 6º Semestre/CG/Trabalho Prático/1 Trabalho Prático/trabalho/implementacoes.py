#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
Aluno: Geovane Fonseca de Sousa Santos
Matrícula: 553237
Matéria: Coputação Gráfica
Trabalho: Implementações dos algoritmos dados em sala
"""

import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from math import *
from locale import *

class Drawer(QWidget):
	newPoint = pyqtSignal(QPoint)

	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.path = QPainterPath()

	def paintEvent(self, event):
		global cs, lb, lines_dda, lines_bresenham, circs, pontos

		color = Qt.black
		pen = QPen(color, 1, Qt.SolidLine)

		painter = QPainter(self)
		painter.setPen(pen)
		
		if pickedTool in ['Algoritmo de Cohen - Sutherland']:
			for pini, pfim in lines_dda:
				(x1, y1, x2, y2) = cohensutherland(cs["p1"], cs["p2"], pini, pfim)

				if (x1 is not None and y1 is not None and x2 is not None and y2 is not None):
					p_1 = {'x': x1, 'y': y1}
					p_2 = {'x': x2, 'y': y2}

					for point in dda(p_1, p_2):
						painter.drawPoint(point['x'], point['y'])
				
			for pini, pfim in lines_bresenham:
				(x1, y1, x2, y2) = cohensutherland(cs["p1"], cs["p2"], pini, pfim)

				if (x1 is not None and y1 is not None and x2 is not None and y2 is not None):
					p_1 = {'x': x1, 'y': y1}
					p_2 = {'x': x2, 'y': y2}

					for point in bresenham(p_1, p_2):
						painter.drawPoint(point['x'], point['y'])

		elif pickedTool in ['Algoritmo de Liang - Barsky']:
			for pini, pfim in lines_dda:
				(x1, y1, x2, y2) = liangbarsky(lb["p1"], lb["p2"], pini, pfim)

				if (x1 is not None and y1 is not None and x2 is not None and y2 is not None):
					p_1 = {'x': x1, 'y': y1}
					p_2 = {'x': x2, 'y': y2}

					for point in dda(p_1, p_2):
						painter.drawPoint(point['x'], point['y'])

			for pini, pfim in lines_bresenham:
				(x1, y1, x2, y2) = liangbarsky(lb["p1"], lb["p2"], pini, pfim)

				if (x1 is not None and y1 is not None and x2 is not None and y2 is not None):
					p_1 = {'x': x1, 'y': y1}
					p_2 = {'x': x2, 'y': y2}

					for point in bresenham(p_1, p_2):
						painter.drawPoint(point['x'], point['y'])

		else:
			for p1, p2 in lines_dda:
				for point in dda(p1, p2):
					painter.drawPoint(point['x'], point['y'])

			for p1, p2 in lines_bresenham:
				for point in bresenham(p1, p2):
					painter.drawPoint(point['x'], point['y'])

			for p1, p2 in circs:
				for point in circunferencia(p1, p2):
					painter.drawPoint(point['x'], point['y'])

			if pickedTool in ['Algoritmo de Boundary - Fill']:
				for point in pontos:
					painter.drawPoint(point['x'], point['y'])

			elif pickedTool in ['Algoritmo de Flood - Fill']:
				for point in pontos:
					painter.drawPoint(point['x'], point['y'])

	def mousePressEvent(self, event):
		global coord1, pontos
		coord1 = {'x': event.pos().x(), 'y': event.pos().y()}

		self.newPoint.emit(event.pos())

		if pickedTool in ['Algoritmo DDA']:
			lines_dda.append([coord1, coord1])
			self.update()
		elif pickedTool in ['Algoritmo de Bresenham']:
			lines_bresenham.append([coord1, coord1])
			self.update()
		elif pickedTool in ['Circunferências']:
			circs.append([coord1, coord1])
			self.update()
		elif pickedTool in ['Algoritmo de Boundary - Fill']:

			borda = QColor(Qt.black).rgb()
			novaCor = QColor(Qt.black).rgb()
			pontos = boundaryfill({'x':coord1['x'], 'y':coord1['y']}, borda, novaCor, self.getPixelColor)
			self.update()
		elif pickedTool in ['Algoritmo de Flood - Fill']:

			corAntiga = self.getPixelColor(coord1['x'], coord1['y'])
			novaCor = QColor(Qt.black).rgb()
			pontos = floodfill({'x':coord1['x'], 'y':coord1['y']}, corAntiga, novaCor, self.getPixelColor)
			self.update()

	def mouseMoveEvent(self, event):
		global coord2, lines_dda, lines_bresenham, circs
		coord2 = {'x': event.pos().x(), 'y': event.pos().y()}

		self.newPoint.emit(event.pos())

		if pickedTool in ['Algoritmo DDA']:
			lines_dda[len(lines_dda) - 1][1] = coord2
			self.update()
		elif pickedTool in ['Algoritmo de Bresenham']:
			lines_bresenham[len(lines_bresenham) - 1][1] = coord2
			self.update()
		elif pickedTool in ['Circunferências']:
			circs[len(circs) - 1][1] = coord2
			self.update()

	def mouseReleaseEvent(self, event):
		self.selected(pickedTool)
		clearCoordinates()
		self.newPoint.emit(event.pos())

	def sizeHint(self):
		return QSize(1200, 800)

	# Opções de Seleção
	def selected(self, select):
		switcher = {
			'Algoritmo de Cohen - Sutherland': self.makeCS,
			'Algoritmo de Liang - Barsky': self.makeLB
		}
		# Get the function from switcher dictionary
		func = switcher.get(select, lambda: "Erro")
		# Execute the function
		func()

	# Opção Algoritmo de Cohen - Sutherland
	def makeCS(self):
		global coord1, coord2, cs
		p1 = coord1
		p2 = (coord1 if coord2 is None else coord2)

		cs = {"p1" : p1, "p2" : p2}

		self.update()

	# Opção Algoritmo de Liang - Barsky
	def makeLB(self):
		global coord1, coord2, lb
		p1 = coord1
		p2 = (coord1 if coord2 is None else coord2)

		lb = {"p1" : p1, "p2" : p2}

		self.update()
	
	def getPixelColor(self, x, y):
		return self.grab().toImage().pixel(x, y)

class MyWidget(QWidget):

	drawer = None

	# Adicionando Drawer
	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.setLayout(QVBoxLayout())
		label = QLabel(self)
		self.drawer = Drawer(self)
		self.drawer.newPoint.connect(lambda p: label.setText(self.my_label(p)))
		self.layout().addWidget(label)
		self.layout().addWidget(self.drawer)

	def my_label(self, p):
		if coord1 is None:
			return ('No coordinates')
		elif coord2 is None:
			return ('Init Point: ( %d : %d)' % (coord1['x'], coord1['y']))
		else:
			return ('Init Point: ( %d : %d) - Final Point: ( %d : %d )' % (coord1['x'], coord1['y'], coord2['x'], coord2['y']))

	def getDrawer():
		return self.drawer

class MyPaint(QMainWindow):

	widget = None
    
	def __init__(self):
		super().__init__()
		self.setWindowIcon(QIcon('../Imagens/logo.png'))
		self.initUI()
        
        # Adicionando Menus
	def initUI(self):		

		self.widget = MyWidget()
		self.setCentralWidget(self.widget)
        
		# Translation Action
		tranAct = QAction(QIcon('../Imagens/translation.png'), 'Translação', self)
		tranAct.setShortcut('Ctrl+T')
		tranAct.setStatusTip('Transladar a imagem')
		tranAct.triggered.connect(self.translationEvent)

		# Scale Action
		scaleAct = QAction(QIcon('../Imagens/scale.png'), 'Escala', self)
		scaleAct.setShortcut('Ctrl+E')
		scaleAct.setStatusTip('Escalar a imagem')
		scaleAct.triggered.connect(self.scaleEvent)

		# Rotation Action
		rotAct = QAction(QIcon('../Imagens/rotation.png'), 'Rotação', self)
		rotAct.setShortcut('Ctrl+R')
		rotAct.setStatusTip('Rotacionar a imagem')
		rotAct.triggered.connect(self.rotationEvent)

		# Reflection Action
		refAct = QAction(QIcon('../Imagens/reflection.png'), 'Reflexão', self)
		refAct.setShortcut('Ctrl+F')
		refAct.setStatusTip('Reflexão da imagem')
		refAct.triggered.connect(self.reflectionEvent)

		# Shear Action
		shearAct = QAction(QIcon('../Imagens/shear.png'), 'Cisalhamento', self)
		shearAct.setShortcut('Ctrl+S')
		shearAct.setStatusTip('Cisalhamento da imagem')
		shearAct.triggered.connect(self.shearEvent)

		# Line Action
		lineMenu = QMenu('Retas', self)

		# Algoritmo DDA
		ddaAct = QAction(QIcon('../Imagens/line_dda.png'), 'Algoritmo DDA', self)
		ddaAct.setShortcut('Ctrl+D')
		ddaAct.setStatusTip('Inserir uma reta com o algoritmo DDA')
		ddaAct.triggered.connect(self.ddaEvent)

		# Algoritmo de Bresenham
		bresAct = QAction(QIcon('../Imagens/line_bres.png'), 'Algoritmo de Bresenham', self)
		bresAct.setShortcut('Ctrl+B')
		bresAct.setStatusTip('Inserir uma reta com o algoritmo de Bresenham')
		bresAct.triggered.connect(self.bresenhamEvent)

		lineMenu.addAction(ddaAct)
		lineMenu.addAction(bresAct)

		# Circle Action
		circAct = QAction(QIcon('../Imagens/circle.png'), 'Circunferências', self)
		circAct.setShortcut('Ctrl+C')
		circAct.setStatusTip('Inserir uma circunferência')
		circAct.triggered.connect(self.circleEvent)

		# Cut Action
		cutMenu = QMenu('Recortes', self)

		# Algoritmo de Cohen - Sutherland
		csAct = QAction(QIcon('../Imagens/cutting_cs.png'), 'Algoritmo de Cohen - Sutherland', self)
		csAct.setShortcut('Ctrl+X')
		csAct.setStatusTip('Recortar uma Imagem com o algoritmo de Cohen - Sutherland')
		csAct.triggered.connect(self.csEvent)

		# Algoritmo de Liang - Barsky
		lbAct = QAction(QIcon('../Imagens/cutting_lb.png'), 'Algoritmo de Liang - Barsky', self)
		lbAct.setShortcut('Ctrl+Y')
		lbAct.setStatusTip('Recortar uma Imagem com o algoritmo de Liang - Barsky')
		lbAct.triggered.connect(self.lbEvent)

		cutMenu.addAction(csAct)
		cutMenu.addAction(lbAct)

		# Fill Action
		fillMenu = QMenu('Preenchimentos', self)

		# Algoritmo de Boundary - Fill
		bfAct = QAction(QIcon('../Imagens/fill_bf.png'), 'Algoritmo de Boundary - Fill', self)
		bfAct.setShortcut('Ctrl+B')
		bfAct.setStatusTip('Preencher uma Imagem com o algoritmo de Boundary - Fill')
		bfAct.triggered.connect(self.bfEvent)

		# Algoritmo de Flood - Fill
		ffAct = QAction(QIcon('../Imagens/fill_ff.png'), 'Algoritmo de Flood - Fill', self)
		ffAct.setShortcut('Ctrl+P')
		ffAct.setStatusTip('Preencher uma Imagem com o algoritmo de Flood - Fill')
		ffAct.triggered.connect(self.ffEvent)

		fillMenu.addAction(bfAct)
		fillMenu.addAction(ffAct)

		# Clear Action
		clearAct = QAction(QIcon('../Imagens/clear.png'), 'Limpar', self)
		clearAct.setShortcut('Ctrl+K')
		clearAct.setStatusTip('Limpar Desenhos')
		clearAct.triggered.connect(self.clearEvent)

		# Exit Action
		exitAct = QAction(QIcon('../Imagens/exit.png'), 'Sair', self)
		exitAct.setShortcut('Ctrl+Q')
		exitAct.setStatusTip('Sair da aplicação')
		exitAct.triggered.connect(self.close)

		# StatusBar Action
		viewStatAct = QAction('Ver Barra de Status', self, checkable=True)
		viewStatAct.setStatusTip('Ver Barra de Status')
		viewStatAct.setChecked(True)
		viewStatAct.triggered.connect(self.toggleMenu)

		self.statusbar = self.statusBar()

		# MenuBar
		menubar = self.menuBar()
		# Menu File
		fileMenu = menubar.addMenu('&Arquivo')
		fileMenu.addAction(tranAct)
		fileMenu.addAction(scaleAct)
		fileMenu.addAction(rotAct)
		fileMenu.addAction(refAct)
		fileMenu.addAction(shearAct)
		fileMenu.addMenu(lineMenu)
		fileMenu.addAction(circAct)
		fileMenu.addMenu(cutMenu)
		fileMenu.addMenu(fillMenu)
		fileMenu.addAction(clearAct)
		fileMenu.addAction(exitAct)
		#Menu View
		viewMenu = menubar.addMenu('Ver')
		viewMenu.addAction(viewStatAct)

		#fileMenu.actionTriggered[QAction].connect(self.menuPressed)

		# ToolBar
		toolbar = self.addToolBar('File')
		toolbar.addAction(tranAct)
		toolbar.addAction(scaleAct)
		toolbar.addAction(rotAct)
		toolbar.addAction(refAct)
		toolbar.addAction(shearAct)
		toolbar.addAction(ddaAct)
		toolbar.addAction(bresAct)
		toolbar.addAction(circAct)
		toolbar.addAction(csAct)
		toolbar.addAction(lbAct)
		toolbar.addAction(bfAct)
		toolbar.addAction(ffAct)
		toolbar.addAction(clearAct)
		toolbar.addAction(exitAct)

		toolbar.actionTriggered[QAction].connect(self.menuPressed)

		self.setGeometry(0, 0, 1200, 800)
		self.setWindowTitle('Meu Paint')    
		self.show()

	# Altera visão da barra de status
	def toggleMenu(self, state):
		if state:
			self.statusbar.show()
		else:
			self.statusbar.hide()

	# Menu estiver Pressionado
	def menuPressed(self, picked):
		global pickedTool
		pickedTool = picked.text()

	# Events
	# Translation Event
	def translationEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Translação')
		trans_x, trans_y, ok = TranslacaoDialog.getResults()

		if ok:

			for dda in lines_dda:
				for point in dda:
					point['x'] += trans_x
					point['y'] += trans_y

			for bresenham in lines_bresenham:
				for point in bresenham:
					point['x'] += trans_x
					point['y'] += trans_y

			for circ in circs:
				for point in circ:
					point['x'] += trans_x
					point['y'] += trans_y

	# Scale Event
	def scaleEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Escala')
		scale_a, scale_b, ok = EscalaDialog.getResults()

		if ok:
			for dda in lines_dda:
				init_point = dda[0]
				for point in dda:
					point['x'] = ((point['x']-init_point['x'])*scale_a) + init_point['x']
					point['y'] = ((point['y']-init_point['y'])*scale_b) + init_point['y']

			for bresenham in lines_bresenham:
				init_point = bresenham[0]
				for point in bresenham:
					point['x'] = ((point['x']-init_point['x'])*scale_a) + init_point['x']
					point['y'] = ((point['y']-init_point['y'])*scale_b) + init_point['y']

	# Rotation Event
	def rotationEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Rotação')
		angle, ok = RotacaoDialog.getResults()

		two_decimals = '{0:.2f}'

		angle_sin = float(two_decimals.format(sin(angle)))
		angle_cos = float(two_decimals.format(cos(angle)))

		if ok:
			for dda in lines_dda:
				init_point = dda[0]

				for point in dda:
					x1 = ((point['x']-init_point['x']) * angle_cos)
					y1 = ((point['y']-init_point['y']) * angle_sin)

					x2 = ((point['x']-init_point['x']) * angle_sin)
					y2 = ((point['y']-init_point['y']) * angle_cos)

					point['x'] = int(x1 - y1 + init_point['x'])
					point['y'] = int(x2 + y2 + init_point['y'])

			for bresenham in lines_bresenham:
				init_point = bresenham[0]
				for point in bresenham:
					x1 = ((point['x']-init_point['x']) * angle_cos)
					y1 = ((point['y']-init_point['y']) * angle_sin)

					x2 = ((point['x']-init_point['x']) * angle_sin)
					y2 = ((point['y']-init_point['y']) * angle_cos)

					point['x'] = int(x1 - y1 + init_point['x'])
					point['y'] = int(x2 + y2 + init_point['y'])
	# Reflection Event
	def reflectionEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Reflexão')
		axis, ok = ReflexaoDialog.getResults()

		if axis == 'Origem':
			x = -1
			y = -1
		elif axis == 'X':
			x = 1
			y = -1
		elif axis == 'Y':
			x = -1
			y = 1
		else:
			x = 1
			y = 1

		if ok:
			for dda in lines_dda:
				init_point = dda[0]

				for point in dda:
					point['x'] = (x * (point['x'] - init_point['x'])) + init_point['x']
					point['y'] = (y * (point['y'] - init_point['y'])) + init_point['y']

			for bresenham in lines_bresenham:
				init_point = bresenham[0]

				for point in bresenham:
					point['x'] = (x * (point['x'] - init_point['x'])) + init_point['x']
					point['y'] = (y * (point['y'] - init_point['y'])) + init_point['y']

	# Shear Event
	def shearEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Cisalhamento')
		axis, force, ok = CisalhamentoDialog.getResults()

		if axis == 'X':
			x = 0
			y = force
		elif axis == 'Y':
			x = force
			y = 0
		else:
			x = 1
			y = 1

		if ok:
			for dda in lines_dda:
				init_point = dda[0]

				for point in dda:
					x1 = (point['x'] - init_point['x'])
					y1 = (y * (point['y'] - init_point['y']))

					x2 = (x * (point['x'] - init_point['x']))
					y2 = (point['y'] - init_point['y'])

					point['x'] = int(x1 + y1 + init_point['x'])
					point['y'] = int(x2 + y2 + init_point['y'])

			for bresenham in lines_bresenham:
				init_point = bresenham[0]

				for point in bresenham:
					x1 = (point['x'] - init_point['x'])
					y1 = (y * (point['y'] - init_point['y']))

					x2 = (x * (point['x'] - init_point['x']))
					y2 = (point['y'] - init_point['y'])

					point['x'] = x1 + y1 + init_point['x']
					point['y'] = x2 + y2 + init_point['y']

	# Line Events
	# DDA Event
	def ddaEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Retas (DDA)')

	# Bresenham Event
	def bresenhamEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Retas (Bresenham)')

	# Circle Event
	def circleEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Circunferência')

	# Cutting Events
	# Cohen - Sutherland Event
	def csEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Recorte (Cohen - Sutherland)')

	# Liang - Barsky Event
	def lbEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Recorte (Liang - Barsky)')

	# Filling Events
	# Boundary - Fill Event
	def bfEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Preenchimento (Boundary - Fill)')

	# Flood - Fill Event
	def ffEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Preenchimento (Flood - Fill)')

	# Clear Event
	def clearEvent(self):
		clearAll()
		self.widget = MyWidget()
		self.setCentralWidget(self.widget)
		self.setWindowTitle('Meu Paint')

# Implementação das Retas
# Implementação do Algoritmo DDA
def dda(p1, p2):
	line = []

	x = round(p1['x'])
	y = round(p1['y'])
	dx = round(p2['x'] - x)
	dy = round(p2['y'] - y)

	if abs(dx) > abs(dy):
		passos = abs(dx)
	else:
		passos = abs(dy)

	xincr = dx/(1 if passos == 0 else passos)
	yincr = dy/(1 if passos == 0 else passos)

	point = {'x': x, 'y': y}
	line.append(point)

	for _ in range(passos):
		x += xincr;
		y += yincr;

		point = {'x': round(x), 'y': round(y)}
		line.append(point)

	return line

# Implementação do Algoritmo de Bresenham
def bresenham(p1, p2):
	line = []

	x = round(p1['x'])
	y = round(p1['y'])
	dx = round(p2['x'] - x)
	dy = round(p2['y'] - y)

	point = {'x': x, 'y': y}
	line.append(point)

	if dx < 0:
		dx = -dx
		xincr = -1
	else:
		xincr = +1
	if dy < 0:
		dy = -dy
		yincr = -1
	else:
		yincr = +1
	if dx > dy:
		p = 2*dy - dx
		const1 = 2*dy
		const2 = 2*(dy-dx)

		for _ in range(dx):
			x += xincr
			
			if p < 0:
				p += const1
			else:
				y += yincr
				p += const2
			
			point = {'x': x, 'y': y}
			line.append(point)
	else:
		p = 2*dx - dy
		const1 = 2*dx
		const2 = 2*(dx-dy)

		for _ in range(dy):
			y += yincr
			
			if p < 0:
				p += const1
			else:
				x += xincr
				p += const2
			
			point = {'x': x, 'y': y}
			line.append(point)

	return line

# Implementação das Circunferências
def circunferencia(p1, p2):
	circ = []

	c = p1
	r = round(hypot(p2['x'] - p1['x'], p2['y'] - p1['y']))

	x = 0
	y = r
	p = 3 - 2*(r)

	circ = circ + plotaSimetricos(x, y, c)

	while x < y:
		if p < 0:
			p += 4*x + 6
		else:
			p += 4*(x-y) + 10
			y -= 1
		x += 1
		circ = circ + plotaSimetricos(x, y, c)
	
	return circ

def plotaSimetricos(a, b, c):
	sim = []

	point = {'x': c['x'] + a, 'y': c['y'] + b}
	sim.append(point)
	point = {'x': c['x'] + a, 'y': c['y'] - b}
	sim.append(point)
	point = {'x': c['x'] - a, 'y': c['y'] + b}
	sim.append(point)
	point = {'x': c['x'] - a, 'y': c['y'] - b}
	sim.append(point)
	point = {'x': c['x'] + b, 'y': c['y'] + a}
	sim.append(point)
	point = {'x': c['x'] + b, 'y': c['y'] - a}
	sim.append(point)
	point = {'x': c['x'] - b, 'y': c['y'] + a}
	sim.append(point)
	point = {'x': c['x'] - b, 'y': c['y'] - a}
	sim.append(point)

	return sim

def cohensutherland(p1, p2, pini, pfim):
	aceito = False
	feito = False

	(xmax, ymax, xmin, ymin) = limites(p1, p2)

	(x1, y1, x2, y2) = (pini['x'], pini['y'], pfim['x'], pfim['y'])

	while(not feito):
		cod1 = obtemCodigo(p1, p2, x1, y1)
		cod2 = obtemCodigo(p1, p2, x2, y2)

		if(cod1 == 0 and cod2 == 0):
			aceito = True
			feito = True
		elif(cod1 & cod2 != 0 ):
			feito = True
		else:
			if(cod1 != 0):
				cfora = cod1
			else:
				cfora = cod2

			if(verificaBit(cfora, 0)):
				xint = xmin
				yint = y1 + (y2 - y1)*(xmin - x1)/(x2 - x1)
			elif(verificaBit(cfora, 1)):
				xint = xmax
				yint = y1 + (y2 - y1)*(xmax - x1)/(x2 - x1)
			elif(verificaBit(cfora, 2)):
				yint = ymin
				xint = x1 + (x2 - x1)*(ymin - y1)/(y2 - y1)
			elif(verificaBit(cfora, 3)):
				yint = ymax
				xint = x1 + (x2 - x1)*(ymax - y1)/(y2 - y1)

			if(cfora == cod1):
				x1 = xint
				y1 = yint
			else:
				x2 = xint
				y2 = yint
	if(aceito):
		return (round(x1), round(y1), round(x2), round(y2))
	else:
		return (None, None, None, None)

def obtemCodigo(p1, p2, x , y):
	cod = 0

	(xmax, ymax, xmin, ymin) = limites(p1, p2)

	if(x < xmin):
		cod += 1
	if(x > xmax):
		cod += 2
	if(y < ymin):
		cod += 4
	if(y > ymax):
		cod += 8 

	return cod

def limites(p1, p2):

	if(p1['x'] > p2['x']):
		xmax = p1['x']
		xmin = p2['x']
	else:
		xmax = p2['x']
		xmin = p1['x']

	if(p1['y'] > p2['y']):
		ymax = p1['y']
		ymin = p2['y']
	else:
		ymax = p2['y']
		ymin = p1['y']

	return (xmax, ymax, xmin, ymin)

def verificaBit(cfora, bit):
	while(bit):
		cfora >>= 1
		bit-=1
	return (cfora & 1)

def liangbarsky(p1, p2, pini, pfim):
	(xmax, ymax, xmin, ymin) = limites(p1, p2)
	(x1, y1, x2, y2) = (pini['x'], pini['y'], pfim['x'], pfim['y'])

	u1 = 0
	u2 = 1
	dx = x2-x1
	dy = y2-y1

	(u1, u2, esqu) = clipset(-dx, x1-xmin, u1, u2)
	if(esqu):
		(u1, u2, dire) = clipset(dx, xmax-x1, u1, u2)
		if(dire):
			(u1, u2, infe) = clipset(-dy, y1-ymin, u1, u2)
			if(infe):
				(u1, u2, supe) = clipset(dy, ymax-y1, u1, u2)
				if(supe):
					if(u2 < 1):
						x2 = x1 + dx * u2
						y2 = y1 + dy * u2
					if(u1 > 0):
						x1 = x1 + dx * u1
						y1 = y1 + dy * u1
					
					return (round(x1), round(y1), round(x2), round(y2))

	return (None, None, None, None)

def clipset(p, q, u1, u2):
	result = True

	if(p < 0):
		r = (q/p)

		if(r > u2):
			result = False
		elif(r > u1):
			u1 = r
	elif(p > 0):
		r = (q/p)

		if(r < u1):
			result = False
		elif(r < u2):
			u2 = r
	elif(q < 0):
		result = False

	return (u1, u2, result)

def boundaryfill(init, borda, corNova, getCor):

	pilha = [init]
	pontos = []
	dicionario = {}

	while pilha:
		p = pilha.pop()
		x, y = p.values()
		key = '{},{}'.format(x, y)

		if key in dicionario:
			corAtual = dicionario[key]
		else:
			corAtual = getCor(x, y)
			dicionario[key] = corAtual

		if corAtual != borda and corAtual != corNova:
			dicionario[key] = corNova
			pontos.append(p)

			pilha.append({'x': x, 'y': y - 1})
			pilha.append({'x': x, 'y': y + 1})
			pilha.append({'x': x - 1, 'y': y})
			pilha.append({'x': x + 1, 'y': y})

	return pontos

def floodfill(init, corAntiga, corNova, getCor):
	
	pilha = [init]
	pontos = []
	dicionario = {}

	while pilha:
		p = pilha.pop()
		x, y = p.values()
		key = '{},{}'.format(x, y)

		if key in dicionario:
			corAtual = dicionario[key]
		else:
			corAtual = getCor(x, y)
			dicionario[key] = corAtual

		if corAtual == corAntiga:
			dicionario[key] = corNova
			pontos.append(p)

			pilha.append({'x': x, 'y': y - 1})
			pilha.append({'x': x, 'y': y + 1})
			pilha.append({'x': x - 1, 'y': y})
			pilha.append({'x': x + 1, 'y': y})
	return pontos

# Dialog Translação
class TranslacaoDialog(QDialog):
	def __init__(self, parent = None):
		super(TranslacaoDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Translação Eixo X
		self.trans_x = QLineEdit()
		self.trans_x.setValidator(QIntValidator())
		self.trans_x.setMaxLength(4)
		layout.addRow("Translação eixo X: ", self.trans_x)

		# Input Translação Eixo Y
		self.trans_y = QLineEdit()
		self.trans_y.setValidator(QIntValidator())
		self.trans_y.setMaxLength(4)
		layout.addRow("Translação eixo Y: ", self.trans_y)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getX(self):
		return self.trans_x.text()

	def getY(self):
		return self.trans_y.text()

	# Método estático que cria o dialog e retorna (trans_x, trans_y, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = TranslacaoDialog(parent)
		result = dialog.exec_()
		trans_x = dialog.getX()
		trans_y = dialog.getY()
		return (int(0 if trans_x is '' else trans_x), -int(0 if trans_y is '' else trans_y), result == QDialog.Accepted)

# Dialog Escala
class EscalaDialog(QDialog):
	def __init__(self, parent = None):
		super(EscalaDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Escala
		self.scale_a = QDoubleSpinBox()
		self.scale_a.setMinimum(0)
		layout.addRow("Nova Escala (X): ", self.scale_a)

		# Input Escala
		self.scale_b = QDoubleSpinBox()
		self.scale_b.setMinimum(0)
		layout.addRow("Nova Escala (Y): ", self.scale_b)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getScaleA(self):
		return self.scale_a.text()

	def getScaleB(self):
		return self.scale_b.text()

	# Método estático que cria o dialog e retorna (scale, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = EscalaDialog(parent)
		result = dialog.exec_()
		scale_a = dialog.getScaleA()
		scale_b = dialog.getScaleB()
		return (atof(scale_a), atof(scale_b), result == QDialog.Accepted)

# Dialog Rotação
class RotacaoDialog(QDialog):
	def __init__(self, parent = None):
		super(RotacaoDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input ângulo
		self.angle = QDoubleSpinBox()
		self.angle.setRange(-360, 360)
		layout.addRow("Ângulo de Rotação: ", self.angle)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getAngle(self):
		return self.angle.text()

	# Método estático que cria o dialog e retorna (angle, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = RotacaoDialog(parent)
		result = dialog.exec_()
		angle = dialog.getAngle()
		return (radians(-atof(angle)), result == QDialog.Accepted)

# Dialog Reflexão
class ReflexaoDialog(QDialog):
	def __init__(self, parent = None):
		super(ReflexaoDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Eixo
		self.axis = QComboBox()
		self.axis.addItems(["Origem", "X", "Y"])
		self.axis.currentIndexChanged.connect(self.selectionchange)
		layout.addRow("Eixo: ", self.axis)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def selectionchange(self, i):
		pass

	def getAxis(self):
		return self.axis.currentText()

	# Método estático que cria o dialog e retorna (angle, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = ReflexaoDialog(parent)
		result = dialog.exec_()
		axis = dialog.getAxis()
		return (axis, result == QDialog.Accepted)

# Dialog Cisalhamento
class CisalhamentoDialog(QDialog):
	def __init__(self, parent = None):
		super(CisalhamentoDialog, self).__init__(parent)

		layout = QFormLayout(self)

		# Input Eixo
		self.axis = QComboBox()
		self.axis.addItems(["X", "Y"])
		self.axis.currentIndexChanged.connect(self.selectionchange)
		layout.addRow("Eixo: ", self.axis)
		
		# Input Força
		self.force = QDoubleSpinBox()
		self.force.setRange(-10, 10)
		layout.addRow("Força: ", self.force)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def selectionchange(self, i):
		pass

	def getAxis(self):
		return self.axis.currentText()

	def getForce(self):
		return self.force.text()

	# Método estático que cria o dialog e retorna (angle, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = CisalhamentoDialog(parent)
		result = dialog.exec_()
		axis = dialog.getAxis()
		force = dialog.getForce()
		return (axis, atof(force), result == QDialog.Accepted)

def clearCoordinates():
	global coord1, coord2, cs, lb

	coord1 = None
	coord2 = None

def clearAll():
	global pickedTool, lines_dda, lines_bresenham, circs, cs, lb
	
	clearCoordinates()
	
	pickedTool = 'None'

	lines_dda.clear()
	lines_bresenham.clear()
	circs.clear()

	cs = None
	lb = None

	pontos.clear()

if __name__ == '__main__':

	coord1 = None
	coord2 = None

	pickedTool = 'None'

	lines_dda = []
	lines_bresenham = []
	circs = []

	cs = None
	lb = None

	pontos = []
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())

	
