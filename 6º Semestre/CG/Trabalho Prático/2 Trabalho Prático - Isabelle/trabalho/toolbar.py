from PyQt5.QtWidgets import QToolBar
from enum import IntEnum

class ToolBar(QToolBar):
	TOOLS = IntEnum('tools', 'none curve clear')

	def __init__(self, parent):
		super().__init__(parent)
		self.parent = parent
		self.actions = {}
		self.pickedTool = ToolBar.TOOLS.none


	def addAction(self, action, key):
		super().addAction(action)
		self.actions[key] = action


	def chooseAction(self, key):
		if key == ToolBar.TOOLS.clear:
		    self.actions[self.pickedTool].setChecked(False)
		self.pickedTool = key

	def clear(self):
		self.parent.curves.clear()
		self.parent.update()
