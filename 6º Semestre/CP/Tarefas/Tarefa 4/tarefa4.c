/*
 * Aluno: Geovane Fonseca de Sousa Santos
 * Matrícula: 553237
 * Matéria: Coputação Paralela
 * Tarefa 4: Multiplicação de Matrizes Paralela
 *
 *	* Local: 
 *	- Sequencial:
 *		72.49		user
 *		0.02		system
 *		1:12.60		elapsed
 *		99%		CPU
 *
 *	- Paralelo:
 *		140.88		user
 *		0.06		system
 *		0:35.67		elapsed
 *		395%		CPU
 *
 *	- Speedup: (72.6/35.67) = 2,035323802
 *
 *	* PUC:
 *	- Sequencial:
 *		real	39m29.446s
 *		user	2m19.318s
 *		sys	0m9.544s
 *	- Paralelo:
 *		real	4m6.088s
 *		user	2m19.690s
 *		sys	0m0.194s
 *
 *	- Speedup: (2369,446/246,088) = 9,628449985
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

void mm(double* a, double* b, double* c, int width) 
{
	// Paralelizando os dois for's aninhados
	#pragma omp parallel for collapse(2)
	for (int i = 0; i < width; i++) {
		for (int j = 0; j < width; j++) {
			double sum = 0;
			// Utilizando a cláusula simd para vetorizar a soma de a e b utilizando o reduction
			#pragma omp simd reduction(+:sum)
			for (int k = 0; k < width; k++) {
				double x = a[i * width + k];
				double y = b[k * width + j];
				sum += x * y;
			}
			c[i * width + j] = sum;
		}
	}
}

int main()
{
	int width = 2000;
	double *a = (double*) malloc (width * width * sizeof(double));
	double *b = (double*) malloc (width * width * sizeof(double));
	double *c = (double*) malloc (width * width * sizeof(double));

	// Paralelizando os dois for's aninhados
	#pragma omp parallel for collapse(2)
	for(int i = 0; i < width; i++) {
		for(int j = 0; j < width; j++) {
			a[i*width+j] = i;
			b[i*width+j] = j;
			c[i*width+j] = 0;
		}
	}

	mm(a,b,c,width);

	/*for(int i = 0; i < width; i++) {
		for(int j = 0; j < width; j++) {
			printf("\n c[%d][%d] = %f",i,j,c[i*width+j]);
		}
	}*/
}
