#include <iostream>
#include <queue>
#include <algorithm>
#include <math.h>

/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Prova - Bilhar N+1
 * Language:  C++
 */

using namespace std;

typedef struct
{
    int pos_x;
    int pos_y;
} Bola;

typedef struct
{
    int qt_bolas;
    Bola branca;
    vector <Bola> bolas;
} Sinuca;

Sinuca sinuca;

/*
 * Function: Limpa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void clear()
{
    sinuca.bolas.clear();
}

/*
 * Function: Retorna a distância entre duas coordenadas (pontos) dadas
 * Parameters: (int)x1, (int)y1, (int)x2, (int)y2
 * Return: (double) distancia
 */
double distancia(int x1, int y1, int x2, int y2)
{
    double distancia = 0;
    distancia = sqrt(pow (x1-x2, 2.0) + pow (y1-y2, 2.0));
    return distancia;
}

/*
 * Function: Verifica qual das bolas está mais perto da bola Branca
 * Parameters: (void)
 * Return: (void)
 */
void resolucao()
{
    int proxima_branca=0;
    double menor_distancia=999999.9, distancia_atual;
    for(int i=0; i<sinuca.qt_bolas; i++){
        distancia_atual = distancia(sinuca.branca.pos_x, sinuca.branca.pos_y, sinuca.bolas[i].pos_x, sinuca.bolas[i].pos_y);
        if(distancia_atual < menor_distancia){
            menor_distancia=distancia_atual;
            proxima_branca=i+1;
        }
    }

    cout << proxima_branca << "\n";
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main()
{
    int testes, pos_x, pos_y;
    Bola bola;

    cin >> testes;

    while(testes != 0)
    {
        cin >> sinuca.qt_bolas;

        // entradas
        cin >> sinuca.branca.pos_x;
        cin >> sinuca.branca.pos_y;

        for(int i=0; i<sinuca.qt_bolas; i++)
        {
            cin >> pos_x;
            cin >> pos_y;

            bola.pos_x = pos_x;
            bola.pos_y = pos_y;

            sinuca.bolas.push_back(bola);
        }

        // Problema
        resolucao();

        clear();
        testes--;
    }
    return 0;
}
