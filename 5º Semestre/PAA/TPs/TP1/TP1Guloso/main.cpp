/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Programação GULOSA: Constrói um cardápio que maximiza o lucro sob a
 * restrição de que seu orçamento não seja excedido.
 * Language:  C++
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>

using namespace std;

/*
 * Struct: Prato
 */
typedef struct{
    int id;
    double custo;
    double lucro;
    double custo_beneficio;
} Prato;

/*
 * Struct: Cozinha
 */
typedef struct{
    int dias;
    int pratos;
    double orcamento;

    vector <Prato> list_pratos;
} Cozinha;

Cozinha cozinha{};

/*
 * Function: Verifica se o custo benefício de p1 é menor que p2, se forem iguais verifica o custo
 * Parameters: (Prato)p1, (Prato)p2
 * Return: (bool) ehMenor
 */
bool ehMenor(Prato p1, Prato p2){
    return (p1.custo_beneficio < p2.custo_beneficio)
           || (p1.custo_beneficio == p2.custo_beneficio && p1.custo < p2.custo);
}

/*
 * Function: Verifica se o valor do prato cabe no orçamento
 * Parameters: (Prato) prato, (double) orcamento
 * Return: (bool) cabeOrcamento
 */
bool cabeOrcamento(Prato prato, double orcamento){
    return (orcamento >= prato.custo);
}

/*
 * Function: Preenche a lista com os melhores pratos para cozinhar do cardápio
 * Parameters: null
 * Return: (void)
 */
void preencheCozinha(){
    double custo, lucro;

    vector <Prato> list_pratos;

    for(int i=0; i<cozinha.pratos; i++){
        cin >> custo >> lucro;

        cozinha.list_pratos.push_back({i+1, custo, lucro, custo/lucro});
        cozinha.list_pratos.push_back({i+1, custo, lucro/2.0, (custo/lucro)/2.0});
        list_pratos.push_back({i+1, custo, 0.0, 0.0});
    }

    sort(cozinha.list_pratos.begin(), cozinha.list_pratos.end(), ehMenor);
    sort(list_pratos.begin(), list_pratos.end(), ehMenor);
    cozinha.list_pratos.insert(cozinha.list_pratos.end(), list_pratos.begin(), list_pratos.end());
}

/*
 * Function: Limpa a lista de pratos
 * Parameters: null
 * Return: (void)
 */
void limpaCozinha(){
    cozinha.list_pratos.clear();
}

/*
 * Function: Imprime a solução no formato correto
 * Parameters: (vector<Prato>) &resp
 * Return: (void)
 */
void imprimeSolucao(vector<Prato> &resp) {
    float saida = 0;
    if(resp.empty()){
        cout << "0.0" << endl;
        cout << endl;
    } else {
        for(Prato &prato : resp){
            saida += prato.lucro;
        }

        cout << fixed << setprecision(1) << saida << endl;

        for(Prato &prato : resp){
            cout << prato.id << " ";
        }
        cout << endl;
    }
}

/*
 * Function: Maximiza o Lucro dos pratos a serem cozinhados
 * Parameters: null
 * Return: (void)
 */
void maximizaLucro() {
    vector<Prato> resp;
    int anterior = -1;
    double orcamento = cozinha.orcamento;

    int j = 0, k = 0;
    for (int i  = 0 ; i < cozinha.dias; i ++ ) {
        Prato prato = cozinha.list_pratos[j];
        while (j < cozinha.list_pratos.size() && !cabeOrcamento(prato, orcamento)) {
            j++;
            k++;

            if(j < cozinha.list_pratos.size()) {
                prato = cozinha.list_pratos[j];
            }
        }

        if(j >= cozinha.list_pratos.size()) {
            break;
        }

        resp.push_back(prato);
        orcamento -= prato.custo;

        if(anterior != -1 && prato.id != cozinha.list_pratos[anterior].id) {
            j = k;
        } else {
            anterior = j;
            j++;
        }
    }

    if(j >= cozinha.list_pratos.size()) {
        resp.clear();
    }

    imprimeSolucao(resp);
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main(){
    cin >> cozinha.dias >> cozinha.pratos >> cozinha.orcamento;

    while(cozinha.dias != 0 && cozinha.pratos != 0 && cozinha.orcamento != 0){
        preencheCozinha();

        maximizaLucro();

        limpaCozinha();
        cin >> cozinha.dias >> cozinha.pratos >> cozinha.orcamento;
    }
    return 0;
}
