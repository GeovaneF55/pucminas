/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Dollars - Problema do montante de opções para uma quantidade em dinheiro
 * Language:  C
 */
#include <stdio.h>
#include <math.h>

/*
 * type: Struct representando o valor em dollar juntamente com a quantidade de informações
 */
typedef struct
{
    int dolar;
    unsigned long long montante;
} Entrada;

const int moedas[11] = { 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10 , 5 };

unsigned long long hash[6001][11] = {0};

/*
 * Function: Retorna a quantidade de combinações que a entrada em float tiver (Recursivo)
 * Parameters: (int) dolar, (int) inicio
 * Return: (unsigned long long) count_combinacoes
 */
unsigned long long gera_combinacoes_recursivo( int dolar, int inicio )
{
    unsigned long long count;

    if( dolar == 0 )
    {
        count = 1;
    }

    else if(dolar < 0 || inicio > 10 )
    {
        count = 0;
    }

    else if( hash[dolar/5][inicio] != 0 )
    {
        count = hash[dolar/5][inicio];
    }

    else
    {
        // Caso selecione a moeda
        count = gera_combinacoes_recursivo( dolar - moedas[inicio], (dolar - moedas[inicio] < moedas[inicio]) ? inicio+1 : inicio );
        // Caso não selecione a moeda
        count += gera_combinacoes_recursivo( dolar, inicio+1 );
    }

    if( count > 0 && count > hash[dolar/5][inicio] )
    {
        hash[dolar/5][inicio] = count;
    }

    return count;
}

/*
 * Function: Retorna a quantidade de combinações que a entrada em float tiver
 * Parameters: (int) dolar
 * Return: (unsigned long long) count_combinacoes
 */
unsigned long long gera_combinacoes( int dolar )
{
    int i=0;

    while(dolar < moedas[i]){
        i++;
    }

    return gera_combinacoes_recursivo( dolar, i );
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main ()
{
    Entrada entrada;
    double valor;
    // Entrada
    scanf( "%lf", &valor );

    while( valor != 0.00 ){

        entrada.dolar = (int)(valor * 100 + 0.5);
        entrada.montante = gera_combinacoes( entrada.dolar );

        // Saída
        printf( "%*s%.2f%*s%llu\n", 6, "", valor, 17, "", entrada.montante );

        scanf( "%lf", &valor );
    }

    return 0;
}