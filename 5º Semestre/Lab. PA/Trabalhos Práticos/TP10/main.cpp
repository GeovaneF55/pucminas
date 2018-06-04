/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Dividindo os Trabalhos - (Algoritmo Guloso) pega a melhor opção ao dividir os trabalhos
 * Language:  C++
 */
#include <iostream>
#include <vector>
#include <algorithm>
#include <stdlib.h>

using namespace std;

/*
 * type: Struct representando um semestre
 */
typedef struct
{
    int qt_trabalhos{};
    vector <int> trabalhos;
}Semestre;

Semestre semestre;

/*
 * Function: Preenche o semestre com os trabalhos pela entrada padrão
 * Parameters: null
 * Return: (void)
 * */
void preencheSemestre(){
    int trabalho;

    for(int i=0; i<semestre.qt_trabalhos; i++){
        cin >> trabalho;

        semestre.trabalhos.push_back(trabalho);
    }
}

/*
 * Function: Verifica se t1 é mais difícil que t2
 * Parameters: (int) t1, (int) t2
 * Return: (bool) maiorDificuldade
 * */
bool maiorDificuldade(int t1, int t2){
    return t1 < t2;
}

/*
 * Function: Diferença mínima entre a divisão dos trabalhos pela dificuldade de cada um
 * Parameters: null
 * Return: (int) diferenca
 */
int diferencaDivisao(){

    int i=0, j=semestre.qt_trabalhos-1,
        p1=0, p2=0;

    sort(semestre.trabalhos.begin(), semestre.trabalhos.end(), maiorDificuldade);

    while( i <= j ){
        if(p2 <= p1){
            p2 += semestre.trabalhos[j];
            j--;
        } else {
            p1 += semestre.trabalhos[i];
            i++;
        }
    }

    return abs(p2-p1);
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main () {

    while(cin >> semestre.qt_trabalhos) {
        preencheSemestre();

        cout << diferencaDivisao() << endl;

        semestre.trabalhos.clear();
    }

    return(0);
}
