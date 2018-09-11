/* C implementation QuickSort from  http://w...content-available-to-author-only...s.org/quick-sort/ */

/*

Alunos: Gabriel Oliveira Campos
	Geovane Fonseca de Sousa Santos
	
Matéria: Coputação Paralela
Tarefa 14: Quicksort Paralelo

Sequencial:

real	0m2.490s
user	0m2.474s
sys	0m0.016s

Paralelo:

real	0m1.811s
user	0m2.520s
sys	0m0.060s


Speedup: (tempo Seq./ tempo Par.) = (2.490/1.811) = 1,374

*/

#include<stdio.h>
#include<stdlib.h>
#include<omp.h>

// A utility function to swap two elements
void swap(int* a, int* b)
{
  int t = *a;
  *a = *b;
  *b = t;
}
 
/* This function takes last element as pivot, places
   the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
   to left of pivot and all greater elements to right
   of pivot */
int partition (int arr[], int low, int high)
{
  int pivot = arr[high];    // pivot
  int i = (low - 1);  // Index of smaller element

  for (int j = low; j <= high- 1; j++)
    {
      // If current element is smaller than or
      // equal to pivot
      if (arr[j] <= pivot)
        {
	  i++;    // increment index of smaller element
	  swap(&arr[i], &arr[j]);
        }
    }
  swap(&arr[i + 1], &arr[high]);
  return (i + 1);
}
 
/* The main function that implements QuickSort
 arr[] --> Array to be sorted,
  low  --> Starting index,
  high  --> Ending index */
void quickSort(int arr[], int low, int high)
{
  if (low < high)
  {
      /* pi is partitioning index, arr[p] is now
	 at right place */
      int pi = partition(arr, low, high);

      // Separately sort elements before
      // partition and after partition
      if((pi - 1) - low > 50 && high - (pi + 1) > 50){
         #pragma omp parallel sections
         {
            #pragma omp section
            quickSort(arr, low, pi - 1);

            #pragma omp section
            quickSort(arr, pi + 1, high);
         } 
      } else { 
         quickSort(arr, low, pi - 1);
         quickSort(arr, pi + 1, high);
      }
   }
}

/* Function to print an array */
void printArray(int arr[], int size)
{
  int i;
  for (i=0; i < size; i++)
    printf("%d ", arr[i]);
  printf("\n");
}
 
// Driver program to test above functions
int main()
{
  int i,n = 10000000;
  int *arr = (int*) malloc(n*sizeof(int));

  omp_set_num_threads(2);

  for(i=0; i < n; i++)
    arr[i] = rand()%n;

  quickSort(arr, 0, n-1);
  printf("Sorted array: \n");
  printArray(arr, n);
  return 0;
}
