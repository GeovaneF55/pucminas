#include <stdlib.h>
#include <stdio.h>
#include <omp.h>
#include <time.h>

int tamanho=1000;

struct Compare { 
	int val; 
	int index; 
};

/*
   Declara uma redução do OpenMP.
   Uma redução garante que a variável tem uma cópia em cada thread. Mas seus valores
   são reduzidos em uma variável global compartilhada.
   Esta redução garante que a variável usada como parâmetro, do tipo Compare,
   terá o maior valor ao final do processamento da thread.  
 */
//#pragma omp declare reduction(maximum : struct Compare : omp_out = omp_in.val > omp_out.val ? omp_in : omp_out)

void selectionsort(int arr[], int size, int num_threads) {

//#pragma omp parallel 

	omp_set_num_threads(num_threads);	
	for (int i = size - 1; i > 0; --i) {
		struct Compare max;
		max.val = arr[i];
		max.index = i;

#pragma omp parallel for //reduction(maximum:max)
		for (int j = i - 1; j >= 0; j--) {
#pragma omp critical
			{
				if (arr[j] > max.val) {
					max.val = arr[j];
					max.index = j;
				}
			}
		}

		int tmp = arr[i];
		arr[i] = max.val;
		arr[max.index] = tmp;
	}
}

void inicializaVetor(int vetor[], int max){
	for(int i=0; i<max; i++){
		vetor[i] = rand()%100;
	}
}

void processamento(int arr[], int max, int num_threads, FILE *arquivo){
	char string[tamanho];
	double tempoI = clock();
	selectionsort(arr, max, num_threads);
	double tempoF = clock();

	double procTime = ((double)tempoF - tempoI) / CLOCKS_PER_SEC;
	sprintf(string, "%6.4f\t\t", procTime);
	fputs(string, arquivo);
}

int main()
{
	FILE *arquivo;
	char nomeArquivo[] = "psel1.txt";
	arquivo = fopen(nomeArquivo, "w");
	int max = 10,
	    num_threads;
	char string[tamanho];

	fputs("(psel1.c)\nTAMANHO\t\t1\t\t2\t\t4\t\t8\t\t16\t\t32\n", arquivo);

	for(int i=0; i<4; i++){
		num_threads = 1;

		int arr[max];
		srand(time(NULL));
		inicializaVetor(arr, max);

		sprintf(string, "%d\t\t", max);
		fputs(string, arquivo);

		for(int j=0; j<6; j++){
			processamento(arr, max, num_threads, arquivo);
			num_threads *= 2;
		}
		fputs("\n", arquivo);
		max *= 10;
	}
	return 0;
}
