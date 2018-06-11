/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Prova - Liga da Justiça
 * Language:  C++
 */

#include <iostream>
#include<bits/stdc++.h>

using namespace std;

/*
 * type: Struct representando o herói
 */
typedef struct{
    int id_secreto;
    int liga;
    int qt_parceiros;
    vector<int> parceiros;
}Heroi;

/*
 * type: Struct representando a liga
 */
typedef struct{
    int qt_herois, qt_parcerias;
    vector<Heroi> herois;
}Liga;

Liga liga;

bool maiorGrauHeroi(Heroi a, Heroi b){
    return a.parceiros.size() > b.parceiros.size();
}

bool haAmizade(int heroiA, int heroiB){
    for (int parceiro : liga.herois[heroiA].parceiros) {
        if(parceiro == heroiB){
            return true;
        }
    }

    return false;
}

bool estaClick(int heroi, vector <int> &click){
    for (int i : click) {
        if(i == heroi){
            return true;
        }
    }
    return false;
}

bool pertenceClick(int i, vector <int> &click){
    for (int &j : click) {
        if(!haAmizade(liga.herois[i].id_secreto, j)){
            return false;
        }
    }
    return true;
}

void criarLigas(vector <int> &click){
    for (int i=1; i<liga.herois.size(); i++) {
        if(pertenceClick(i, click)) {

            if(!estaClick(liga.herois[i].id_secreto, click)){
                click.push_back(liga.herois[i].id_secreto);
            }

        }
    }
}

void ligaJustica(vector <int> &click){
    for (int i : click) {
        liga.herois[i].liga = 1;
    }

    for(int i=0; i<liga.qt_herois; i++){
        if(liga.herois[i].liga != 1){
            liga.herois[i].liga = 2;
        }
    }
}

void preencheLiga(){
    int heroiA, heroiB;

    liga.herois.clear();

    for(int i=0; i<liga.qt_herois; i++){
        Heroi heroi{};
        heroi.id_secreto = i;
        heroi.liga = 0;
        liga.herois.push_back(heroi);
    }

    for(int i=0; i<liga.qt_parcerias; i++){
        cin >> heroiA >> heroiB;
        liga.herois[heroiA-1].parceiros.push_back(heroiB-1);
        liga.herois[heroiB-1].parceiros.push_back(heroiA-1);
    }

    for(int i=0; i<liga.qt_herois; i++){
        sort(liga.herois.begin(), liga.herois.end(), maiorGrauHeroi);
    }
}

bool haOutraLiga(int heroi){
    for(int i=0; i<liga.herois[heroi].parceiros.size(); i++){
        if(liga.herois[heroi].liga == 2 &&
                liga.herois[heroi].liga == liga.herois[liga.herois[heroi].parceiros[i]].liga){
            return true;
        }
    }

    return false;
}

bool verificarLiga(vector <int>&click) {
    bool ligaFormada = true;
    int i = 0;

    while (ligaFormada && i < liga.qt_herois) {
        if (liga.herois[i].liga != 1) {
            ligaFormada = !haOutraLiga(i);
        }
        i++;
    }

    return ligaFormada;
}

void printaVetor(){
    for (auto &heroi : liga.herois) {
        cout << "Heroi: " << heroi.id_secreto+1 << " - liga: " << heroi.liga << endl;
        for (int parceiro : heroi.parceiros) {
            cout << " -> Parceiro: " << parceiro+1 << endl;
        }
    }
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main(){
    cin >> liga.qt_herois >> liga.qt_parcerias;

    vector<int> click;

    while(liga.qt_herois != 0 && liga.qt_parcerias != 0){
        preencheLiga();

        click.clear();
        click.push_back(liga.herois[0].id_secreto);

        criarLigas(click);
        ligaJustica(click);

        //printaVetor();

        if(verificarLiga(click)){
            cout << "Y" << endl;
        } else{
            cout << "N" << endl;
        }

        cin >> liga.qt_herois >> liga.qt_parcerias;
    }

    return 0;
}