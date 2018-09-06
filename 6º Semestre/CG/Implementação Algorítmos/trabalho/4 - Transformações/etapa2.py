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
		global lines_dda, lines_bresenham, circs

		color = Qt.black
		pen = QPen(color, 1, Qt.SolidLine)

		painter = QPainter(self)
		painter.setPen(pen)
		
		for p1, p2 in lines_dda:
			for point in dda(p1, p2):
				painter.drawPoint(point['x'], point['y'])

		for p1, p2 in lines_bresenham:
			for point in bresenham(p1, p2):
				painter.drawPoint(point['x'], point['y'])

		for p1, p2 in circs:
			for point in circunferencia(p1, p2):
				painter.drawPoint(point['x'], point['y'])

	def mousePressEvent(self, event):
		global coord1
		coord1 = {'x': event.pos().x(), 'y': event.pos().y()}

		self.newPoint.emit(event.pos())

	def mouseMoveEvent(self, event):
		global coord2
		coord2 = {'x': event.pos().x(), 'y': event.pos().y()}

		self.newPoint.emit(event.pos())

	def mouseReleaseEvent(self, event):
		self.selected(pickedTool)
		clearCoordinates()

		self.newPoint.emit(event.pos())
		self.update()

	def sizeHint(self):
		return QSize(1200, 800)

	# Opções de Seleção
	def selected(self, select):
		#print(select)
		switcher = {
			'Algoritmo DDA': self.makeDDA,
			'Algoritmo de Bresenham': self.makeBresenham,
			'Circunferências': self.makeCircunferencia,
			#'Algoritmo de Cohen - Sutherland': self.makeCS,
			#'Algoritmo de Liang - Barsky': self.makeLB
		}
		# Get the function from switcher dictionary
		func = switcher.get(select, lambda: "Erro")
		# Execute the function
		func()

	# Opção Algoritmo DDA 
	def makeDDA(self):
		global lines_dda
		p1 = coord1
		p2 = (coord1 if coord2 is None else coord2)

		lines_dda.append([p1, p2])

	# Opção Algoritmo Bresenham
	def makeBresenham(self):
		global lines_bresenham
		p1 = coord1
		p2 = (coord1 if coord2 is None else coord2)

		lines_bresenham.append([p1, p2])

	# Opção Circunferência
	def makeCircunferencia(self):
		global circs
		p1 = coord1
		p2 = (coord1 if coord2 is None else coord2)

		circs.append([p1, p2])

	# Opção Algoritmo de Cohen - Sutherland
	def makeCS(self):
		pass

	# Opção Algoritmo de Liang - Barsky
	def makeLB(self):
		pass
		

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

		trans_x = int(trans_x)
		trans_y = int(trans_y)

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

		scale_a = atof(scale_a)
		scale_b = atof(scale_b)

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

			for circ in circs:
				init_point = circ[0]
				for point in circ:
					point['x'] = ((point['x']-init_point['x'])*scale_a) + init_point['x']
					point['y'] = ((point['y']-init_point['y'])*scale_b) + init_point['y']

	# Rotation Event
	def rotationEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Rotação')
		angle, ok = RotacaoDialog.getResults()

		if ok:
			pass

	# Reflection Event
	def reflectionEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Reflexão')
		#angle, ok = ReflectionDialog.getResults()

		#if ok:
		#	pass

	# Shear Event
	def shearEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Cisalhamento')
		axis, force, ok = CisalhamentoDialog.getResults()

		if ok:
			pass

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
		return (trans_x, trans_y, result == QDialog.Accepted)

# Dialog Escala
class EscalaDialog(QDialog):
	def __init__(self, parent = None):
		super(EscalaDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Escala
		self.scale_a = QDoubleSpinBox()
		self.scale_a.setMinimum(0)
		layout.addRow("Nova Escala (A): ", self.scale_a)

		# Input Escala
		self.scale_b = QDoubleSpinBox()
		self.scale_b.setMinimum(0)
		layout.addRow("Nova Escala (B): ", self.scale_b)

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
		return (scale_a, scale_b, result == QDialog.Accepted)

# Dialog Rotação
class RotacaoDialog(QDialog):
	def __init__(self, parent = None):
		super(RotacaoDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input ângulo
		self.angle = QDoubleSpinBox()
		self.angle.setRange(0, 360)
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
		return (angle, result == QDialog.Accepted)

# Dialog Cisalhamento
class CisalhamentoDialog(QDialog):
	def __init__(self, parent = None):
		super(CisalhamentoDialog, self).__init__(parent)

		layout = QFormLayout(self)

		self.axis = QComboBox()
		self.axis.addItems(["X", "Y"])
		self.axis.currentIndexChanged.connect(self.selectionchange)
		layout.addRow("Eixo: ", self.axis)
		
		# Input Força
		self.force = QLineEdit()
		self.force.setValidator(QIntValidator())
		self.force.setMaxLength(4)
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
		#print("Items in the list are :")
		#for count in range(self.axis.count()):
		#	print(self.axis.itemText(count))
		#print("Current index", i, "selection changed ", self.axis.currentText())

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
		return (axis, force, result == QDialog.Accepted)

def clearCoordinates():
	global coord1, coord2

	coord1 = None
	coord2 = None

def clearAll():
	global pickedTool, lines_dda, lines_bresenham, circs
	
	clearCoordinates()
	
	pickedTool = 'None'

	lines_dda.clear()
	lines_bresenham.clear()
	circs.clear()

if __name__ == '__main__':

	coord1 = None
	coord2 = None

	pickedTool = 'None'

	lines_dda = []
	lines_bresenham = []
	circs = []
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())

	
