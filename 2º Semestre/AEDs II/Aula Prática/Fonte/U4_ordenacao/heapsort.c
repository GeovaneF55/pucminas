/**
 * Metodo de ordenacao heapsort
 * @author Max do Val Machado
 * @version 2 01/2015
 */
#include "geracao.h"

void constroi(int tamHeap){
      for(int i = tamHeap; i > 1 && array[i] > array[i/2]; i /= 2){
         swap(i, i/2);
      }
}

void reconstroi(int tamHeap){
   int i = 1, filho;
   while(i <= (tamHeap/2)){

      if (array[2*i] > array[2*i+1] || 2*i == tamHeap){
         filho = 2*i;
      } else {
         filho = 2*i + 1;
      }

      if(array[i] < array[filho]){
         swap(i, filho);
         i = filho;
      }else{
         i = tamHeap;
      }
   }
}



// Algoritmo de ordenacao
void heapsort() {
   //Alterar o vetor ignorando a posicao zero
   int* tmp = (int*) malloc((n+1)*sizeof(int));
   for(int i = 0; i < n; i++){
      tmp[i+1] = array[i];
   }
   free(array);
   array = tmp;

   //Contrucao do heap
   for(int tamHeap = 1; tamHeap <= n; tamHeap++){
      constroi(tamHeap);
   }

   //Ordenacao propriamente dita
   int tamHeap = n;
   while(tamHeap > 1){
      swap(1, tamHeap--);
      reconstroi(tamHeap);
   }

   //Alterar o vetor para voltar a posicao zero
   tmp = array;
   array = (int*) malloc(n*sizeof(int));
   for(int i = 0; i < n; i++){
      array[i] = tmp[i+1];
   }
   free(tmp);
}

int main() {
   srand(time(NULL));
   decrescente();
   //mostrar();		
   clock_t comeco = clock();
   heapsort();
   clock_t fim = clock();
   int total = (clock() - comeco) / CLOCKS_PER_SEC / 1000;

   mostrar();
   printf("Tempo para ordenar: %d ms.", total);
}
