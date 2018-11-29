#include <time.h>
#include <stdio.h>
#include <mpi.h>

#define N 10
#define MAX 4
#define NUMBER 3

void main(int argc, char* argv[]) {
	int p, rank, maior_parcial, maior_final, numProcs;
	int buffer[N];
	MPI_Status status;

	MPI_Init(&argc, &argv) ;
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &numProcs);

	if (rank == 0) {
		// preencher o buffer com N valores inteiros aleatórios

	} 

	// distribuir o vetor para todos os outros processos

	// processar o maior dos valores dentro do seu intervalo

	// reduzir os maiores no maior, enviando o resultado para o processo com rank = 0

	if (rank == 0) {
		// imprimir maior
	}

	MPI_Finalize();
}
