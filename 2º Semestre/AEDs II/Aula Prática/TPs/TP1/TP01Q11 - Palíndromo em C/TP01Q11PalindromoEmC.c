#include <stdio.h>

// Variável para simular valores booleanos
int falso=0, verdadeiro=1, tamanho=1000;

// Método que retorna o tamanho de uma string
int length(char str[]){
        int cont = 0;
        while(str[cont] != '\0'){
                cont++;
        }
        return cont;
}

// Método que retorna verdadeiro caso as duas strings passadas por parâmetro seja iguais
int igual(char fraseA[], char fraseB[]){
	int ehIgual = verdadeiro;
	if(length(fraseA) != length(fraseB)){
		ehIgual = falso;
	} else{
		int i=0;
		while(i<length(fraseA) && ehIgual == verdadeiro){
			if(fraseA[i] != fraseB[i]){
				ehIgual = falso;
			}
			i++;
		}
	}
	return ehIgual;
}

// Método remove caractere especial de nova linha da string
char *trim(char *string) {
	int i = length(string)-1;
	if ((i > 0) && (string[i] == '\n')){
		string[i] = '\0';
	}
	return string;
}

// Método que retorna verdadeiro se a string for um palíndromo
int ehPalindromo(char frase[]){
	int ehPalindromo = verdadeiro;
	int ultimoChar = length(frase)-1;

	int i=0;

	while(i<(float)ultimoChar/2 && ehPalindromo == verdadeiro){
		if(frase[i] != frase[ultimoChar-i]){
			ehPalindromo = falso;
		}
		i++;
	}
	return ehPalindromo;
}

int main(){
	char entrada[tamanho];
	char fim[] = "FIM";

	fgets(entrada, tamanho, stdin);
	while(igual(trim(entrada), fim) == falso){
		//Verifica se a frase entregue pelo parâmetro é um palíndromo
		if(ehPalindromo(entrada) == verdadeiro){
			printf("%s", "SIM\n");
		} else{
			printf("%s", "NAO\n");
		}
		fgets(entrada, tamanho, stdin);
	}

	return 0;
}
