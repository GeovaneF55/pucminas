/**
 * Metodo de ordenacao por insercao
 * @author Max do Val Machado
 * @version 2 01/2015
 */
#include "geracao.h"

// Algoritmo de ordenacao
void insertionSort() {
   for (i = 1; i < n; i++) {
      int tmp = array[i];
         int j = i - 1;
         
         while ((j >= 0) && (array[j] > tmp)) {
            array[j + 1] = array[j];
               j--;
         }
      array[j + 1] = tmp;
         
   }
}

int main() {
   srand(time(NULL));
   decrescente();
   //mostrar();		
   clock_t comeco = clock();
   insertionSort();
   clock_t fim = clock();
   int total = (clock() - comeco) / CLOCKS_PER_SEC / 1000;

   mostrar();
   printf("Tempo para ordenar: %d ms.", total);
}
