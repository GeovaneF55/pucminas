#!/usr/bin/python3
# -*- coding: utf-8 -*-
from PyQt5.QtGui import QPainter

from curves import Curves
from bezier import bezier

class Painter(QPainter):

	def __init__(self, parent, curves):
		super().__init__(parent)

		self.curves = curves

	def drawCurves(self):
		for p1, p2, p3, p4 in self.curves.array:
			if p1 is not None:
				for point in self.px(p1):
					self.drawPoint(point['x'], point['y'])
			if p2 is not None:
				for point in self.px(p2):
					self.drawPoint(point['x'], point['y'])
			if p3 is not None:
				for point in self.px(p3):
					self.drawPoint(point['x'], point['y'])
			if p4 is not None:
				for point in self.px(p4):
					self.drawPoint(point['x'], point['y'])

				for point in self.curves.fn(p1, p2, p3, p4):
					self.drawPoint(point['x'], point['y'])

	def px(self, px):
		points = []

		point = {'x': px['x'], 'y': px['y']}
		points.append(point)
		point = {'x': px['x'], 'y': px['y']+1}
		points.append(point)
		point = {'x': px['x']+1, 'y': px['y']}
		points.append(point)
		point = {'x': px['x']+1, 'y': px['y']+1}
		points.append(point)
		point = {'x': px['x'], 'y': px['y']-1}
		points.append(point)
		point = {'x': px['x']-1, 'y': px['y']}
		points.append(point)
		point = {'x': px['x']-1, 'y': px['y']-1}
		points.append(point)
		point = {'x': px['x']-1, 'y': px['y']+1}
		points.append(point)
		point = {'x': px['x']+1, 'y': px['y']-1}
		points.append(point)

		return points
