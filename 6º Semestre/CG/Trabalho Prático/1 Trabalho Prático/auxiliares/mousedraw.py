import sys
from PyQt5 import QtCore 
from PyQt5 import QtGui, QtWidgets

from PyQt5.QtWidgets import QApplication, QWidget, QInputDialog, QLineEdit, QFileDialog,QGraphicsView,QGraphicsScene,QVBoxLayout

from PyQt5.QtWidgets import (QApplication, QLabel, QWidget)
from PyQt5.QtGui import QPainter, QColor, QPen
from PyQt5.QtCore import Qt, QSize
from PyQt5.QtGui import QPainter, QPainterPath
from PyQt5.QtCore import pyqtSignal, QObject, QPoint
from PyQt5.QtWidgets import QMainWindow, QApplication

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
        return QSize(400, 400)

class MyWidget(QWidget):
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
    w = MyWidget()
    w.show()
    sys.exit(app.exec_())
