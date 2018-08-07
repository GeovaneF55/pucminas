# Cálculo de Distâncias

library(e1071)
data(iris)
attach(iris)

# Instala pacote de classificação
install.packages("class")
library(class)


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

## Excluir a classe da base de dados, e colocá-la em uma variável separada 
x <- subset(irisr, select = -Species) # x é igual a Iris, excluindo-se a classe.
# subset returns subsets of vectors, matrices or data frames which meet conditions.
y <- irisr$Species

test <- 1:75
train.iris <- x[-test,]
test.iris <- x[test,]
head(train.iris) # primeiras linhas da base de treino
head(test.iris) # primeiras linhas da base de teste
train.def <- y[-test] # classe das observações usadas no treinamento
test.def <- y[test] # classe das observações usadas no teste

# knn(observaçoes de treinamento sem classe, observaçoes de teste sem classe, classe das onservações de treinamento, k=1)
knn.1 <-  knn(train.iris, test.iris, train.def, k=1) # retorna a classe do vizinho mais próximo
knn.5 <-  knn(train.iris, test.iris, train.def, k=5) # retorna a classe "moda" dos 5 vizinhos mais próximos
knn.10 <-  knn(train.iris, test.iris, train.def, k=10)
knn.20 <-  knn(train.iris, test.iris, train.def, k=20)

## Let's calculate the proportion of correct classification for k = 1, 5, 10 and 20 
100 * sum(test.def == knn.1)/75  # For knn = 1
100 * sum(test.def == knn.5)/75  # For knn = 5
100 * sum(test.def == knn.10)/75  # For knn = 10
100 * sum(test.def == knn.20)/75 # For knn = 20

## If we look at the above proportions, it's quite evident that K = 1, K = 5
# and K = 10 correctly classifies 97,33% of the outcomes and K = 20 
# does it for 94,66% of the outcomes. 
## We should also look at the success rate against the value of increasing K.
table(knn.1 ,test.def)
table(knn.5 ,test.def)
table(knn.10 ,test.def)
table(knn.20 ,test.def)
