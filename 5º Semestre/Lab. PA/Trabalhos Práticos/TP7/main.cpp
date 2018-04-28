/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Colônia de Formigas - Dada a estrutura de uma colônia e um conjunto de consultas, calcular, para cada
 * uma das consultas, o menor caminho entre pares de formigueiros.
 * Language:  C++
 */

#include <iostream>
#include<bits/stdc++.h>
using namespace std;

vector<int> precedentes;
vector<long long int> estimativas;
vector<int> fechado;

/*
 * type: Struct representando um formigueiro
 */
typedef pair<int, long long int> Formigueiro;

/*
 * type: Struct representando os túneis
 */
typedef vector<list <Formigueiro>> Tuneis;

/*
 * Function: Preenche a lista de adjacência com os túneis dados
 * Parameters: (Tuneis) &tuneis, (int const&) qt_tuneis
 * Return: (void)
 */
void preencheTuneis(Tuneis &tuneis, int const& qt_tuneis){
    int origem, peso;

    for(int destino = 1; destino <= qt_tuneis; ++destino){
        cin >> origem >> peso;

        tuneis[origem].emplace_back(destino, peso);
        tuneis[destino].emplace_back(origem, peso);
    }
}

/*
 * Function: Imprime a lista de adjacência da colônia
 * Parameters: (Tuneis) &tuneis,
 * Return: (void)
 */
void imprimeColonia(Tuneis &tuneis){

    for(int i = 0; i < tuneis.size(); ++i){
        auto adjNodes = static_cast<int>(tuneis[i].size());
        cout << "Adjacência de: [" << i << "]";
        if(adjNodes > 0){
            auto tunel = tuneis[i].begin();
            while(tunel != tuneis[i].end()){
                cout << " - " << (*tunel).second << " -> [" << (*tunel).first << "]";
                ++tunel;
            }

        }else{
            cout << " -> Nenhum";
        }
        cout << "\n";
    }
}

/*
 * Function: Imprime a lista de Adjacência de um formigueiro dado
 * Parameters: (Tuneis) &tuneis, (int const&) indice_formigueiro
 * Return: (void)
 */
void formigueirosAdjacentes(Tuneis &tuneis, int const& indice_formigueiro){
    cout << indice_formigueiro;
    auto tunel = tuneis[indice_formigueiro].begin();
    while(tunel != tuneis[indice_formigueiro].end()){
        cout << " - " << (*tunel).second << " -> [" << (*tunel).first << "]";
        ++tunel;
    }
}

/*
 * Function: Aplica o algoritmo de dijkstra na Colônias de formigas considerando a origem fornecida
 * Parameters: (Tuneis) &tuneis, (Formigueiro) formigueiro, (int const&) origem, (int const&) destino
 * Return: (void)
 */
void dijkstra(Tuneis &tuneis, Formigueiro formigueiro, int const& origem, int const& destino){

    auto tunel = tuneis[formigueiro.first].begin();
    while(tunel != tuneis[formigueiro.first].end()){
        if(estimativas[(*tunel).first] > (*tunel).second + estimativas[formigueiro.first]){
            estimativas[(*tunel).first] = (*tunel).second + estimativas[formigueiro.first];
            precedentes[(*tunel).first] = formigueiro.first;
        }

        ++tunel;
    }

    fechado[formigueiro.first] = 1;

    tunel = tuneis[formigueiro.first].begin();
    while(tunel != tuneis[formigueiro.first].end()){
        if(fechado[(*tunel).first] == 0) {
            dijkstra(tuneis, (*tunel), origem, destino);
        }
        ++tunel;
    }
}

/*
 * Function: Retorna o peso do caminho mínimo entre os dois formigueiros dados
 * Parameters: (Tuneis) &tuneis, (int const&) origem, (int const&) destino
 * Return: (int) menorCaminho
 */
long long int caminhoMinimo(Tuneis &tuneis, int const& origem, int const& destino){

    for(int i=0; i<tuneis.size(); i++){
        precedentes.push_back(-1);
        if(i == origem){
            estimativas.push_back(0);
        } else {
            estimativas.push_back(LONG_LONG_MAX);
        }
        fechado.push_back(0);
    }

    auto tunel = tuneis[origem].begin();
    while(tunel != tuneis[origem].end()){
        estimativas[(*tunel).first] = (*tunel).second + estimativas[origem];
        precedentes[(*tunel).first] = origem;

        ++tunel;
    }

    fechado[origem] = 1;

    tunel = tuneis[origem].begin();
    while(tunel != tuneis[origem].end()){
        dijkstra(tuneis, (*tunel), origem, destino);
        ++tunel;
    }

    return estimativas[destino];
}

/*
 * Function: Executa os testes de caminho mínimo
 * Parameters: (Tuneis) &tuneis, (int const&) qtTestes
 * Return: (void)
 */
void executaTestes(Tuneis &tuneis, int const& qtTestes){
    int origem, destino;
    for(int i = 0; i < qtTestes; ++i) {
        cin >> origem >> destino;
        cout << caminhoMinimo(tuneis, origem, destino) << " ";

        precedentes.clear();
        estimativas.clear();
        fechado.clear();
    }
    cout << "\n";
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main(){

    int qt_formigueiros, qt_tuneis, qt_testes;

    cin >> qt_formigueiros;

    while(qt_formigueiros != 0){

        /******
         * Constrói Colônia
         *****/
        qt_tuneis = qt_formigueiros - 1;

        Tuneis tuneis(static_cast<unsigned long>(qt_formigueiros));
        preencheTuneis(tuneis, qt_tuneis);

        /******
         * Testes
         *****/
        cin >> qt_testes;
        executaTestes(tuneis, qt_testes);

        cin >> qt_formigueiros;
        tuneis.clear();
    }

    return 0;
}