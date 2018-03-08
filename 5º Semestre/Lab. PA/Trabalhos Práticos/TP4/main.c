#include <stdio.h>

/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Knights in FEN - Problema de encontrar a menor quantidade de passos para chegar ao estado padrão
 * Language:  C
 */

char matriz_entrada[5][5];
const char matriz_padrao[5][5]={
    '1', '1', '1', '1', '1',
    '0', '1', '1', '1', '1',
    '0', '0', ' ', '1', '1',
    '0', '0', '0', '0', '1',
    '0', '0', '0', '0', '0'
};

const int mov_x[8]={ 1, 2, 2, 1,-1,-2,-2,-1},
          mov_y[8]={-2,-1, 1, 2, 2, 1,-1,-2};

int passo;

/*
 * Function: Verifica se a posição que o cavalo irá é válida
 * Parameters: (int) posx, (int) posy
 * Return: (int) ehValido
 */
int ehValido (int pos_x, int pos_y) {
    if(pos_x<5 && pos_x>=0 &&
       pos_y<5 && pos_y>=0){
        return 1;  // True -> Posição válida
    }
    return 0;  // False -> Posição inválida

}

/*
 * Function: Verifica se a posição que o cavalo irá é válida
 * Parameters: (int) x_ini, (int) y_ini, (int) x_fim, (int) y_fim
 * Return: (void)
 */
void trocaPosicao (int x_ini, int y_ini, int x_fim, int y_fim) {
    char aux = matriz_entrada[x_fim][y_fim];
    matriz_entrada[x_fim][y_fim] = matriz_entrada[x_ini][y_ini];
    matriz_entrada[x_ini][y_ini] = aux;
}

/*
 * Function: Compara a matriz de entrada no seu estado atual com a matriz padrão
 * Parameters: (void)
 * Return: (int) ehIgual
 */
int compara(){
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            if(matriz_entrada[i][j] != matriz_padrao[i][j]){
                return 0;  // False -> são diferentes
            }
        }
    }
    return 1;  // True -> são iguais
}

/*
 * Function: Verifica se a posição que o cavalo irá é válida
 * Parameters:
 * Return: (void)
 */
void buscaProfundidadeDFS (int qt_trocas, int x, int y) {
    if(qt_trocas==11){
        return;
    }

    if(compara()){
        if(passo > qt_trocas){
            passo = qt_trocas;
        }
        return;
    }

    for(int i=0; i<8; i++){
        int novo_x = x + mov_x[i];
        int novo_y = y + mov_y[i];
        if(ehValido(novo_x, novo_y)){
            trocaPosicao(x, y, novo_x, novo_y);
            buscaProfundidadeDFS(qt_trocas+1, novo_x, novo_y);
            trocaPosicao(novo_x, novo_y, x, y);
        }
    }
}

/*
 * Function: Verifica se a posição que o cavalo irá é válida
 * Parameters:
 * Return: (void)
 */
void arrumaTabuleiro() {
    int x_vazio=-1, y_vazio=-1;

    for(int i=0;i<5; i++){
        for(int j=0;j<5; j++){
            if(matriz_entrada[i][j] == ' '){
                x_vazio=i;
                y_vazio=j;
            }
        }
    }

    buscaProfundidadeDFS(0, x_vazio, y_vazio);
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main ()
{
    int qt_conjuntos;
    // Entrada
    scanf("%d", &qt_conjuntos);
    for(int c=0; c<qt_conjuntos; c++){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++) {
                scanf("%c", &matriz_entrada[i][j]);
                if(matriz_entrada[i][j] == '\n'){
                    scanf("%c", &matriz_entrada[i][j]);
                }
            }
        }

        passo = 11;
        arrumaTabuleiro();

        // Saída
        if(passo == 11){
            printf("Unsolvable in less than 11 move(s).\n");
        } else{
            printf("Solvable in %d move(s).\n", passo);
        }
    }

    return 0;
}