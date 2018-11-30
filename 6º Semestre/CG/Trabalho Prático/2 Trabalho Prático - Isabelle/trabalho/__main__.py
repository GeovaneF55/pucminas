#!/usr/bin/python3
# -*- coding: utf-8 -*-
import sys

from PyQt5.QtWidgets import (QApplication)
from window import (Window)

if __name__ == '__main__':    
	app = QApplication(sys.argv)
	win = Window()
	sys.exit(app.exec_())
