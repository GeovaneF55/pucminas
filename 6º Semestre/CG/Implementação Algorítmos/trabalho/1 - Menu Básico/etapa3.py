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
from PyQt5.QtCore import pyqtSignal, QPoint, QSize
from PyQt5.QtGui import QIcon, QPainter, QPainterPath

class Drawer(QWidget):
	newPoint = pyqtSignal(QPoint)
	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.path = QPainterPath()    

	def paintEvent(self, event):
		painter = QPainter(self)
		painter.drawPath(self.path)

	def mousePressEvent(self, event):
		self.path.moveTo(event.pos())
		self.update()

	def mouseMoveEvent(self, event):
		self.path.lineTo(event.pos())
		self.newPoint.emit(event.pos())
		self.update()

	def sizeHint(self):
		return QSize(1200, 800)

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

		# Scale Action
		scaleAct = QAction(QIcon('../Imagens/scale.png'), 'Escala', self)
		scaleAct.setShortcut('Ctrl+E')
		scaleAct.setStatusTip('Escalar a imagem')

		# Rotation Action
		rotAct = QAction(QIcon('../Imagens/rotation.png'), 'Rotação', self)
		rotAct.setShortcut('Ctrl+R')
		rotAct.setStatusTip('Rotacionar a imagem')

		# Shear Action
		shearAct = QAction(QIcon('../Imagens/shear.png'), 'Cisalhamento', self)
		shearAct.setShortcut('Ctrl+S')
		shearAct.setStatusTip('Cisalhamento da imagem')

		# Line Action
		lineMenu = QMenu('Retas', self)
		# Algoritmo DDA
		ddaAct = QAction(QIcon('../Imagens/line.png'), 'Algoritmo DDA', self)
		ddaAct.setShortcut('Ctrl+D')
		ddaAct.setStatusTip('Inserir uma reta com o algoritmo DDA')
		# Algoritmo de Bresenham
		bresAct = QAction(QIcon('../Imagens/line.png'), 'Algoritmo de Bresenham', self)
		bresAct.setShortcut('Ctrl+B')
		bresAct.setStatusTip('Inserir uma reta com o algoritmo de Bresenham')

		lineMenu.addAction(ddaAct)
		lineMenu.addAction(bresAct)

		# Circle Action
		circAct = QAction(QIcon('../Imagens/circle.png'), 'Circunferências', self)
		circAct.setShortcut('Ctrl+C')
		circAct.setStatusTip('Inserir uma circunferência')

		# Cut Action
		cutMenu = QMenu('Recortes', self)
		# Algoritmo de Cohen - Sutherland
		csAct = QAction(QIcon('../Imagens/cutting.png'), 'Algoritmo de Cohen - Sutherland', self)
		csAct.setShortcut('Ctrl+X')
		csAct.setStatusTip('Recortar uma Imagem com o algoritmo de Cohen - Sutherland')
		# Algoritmo de Liang - Barsky
		lbAct = QAction(QIcon('../Imagens/cutting.png'), 'Algoritmo de Liang - Barsky', self)
		lbAct.setShortcut('Ctrl+Y')
		lbAct.setStatusTip('Recortar uma Imagem com o algoritmo de Liang - Barsky')

		cutMenu.addAction(csAct)
		cutMenu.addAction(lbAct)

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

class MyWidget(QWidget):

	# Adicionando Drawer
	def __init__(self, parent=None):
		QWidget.__init__(self, parent)
		self.setLayout(QVBoxLayout())
		label = QLabel(self)
		drawer = Drawer(self)
		drawer.newPoint.connect(lambda p: label.setText('Coordinates: ( %d : %d )' % (p.x(), p.y())))
		self.layout().addWidget(label)
		self.layout().addWidget(drawer)
        
if __name__ == '__main__':
    
	app = QApplication(sys.argv)
	mp = MyPaint()
	sys.exit(app.exec_())

	
