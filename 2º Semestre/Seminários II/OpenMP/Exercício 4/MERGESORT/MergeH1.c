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

void merge(int vec[], int vecSize) {
	int mid;
	int i, j, k;
	int* tmp;
 
	tmp = (int*) malloc(vecSize * sizeof(int));
	if (!tmp) 
		exit(1);
 
	mid = vecSize / 2;
 
	i = 0;
	j = mid;
	k = 0;

	while (i < mid && j < vecSize) {
		if (vec[i] < vec[j]) 
			tmp[k] = vec[i++];
		else 
			tmp[k] = vec[j++];
		++k;
	}
	
	if (i == mid) 
		while (j < vecSize) 
			tmp[k++] = vec[j++];	
	else 
		while (i < mid) 
			tmp[k++] = vec[i++];
	
	for (i = 0; i < vecSize; ++i) 
		vec[i] = tmp[i];
	
	free(tmp);
}

void mergesortSequencial(int arr[], int size) {
	int mid;
	if(size > 1) {
		mid = size / 2;
		mergesortSequencial(arr, mid);
		mergesortSequencial(arr + mid, size - mid);
		merge(arr, size);
	}
}

void mergesortParalelo(int arr[], int size, int thread){
	int mid;
	if(size > 1) {
		mid = size / 2;
		if(thread > 1) {
			 #pragma omp parallel sections
			{
				#pragma omp section
				{
					mergesortParalelo(arr, mid, thread-1);
				}
				#pragma omp section
				{
					mergesortParalelo(arr + mid, size - mid, thread-1);
				}
			}
		} else {
			mergesortParalelo(arr, mid, 1);
			mergesortParalelo(arr + mid, size - mid, 1);		
		}
		merge(arr, size);
	}
}

double processamentoSerial(int arr[], int max){
	double tempoI,
	       tempoF;

	arr = malloc(size* sizeof(int));
	srand(time(NULL));
	for (int j = 0; j < size; j++){
		arr[j] = rand()%size;
	}

	tempoI = omp_get_wtime();
	mergesortSequencial(arr, size);
	tempoF = omp_get_wtime();

	free(arr);

	return tempoF - tempoI;
}

double processamentoParalelo(int arr[], int max, int threads){
	double tempoI,
	       tempoF;

	arr = malloc(size* sizeof(int));
	srand(time(NULL));
	for (int j = 0; j < size; j++){
		arr[j] = rand()%size;
	}

	omp_set_num_threads(threads);

	tempoI = omp_get_wtime();
	mergesortParalelo(arr, size, threads);
	tempoF = omp_get_wtime();

	free(arr);

	return tempoF - tempoI;
}


int main(int argc, char** argv) {
	FILE *arquivo;
	char nomeArquivo[] = "MergeH1.txt";
	arquivo = fopen(nomeArquivo, "w");
	char string[tamanho];

	int *arr,
	    num_threads[] = {1, 2, 4, 8, 16, 32};
	double procSerial,
	       procParalelo[multi_thread],
	       speedup[multi_thread],
	       eficiencia;

	fputs("(MergeH1.c)\nTAMANHO\t\tSerial\t\t1\t\t2\t\t4\t\t8\t\t16\t\t32\n", arquivo);

	procSerial = processamentoSerial(arr, size);
	
	sprintf(string, "%d\t\t%6.4f\t\t", size, procSerial);
	fputs(string, arquivo);
	
	for(int i = 0; i < 6; i++){
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
