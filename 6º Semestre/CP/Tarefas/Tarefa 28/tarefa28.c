/*
    Alunos: Geovane Fonseca de Sousa Santos
	    Luigi Domenico Cecchini Soares
    Matéria: Computação Paralela
    Tarefa 28: Padrão Pipeline em MPI
*/

#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX_N 100000
#define PIPE_MSG 0
#define END_MSG 1
#define REDUCE_MSG 2

int size;  
int n;     
int rank;

void removeMultipleOf3() {  
   int number, i;

    for (i = 1; i <= n/2; i++)  {
        number = 2 * i + 1;
        if (number > n) break;
        if (number % 3 > 0)
            MPI_Send(&number,1,MPI_INT,1,PIPE_MSG,MPI_COMM_WORLD);
    }
    MPI_Send(&number,1,MPI_INT,1,END_MSG,MPI_COMM_WORLD);
}

void removeMultipleOf5() {  	
    int number, j=2;
    MPI_Status Status; 
	
    while (1)  {
        MPI_Recv(&number,1,MPI_INT,0,MPI_ANY_TAG,MPI_COMM_WORLD,&Status);
        if (Status.MPI_TAG == END_MSG) break;
        if (number % 5 > 0) {
            // enviar para o próximo estágio
            MPI_Send(&number,1,MPI_INT,j,PIPE_MSG,MPI_COMM_WORLD);
        }
        j = ((j+1) % size < 2) ? 2 : (j+1) % size; 
    }
    // enviar mensagem de finalização
    for(int i=2; i<size; i++){
       MPI_Send(&number,1,MPI_INT,i,END_MSG,MPI_COMM_WORLD);
    }
}

void countOnlyPrimes() {

    int number, primeCount, i, isComposite;
    MPI_Status Status; 

    primeCount = 0;  

    while (1)  {
        // receber mensagem do estágio anterior
        MPI_Recv(&number,1,MPI_INT,1,MPI_ANY_TAG,MPI_COMM_WORLD,&Status);
        // sair do loop se fim
        if (Status.MPI_TAG == END_MSG) break;

        isComposite = 0;
        for (i = 7; i*i <= number; i += 2){
            if (number % i == 0)  {
                isComposite = 1;
                break;
            }
        }
        if (!isComposite) primeCount++;  
    }

    /* printf("number of primes = %d\n",primeCount); */
	MPI_Send(&primeCount, 1, MPI_INT, 0, REDUCE_MSG, MPI_COMM_WORLD);
}

int reduce() {
	MPI_Status Status;
	int numPrimes = 3;
	int count;

	for (int i = 2; i < size; i++) {
		MPI_Recv(&count, 1, MPI_INT, i, REDUCE_MSG, MPI_COMM_WORLD, &Status);
		numPrimes += count;
	}

	return numPrimes;
}

void main(int argc, char **argv) {  
    n = atoi(argv[1]); 
    MPI_Init(&argc,&argv);	

    MPI_Comm_size(MPI_COMM_WORLD,&size);
    MPI_Comm_rank(MPI_COMM_WORLD,&rank);

    switch (rank)  {
        case 0:
			removeMultipleOf3();
			int numPrimes = reduce();
			printf("number of primes = %d\n", numPrimes);
			break;
        
        case 1:
			removeMultipleOf5();
			break;
        
        default:
			countOnlyPrimes();
    };
	
    MPI_Finalize();
}
