#include <iostream>

using namespace std;

int retornaTamanhoVetor(){
	int tamanho;
	cout << "\nDigite o tamanho do Vetor: ";
	cin >> tamanho;
	return tamanho;
}

int* criaVetor(int tamanho){
	int *vetor = new int[tamanho];
	for(int i=0; i<tamanho; i++){
		cout << "\nDigite a posição " << (i+1) << " do vetor: ";
		cin >> vetor[i];
	}
	return vetor;
}

int elementoProcurado(){
	int elemento;
	cout << "\nDigite o elemento a ser procurado no vetor: ";
	cin >> elemento;
	return elemento;
}

int procuraElemento(int *vetor, int tamanho, int elemento){
	int indiceElementoProcurado = -1;
	for(int i=0; i<tamanho; i++){
		if(vetor[i] == elemento){
			indiceElementoProcurado = i;
		}
	}
	return indiceElementoProcurado;
}

void resposta(int indice, int elemento){
	if(indice == -1){
		cout << "\nO elemento " << elemento << " não foi encontrado!" << endl << endl;
	} else{
		cout << "\nO elemento " << elemento << " foi encontrado no vetor na posição " << (indice+1) << endl << endl;
	}
}

int main(){
	int tamanho = retornaTamanhoVetor();
	int *vetor = criaVetor(tamanho);
	int elemento = elementoProcurado();
	int indice = procuraElemento(vetor, tamanho, elemento);
	resposta(indice, elemento);
	return 1;
}
