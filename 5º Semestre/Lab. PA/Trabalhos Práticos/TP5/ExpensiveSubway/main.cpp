#include <iostream>
#include <queue>
#include <algorithm>

/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Expensive Subway - Problema de encontrar o menor valor de uma estação inicial para todas as outras
 * Language:  C++
 */

using namespace std;

struct Conexao
{
    int origem;
    int destino;
    int valor;
};

typedef struct
{
    int qt_estacoes,
        qt_conexoes,
        ponto_partida;

    vector <string> estacoes;
    vector <int> pais;
    vector <Conexao> conexoes;

} Metro;

Metro metro;

/*
 * Function: Inicializa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void init()
{
    cin >> metro.qt_estacoes;
    cin >> metro.qt_conexoes;
}

/*
 * Function: Limpa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void clear()
{
    init();

    metro.estacoes.clear();
    metro.pais.clear();
    metro.conexoes.clear();
}

/*
 * Function: Imprime estações
 * Parameters: (void)
 * Return: (void)
 */
void imprime_estacoes()
{
    for(int i=0; i<metro.qt_estacoes; i++)
    {
        cout << "[" << i << ":" << metro.estacoes[i] << "]\n";
    }
}

/*
 * Function: Imprime conexões entre as estações
 * Parameters: (void)
 * Return: (void)
 */
void imprime_conexoes()
{
    for(int i=0; i<metro.qt_conexoes; i++)
    {
        cout << "[" << metro.conexoes[i].origem << "->"
             << metro.conexoes[i].destino << "]=" << metro.conexoes[i].valor << "\n";
    }
}

/*
 * Function: Encontra o valor numérico de uma estação dada através de sua string
 * Parameters: (const string) &estacao
 * Return: (int) estacao
 */
int encontra_estacao(const string &estacao)
{
    for(int i=0; i<metro.qt_estacoes; i++)
    {
        if(estacao == metro.estacoes[i])
        {
            return i;
        }
    }
    return -1;
}

/*
 * Function: Compara se o valor da conexão c1 é menor que o da c2
 * Parameters: (Conexao) c1, (Conexao) c2
 * Return: (int) ehMenor
 */
int comp(Conexao c1, Conexao c2)
{
    return (c1.valor < c2.valor);
}

/*
 * Function: Encontra o pai da estação atual
 * Parameters: (int) estacao
 * Return: (int) pai
 */
int encontraPai(int estacao)
{
    if(metro.pais[estacao] == estacao) return estacao;
    else return metro.pais[estacao] = encontraPai(metro.pais[estacao]);
}

/*
 * Function: Encontra o menor valor da estação inicial até todas as outras estações
 * Parameters: (void)
 * Return: (void)
 */
void resolucao()
{
    sort(metro.conexoes.begin(), metro.conexoes.end(), comp);

    int total = 0,
        estacoes_a_verificar = metro.qt_estacoes-1,
        paiinicio,
        paifin;

    for(int i=0; i<=(metro.qt_conexoes-1); i++)
    {
        paiinicio = encontraPai(metro.conexoes[i].origem);
        paifin = encontraPai(metro.conexoes[i].destino);
        if(paiinicio != paifin)
        {
            total += metro.conexoes[i].valor;
            metro.pais[paiinicio] = paifin;
            estacoes_a_verificar--;
        }
    }
    if(estacoes_a_verificar) cout << "Impossible\n";
    else  cout << total << "\n";
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main()
{
    Conexao conexao;
    string estacao, ponto_partida, origem, destino;

    init();

    while(metro.qt_estacoes != 0){

        // estações
        for(int i=0; i<metro.qt_estacoes; i++)
        {
            cin >> estacao;
            metro.estacoes.push_back(estacao);
            metro.pais.push_back(i);
        }

        // conexões
        for(int i=0; i<metro.qt_conexoes; i++)
        {
            cin >> origem;
            cin >> destino;
            cin >> conexao.valor;

            conexao.origem = encontra_estacao(origem);
            conexao.destino = encontra_estacao(destino);

            metro.conexoes.push_back(conexao);
        }

        cin >> ponto_partida;
        metro.ponto_partida = encontra_estacao(ponto_partida);

        // Problema
        resolucao();

        clear();
    }
    return 0;
}
