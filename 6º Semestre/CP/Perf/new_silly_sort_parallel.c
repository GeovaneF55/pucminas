#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main() 
{
   int i, j, n = 10000;
   int nthreads = 2; 

   // Allocate input, output and position arrays
   int *in = (int*) calloc(n, sizeof(int));
   int *out = (int*) calloc(n, sizeof(int));   
   int **pos = (int**) calloc(nthreads, sizeof(int*));   

   // Initialize input array in the reverse order
   for(i=0; i < n; i++)
      in[i] = n-i;  

   // Silly sort
   #pragma omp parallel num_threads(nthreads) private(i,j)
   {
     int tid = omp_get_thread_num();
     pos[tid] = (int*) calloc(n, sizeof(int));   
     
     #pragma omp for
     for(i=0; i < n; i++) 
       for(j=0; j < n; j++)
	 if(in[i] >= in[j]) 
           pos[tid][j]++;
   }

   // Move elements to final position
   for(i=0; i < n; i++) 
   {
     int pos_final = 0;
     for(j=0; j < nthreads; j++) 
       pos_final+= pos[j][i];
      
     out[n-pos_final] = in[i];
   }

   // Check if answer is correct
   for(i=0; i < n; i++)
      if(i+1 != out[i]) 
      {
         printf("test failed");
         exit(0);
      }

   printf("test passed"); 
}  
