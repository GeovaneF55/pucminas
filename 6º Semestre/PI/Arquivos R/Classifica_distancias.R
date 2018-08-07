# Cálculo de Distâncias

# Instala pacote de classificação
#install.packages("distances")
library(distances)

library(e1071)
data(iris)
attach(iris)

## Taking back-up of the input file, in case the original data is required later
iris.bkup <- iris
head(iris.bkup)
summary(iris.bkup)

#Determina quais variáveis são numéricas
num.vars <- sapply(iris, is.numeric) # retorna matriz de mesmo tamanho que iris, com
# componentes TRUE se o valor é numérico
num.vars
# Normalize the numeric variables 
iris[num.vars] <- lapply(iris[num.vars], scale) # retorna matriz de mesmo tamanho que iris, 
# aplicando uma escala sobre as variáveis numéricas.
summary(iris) # base de dados original
summary(iris[num.vars]) # subset da base de dados original, considerando apenas variáveis numéricas

# Gera conjunto de treino e teste, usando uma semente, com 75 observações em cada 
# (já que a base tem 150), para todos alunos encontrarem os mesmos subconjuntos.
# Isso foi necessário porque na base iris, há 50 observações consecutivas de cada
# espécie.
set.seed(9850) 
g<-runif(nrow(iris)) # distribuição randômica uniforme - vetor de mesmo tamanho do número de linhas de iris
irisr<-iris[order(g),] #ordena o conjunto usando a ordem gerada pelos elementos aleatórios

test <- 1:25
train.iris <- irisr[-test,] # serão 125 observações
test.iris <- irisr[test,] # serão 25 observações
## Excluir a classe e colocá-la em uma variável separada 
train.iris_num <- subset(train.iris, select = -Species) 
# train.iris_num é igual a train.iris, excluindo-se a classe.
test.iris_num <- subset(test.iris, select = -Species) 
train.def <- train.iris$Species
test.def <- test.iris$Species

head(train.iris) # primeiras linhas da base de treino
head(test.iris) # primeiras linhas da base de teste
head(train.iris_num) # primeiras linhas da base de treino, sem a classe
head(test.iris_num) # primeiras linhas da base de teste, sem a classe


# CALCULA CENTROIDES DAS 3 CLASSES A PARTIR DAS OBSERVAÇÕES DE TREINAMENTO
treino_setosa=subset(train.iris,train.iris$Species=='setosa', select = -Species)
treino_setosa
treino_virginica=subset(train.iris,train.iris$Species=='virginica', select = -Species)
treino_virginica
treino_versicolor=subset(train.iris,train.iris$Species=='versicolor', select = -Species)
treino_versicolor

# CALCULA centroides para cada classe e insere essas médias na primeira linha
SL_media = mean(treino_setosa$Sepal.Length)
SW_media = mean(treino_setosa$Sepal.Width)
PL_media = mean(treino_setosa$Petal.Length)
PW_media = mean(treino_setosa$Petal.Width)
M_setosa=c(SL_media,SW_media,PL_media,PW_media)

SL_media = mean(treino_virginica$Sepal.Length)
SW_media = mean(treino_virginica$Sepal.Width)
PL_media = mean(treino_virginica$Petal.Length)
PW_media = mean(treino_virginica$Petal.Width)
M_virginica=c(SL_media,SW_media,PL_media,PW_media)

SL_media = mean(treino_versicolor$Sepal.Length)
SW_media = mean(treino_versicolor$Sepal.Width)
PL_media = mean(treino_versicolor$Petal.Length)
PW_media = mean(treino_versicolor$Petal.Width)
M_versicolor=c(SL_media,SW_media,PL_media,PW_media)

M=cbind(M_setosa,M_virginica,M_versicolor)
M


# CÁLCULO DAS DISTÂNCIAS das OBSERVAÇOES DE TESTE PARA OS CENTROIDES DE CADA CLASSE
# distances constructs a distance metric for a set of points. 
# Currently, it only creates Euclidean distances. It can, however, create distances
# in any linear projection of Euclidean space. In other words, Mahalanobis distances 
# or normalized Euclidean distances are both possible. It is also possible to
# give each dimension of the space different weights.

# distances(data, id_variable = NULL, dist_variables = NULL,
#          normalize = NULL, weights = NULL)

test.iris_num
M_setosa
M_virginica
M_versicolor
dis_test.iris_num_setosa = rbind(M_setosa,test.iris_num) # Inseriu centroide de setoda como primeira linha
dis_test.iris_num_versicolor = rbind(M_versicolor,test.iris_num) # Inseriu centroide de versicolor como primeira linha
dis_test.iris_num_virginica = rbind(M_virginica,test.iris_num) # Inseriu centroide de versicolor como primeira linha

# DISTÂNCIA PARA CENTROIDE DA CLASSE SETOSA
my_distances_setosa <- distances(dis_test.iris_num_setosa,
                           normalize = "mahalanobize")
my_distances_setosa = as.matrix(my_distances_setosa) # converte o objeto "distance" para "matrix"
dist_centroide_setosa=my_distances_setosa[,1] # retorna a primeira coluna, 
# que é a distância de cada observação (x,y) para o centroide
dist_centroide_setosa=dist_centroide_setosa[-1] #exclui o primeiro elemento, que é a distância do centroide para ele mesmo
dist_centroide_setosa 

# DISTÂNCIA PARA CENTROIDE DA CLASSE VIRGINICA
my_distances_virginica <- distances(dis_test.iris_num_virginica,
                                 normalize = "mahalanobize")
my_distances_virginica = as.matrix(my_distances_virginica) # converte o objeto "distance" para "matrix"
dist_centroide_virginica=my_distances_virginica[,1] # retorna a primeira coluna, 
# que é a distância de cada observação (x,y) para o centroide
dist_centroide_virginica=dist_centroide_virginica[-1] #exclui o primeiro elemento, que é a distância do centroide para ele mesmo
dist_centroide_virginica 

# DISTÂNCIA PARA CENTROIDE DA CLASSE VERSICOLOR
my_distances_versicolor <- distances(dis_test.iris_num_versicolor,
                                    normalize = "mahalanobize")
my_distances_versicolor = as.matrix(my_distances_versicolor) # converte o objeto "distance" para "matrix"
dist_centroide_versicolor=my_distances_versicolor[,1] # retorna a primeira coluna, 
# que é a distância de cada observação (x,y) para o centroide
dist_centroide_versicolor=dist_centroide_versicolor[-1] #exclui o primeiro elemento, que é a distância do centroide para ele mesmo
dist_centroide_versicolor 

dist_centroide_setosa
dist_centroide_virginica 
dist_centroide_versicolor 

dist=data.frame(dist_centroide_setosa,dist_centroide_virginica,dist_centroide_versicolor )
dist

for(i in 1: nrow(dist))
  {y[i]=which.min(dist[i,])} #retorna o índice do maior elemento de x.

for(i in 1: nrow(dist))
{if(y[i]==1)
{z[i]="setosa"}
  else {if(y[i]==2)
  {z[i]="virginica"}
    else {z[i]="versicolor"}}}
    
classe_estimada=z

test.def # classe das observações de teste
classe_estimada # classe estimada das observações de teste

table(test.def,classe_estimada)
