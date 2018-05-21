/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose:
 * Language:  C++
 */

#include <iostream>
#include <vector>

using namespace std;

/*
 * Struct: ColetorMoeda
 */
typedef struct{
    int qt_moedas{};
    vector <int> moedas;
    vector <int> escolhidas;
} ColetorMoeda;

ColetorMoeda coletor;

/*
 * Function: Maximiza a quantidade de moedas
 * Parameters: null
 * Return: (int) resp
 */
int maximizar(){
    int resp = 0;
    int somatorio = 0;

    for(int i=0; i<coletor.moedas.size(); i++){
        if(i == coletor.moedas.size()-1 || somatorio + coletor.moedas[i] < coletor.moedas[i+1]){
            somatorio += coletor.moedas[i];
            resp++;
        }
    }

    return resp;
}

/*
 * Function: Novo coletor
 * Parameters: null
 * Return: (void)
 */
void novoColetor(){

    int moeda;

    cin >> coletor.qt_moedas;

    for(int i=0; i<coletor.qt_moedas; i++){
        cin >> moeda;
        coletor.moedas.push_back(moeda);
    }
}

/*
 * Function: Limpa coletor
 * Parameters: null
 * Return: (void)
 */
void limpaColetor(){
    coletor.moedas.clear();
    coletor.escolhidas.clear();

    novoColetor();
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main(){

    int qt_testes;

    cin >> qt_testes;

    novoColetor();

    for(int i=0; i<qt_testes; i++) {

        cout << maximizar() << endl;

        limpaColetor();
    }

    return 0;
}