# Cálculo de Distâncias

# Instala pacote de classificação
#install.packages("distances")
library(distances)

# distances constructs a distance metric for a set of points. 
# Currently, it only creates Euclidean distances. It can, however, create distances
# in any linear projection of Euclidean space. In other words, Mahalanobis distances 
# or normalized Euclidean distances are both possible. It is also possible to
# give each dimension of the space different weights.

# distances(data, id_variable = NULL, dist_variables = NULL,
#          normalize = NULL, weights = NULL)

x = c(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
c(10, 9, 8, 7, 6, 6, 7, 8, 9, 10)
my_data_points <- data.frame(x,y)
names(my_data_points) # Veja que x e y são duas variáveis


# Euclidean distances
my_distances1 <- distances(my_data_points)
my_distances1
# O código abaixo gera o mesmo resultado
my_distances2 <- distances(my_data_points,
                           dist_variables = NULL) #dist_variables=NULL é o default
my_distances2



# Euclidean distances in only one dimension
# Calcula distâncias apenas entre os elementos da linha x
my_distances3 <- distances(my_data_points,
                           dist_variables = "x")
my_distances3
# Calcula distâncias apenas entre os elementos da linha y
my_distances4 <- distances(my_data_points,
                           dist_variables = "y")
my_distances4


# Mahalanobis distances
my_distances5 <- distances(my_data_points,
                           normalize = "mahalanobize")
my_distances5


# Custom normalization matrix
my_norm_mat <- matrix(c(3, 1, 1, 3), nrow = 2)
my_norm_mat
my_distances6 <- distances(my_data_points,
                           normalize = my_norm_mat)
my_distances6

my_norm_mat <- var(my_data_points) # matriz de covariância, que é 2x2
my_distances7 <- distances(my_data_points,
                           normalize = my_norm_mat) # equivale a encontrar a distância de Mahalanobis
my_distances7

##################################################################

# CALCULA MÉDIAS DE x E y e insere essas médias na primeira linha de 
Mx = mean(my_data_points$x)
My = mean(my_data_points$y)
Mx
My
M=c(Mx,My)
M
my_data_points_com_media1 = rbind(M,my_data_points) # Inseriu M como primeira linha de my_data_points
my_data_points_com_media1


my_distances8 <- distances(my_data_points_com_media1,
                           normalize = "mahalanobize")
my_distances9 = as.matrix(my_distances8) # converte o objeto "distance" para "matrix"
distancia_para_centroide=my_distances9[,1] # retorna a primeira coluna, 
# que é a distância de cada observação (x,y) para o centroide
distancia_para_centroide1=distancia_para_centroide[-1]
distancia_para_centroide1 #exclui o primeiro elemento, que é a distância do centroide para ele mesmo
