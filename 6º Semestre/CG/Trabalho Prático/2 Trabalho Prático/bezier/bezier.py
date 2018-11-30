#!/usr/bin/python3
# -*- coding: utf-8 -*-

"""
Aluno: Geovane Fonseca de Sousa Santos
Matrícula: 553237
Matéria: Coputação Gráfica
Trabalho: Implementações dos algoritmos dados em sala
"""

import sys
import math
import numpy as np
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
from PyQt5.QtGui import *

class Drawer(QWidget):
	newPoint = pyqtSignal(QPoint)

	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.path = QPainterPath()

	def paintEvent(self, event):
		global curves, coord1, coord2, coord3, coord4

		color = Qt.black
		pen = QPen(color, 1, Qt.SolidLine)

		painter = QPainter(self)
		painter.setPen(pen)

		if coord1 is not None:
			for point in self.coords(coord1):
				painter.drawPoint(point['x'], point['y'])

		if coord2 is not None:
			for point in self.coords(coord2):
				painter.drawPoint(point['x'], point['y'])

		if coord3 is not None:
			for point in self.coords(coord3):
				painter.drawPoint(point['x'], point['y'])

		if coord4 is not None:
			for point in self.coords(coord4):
				painter.drawPoint(point['x'], point['y'])

		if curves:
			for p1, p2, p3, p4 in curves:
				for point in bezier(p1, p2, p3, p4):
					painter.drawPoint(point['x'], point['y'])

	def mousePressEvent(self, event):
		global coord1, coord2, coord3, coord4, pontos

		if coord1 is None:
			coord1 = { 'x': event.pos().x(), 'y': event.pos().y() }
			self.newPoint.emit(event.pos())
			self.update()
		elif coord2 is None:
			coord2 = { 'x': event.pos().x(), 'y': event.pos().y() }
			self.newPoint.emit(event.pos())
			self.update()
		elif coord3 is None:
			coord3 = { 'x': event.pos().x(), 'y': event.pos().y() }
			self.newPoint.emit(event.pos())
			self.update()
		else:
			coord4 = { 'x': event.pos().x(), 'y': event.pos().y() }
			self.newPoint.emit(event.pos())
			if pickedTool in ['Curvas Paramétricas de Bêzier']:
				curves.clear()
				curves.append([coord1, coord2, coord3, coord4])
				self.update()
				clearCoordinates()

	def sizeHint(self):
		return QSize(1200, 800)

	def coords(self, coord):
		points = []

		point = {'x': coord['x'], 'y': coord['y']}
		points.append(point)
		point = {'x': coord['x'], 'y': coord['y']+1}
		points.append(point)
		point = {'x': coord['x']+1, 'y': coord['y']}
		points.append(point)
		point = {'x': coord['x']+1, 'y': coord['y']+1}
		points.append(point)
		point = {'x': coord['x'], 'y': coord['y']-1}
		points.append(point)
		point = {'x': coord['x']-1, 'y': coord['y']}
		points.append(point)
		point = {'x': coord['x']-1, 'y': coord['y']-1}
		points.append(point)
		point = {'x': coord['x']-1, 'y': coord['y']+1}
		points.append(point)
		point = {'x': coord['x']+1, 'y': coord['y']-1}
		points.append(point)

		return points

class MyWidget(QWidget):

	drawer = None

	# Adicionando Drawer
	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.setLayout(QVBoxLayout())
		label = QLabel(self)
		self.drawer = Drawer(self)
		label.setText(self.my_label(None))
		self.drawer.newPoint.connect(lambda p: label.setText(self.my_label(p)))
		self.layout().addWidget(label)
		self.layout().addWidget(self.drawer)

	def my_label(self, p):
		if coord1 is None:
			return ('No coordinates')
		elif coord2 is None:
			return ('P1: ( %d : %d)' % (coord1['x'], coord1['y']))
		elif coord3 is None:
			return ('P1: ( %d : %d), P2: ( %d : %d )' % (coord1['x'], coord1['y'], coord2['x'], coord2['y']))
		elif coord4 is None:
			return ('P1: ( %d : %d), P2: ( %d : %d ), P3: ( %d : %d )' % (coord1['x'], coord1['y'], coord2['x'], coord2['y'], coord3['x'], coord3['y']))
		else:
			return ('P1: ( %d : %d), P2: ( %d : %d ), P3: ( %d : %d ), P4: ( %d : %d )' % (coord1['x'], coord1['y'], coord2['x'], coord2['y'], coord3['x'], coord3['y'], coord4['x'], coord4['y']))

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

		# Curvas Paramétricas
		curAct = QAction(QIcon('../Imagens/curve.png'), 'Curvas Paramétricas de Bêzier', self)
		curAct.setShortcut('Ctrl+D')
		curAct.setStatusTip('Inserir uma curva paramétrica com o algoritmo de Bêzier')
		curAct.triggered.connect(self.curEvent)

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
		fileMenu.addAction(curAct)
		fileMenu.addAction(clearAct)
		fileMenu.addAction(exitAct)
		#Menu View
		viewMenu = menubar.addMenu('Ver')
		viewMenu.addAction(viewStatAct)

		#fileMenu.actionTriggered[QAction].connect(self.menuPressed)

		# ToolBar
		toolbar = self.addToolBar('File')
		toolbar.addAction(curAct)
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
	# Bêzier Event
	def curEvent(self):
		clearCoordinates()
		self.setWindowTitle('Meu Paint - Curvas Paramétricas (Bêzier)')

	# Clear Event
	def clearEvent(self):
		clearAll()
		self.widget = MyWidget()
		self.setCentralWidget(self.widget)
		self.setWindowTitle('Meu Paint')

# Implementação das Curvas Paramétricas
# Implementação do Algoritmo de Bêzier
def bezier(p1, p2, p3, p4):
	curve = []

	mb = [
		[1, 0, 0, 0],
		[-3, 3, 0, 0],
		[3, -6, 3, 0],
		[-1, 3, -3, 1]
	]

	x = [
		[p1['x']],
		[p2['x']],
		[p3['x']],
		[p4['x']]
	]
	y = [
		[p1['y']],
		[p2['y']],
		[p3['y']],
		[p4['y']]
	]

	mbx = np.matmul(mb, x)
	mby = np.matmul(mb, y)

	dx = abs(p1['x']-p2['x']) + abs(p2['x']-p3['x']) + abs(p3['x']-p4['x'])
	dy = abs(p1['y']-p4['y']) + abs(p2['y']-p3['y']) + abs(p3['y']-p4['y'])

	passos = 2*((1 if dx == 0 else dx) + (1 if dy == 0 else dy))

	vx = p1['x']
	vy = p2['y']

	for atual in range(passos):
		u = atual/passos
		vx = mbx[0][0] + mbx[1][0] * u + mbx[2][0] * math.pow(u, 2) + mbx[3][0] * math.pow(u, 3)
		vy = mby[0][0] + mby[1][0] * u + mby[2][0] * math.pow(u, 2) + mby[3][0] * math.pow(u, 3)

		point = {'x': round(vx), 'y': round(vy)}
		curve.append(point)

	return curve

def clearCoordinates():
	global coord1, coord2, coord3, coord4

	coord1 = None
	coord2 = None
	coord3 = None
	coord4 = None

def clearAll():
	global pickedTool, curves
	
	clearCoordinates()
	
	pickedTool = 'None'

	curves.clear()

if __name__ == '__main__':

	coord1 = None
	coord2 = None
	coord3 = None
	coord4 = None

	pickedTool = 'None'

	curves = []
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())
