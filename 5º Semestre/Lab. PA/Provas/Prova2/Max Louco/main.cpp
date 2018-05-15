/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Prova - Max, o louco
 * Language:  C++
 */

#include <iostream>
#include <vector>

using namespace std;

/*
 * Struct: Rota
 */
typedef struct {

    int origem;
    int destino;
    int custo;

} Rota;

/*
 * Struct: MaxL
 */
typedef struct{
    int qt_cidades{};
    int qt_estradas{};
    int capacidade{};

    Rota rota[10000]{};

} MaxL;

MaxL max_l;

/*
 * Function: Dijkstra
 * Parameters: (int) capacidade, (int) origem, (int) destino
 * Return: (int) valor da rota calculada
 */
int dijkstra(int capacidade, int origem, int destino) {

    int custo = 0;

    if (capacidade < max_l.rota[origem].custo) {
        custo = -1;
    }else {
        capacidade = capacidade - max_l.rota[origem].custo;
        if(capacidade < max_l.rota[origem+1].custo) {
            custo = max_l.rota[origem+1].custo - capacidade;
        }
    }

    return custo;
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main() {
    vector<int> custoRota;

    cin >> max_l.qt_cidades >> max_l.qt_estradas >> max_l.capacidade;

    while(max_l.qt_cidades != 0 && max_l.qt_estradas != 0 && max_l.capacidade != 0) {
        for(int m = 1; m <= max_l.qt_estradas; m ++) {
            cin >> max_l.rota[m].origem >> max_l.rota[m].origem >> max_l.rota[m].custo;
        }

        for(int n = 1; n <= max_l.qt_cidades; n ++) {
            int custoCidade;
            cin >> custoCidade;
        }

        int resultado = dijkstra(max_l.capacidade, 1, max_l.qt_cidades);

        if(resultado < 0) {
            cout << "-1" << endl;
        }else {
            cout << resultado << endl;
        }

        cin >> max_l.qt_cidades >> max_l.qt_estradas >> max_l.capacidade;
    }
    return 0;
}