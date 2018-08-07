#BPNN
#install.packages('neuralnet')
library(neuralnet)
testidx <- which(1:length(iris[,1])%%5 == 0) # de 5 em 5, pega para testar
iristrain <- iris[-testidx,] # o resto será para treinar
iristest <- iris[testidx,]
nnet_iristrain <-iristrain
nnet_iristrain <- cbind(nnet_iristrain, iristrain$Species == 'setosa') # inserindo as 3 saídas
nnet_iristrain <- cbind(nnet_iristrain, iristrain$Species == 'versicolor')
nnet_iristrain <- cbind(nnet_iristrain, iristrain$Species == 'virginica')
names(nnet_iristrain)[6] <- 'setosa'
names(nnet_iristrain)[7] <- 'versicolor'
names(nnet_iristrain)[8] <- 'virginica'
#nn <- neuralnet(setosa+versicolor+virginica~Sepal.Length+Sepal.Width+Petal.Length+Petal.Width,data=nnet_iristrain,hidden=c(3)) # 1 camada esondida com 3 neurônios
nn <- neuralnet(setosa+versicolor+virginica~Sepal.Length+Sepal.Width+Petal.Length+Petal.Width,data=nnet_iristrain,hidden=c(3,3,3,3)) # 4 camadas escondidas, com 3 neurônios cada
#nn <- neuralnet(setosa+versicolor+virginica~Sepal.Length+Sepal.Width+Petal.Length+Petal.Width,data=nnet_iristrain,hidden=c(3,2)) # 2 camadas esondidas: 1 com 3 neurônios, outra com 2

# 3 camadas escondidas
plot(nn)
mypredict <- compute(nn, iristest[-5])$net.result 
# A função compute retorna um objeto e estamos pegando o atributo net.result deste objeto
maxidx <- function(arr) {return(which(arr == max(arr)))} # qual saída ganhou
idx <- apply(mypredict, c(1), maxidx) # indexador da classe
prediction <- c('setosa', 'versicolor', 'virginica')[idx]
table(prediction, iristest$Species)

#SOM - não está funcionando
#install.packages('kohonen')
library(kohonen)
set.seed(101)
train.obs <- sample(nrow(iris), 50) # get the training set observations
train.set <- scale(iris[train.obs,][,-5]) # check info about scaling data below
test.set  <- scale(iris[-train.obs, ][-5],
                   center = attr(train.set, "scaled:center"),
                   scale  = attr(train.set, "scaled:scale"))
som.iris <- som(train.set, grid = somgrid(5, 5, "hexagonal"))
plot(som.iris, type="changes")
plot(som.iris, type="dist.neighbours")
plot(som.iris, type="codes")
plot(som.iris, type = "property", property = som.iris$codes[,1], main=names(som.iris$data)[4])
som.prediction <-
  predict(som.iris, newdata = test.set,
          trainX = train.set,
          trainY = classvec2classmat(iris[,5][train.obs]))

table(iris[,5][-train.obs], som.prediction$prediction)
