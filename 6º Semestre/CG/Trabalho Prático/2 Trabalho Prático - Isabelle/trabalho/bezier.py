import math
import numpy as np

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
