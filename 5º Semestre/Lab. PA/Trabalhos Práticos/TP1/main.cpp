/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Maximun Sum - Problema da soma máxima de Sub Matriz
 * Language:  C
 */
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

/*
 * type: Struct representando uma matriz
 */
typedef struct
{
    int tamanho;
    int *elementos;
}Matriz;

/*
 * Function: Imprime a matriz na tela
 * Parameters: (int) tamanho, (int*) allocMatriz
 * Return: (void)
 */
void imprime_matriz(int tamanho, int *elementos){
    printf("MATRIZ (%dx%d):\n", tamanho, tamanho);
    for(int i = 0; i < tamanho; i++) {
        for (int j = 0; j < tamanho; j++) {
            printf("[%d]", elementos[i * tamanho + j]);
        }
        printf("\n");
    }
    printf("\n");
}

/*
 * Function: Preenche a matriz com o tamanho definido, com os elementos vindos da entrada padrão
 * Parameters: (int) tamanho, (int*) allocMatriz
 * Return: (int*) allocMatriz
 */
int* preenche_matriz(int tamanho, int *allocMatriz){
    for(int i = 0; i < tamanho; i++){
        for(int j = 0; j < tamanho; j++){
            scanf("%d", &allocMatriz[i*tamanho + j]);
        }
    }
    return allocMatriz;
}

/*
 * Function: Retorna a soma máxima da Sub Matriz por Força Bruta
 * Parameters: (int) tamanho, (int*) elementos
 * Return: (int) maior_soma
 */
int soma_maxima_fb(int tamanho, int *elementos) {
    int maior_soma = INT_MIN;
    int soma = 0;

    for (int k = 0; k < tamanho; k++) {
        for (int l = 0; l < tamanho; l++) {
            for (int n = 0; n < tamanho; n++) {
                for (int m = 0; m < tamanho; m++) {
                    for (int i = k; i <= n; i++) {
                        for (int j = l; j <= m; j++) {
                            soma += elementos[i * tamanho + j];
                        }
                    }
                    if (soma > maior_soma) {
                        maior_soma = soma;
                    }
                    soma = 0;
                }
            }
        }
    }

    return maior_soma;
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main () {
    Matriz matriz;
    int *allocMatriz;

    // Entrada
    scanf("%d\n", &matriz.tamanho);

    allocMatriz = (int *)malloc(matriz.tamanho * matriz.tamanho * sizeof(int));
    matriz.elementos = preenche_matriz(matriz.tamanho, allocMatriz);
    //imprime_matriz(matriz.tamanho, matriz.elementos);

    // Saída
    printf("%d\n", soma_maxima_fb(matriz.tamanho, matriz.elementos));

    return(0);
}