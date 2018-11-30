import os

from PyQt5.QtGui import QIcon

PATH = os.path.join(os.path.dirname(__file__), '../resources/images/')

LOGO_ICON = 'logo.png'
CURVE_ICON = 'curve.png'
CLEAR_ICON = 'clear.png'

def logoIcon():
    return QIcon(PATH + LOGO_ICON)

def curveIcon():
    return QIcon(PATH + CURVE_ICON)

def clearIcon():
    return QIcon(PATH + CLEAR_ICON)
