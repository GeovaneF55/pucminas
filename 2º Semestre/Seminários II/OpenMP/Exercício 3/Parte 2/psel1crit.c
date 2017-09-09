#include <stdlib.h>
#include <stdio.h>
#include <omp.h>
#include <time.h>

int tamanho=1000,
    multi_thread=6;

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
#pragma omp declare reduction(maximum : struct Compare : omp_out = omp_in.val > omp_out.val ? omp_in : omp_out)

void selectionsortSerial(int arr[], int size) {	
	for (int i = size - 1; i > 0; --i) {
		struct Compare max;
		max.val = arr[i];
		max.index = i;

		for (int j = i - 1; j >= 0; j--) {
			if (arr[j] > max.val) {
				max.val = arr[j];
				max.index = j;
			}
		}
		int tmp = arr[i];
		arr[i] = max.val;
		arr[max.index] = tmp;
	}
}

void selectionsortParallel(int arr[], int size, int num_threads) {
//#pragma omp parallel 
	omp_set_num_threads(num_threads);	
	for (int i = size - 1; i > 0; --i) {
		struct Compare max;
		max.val = arr[i];
		max.index = i;

#pragma omp parallel for reduction(maximum:max)
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

double processamentoSerial(int arr[], int max){
	char string[tamanho];
	double tempoI,
	       tempoF;

	tempoI = omp_get_wtime();
	selectionsortSerial(arr, max);
	tempoF = omp_get_wtime();

	return tempoF - tempoI;
}

double processamentoParalelo(int arr[], int max, int num_threads){
	char string[tamanho];
	double tempoI,
	       tempoF;

	// Tempo com Paralelismo
	tempoI = omp_get_wtime();
	selectionsortParallel(arr, max, num_threads);
	tempoF = omp_get_wtime();

	return tempoF - tempoI;
}

int main()
{
	FILE *arquivo;
	char nomeArquivo[] = "psel1crit.txt";
	arquivo = fopen(nomeArquivo, "w");
	int max = 100,
	    num_threads[] = {1, 2, 4, 8, 16, 32};
	char string[tamanho];

	fputs("(psel1crit.c)\nTAMANHO\t\tSerial\t\t1\t\t2\t\t4\t\t8\t\t16\t\t32\n", arquivo);

	for(int i=0; i<3; i++){
		int arrSerial[max],
		    arrParallel[max];
		double procSerial,
		       procParallel[multi_thread],
		       speedup[multi_thread],
		       eficiencia;
		srand(time(NULL));

		inicializaVetor(arrSerial, max);
		inicializaVetor(arrParallel, max);

		procSerial = processamentoSerial(arrSerial, max);

		sprintf(string, "%d\t\t%6.4f\t\t", max, procSerial);
		fputs(string, arquivo);

		for(int j=0; j<multi_thread; j++){
			procParallel[j] = processamentoParalelo(arrParallel, max, num_threads[j]);

			sprintf(string, "%6.4f\t\t", procParallel[j]);
			fputs(string, arquivo);
		}
		fputs("\n\t\t\t\t", arquivo);
		for(int j=0; j<multi_thread; j++){
			speedup[j] = procSerial/procParallel[j];

			sprintf(string, "%6.4f\t\t", speedup[j]);
			fputs(string, arquivo);
		}
		fputs("\n\t\t\t\t", arquivo);
		for(int j=0; j<multi_thread; j++){
			eficiencia = speedup[j]/num_threads[j];

			sprintf(string, "%6.4f\t\t", eficiencia);
			fputs(string, arquivo);
		}
		fputs("\n", arquivo);
		max *= 10;
	}
	fclose(arquivo);
	return 0;
}
