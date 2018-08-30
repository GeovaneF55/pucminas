/*
  Alunos: Geovane Fonseca de Sousa Santos
	  Guilherme Reis Barbosa de Oliveira
	  Rafael Câmara Magalhães

  Matéria: Coputação Paralela
  Tarefa 09: Silly Sort Paralelo
 
	Local:
		- Sequencial:
		2.31		user
		0.01		system
		0:02.33		elapsed
		100%		CPU

		- Paralelo Sem Escalonamento:
		2.89		user
		0.00		system
		0:01.52		elapsed
		190%		CPU

		Speedup = (2,33/1,52) = 1,53

		- Paralelo Com Escalonamento:
		2.90		user
		0.00		system
		0:01.45		elapsed
		199%		CPU

		Speedup = (2,33/1,45) = 1,6
	

	PUC:
		- Sequencial:
		real	0m4.362s
		user	0m4.359s
		sys	0m0.000s

		- Paralelo Sem Escalonamento:
		real	0m4.124s
		user	0m6.586s
		sys	0m0.000s

		Speedup = (4,362/4,124) = 1,05

		- Paralelo Com Escalonamento:
		real	0m3.177s
		user	0m6.276s
		sys	0m0.004s

		Speedup = (4,362/3,177) = 1,37
 */

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main() 
{
   int i, j, n = 30000;

   omp_set_num_threads(2);

   // Allocate input, output and position arrays
   int *in = (int*) calloc(n, sizeof(int));
   int *pos = (int*) calloc(n, sizeof(int));   
   int *out = (int*) calloc(n, sizeof(int));   

   // Initialize input array in the reverse order
   for(i=0; i < n; i++)
      in[i] = n-i;  

   // Print input array
   //   for(i=0; i < n; i++) 
   //      printf("%d ",in[i]);
    
   // Silly sort (you have to make this code parallel)
   #pragma omp parallel for firstprivate(j, n) schedule(dynamic, 500)
   for(i=0; i < n; i++)
      for(j=0; j < n; j++)
         if(in[i] > in[j])
            pos[i]++;

   // Move elements to final position
   for(i=0; i < n; i++) 
      out[pos[i]] = in[i];
   
   // print output array
   //   for(i=0; i < n; i++) 
   //      printf("%d ",out[i]);

   // Check if answer is correct
   for(i=0; i < n; i++)
      if(i+1 != out[i]) 
      {
         printf("test failed\n");
         exit(0);
      }

   printf("test passed\n"); 
}  
