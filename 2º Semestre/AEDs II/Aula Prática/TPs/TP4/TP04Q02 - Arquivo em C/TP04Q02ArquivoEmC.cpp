/*Autor: Geovane Fonseca de Sousa Santos
* Data: 09/2016
* Objetivo: Arquivo em C que lê em ordem invertida
*/

#include <stdio.h>
#include <stdlib.h>

int tamanho=1000;

int escreveArquivo(char *nomeArquivo, int n){
	FILE *arquivo = fopen(nomeArquivo, "w");
	int ponteiro=0, i=0;

	if (arquivo != NULL) {
		while(i < n){
			char entrada[tamanho], string[tamanho];
			float real = atof(fgets(entrada, tamanho, stdin));
			sprintf(string, "%g\n", real);
			fputs(string, arquivo);
			i++;
		}
	}
	ponteiro = ftell(arquivo);
	fclose(arquivo);

	return ponteiro;
}

int posicaoUltimaQuebraLinha(char *nomeArquivo, int limite){
	int i=0,
	     ponteiro=-1;
	char caractere;
	FILE *arquivo = fopen(nomeArquivo, "r");

	while(i < limite){
		caractere = fgetc(arquivo);

		if(caractere == '\n'){
			ponteiro = ftell(arquivo);
		}
		i+=1;
	}
	fclose(arquivo);

	return ponteiro;
}

void leContrarioArquivo(char *nomeArquivo, int tamanhoArquivo){
	FILE *arquivo;
	char entrada[tamanho];
	int ultimaQuebralinha = posicaoUltimaQuebraLinha(nomeArquivo, tamanhoArquivo-1);

	while(ultimaQuebralinha > 0){
		arquivo = fopen(nomeArquivo, "r");
		fseek(arquivo, ultimaQuebralinha, SEEK_SET);
		printf("%s", fgets(entrada, tamanho, arquivo));
		fclose(arquivo);

		ultimaQuebralinha = posicaoUltimaQuebraLinha(nomeArquivo, ultimaQuebralinha-1);
	}
	// Lê mais uma vez para a primeira linha
	arquivo = fopen(nomeArquivo, "r");
	fseek(arquivo, 0, SEEK_SET);
	printf("%s", fgets(entrada, tamanho, arquivo));
	fclose(arquivo);

}

int main(int argc, char *argv[]){
	char nomeArquivo[] = "numeros.txt";
	char entrada[tamanho];
	int n = atoi(fgets(entrada, tamanho, stdin));

	int tamanhoArquivo = escreveArquivo(nomeArquivo, n);
	leContrarioArquivo(nomeArquivo, tamanhoArquivo);
	return 0;
}
