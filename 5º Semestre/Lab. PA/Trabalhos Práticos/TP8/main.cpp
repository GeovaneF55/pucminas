/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Verifica se é possível distribuir camisetas para todas as pessoas
 * Language:  C++
 */

#include <iostream>
#include <vector>
#include <string>

using namespace std;

enum Tamanho { XS, S, M, L, XL, XXL};

/*
 * Typedef: Camisa
 */
typedef struct{
    Tamanho tamanho;
    bool sendoUsada;
} Camisa;

/*
 * Typedef: Voluntario
 */
typedef struct{
    Tamanho tamanho1, tamanho2;
    bool vestido;
} Voluntario;

/*
 * Typedef: Estoque Voluntarios
 */
typedef struct{
    int qt_camisas;
    int qt_voluntarios;
    vector <Voluntario> voluntarios;
    vector <Camisa> camisas;
} EstoqueVoluntarios;

EstoqueVoluntarios est_vol;

/*
 * Function: Faz a coerção da string que respresenta o tamanho da camisa
 * para seu respectivo valor do enum
 * Parameters: (const string) &tamanho
 * Return: (Camisa) tamanho
 */
Tamanho tamanhoCamisa(const string &tamanho){
    if(tamanho == "XS"){
        return XS;
    } else if(tamanho == "S"){
        return S;
    } else if(tamanho == "M"){
        return M;
    } else if(tamanho == "L"){
        return L;
    } else if(tamanho == "XL"){
        return XL;
    } else if(tamanho == "XLL"){
        return XXL;
    }
}

/*
 * Function: Limpa estoque voluntários para um novo teste
 * Parameters: null
 * Return: (void)
 */
void limpaEstoqueVoluntarios(){
    est_vol.qt_camisas = 0;
    est_vol.qt_voluntarios = 0;
    est_vol.camisas.clear();
    est_vol.voluntarios.clear();
}

/*
 * Function: Coloca uma camisa do tamanho dado como disponível
 * Parameters: (int), indice_voluntario, (Tamanho) tamanho
 * Return: (void)
 */
void disponibilizaCamisa(int indice_voluntario, Tamanho tamanho){
    int i=0;
    while(est_vol.camisas[i].tamanho != tamanho
          && est_vol.camisas[i].sendoUsada
          && i < est_vol.qt_camisas){
        i++;
    }
    if(i < est_vol.qt_camisas){
        est_vol.camisas[i].sendoUsada = false;
        est_vol.voluntarios[indice_voluntario].vestido = false;
    }
}

/*
 * Function: Verifica se camisa do tamanho fornecido disponível para o voluntário em questão está disponível
 * Parameters: (int) indice_voluntario, (Tamanho) tamanho
 * Return: (bool) haCamisa
 */
bool camisaDisponivel(int indice_voluntario, Tamanho tamanho){
    bool haCamisa = false;
    int i=0;
    while(!haCamisa && i < est_vol.qt_camisas){
        if(!est_vol.camisas[i].sendoUsada){
            if(est_vol.camisas[i].tamanho == tamanho){
                haCamisa = true;
                est_vol.camisas[i].sendoUsada = true;
                est_vol.voluntarios[indice_voluntario].vestido = true;
            }
        }
        i++;
    }
    return haCamisa;
}

/*
 * Function: Verifica se camisa do tamanho fornecido disponível para o voluntário em questão está disponível
 * Parameters: (int) indice_voluntario, (Tamanho) tamanho
 * Return: (bool) haCamisa
 */
bool todosVoluntariosVestidos(){
    bool estaoVestidos = true;
    int i=0;
    while(estaoVestidos && i < est_vol.qt_voluntarios){
        if(!est_vol.voluntarios[i].vestido){
            estaoVestidos = false;
        }
        i++;
    }
    return estaoVestidos;
}

/*
 * Function: Verifica se há alguma forma de distribuir as camisetas
 * Parameters: null
 * Return: (bool) existeManeira
 */
bool distribuiCamisetas(int indice_voluntario){
    bool existeManeira = false;
    Voluntario voluntario = est_vol.voluntarios[indice_voluntario];
    if(todosVoluntariosVestidos()){
        existeManeira = true;
    } else if (indice_voluntario >= est_vol.qt_voluntarios) {
        existeManeira = false;
    } else{
        if (camisaDisponivel(indice_voluntario, voluntario.tamanho1)){
            existeManeira = distribuiCamisetas(indice_voluntario+1);
            disponibilizaCamisa(indice_voluntario, voluntario.tamanho1);
        }
        if(!existeManeira && camisaDisponivel(indice_voluntario, voluntario.tamanho2)){
            existeManeira = distribuiCamisetas(indice_voluntario+1);
            disponibilizaCamisa(indice_voluntario, voluntario.tamanho2);
        }
    }

    return existeManeira;
}

/*
 * Function: Verifica se há alguma forma de distribuir as camisetas
 * Parameters: null
 * Return: (bool) existeManeira
 */
bool distribuiCamisetas(){
    return distribuiCamisetas(0);
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main(){

    int qt_testes;
    string tamanho;

    cin >> qt_testes;

    for(int i=0; i<qt_testes; i++){

        cin >> est_vol.qt_camisas >> est_vol.qt_voluntarios;

        for(int j=0; j<est_vol.qt_camisas; j++){
            Camisa camisa{};

            camisa.tamanho = static_cast<Tamanho>(j % 6);
            camisa.sendoUsada = false;

            est_vol.camisas.push_back(camisa);
        }

        for(int j=0; j<est_vol.qt_voluntarios; j++){
            Voluntario voluntario{};
            cin >> tamanho;
            voluntario.tamanho1 = tamanhoCamisa(tamanho);
            cin >> tamanho;
            voluntario.tamanho2 = tamanhoCamisa(tamanho);
            voluntario.vestido = false;

            est_vol.voluntarios.push_back(voluntario);
        }

        if(distribuiCamisetas()){
            cout << "YES\n";
        } else {
            cout << "NO\n";
        }

        limpaEstoqueVoluntarios();
    }
    return 0;
}