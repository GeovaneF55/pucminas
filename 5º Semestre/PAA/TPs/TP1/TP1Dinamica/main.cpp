/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Programação DINÂMICA: Constrói um cardápio que maximiza o lucro sob a
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
typedef struct
{
    int custo;
    int lucro;
} Prato;

/*
 * Struct: Cozinha
 */
typedef struct
{
    int dias;
    int pratos;
    int orcamento;

    vector <Prato> pratos_list;

    vector <vector < vector <double>>> matriz;
    vector <vector < vector <int>>> umDiaSeguido;
    vector <vector < vector <int>>> doisDiasSeguidos;
} Cozinha;

Cozinha cozinha{};

/*
 * Function: Preenche os dados da cozinha incluindo a inicialização da matriz de Programação Dinâmica
 * Parameters: null
 * Return: (void)
 */
void preencheCozinha()
{
    int custo, lucro;

    cozinha.pratos_list.push_back({0, 0});

    for(int i=1; i<=cozinha.pratos; i++)
    {
        cin >> custo >> lucro;
        cozinha.pratos_list.push_back({custo, lucro});
    }

    // Inicializa Matriz
    for (int i = 0; i <= cozinha.dias; i++)
    {
        cozinha.matriz.emplace_back();
        cozinha.umDiaSeguido.emplace_back();
        cozinha.doisDiasSeguidos.emplace_back();

        for (int j = 0; j <= cozinha.pratos; j++)
        {
            cozinha.matriz[i].emplace_back();
            cozinha.umDiaSeguido[i].emplace_back();
            cozinha.doisDiasSeguidos[i].emplace_back();

            for (int z = 0; z <= cozinha.orcamento; z++)
            {
                cozinha.matriz[i][j].emplace_back();
                cozinha.umDiaSeguido[i][j].emplace_back();
                cozinha.doisDiasSeguidos[i][j].emplace_back();

                if (i == 0)
                {
                    cozinha.matriz[i][j][z] = 0;
                }
                else{
                    cozinha.matriz[i][j][z] = -1;
                    cozinha.umDiaSeguido[i][j][z] = 0;
                    cozinha.doisDiasSeguidos[i][j][z] = 0;
                }
            }
        }
    }
}

/*
 * Function: Inicializa valores para uma nova cozinha
 * Parameters: null
 * Return: (void)
 */
void novaCozinha(){
    int dias, pratos, orcamento;

    cin >> dias >> pratos >> orcamento;
    cozinha.dias = dias;
    cozinha.pratos = pratos;
    cozinha.orcamento = orcamento;
}

/*
 * Function: Limpa os valores antigos da cozinha e cria uma nova cozinha
 * Parameters: null
 * Return: (void)
 */
void limpaCozinha(){

    cozinha.matriz.clear();
    cozinha.umDiaSeguido.clear();
    cozinha.doisDiasSeguidos.clear();

    novaCozinha();
}

/*
 * Function: Método de programação dinâmica que maximiza os lucros da cozinha
 * Parameters: null
 * Return: (void)
 */
void maximizaLucro()
{
    int k = cozinha.dias,
        n = cozinha.pratos,
        m = cozinha.orcamento;

    // Dynamic Programming
    for (int i = 1; i <= k; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            for (int z = 0; z <= m; z++)
            {
                for (int h = 1; h <= i; h++)
                {
                    if (z >= h * cozinha.pratos_list[j].custo)
                    {
                        for (int x = 1; x <= n; x++)
                        {
                            if (x != j && cozinha.matriz[i - h][x][z - h * cozinha.pratos_list[j].custo] != -1)
                            {
                                double lucroExtra = cozinha.pratos_list[j].lucro;

                                if (h >= 2)
                                {
                                    lucroExtra = 1.5 * cozinha.pratos_list[j].lucro;
                                }

                                if(cozinha.matriz[i-h][x][z-h*cozinha.pratos_list[j].custo] + lucroExtra > cozinha.matriz[i][j][z] ||
                                        (cozinha.matriz[i-h][x][z-h*cozinha.pratos_list[j].custo] + lucroExtra == cozinha.matriz[i][j][z] &&
                                                (cozinha.umDiaSeguido[i][j][z] == 0 || cozinha.doisDiasSeguidos[i][j][z] * cozinha.pratos_list[cozinha.umDiaSeguido[i][j][z]].custo > h*cozinha.pratos_list[k].custo)))
                                {
                                    cozinha.matriz[i][j][z] = cozinha.matriz[i - h][x][z - h * cozinha.pratos_list[j].custo] + lucroExtra;
                                    cozinha.umDiaSeguido[i][j][z] = x;
                                    cozinha.doisDiasSeguidos[i][j][z] = h;
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

/*
 * Function: Imprime o resultado da tabela dinâmica da cozinha
 * Parameters: null
 * Return: (void)
 */
void imprimeSolucao()
{
    int indiceTemporario = 1;

    for (int j = 2; j <= cozinha.pratos; j++)
    {
        if (cozinha.matriz[cozinha.dias][indiceTemporario][cozinha.orcamento] < cozinha.matriz[cozinha.dias][j][cozinha.orcamento])
        {
            indiceTemporario = j;
        }
    }

    if (cozinha.matriz[cozinha.dias][indiceTemporario][cozinha.orcamento] == -1){
        cout << "0.0" << endl;
    } else
    {
        cout << cozinha.matriz[cozinha.dias][indiceTemporario][cozinha.orcamento] << endl;
        // Find out the menu
        int tempK = cozinha.dias;
        int tempM = cozinha.orcamento;
        int tg1, tg2;

        while (tempK > 0)
        {
            tg1 = cozinha.umDiaSeguido[tempK][indiceTemporario][tempM];
            tg2 = cozinha.doisDiasSeguidos[tempK][indiceTemporario][tempM];

            for (int i = 0 ; i < tg2 ; i++)
            {
                cout << indiceTemporario;

                if (i != tg2 - 1)
                {
                    cout << " ";
                }

            }

            tempK = tempK - tg2;
            tempM = tempM - tg2 * cozinha.pratos_list[indiceTemporario].custo;
            indiceTemporario = tg1;

            if (tempK > 0){
                cout << " ";
            }
        }

    }
    cout << endl;
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main()
{

    novaCozinha();

    cout << fixed << setprecision(1);

    while(cozinha.dias != 0 && cozinha.pratos != 0 && cozinha.orcamento != 0)
    {
        preencheCozinha();
        maximizaLucro();
        imprimeSolucao();

        limpaCozinha();
    }
    return 0;
}