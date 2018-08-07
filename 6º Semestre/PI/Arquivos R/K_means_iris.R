#iris<-read.table("/Users/Flavia/PUC/Analise_BD_ Alexei/Base_Dados_Alexei/iris.txt",header=TRUE)
data(iris)
summary(iris)
plot(iris,col=iris$Species) #mostra o agrupamento correto, por espécie (usando
# cores diferentes para as espécies), antes de usar o K-means. 
# Gostaríamos que ele agrupasse exatamente assim, nas 3 classes,
# que são exatamente a setosa, a versicolor e a virginica.
c<-iris[,5] # cria um vetor c com as classes
c
d<-iris[,1:4] # dados que serão usados no K-means, sem informar a classe
res<-kmeans(d,3,10)
#d: matriz com dados; 3 é o número de clusters e 10 é o número máximo de iterações
plot(iris,col=res$cluster) #plotar, colocando a classe como cor
# Cada vez que vc rodar pode dar diferente, já que o algoritmo é randômico

twss <- numeric(20) # verificar a quantidade de grupos se está legal;
# twss é vetor de 15 posições
for (i in 1:length(twss)) twss[i] <- kmeans(d,centers=i)$tot.withinss # guarda erro total, 
# para fazer gráfico do Elbow
plot(1:length(twss), twss,ylab="Within groups sum of squares")
plot(1:15, twss,ylab="Within groups sum of squares",type="l")

#Comparar o desempenho do K-means com um classificador. Dadas as classes, quanto
# ele vai errar?
# Em que situações um agrupador não consegue agrupar direito? Mesmo sabendo o melhor K.
# A culpa seria dele? 
# Se as variáveis (características) não forem discriminantes, não tem classificador que faça milagre.

#VALIDAÇÃO

compr=length(c)
matriz.comp =data.frame(c,res$cluster)
#Com a função data.frame reunimos vetores de mesmo comprimento em um só objeto
matriz.comp #compara a classe real (c) com a classe encontrada pelo K-means

#Computando a matriz de confusão para 3 classes (não é exatamente a matriz de confusão, porque
# não conhecemos os nomes das classes no k-means)
 
matriz_confusao=matrix(0,nrow=3,ncol=3)


lst=list("virginica","versicolor","setosa")
lst

# Classe 1 = Virginica
# Classe 2 = Versicolor
# Classe 3 = Setosa

# Na matriz de confusão, as linhas serão as classes verdadeiras e as colunas serão
# as classes encontradas pelo K-means.

for (i in 1:compr){
  if(matriz.comp[i,1]=="setosa"){
    if(matriz.comp[i,2]=="3"){
      matriz_confusao[3,3]=matriz_confusao[3,3]+1}
    else if(matriz.comp[i,2]=="2"){
      matriz_confusao[3,2]=matriz_confusao[3,2]+1}
    else{
      matriz_confusao[3,1]=matriz_confusao[3,1]+1}}
else 
  if(matriz.comp[i,1]=="versicolor"){
    if(matriz.comp[i,2]=="3"){
      matriz_confusao[2,3]=matriz_confusao[2,3]+1}
    else if(matriz.comp[i,2]=="2"){
      matriz_confusao[2,2]=matriz_confusao[2,2]+1}
    else{
      matriz_confusao[2,1]=matriz_confusao[2,1]+1}}
else
  if(matriz.comp[i,1]=="virginica"){
    if(matriz.comp[i,2]=="3"){
      matriz_confusao[1,3]=matriz_confusao[1,3]+1}
    else if(matriz.comp[i,2]=="2"){
      matriz_confusao[1,2]=matriz_confusao[1,2]+1}
    else{
      matriz_confusao[1,1]=matriz_confusao[1,1]+1}}}

matriz_confusao

##############################

# K-MEANS COM NORMALIZAÇÃO DAS CLASSES

Sepal_length_n=(d$Sepal.Length-min(d$Sepal.Length))/(max(d$Sepal.Length)-min(d$Sepal.Length))
Sepal_width_n=(d$Sepal.Width-min(d$Sepal.Width))/(max(d$Sepal.Width)-min(d$Sepal.Width))
Petal_length_n=(d$Petal.Length-min(d$Petal.Length))/(max(d$Petal.Length)-min(d$Petal.Length))
Sepal_width_n=(d$Petal.Width-min(d$Petal.Width))/(max(d$Petal.Width)-min(d$Petal.Width))
d_n =data.frame(Sepal_length_n,Sepal_width_n,Petal_length_n,Sepal_width_n)

c<-iris[,5] # cria um vetor c com as classes
c


twss <- numeric(20) # verificar a quantidade de grupos se está legal;
# twss é vetor de 15 posições
for (i in 1:length(twss)) twss[i] <- kmeans(d_n,centers=i)$tot.withinss # guarda erro total, 
# para fazer gráfico do Elbow
plot(1:length(twss), twss,ylab="Within groups sum of squares")
plot(1:15, twss,ylab="Within groups sum of squares",type="l")

res_n<-kmeans(d_n,3,10)
#d: matriz com dados; 3 é o número de clusters e 10 é o número máximo de iterações
plot(iris,col=res_n$cluster) #plotar, colocando a classe como cor
# Cada vez que vc rodar pode dar diferente, já que o algoritmo é randômico


#Comparar o desempenho do K-means com um classificador. Dadas as classes, quanto
# ele vai errar?
# Em que situações um agrupador não consegue agrupar direito? Mesmo sabendo o melhor K.
# A culpa seria dele? 
# Se as variáveis (características) não forem discriminantes, não tem classificador que faça milagre.

#VALIDAÇÃO

compr=length(c)
matriz.comp_n =data.frame(c,res_n$cluster)
#Com a função data.frame reunimos vetores de mesmo comprimento em um só objeto
matriz.comp_n #compara a classe real (c) com a classe encontrada pelo K-means

#Computando a matriz de confusão para 3 classes (não é exatamente a matriz de confusão, porque
# não conhecemos os nomes das classes no k-means)

matriz_confusao_n=matrix(0,nrow=3,ncol=3)


lst=list("virginica","versicolor","setosa")
lst

# Classe 1 = Virginica
# Classe 2 = Versicolor
# Classe 3 = Setosa

# Na matriz de confusão, as linhas serão as classes verdadeiras e as colunas serão
# as classes encontradas pelo K-means.

for (i in 1:compr){
  if(matriz.comp_n[i,1]=="setosa"){
    if(matriz.comp_n[i,2]=="3"){
      matriz_confusao_n[3,3]=matriz_confusao_n[3,3]+1}
    else if(matriz.comp_n[i,2]=="2"){
      matriz_confusao_n[3,2]=matriz_confusao_n[3,2]+1}
    else{
      matriz_confusao_n[3,1]=matriz_confusao_n[3,1]+1}}
  else 
    if(matriz.comp_n[i,1]=="versicolor"){
      if(matriz.comp_n[i,2]=="3"){
        matriz_confusao_n[2,3]=matriz_confusao_n[2,3]+1}
      else if(matriz.comp_n[i,2]=="2"){
        matriz_confusao_n[2,2]=matriz_confusao_n[2,2]+1}
      else{
        matriz_confusao_n[2,1]=matriz_confusao_n[2,1]+1}}
  else
    if(matriz.comp_n[i,1]=="virginica"){
      if(matriz.comp_n[i,2]=="3"){
        matriz_confusao_n[1,3]=matriz_confusao_n[1,3]+1}
      else if(matriz.comp_n[i,2]=="2"){
        matriz_confusao_n[1,2]=matriz_confusao_n[1,2]+1}
      else{
        matriz_confusao_n[1,1]=matriz_confusao_n[1,1]+1}}}

#Resultados do k-means sem normalização:
matriz.comp
matriz_confusao
#Resultados do k-means sem normalização:
matriz.comp_n
matriz_confusao_n
