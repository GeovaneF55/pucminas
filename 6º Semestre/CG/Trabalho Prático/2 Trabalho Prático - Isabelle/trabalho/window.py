#!/usr/bin/python3
# -*- coding: utf-8 -*-

from math import sqrt
from PyQt5.QtWidgets import qApp, QDesktopWidget, QMainWindow, QActionGroup
from PyQt5.QtGui import QColor, QPainter, QPen, QIcon
from PyQt5.QtCore import Qt

from painter import (Painter)

from action import Action
from icon import logoIcon, curveIcon, clearIcon
from toolbar import ToolBar

from bezier import bezier
from curves import Curves

class Window(QMainWindow):
	def __init__(self):
		super().__init__()

		self.setWindowIcon(QIcon(logoIcon()))

		self.toolButtons = {}
		self.curves = Curves(bezier)
		self.fillPoints = []

		exitAct = Action(qApp.quit, self)
		exitAct.setShortcut('Ctrl+Q')
		self.addAction(exitAct)

		self.initUI()


	def initUI(self):
		self.resize(1024, 512)
		self.center()
		self.setWindowTitle('Computação Gráfica')

		self.createMenuBar()        
		self.createToolBar()
		self.show()

	def createMenuBar(self):
		self.menubar = self.menuBar()

		menu = self.menubar.addMenu('Curvas')
		group  = QActionGroup(menu)

		action = Action(lambda: self.curves.setFn(bezier),
		                   group, 'Bêzier', True)
		action.setChecked(True)
		menu.addAction(action)

		
	def createToolBar(self):
		self.toolbar = ToolBar(self)
		self.toolbar.setMovable(False)

		group  = QActionGroup(self.toolbar)
		action = Action(lambda: self.toolbar.chooseAction(ToolBar.TOOLS.curve),
				group, "Curva", True, curveIcon())
		self.toolbar.addAction(action, ToolBar.TOOLS.curve)

		action = Action(lambda: self.toolbar.clear(),
				self.toolbar, "Limpar Tela", False, clearIcon())
		self.toolbar.addAction(action, ToolBar.TOOLS.clear)

		self.addToolBar(Qt.LeftToolBarArea, self.toolbar)


	def center(self):
		frame = self.frameGeometry()
		cpoint = QDesktopWidget().availableGeometry().center()
		frame.moveCenter(cpoint)
		self.move(frame.topLeft())

	def mousePressEvent(self, event):
		if event.button() == Qt.LeftButton:
			x, y = event.x(), event.y()

		if self.toolbar.pickedTool == ToolBar.TOOLS.curve:
			pos = len(self.curves.array) - 1		

			if len(self.curves.array) == 0:
				p1 = {'x': x, 'y': y}
				self.curves.append(p1, None, None, None)
				self.update()

			elif self.curves.array[pos][1] is None:
				p2  = {'x': event.pos().x(), 'y': event.pos().y()}
				self.curves.update(pos, self.curves.array[pos][0], p2, None, None)
				self.update()

			elif self.curves.array[pos][2] is None:
				p3  = {'x': event.pos().x(), 'y': event.pos().y()}
				self.curves.update(pos, self.curves.array[pos][0], self.curves.array[pos][1], p3, None)
				self.update()

			elif self.curves.array[pos][3] is None:
				p4  = {'x': event.pos().x(), 'y': event.pos().y()}
				self.curves.update(pos, self.curves.array[pos][0], self.curves.array[pos][1], self.curves.array[pos][2], p4)
				self.update()

	def paintEvent(self, event):
		color = Qt.black
		pen = QPen(color, 1)
		painter = Painter(self, self.curves)
		painter.setPen(pen)

		painter.drawCurves()

