#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
ZetCode PyQt5 tutorial 

This program creates a skeleton of
a classic GUI application with a menubar,
toolbar, statusbar, and a central widget. 

Author: Jan Bodnar
Website: zetcode.com 
Last edited: August 2017
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
		color = Qt.black
		pen = QPen(color, 5, Qt.SolidLine)

		painter = QPainter(self)
		painter.setPen(pen)
		
		#painter.drawPoint(coord2.x(), coord2.y())

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
		self.update()

	def mouseReleaseEvent(self, event):
		self.update()

		#print(self.parent)

		clearCoordinates()

		self.newPoint.emit(event.pos())

	def sizeHint(self):
		return QSize(1200, 800)

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
			return ('Current coordinates: ( %d : %d )' % (p.x(), p.y()))
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

		# ToolBar
		tooltran = self.addToolBar('Translação')
		tooltran.addAction(tranAct)
		toolscale = self.addToolBar('Escala')
		toolscale.addAction(scaleAct)
		toolrot = self.addToolBar('Rotação')
		toolrot.addAction(rotAct)
		toolshear = self.addToolBar('Cisalhamento')
		toolshear.addAction(shearAct)
		tooldda = self.addToolBar('Retas - Algoritmo DDA')
		tooldda.addAction(ddaAct)
		toolbres = self.addToolBar('Retas - Algoritmo de Bresenham')
		toolbres.addAction(bresAct)
		toolcirc = self.addToolBar('Circunferência')
		toolcirc.addAction(circAct)
		toolcs = self.addToolBar('Recortes - Cohen - Sutherlan')
		toolcs.addAction(csAct)
		toollb = self.addToolBar('Recortes - Algoritmo de Liang - Barsky')
		toollb.addAction(lbAct)
		toolclear = self.addToolBar('Limpar')
		toolclear.addAction(clearAct)
		toolexit = self.addToolBar('Sair')
		toolexit.addAction(exitAct)

		self.setGeometry(0, 0, 1200, 800)
		self.setWindowTitle('Meu Paint')    
		self.show()

	# Altera visão da barra de status
	def toggleMenu(self, state):
        
		if state:
			self.statusbar.show()
		else:
			self.statusbar.hide()

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

	# Liang - Barsky Event
	def clearEvent(self):
		clearCoordinates()
		newWidget = MyWidget()
		self.setCentralWidget(newWidget)
		self.setWindowTitle('Meu Paint')

class Reta:
	p1 = None
	p2 = None

	line = []

	def dda(self):
		x = self.p1.x()
		y = self.p1.y()
		dx = self.p2.x() - x
		dy = self.p2.y() - y

		if abs(dx) > abs(dy):
			passos = abs(dx)
		else:
			passos = abs(dy)

		xincr = dx/passos
		yincr = dy/passos

		point = {'x': x, 'y': y}
		line.append(point)

		while x < self.p2.x():
			x += xincr;
			y += yincr;

			point = {'x': round(x), 'y': round(y)}
			line.append(point)

def clearCoordinates():
	global coord1, coord2
	coord1 = None
	coord2 = None

if __name__ == '__main__':

	coord1 = None
	coord2 = None
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())

	
