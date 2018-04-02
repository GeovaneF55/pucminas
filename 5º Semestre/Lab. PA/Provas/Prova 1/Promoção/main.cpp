#include <iostream>
#include <queue>
#include <algorithm>

/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Prova - Promoção
 * Language:  C++
 */

using namespace std;

typedef struct
{
    int peso;
    int preco;
} Produto;

typedef struct
{
    int qt_produtos;
    vector <Produto> produtos;
    int peso_maximo;
} Lista;

Lista lista;

/*
 * Function: Inicializa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void init(){
    cin >> lista.qt_produtos;
}

/*
 * Function: Limpa variáveis
 * Parameters: (void)
 * Return: (void)
 */
void clear()
{
    init();
    lista.produtos.clear();
}

/*
 * Function: verifica se o peso do produto 1 é menor do que o peso do produto 2
 * Parameters: (Produto)p1, (Produto)p2
 * Return: (int) ehMenor
 */
int compara(Produto p1, Produto p2)
{
    return (p1.peso < p2.peso);
}

/*
 * Function: Verifica qual o valor dos produtos que a pessoa poderá carregar
 * Parameters: (void)
 * Return: (void)
 */
void resolucao()
{
    sort(lista.produtos.begin(), lista.produtos.end(), compara);
    int peso = lista.peso_maximo;
    int total_produtos=0;

    int i=0;
    while(peso > 0){
        if(lista.produtos[i].peso <= peso){
            total_produtos+=lista.produtos[i].preco;
        }
        peso-=lista.produtos[i].peso;

        i++;
    }

    cout << total_produtos << "\n";
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main()
{
    Produto produto;
    init();

    while(lista.qt_produtos != 0)
    {
        for(int i=0; i<lista.qt_produtos; i++)
        {
            cin >> produto.preco;
            cin >> produto.peso;

            lista.produtos.push_back(produto);
        }

        cin >> lista.peso_maximo;

        // Problema
        resolucao();

        clear();
    }
    return 0;
}
