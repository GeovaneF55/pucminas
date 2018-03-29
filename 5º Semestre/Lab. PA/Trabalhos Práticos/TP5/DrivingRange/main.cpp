#include <iostream>
#include <queue>
#include <algorithm>

/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Driving Range - Problema de encontrar o alcance mínimo de um carro para que percorra todas as cidades
 * Language:  C++
 */

using namespace std;

struct Estrada
{
    int origem;
    int destino;
    int distancia;
};

typedef struct
{
    int qt_cidades,
        qt_estradas;

    vector <int> cidades;
    vector <int> pais;
    vector <Estrada> estradas;

} Mapa;

Mapa mapa;

/*
 * Function: Inicializa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void init()
{
    cin >> mapa.qt_cidades;
    cin >> mapa.qt_estradas;
}

/*
 * Function: Limpa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void clear()
{
    init();

    mapa.cidades.clear();
    mapa.pais.clear();
    mapa.estradas.clear();
}

/*
 * Function: Imprime cidades
 * Parameters: (void)
 * Return: (void)
 */
void imprime_cidades()
{
    for(int i=0; i<mapa.qt_cidades; i++)
    {
        cout << "[" << mapa.cidades[i] << "]\n";
    }
}

/*
 * Function: Imprime estradas entre as cidades
 * Parameters: (void)
 * Return: (void)
 */
void imprime_estradas()
{
    for(int i=0; i<mapa.qt_estradas; i++)
    {
        cout << "[" << mapa.estradas[i].origem << "->"
             << mapa.estradas[i].destino << "]=" << mapa.estradas[i].distancia << "\n";
    }
}

/*
 * Function: Compara se a distancia da estrada e1 é menor que o da e2
 * Parameters: (Estrada) c1, (Estrada) c2
 * Return: (int) ehMenor
 */
int compara(Estrada e1, Estrada e2)
{
    return (e1.distancia < e2.distancia);
}

/*
 * Function: Encontra o pai da cidade atual
 * Parameters: (int) cidade
 * Return: (int) pai
 */
int encontraPai(int cidade)
{
    if(mapa.pais[cidade] == cidade) return cidade;
    else return mapa.pais[cidade] = encontraPai(mapa.pais[cidade]);
}

/*
 * Function: Encontra o alcance mínimo de um carro para que percorra todas as cidades
 * Parameters: (void)
 * Return: (void)
 */
void resolucao()
{
    sort(mapa.estradas.begin(), mapa.estradas.end(), compara);
    int maior_distancia = mapa.estradas[mapa.qt_estradas-1].distancia,
            cidades_a_verificar = mapa.qt_cidades-1,
            paiinicio,
            paifin;

    for(int i=0; i<mapa.qt_estradas; i++)
    {
        paiinicio = encontraPai(mapa.estradas[i].origem);
        paifin = encontraPai(mapa.estradas[i].destino);
        if(paiinicio != paifin)
        {
            if(maior_distancia < mapa.estradas[i].distancia) maior_distancia = mapa.estradas[i].distancia;
            mapa.pais[paiinicio] = paifin;
            cidades_a_verificar--;
        }
    }
    if(cidades_a_verificar) cout << "IMPOSSIBLE\n";
    else  cout << maior_distancia << "\n";
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main()
{
    Estrada estrada;

    init();

    while(mapa.qt_cidades != 0){

        // cidades
        for(int i=0; i<mapa.qt_cidades; i++)
        {
            mapa.cidades.push_back(i);
            mapa.pais.push_back(i);
        }

        // estradas
        for(int i=0; i<mapa.qt_estradas; i++)
        {
            cin >> estrada.origem;
            cin >> estrada.destino;
            cin >> estrada.distancia;

            mapa.estradas.push_back(estrada);
        }

        // Problema
        resolucao();

        clear();
    }
    return 0;
}
