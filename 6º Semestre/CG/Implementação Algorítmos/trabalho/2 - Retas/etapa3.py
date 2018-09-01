#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
Aluno: Geovane Fonseca de Sousa Santos
Matrícula: 553237
Matéria: Coputação Gráfica
Trabalho: Implementações dos algoritmos dados em sala
"""

import sys
from PyQt5.QtWidgets import QMainWindow, QApplication, QWidget, QMenu, QVBoxLayout, QTextEdit, QAction, QLabel
from PyQt5.QtCore import Qt, pyqtSignal, QPoint, QSize
from PyQt5.QtGui import QIcon, QPainter, QPainterPath, QPen

class Coordinate:
	coord_x = None
	coord_y = None

	def setCoordinates(self, x, y):
		self.coord_x = x
		self.coord_y = y

	def clearCoordinate(self):
		self.coord_x = None
		self.coord_y = None

	def x(self):
		return self.coord_x

	def y(self):
		return self.coord_y

class Drawer(QWidget):
	newPoint = pyqtSignal(QPoint)

	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.path = QPainterPath()

	def paintEvent(self, event):
		global lines, circs

		color = Qt.black
		pen = QPen(color, 1, Qt.SolidLine)

		painter = QPainter(self)
		painter.setPen(pen)
		
		for line in lines:
			for point in line:
				painter.drawPoint(point['x'], point['y'])

	def mousePressEvent(self, event):
		global coord1
		coord1 = Coordinate()
		coord1.setCoordinates(event.pos().x(), event.pos().y())

		self.newPoint.emit(event.pos())

	def mouseMoveEvent(self, event):
		global coord2
		coord2 = Coordinate()
		coord2.setCoordinates(event.pos().x(), event.pos().y())

		self.newPoint.emit(event.pos())

	def mouseReleaseEvent(self, event):
		self.selected('Algoritmo DDA')
		clearCoordinates()

		self.newPoint.emit(event.pos())
		self.update()

	def sizeHint(self):
		return QSize(1200, 800)

	# Opções de Seleção
	def selected(self, select):
		switcher = {
			'Algoritmo DDA': self.makeDDA,
			'Bresenham': self.makeBresenham,
		}
		# Get the function from switcher dictionary
		func = switcher.get(select, lambda: "Erro")
		# Execute the function
		func()

	# Opção Algoritmo DDA 
	def makeDDA(self):
		global lines, circs

		reta = Reta()
		reta.p1 = coord1
		reta.p2 = (coord1 if coord2 is None else coord2)
		lines.append(reta.dda())

	# Opção Algoritmo Bresenham
	def makeBresenham(self):
		global lines, circs

		reta = Reta()
		reta.p1 = coord1
		reta.p2 = (coord1 if coord2 is None else coord2)
		lines.append(reta.bresenham())
		

class MyWidget(QWidget):

	# Adicionando Drawer
	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.setLayout(QVBoxLayout())
		label = QLabel(self)
		drawer = Drawer(self)
		drawer.newPoint.connect(lambda p: label.setText(self.my_label(p)))
		self.layout().addWidget(label)
		self.layout().addWidget(drawer)

	def my_label(self, p):
		if coord1 is None:
			return ('No coordinates')
		elif coord2 is None:
			return ('Current coordinates: ( %d : %d ) - Init Point: ( %d : %d)' % (p.x(), p.y(), coord1.x(), coord1.y()))
		else:
			return ('Current coordinates: ( %d : %d ) - Init Point: ( %d : %d) - Final Point: ( %d : %d )' % (p.x(), p.y(), coord1.x(), coord1.y(), coord2.x(), coord2.y()))

class MyPaint(QMainWindow):
    
	def __init__(self):
		super().__init__()
		self.initUI()
        
        # Adicionando Menus
	def initUI(self):		

		widget = MyWidget()
		self.setCentralWidget(widget)
        
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

		# Shear Action
		shearAct = QAction(QIcon('../Imagens/shear.png'), 'Cisalhamento', self)
		shearAct.setShortcut('Ctrl+S')
		shearAct.setStatusTip('Cisalhamento da imagem')
		shearAct.triggered.connect(self.shearEvent)

		# Line Action
		lineMenu = QMenu('Retas', self)
		# Algoritmo DDA
		ddaAct = QAction(QIcon('../Imagens/line.png'), 'Algoritmo DDA', self)
		ddaAct.setShortcut('Ctrl+D')
		ddaAct.setStatusTip('Inserir uma reta com o algoritmo DDA')
		ddaAct.triggered.connect(self.ddaEvent)
		# Algoritmo de Bresenham
		bresAct = QAction(QIcon('../Imagens/line.png'), 'Algoritmo de Bresenham', self)
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
		csAct = QAction(QIcon('../Imagens/cutting.png'), 'Algoritmo de Cohen - Sutherland', self)
		csAct.setShortcut('Ctrl+X')
		csAct.setStatusTip('Recortar uma Imagem com o algoritmo de Cohen - Sutherland')
		csAct.triggered.connect(self.csEvent)
		# Algoritmo de Liang - Barsky
		lbAct = QAction(QIcon('../Imagens/cutting.png'), 'Algoritmo de Liang - Barsky', self)
		lbAct.setShortcut('Ctrl+Y')
		lbAct.setStatusTip('Recortar uma Imagem com o algoritmo de Liang - Barsky')
		lbAct.triggered.connect(self.lbEvent)

		cutMenu.addAction(csAct)
		cutMenu.addAction(lbAct)

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
		fileMenu.addAction(shearAct)
		fileMenu.addMenu(lineMenu)
		fileMenu.addAction(circAct)
		fileMenu.addMenu(cutMenu)
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
		toolbar.addAction(shearAct)
		toolbar.addAction(ddaAct)
		toolbar.addAction(bresAct)
		toolbar.addAction(circAct)
		toolbar.addAction(csAct)
		toolbar.addAction(lbAct)
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

	# Scale Event
	def scaleEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Escala') 

	# Rotation Event
	def rotationEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Rotação') 

	# Shear Event
	def shearEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Cisalhamento')

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

	# Clear Event
	def clearEvent(self):
		clearAll()
		newWidget = MyWidget()
		self.setCentralWidget(newWidget)
		self.setWindowTitle('Meu Paint')

# Implementação das Retas
class Reta:
	p1 = None
	p2 = None

	line = []

	# Implementação do Algoritmo DDA
	def dda(self):
		x = self.p1.x()
		y = self.p1.y()
		dx = self.p2.x() - x
		dy = self.p2.y() - y

		if abs(dx) > abs(dy):
			passos = abs(dx)
		else:
			passos = abs(dy)

		xincr = dx/(1 if passos == 0 else passos)
		yincr = dy/(1 if passos == 0 else passos)

		point = {'x': x, 'y': y}
		self.line.append(point)

		for _ in range(passos):
			x += xincr;
			y += yincr;

			point = {'x': round(x), 'y': round(y)}
			self.line.append(point)

		return self.line

	# Implementação do Algoritmo de Bresenham
	def bresenham(self):
		x = self.p1.x()
		y = self.p1.y()
		dx = self.p2.x() - x
		dy = self.p2.y() - y

		point = {'x': x, 'y': y}
		self.line.append(point)

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
				self.line.append(point)
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
				self.line.append(point)

		return self.line

def clearCoordinates():
	global coord1, coord2

	coord1 = None
	coord2 = None

def clearAll():
	global pickedTool, lines, circs
	
	clearCoordinates()
	
	pickedTool = 'None'

	lines.clear()
	circs.clear()

if __name__ == '__main__':

	coord1 = None
	coord2 = None

	pickedTool = 'None'

	lines = []
	circs = []
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())

	
