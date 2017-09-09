/**
 * Metodo de ordenacao da bolha
 * @author Max do Val Machado
 * @version 2 01/2015
 */
#include <stdio.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>

#define n   10000
#define bool   short
#define true   1
#define false  0

int* array = (int*) malloc(n*sizeof(int));
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
   
void swap(int i, int j) {
   int temp = array[i];
   array[i] = array[j];
   array[j] = temp;
}
