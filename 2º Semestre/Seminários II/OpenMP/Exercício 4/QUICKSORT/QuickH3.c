#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <stdlib.h>

int tamanho=1000,
    multi_thread=6,
    size = 1500000;

int IsSort(int *array, int size) {
	int i, value = 0;
	for(i = 1; i < size; i++) 
		if(array[i-1] > array[i])
			return 0;
	return 1;
}

void swap(int *a, int i, int j) {
	int t = a[i];
	a[i] = a[j];
	a[j] = t;
}

int partition(int *a, int left,int right,int pivot) {
	int pos, i;
	swap(a, pivot, right);
	pos = left;
	for(i = left; i < right; i++)
	{
		if (a[i] < a[right])
		{
			swap(a, i, pos);
			pos++;
		}
	}
	swap(a, right, pos);
	return pos;
}

void quickSerial(int *a, int left, int right) {
	if (left < right)
	{
		int pivot = (left + right) / 2;
		int pos = partition(a,left,right,pivot);
		quickSerial(a, left, pos - 1);
		quickSerial(a, pos + 1, right);
	}
}

void quickParalelo(int *a, int left, int right, int thread) {
	if (left < right)
	{
		int pivot = (left + right) / 2;
		int pos = partition(a,left,right,pivot);
		if(thread > 1) {
			#pragma omp parallel sections
			{
				#pragma omp section
				{
					quickParalelo(a, left, pos - 1, thread/2);
				}
				#pragma omp section
				{
					quickParalelo(a, pos + 1, right, thread/2);
				}
			}
		} else {
			quickParalelo(a, left, pos - 1, 1);
			quickParalelo(a, pos + 1, right, 1);
		}
	}
}

double processamentoSerial(int arr[], int max) {
	double tempoI,
	       tempoF;

	arr = malloc(size* sizeof(int));
	srand(time(NULL));
	for (int j = 0; j < size; j++){
		arr[j] = rand()%size;
	}

	tempoI = omp_get_wtime();
	quickSerial(arr, 0, size-1);
	tempoF = omp_get_wtime();

	free(arr);

	return tempoF - tempoI;
}

double processamentoParalelo(int arr[], int max, int threads) {
	double tempoI,
	       tempoF;

	arr = malloc(size* sizeof(int));
	srand(time(NULL));
	for (int j = 0; j < size; j++){
		arr[j] = rand()%size;
	}

	omp_set_num_threads(threads);

	tempoI = omp_get_wtime();
	quickParalelo(arr, 0, size-1, threads);
	tempoF = omp_get_wtime();

	free(arr);

	return tempoF - tempoI;
}


int main(int argc, char** argv) {
	FILE *arquivo;
	char nomeArquivo[] = "QuickH3.txt";
	arquivo = fopen(nomeArquivo, "w");
	char string[tamanho];

	int *arr,
	    num_threads[] = {1, 2, 4, 8, 16, 32};
	double procSerial,
	       procParalelo[multi_thread],
	       speedup[multi_thread],
	       eficiencia;

	       fputs("(QuickH3.c)\nTAMANHO\t\tSerial\t\t1\t\t2\t\t4\t\t8\t\t16\t\t32\n", arquivo);

	       procSerial = processamentoSerial(arr, size);

	       sprintf(string, "%d\t\t%6.4f\t\t", size, procSerial);
	       fputs(string, arquivo);

	       for(int i = 0; i <= 5; i++){
		      for(int j = 0; j <10; j++){
				procParalelo[i] += processamentoParalelo(arr, size, num_threads[i]);
			}
			procParalelo[i]/=10;
			sprintf(string, "%6.4f\t\t", procParalelo[i]);
			fputs(string, arquivo);
	       }
	       fputs("\n\t\t\t\t", arquivo);
	       for(int i=0; i<multi_thread; i++){
		       speedup[i] = procSerial/procParalelo[i];

		       sprintf(string, "%6.4f\t\t", speedup[i]);
		       fputs(string, arquivo);
	       }
	       fputs("\n\t\t\t\t", arquivo);
	       for(int i=0; i<multi_thread; i++){
		       eficiencia = speedup[i]/num_threads[i];

		       sprintf(string, "%6.4f\t\t", eficiencia);
		       fputs(string, arquivo);
	       }
	       fputs("\n", arquivo);
	       return 0;
}
