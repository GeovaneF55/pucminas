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

int* inverteVetor(int *vetor, int tamanho){
	int *inversoVetor = new int[tamanho];
	for(int i=0; i<tamanho; i++){
		inversoVetor[tamanho-(i+1)] = vetor[i];
	}
	return inversoVetor;
}

void resposta(int *vetor, int *inversoVetor, int tamanho){
	cout << "\n O vetor e seu inverso são: " << endl;
	for(int i=0; i<tamanho; i++){
		cout << "\nv[" << (i+1) << "] = " << vetor[i] << " e i[" << (i+1) << "] = " << inversoVetor[i];
	}
	cout << endl << endl;
}

int main(){
	int tamanho = retornaTamanhoVetor();
	int *vetor = criaVetor(tamanho);
	int *inversoVetor = inverteVetor(vetor, tamanho);
	resposta(vetor, inversoVetor, tamanho);
	return 1;
}
