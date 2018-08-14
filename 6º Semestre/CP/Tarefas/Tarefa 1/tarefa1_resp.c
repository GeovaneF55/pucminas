/*
 * Aluno: Geovane Fonseca de Sousa Santos
 * Matrícula: 553237
 * Matéria: Coputação Paralela
 * Tarefa 1: Padrão MAP
 */

#include <stdio.h>
#include <omp.h>

int main()
{
    int i;

    #pragma omp parallel num_threads(2) // seta o número de threads em 2 
    {
        int tid = omp_get_thread_num(); // lê o identificador da thread

        // Garante uma ordem para que as threads que sejam finalizadas enviem seus resultados para a
        // saída
        #pragma omp for ordered schedule(dynamic)
        for(i = 1; i <= 3; i++) 
        {
           // As próximas linhas serão executadas em paralelo mas a medida que suas execuções acabem,
           // os resultados das threads serão enviados em ordem
           #pragma omp ordered
           printf("[PRINT1] T%d = %d \n",tid,i);
           printf("[PRINT2] T%d = %d \n",tid,i);
        }
    }
}

