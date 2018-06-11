/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Lucro
 * Language:  C++
 */

#include <iostream>
#include <vector>
#include <climits>

using namespace std;

/*
 * Struct: Circo
 */
typedef struct {

    int qt_dias{};
    int custo{};

    vector<int> receitas;

} Circo;

Circo circo;

/*
 * Function: Preenche Circo
 * Parameters: null
 * Return: (void)
 */
void preencheCirco() {

    cin >> circo.custo;

    int receita;

    for(int i=0; i<circo.qt_dias; i++){
        cin >> receita;

        circo.receitas.push_back(receita);
    }

}

/*
 * Function: Limpa Festival
 * Parameters: null
 * Return: (void)
 */
void limpaCirco() {
    circo.receitas.clear();
}

/*
 * Function: Maximiza o lucro do circo
 * Parameters: null
 * Return: (int) minBlocos
 */
int maximoLucro(){
    int maiorLucro = INT_MIN;

    for(int i=0; i<circo.qt_dias; i++) {
        int lucro = 0;
        for(int j=i; j<circo.qt_dias; j++){
            lucro += (circo.receitas[j] - circo.custo);

            if(lucro > maiorLucro){
                maiorLucro = lucro;
            }
        }
    }

    return maiorLucro <= 0? 0: maiorLucro ;
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main() {

    while(cin >> circo.qt_dias){
        preencheCirco();

        cout << maximoLucro() << endl;

        limpaCirco();
    }

    return 0;
}
