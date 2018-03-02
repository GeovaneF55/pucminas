/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Friends - Problema de encontrar o maior grupo de amizade (amigo do meu amigo, também é meu amigo)
 * Language:  C
 */
#include <stdio.h>
#include <stdlib.h>

/*
 * type: Struct representando uma cidade com sua população e as amizades dessa população
 */
typedef struct
{
    int qt_pessoas;
    int qt_amizades;
    int qt_conjuntos;

    int* rank;
    int* pai;
    int* quantidade;
} Cidade;

Cidade cidade;

/*
 * Function: Retorna o conjunto do qual o elemento pertence
 * Parameters: (int) elemento
 * Return: (int) conjunto
 */
int encontraConjunto(int elemento){
    return (cidade.pai[elemento] == elemento) ?
           (elemento) :
           (cidade.pai[elemento] = encontraConjunto(cidade.pai[elemento]));
}

/*
 * Function: Retorna verdadeiro caso os elementos sejam do mesmo conjunto
 * Parameters: (int) elemento1, (int) elemento2
 * Return: (int) mesmoConjunto
 */
int mesmoConjunto(int elemento1, int elemento2){
    return encontraConjunto(elemento1) == encontraConjunto(elemento2);
}

/*
 * Function: Une dois conjuntos passados por parâmetro
 * Parameters: (int) raiz1, (int) raiz2
 * Return: (void)
 */
void uneConjuntos(int raiz1, int raiz2){
    if(!mesmoConjunto(raiz1, raiz2)) {
        int a = encontraConjunto(raiz1),
            b = encontraConjunto(raiz2);
        if(cidade.rank[a] > cidade.rank[b]) {
            cidade.quantidade[a] += cidade.quantidade[b];
            cidade.pai[b] = a;
        } else {
            cidade.quantidade[b] += cidade.quantidade[a];
            cidade.pai[a] = b;

            if(cidade.rank[a] == cidade.rank[b]){
                cidade.rank[b]++;
            }
        }
        cidade.qt_conjuntos--;
    }
}

/*
 * Function: Retorna o tamanho do conjunto
 * Parameters: (int) raiz
 * Return: (int) tamanho
 */
int tamanhoConjunto(int elemento){
    return cidade.quantidade[encontraConjunto(elemento)];
}

/*
 * Function: Retorna a quantidade de conjuntos disjuntos
 * Parameters: (void)
 * Return: (int) tamanho
 */
int numConjuntosDisjuntos(){
    return cidade.qt_conjuntos;
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main ()
{
    int testes, p1, p2;
    int* allocAmizades;

    // Entrada
    scanf( "%d", &testes );

    // Criação de cidades
    for( int i=0; i<testes; i++ ) {

        int max = 1;

        scanf( "%d", &cidade.qt_pessoas );
        scanf( "%d", &cidade.qt_amizades );

        cidade.qt_conjuntos = cidade.qt_pessoas;

        // RANK
        cidade.rank = (int *)malloc(cidade.qt_pessoas * sizeof(int));
        for(int p=0; p<cidade.qt_pessoas; p++) cidade.rank[p] = 0;

        // PAIS
        cidade.pai = (int *)malloc(cidade.qt_pessoas * sizeof(int));
        for(int p=0; p<cidade.qt_pessoas; p++) cidade.pai[p] = p;

        // QUANTIDADES
        cidade.quantidade = (int *)malloc(cidade.qt_pessoas * sizeof(int));
        for(int p=0; p<cidade.qt_pessoas; p++) cidade.quantidade[p] = 1;

        // Criação de amizades
        for( int j=0; j<cidade.qt_amizades; j++ ) {
            scanf( "%d", &p1 ); p1--;
            scanf( "%d", &p2 ); p2--;

            uneConjuntos(p1, p2);
            if (tamanhoConjunto(p1) > max) {
                max = tamanhoConjunto(p1);
            }
        }

        // Saída
        printf("%d\n", max);
    }

    return 0;
}