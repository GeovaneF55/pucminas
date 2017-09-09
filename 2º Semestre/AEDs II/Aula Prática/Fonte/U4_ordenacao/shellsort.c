/**
 * Algoritmo de ordenacao Shellsort
 * @author Max do Val Machado
 * @version 2 01/2015
 */
#include <stdio.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>

#define n   10000000

int array[n];
int i;
int j;

//Produz um arranjo ordenado de modo decrescente.
void decrescente() {
   for (i = 0; i < n; i++) {
      array[i] = n - 1 - i;
   }
}


//Produz um arranjo de numeros aleatorios.
void aleatorio() {
   for (i = 0; i < n; i++) {
      array[i] = rand() % 1000;
   }
}


//Mostrar os elemento de um arranjo.
void mostrar() {
   printf("[ ");

   for (i = 0; i < n; i++) {
      printf("%d ", array[i]);
   }

   printf("] \n");
}


// Algoritmo de ordenacao
void shellsort() {
   int h = 1;

   do { h = (h * 3) + 1; } while (h < n);

   do {
      h /= 3;
      for (i = h; i < n; i++) {
         int auxiliar = array[i];
         int j = i;
         while (j >= h && auxiliar < array[j - h]) {
            array[j] = array[j - h];
            j -= h;
         }
         array[j] = auxiliar;
      }
   } while (h != 1);
}

int main() {
   srand(time(NULL));
   decrescente();
   //mostrar();		
   clock_t comeco = clock();
   shellsort();
   clock_t fim = clock();
   clock_t total = (fim - comeco);// / CLOCKS_PER_SEC / 1000;

   //mostrar();
   printf("Tempo para ordenar: %f ms.", (float)total);
}
