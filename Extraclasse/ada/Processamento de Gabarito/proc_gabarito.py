import numpy as np
from matplotlib import pyplot as plt
import cv2
from math import sqrt
#print "OpenCV Version : %s " % cv2.__version__
print("OpenCV Version : {}".format(cv2.__version__))

#####################################################################################################################

class Card_Reader:
    def __init__(self):
        self.images = {}

    def print_images(self, images, x=10, y=10):
        for image in images:
            plt.figure(figsize=(x,y))
            plt.axis("off")
            plt.xticks([])
            plt.yticks([])
            plt.imshow(image, cmap='gray')
            plt.show()

class Contours_Manager:
    @staticmethod
    def get_shape_aproximation(contour, factor=0.04):
        peri = cv2.arcLength(contour, True)
        approx = cv2.approxPolyDP(contour, factor * peri, True)
        return approx

    @staticmethod
    def apply_transformation(contours, t_matrix):
        rot_contours = []
        for contour in contours:
            rot_contour = []
            for point in contour:
                point = np.insert(point[0], 2, 1)
                rot_point = np.dot(t_matrix, point)
                rot_contour.append([rot_point])
            rot_contour = np.asarray(rot_contour, dtype=np.int32)
            rot_contours.append(rot_contour)
        return rot_contours

    @staticmethod
    def get_center_point(contour):
        M = cv2.moments(contour)
        cx = int(M['m10']/M['m00'])
        cy = int(M['m01']/M['m00'])
        return (cx, cy)

    @staticmethod
    def get_bound_box_points(center_point, width, height):
        x, y = center_point[0], center_point[1]
        dw, dh = width/2, height/2
        return np.asarray([np.asarray([[x+dw, y+dh]], dtype=np.int32),
                np.asarray([[x+dw, y-dh]], dtype=np.int32),
                np.asarray([[x-dw, y+dh]], dtype=np.int32),
                np.asarray([[x-dw, y-dh]], dtype=np.int32)])

#####################################################################################################################

#0.Leitura da Imagem

reader1 = Card_Reader()

qt_questions = input()
qt_answers = input()
image_path = input()

# Reading Image
reader1.images['original'] = cv2.imread(image_path)

# Reading Image Grayscale
reader1.images['grayscale'] = cv2.imread(image_path,cv2.IMREAD_GRAYSCALE)

reader1.print_images([reader1.images['grayscale']])

#####################################################################################################################

#1.1.1 Pré-Processamento: Remoção de Ruído

# Removing Noyse
reader1.images['bilateral_blur'] = cv2.bilateralFilter(reader1.images['grayscale'].copy(),9,75,75)

reader1.print_images([reader1.images['bilateral_blur']])

#####################################################################################################################

#1.1.2 Pré-Processamento: Binarização da Imagem

# Binarizing Image
ret, reader1.images['binary'] = cv2.threshold(reader1.images['bilateral_blur'].copy(),127,255,cv2.THRESH_BINARY_INV)

reader1.print_images([reader1.images['binary']])

#####################################################################################################################

#1.1.3 Pré-Processamento: Abertura da Imagem

# Apply Morphologiacal Transformations
kernel = np.ones((3,3),np.uint8)
reader1.images['opening'] = cv2.morphologyEx(reader1.images['binary'].copy(), cv2.MORPH_OPEN, kernel)

reader1.print_images([reader1.images['opening']])

#####################################################################################################################

# 1.2 Detecting Image Contours

# Computa contornos da imagem
reader1.images['contours'], contours, hierarchy = cv2.findContours(reader1.images['opening'].copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

# Compondo imagem
reader1.images['original+contours'] = reader1.images['original'].copy()
cv2.drawContours(reader1.images['original+contours'], contours, -1, (255,0,0), 3) # Contonos Detectados (vermelho)

reader1.print_images([reader1.images['contours'], reader1.images['original+contours']])

#####################################################################################################################

#1.3.1 Filtragem de Contornos [Parte 1]: Por Hierarquia

# Filtra contornos por hierarquia e forma
rectangle_contours, rectangle_contours_aprox = [], []
for i in range(len(contours)):
    if(hierarchy[0][i][3] == -1 and hierarchy[0][i][2] == -1): #Se não tiver contorno pai nem contorno filho.
        approx = Contours_Manager.get_shape_aproximation(contours[i], factor=0.03)
        rectangle_contours_aprox.append(approx)
        rectangle_contours.append(contours[i])

# Compondo imagem
reader1.images['original+contours2'] = reader1.images['original'].copy()
cv2.drawContours(reader1.images['original+contours2'], contours, -1, (255,0,0), 3) # Contonos Detectados (vermelho)
cv2.drawContours(reader1.images['original+contours2'], rectangle_contours_aprox, -1, (0,0,255), 3) # Aproximação dos Contonos Filtrados (azul)

reader1.print_images([reader1.images['original+contours2']])

#####################################################################################################################

#1.3.2 Filtragem de Contornos [Parte 2]: Por Área

# Computa métricas (max, média, std) das áreas dos contornos aproximados
areas = [cv2.contourArea(ca) for ca in rectangle_contours_aprox]
mean_area, std_area, max_area = np.mean(areas), np.std(areas), np.max(areas)
print('Mean Contour Area: %.3f\nStd Deviation: %.3f\nMax Contour Area: %.3f\n' % (mean_area, std_area, max_area))

# Seleciona apenas os de maiores áreas
final_marks, final_contours = [], []
for i in range(len(rectangle_contours_aprox)):
    if(areas[i] >= max_area-std_area): #above (max_element_area - std_deviation)
        final_marks.append(rectangle_contours_aprox[i])
        final_contours.append(rectangle_contours[i])
print('Number of selected marks: %d' % len(final_marks))

# Compondo imagem
reader1.images['original+contours'] = reader1.images['original'].copy()
cv2.drawContours(reader1.images['original+contours'], rectangle_contours_aprox, -1, (0,0,255), 3)
cv2.drawContours(reader1.images['original+contours'], final_marks, -1, (0,255,0), 3)

reader1.print_images([reader1.images['original+contours']])

#####################################################################################################################

#1.4 Alinhamento da Imagem
# Obtém-se a angulação dos contornos selecionados

# Computa Angulo Mediano de rotação dos contornos das marcações
angles = []
for cont in final_contours:
    if(len(cont) >= 5):
        (x,y),(MA,ma),angle = cv2.fitEllipse(cont)
        angles.append(angle)
        #print((x, y), (MA,ma), angle)
#print(angles)
avg_angle = np.median(angles)
print('Meadian Rotation Angle: %f' % avg_angle)

# Rotaciona a imagem pelo ângulo mediano
(rows, cols) = reader1.images['opening'].shape[:2]
delta_angle = avg_angle-90
rotation_matrix = cv2.getRotationMatrix2D((cols/2,rows/2),delta_angle,1)
aux = cv2.cvtColor(reader1.images['opening'].copy(), cv2.COLOR_GRAY2BGR)
reader1.images['rotated'] = cv2.warpAffine(aux,rotation_matrix,(cols,rows))

# Rotaciona os contornos pelo ângulo médio
rot_marks = Contours_Manager.apply_transformation(final_marks, rotation_matrix)

# Imprime imagem + contornos
reader1.images['rotated+contour'] = reader1.images['rotated'].copy()
cv2.drawContours(reader1.images['rotated+contour'], rot_marks, -1, (0,255,0), 3)
reader1.print_images([reader1.images['rotated+contour']])

#####################################################################################################################

#1.5 Determining Horizontal and Vertical Marks

import operator
def get_marker_countour_sets(marker_contours):
    center_points = []
    for i in range(len(marker_contours)):
        center_p = Contours_Manager.get_center_point(marker_contours[i])
        center_points.append((center_p[0],center_p[1], i))
    center_points = sorted(center_points, key=operator.itemgetter(0))

    vertical_markers = [marker_contours[center_points[i][2]] for i in range(0, int(int(qt_questions)/2))]
    horizontal_markers = [marker_contours[center_points[i][2]] for i in range(int(int(qt_questions)/2), len(marker_contours))]
    return vertical_markers, horizontal_markers

# Compute median mark contour
heights, widths, center_points = [], [], []
for mark in rot_marks:
    x,y,w,h = cv2.boundingRect(mark)
    heights.append(h)
    widths.append(w)
avg_width = np.median(widths)
avg_height = np.median(heights)
print('Median Width: %d\nMedian Height: %d' % (avg_width, avg_height))

# Separate sets of horizontal (blue) and vertical (red) marker contours
reader1.images['markers_classification'] = reader1.images['rotated'].copy()
vertical_markers, horizontal_markers = get_marker_countour_sets(rot_marks)

# Print image
cv2.drawContours(reader1.images['markers_classification'], vertical_markers, -1, (255,0,0), 3) # red
cv2.drawContours(reader1.images['markers_classification'], horizontal_markers, -1, (0,0,255), 3) # blue
reader1.print_images([reader1.images['markers_classification']])

#####################################################################################################################

# Answers Cells Estimation

# Get marker contours center points
vertical_points, horizontal_points = [], []
vertical_points = [Contours_Manager.get_center_point(mark) for mark in vertical_markers]
vertical_points = sorted(vertical_points, key=operator.itemgetter(1))
horizontal_points = [Contours_Manager.get_center_point(mark) for mark in horizontal_markers]
horizontal_points = sorted(horizontal_points, key=operator.itemgetter(0))
print('Vertical Marks Center Points:')
print(vertical_points)
print('Horizontal Marks Center Points:')
print(horizontal_points)

# Removing points that do not belong to filling spaces
del horizontal_points[0]
del horizontal_points[5]

# Computing Filling Cell contours
fill_markers = {}
i = 0
for v_point in vertical_points:
    line_contours = []
    for h_point in horizontal_points:
        line_contours.append(Contours_Manager.get_bound_box_points((h_point[0], v_point[1]), avg_width, avg_height))
    fill_markers[i] = line_contours
    i += 1

# Ploting Filling Cell contours
reader1.images['fill_markers'] = reader1.images['markers_classification'].copy()
for line in fill_markers:
    markers = fill_markers[line]
    for i in range(len(markers)):
        x,y,w,h = cv2.boundingRect(markers[i])
        cv2.rectangle(reader1.images['fill_markers'],(x,y),(x+w,y+h),(0,255,0),3)

reader1.print_images([reader1.images['fill_markers']])

#####################################################################################################################

#Answers Cells Verification: Pre-processing
#[Uma série de operações morfológicas são aplicadas para garantir
#uma prediminancia de pixels brancos nas células desejadas]

# Binarizing Image
ret, reader1.images['rotated+bin'] = cv2.threshold(reader1.images['rotated'].copy(),127,255,cv2.THRESH_BINARY)
# Apply Erosion
kernel = np.ones((5,5),np.uint8)
reader1.images['rotated+bin'] = cv2.erode(reader1.images['rotated+bin'],kernel,iterations = 1)
# Apply Mean Blur
reader1.images['rotated+bin'] = cv2.blur(reader1.images['rotated+bin'], (5,5))
# Apply Dilatation
reader1.images['rotated+bin'] = cv2.dilate(reader1.images['rotated+bin'],kernel,iterations = 2)
# Print Result Image of Morphological Operations
reader1.print_images([reader1.images['rotated+bin']])

# Ploting Filling Cell contours
reader1.images['rotated+bin+contour'] = reader1.images['rotated+bin'].copy()
for line in fill_markers:
    markers = fill_markers[line]
    for i in range(len(markers)):
        x,y,w,h = cv2.boundingRect(markers[i])
        cv2.rectangle(reader1.images['rotated+bin+contour'],(x,y),(x+w,y+h),(0,255,0),3)
# Print Image with Detected Cells
reader1.print_images([reader1.images['rotated+bin+contour']])

#####################################################################################################################
# Get QrCode from image

#reader1.print_images([reader1.images['rotated+bin+contour']])

#####################################################################################################################

# Answers Cells Verification: Checking Estimation

def verify_cell(p1, p2, image):
    white, black = 0, 0
    #print(p1, p2)
    for i in range(p1[0], p2[0]):
        for j in range(p1[1], p2[1]):
            #print(image[j][i])
            if(image[j][i].any() > 0):
                white += 1
            else:
                black += 1
    white_ratio = float(white)/float(white+black)
    #print(black, white, white_ratio)
    return (white_ratio > 0.5), white_ratio

# Verifing checked cells
marked_list = {}
for line in fill_markers:
    marked = []
    for k in range(0, 5):
        x,y,w,h = cv2.boundingRect(fill_markers[line][k])
        is_marked, w_ratio = verify_cell((x,y), (x+w, y+h), reader1.images['rotated+bin'])
        if(is_marked): marked.append(k)
    marked_list[line+1] = marked

    marked = []
    for k in range(5, 10):
        x,y,w,h = cv2.boundingRect(fill_markers[line][k])
        is_marked, w_ratio = verify_cell((x,y), (x+w, y+h), reader1.images['rotated+bin'])
        if(is_marked): marked.append(k)
    marked_list[line+(int(int(qt_questions)/2))+1] = marked

# Computing Answers
file = open('output/answers.txt', 'w')
mapping = {0:'A', 1:'B', 2:'C', 3:'D', 4:'E', 5:'A', 6:'B', 7:'C', 8:'D', 9:'E'}

for i in range(1, len(marked_list)+1):
    n = len(marked_list[i])
    if(n == 0): out = 'Branco'
    elif(n > 1): out = 'Nulo'
    else: out = mapping[marked_list[i][0]]

    print('Questão %d:' % i)
    print('Checked Cells: ', marked_list[i])
    print('Computed Answer: ', out)

    file.write('%d: %s\n' % (i, out))
file.close()
