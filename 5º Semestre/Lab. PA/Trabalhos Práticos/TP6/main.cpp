/*
 * Author: Geovane Fonseca de Sousa Santos
 * Purpose: Light Up - Acender todos os comodos da tabuleiro (matriz)
 * Language:  C++
 */
#include <iostream>
#include <climits>
#include <cstdlib>
#include <vector>
#include <algorithm>

using namespace std;

const int insere1a1[4][1][2] = {{{-1,  0}},
                                {{ 0, -1}},
                                {{ 1,  0}},
                                {{ 0,  1}}};

const int insere2a2[6][2][2] = {{{-1,  0}, { 0, -1}},
                                {{-1,  0}, { 0,  1}},
                                {{-1,  0}, { 1,  0}},
                                {{ 0, -1}, { 0,  1}},
                                {{ 0, -1}, { 1,  0}},
                                {{ 0,  1}, { 1,  0}}};

const int insere3a3[4][3][2] = {{{-1,  0}, { 0, -1}, { 1,  0}},
                                {{ 0, -1}, { 1,  0}, { 0,  1}},
                                {{ 1,  0}, { 0,  1}, {-1,  0}},
                                {{ 0,  1}, {-1,  0}, { 0, -1}}};

const int insere4a4[1][4][2] = {{{-1,  0}, { 0, -1}, { 1,  0}, { 0,  1}}};

enum CasaEstado{ barreira, apagado, lampada, aceso, bloqueado };

/*
 * type: Struct representando um par ordenado
 */
typedef struct
{
    int x;
    int y;
    int dominancia;
}Posicao;

/*
 * type: Struct representando uma as operações das casas
 */
struct maisDominante
{
    inline bool operator() (const Posicao& struct1, const Posicao& struct2)
    {
        return (struct1.dominancia > struct2.dominancia);
    }
};

/*
 * type: Struct representando um casa da matriz
 */
typedef struct
{
    CasaEstado estado;
    int qt_lamp_acesas_lin_col;
    int lampadas_adjacentes;
}Casa;

/*
 * type: Struct representando uma barreira
 */
typedef struct
{
    int tipo;
    int x, y;
}Barreira;

/*
 * type: Struct representando uma as operações das barreiras
 */
struct maiorTipo
{
    inline bool operator() (const Barreira& struct1, const Barreira& struct2)
    {
        return (struct1.tipo > struct2.tipo);
    }
};

/*
 * class: Tabuleiro
 */
class Tabuleiro {
    public :
        int tamanho_x, tamanho_y, qt_barreiras, qt_lampadas, menor_lampadas;
        vector < vector<Casa> > casas;
        vector <Barreira> barreiras;

    bool ehResultado();
    bool barreirasCorretas();
    bool ehBarreira(int, int);
    bool estaApagado(int, int);
    bool ehLampada(int, int);
    bool estaAceso(int, int);
    bool estaBloqueado(int, int);
    bool validaBarreira(int, int);
    bool validaCasaSemBarreira(int, int);
    bool posicaoValida(int, int);
    bool insereLampada(int, int);
    bool removeLampada(int, int);
    bool insereLampadasbarreira1(int, int);
    void removeLampadasbarreira1(int, int);
    bool insereLampadasbarreira2(int, int);
    void removeLampadasbarreira2(int, int);
    bool insereLampadasbarreira3(int, int);
    void removeLampadasbarreira3(int, int);
    bool insereLampadasbarreira4(int, int);
    void removeLampadasbarreira4(int, int);
    int conjuntoDominante(int, int);
    vector<Posicao> maiorCasaDominante();
    void apagar(int, int);
    void acender(int, int);
    void bloquear(int, int);
    int adjacencia(int, int);
    void buscaProfundidadeDFS();
    void buscaProfundidadeBarreiras(int);
    void buscaProfundidadeCasas();
    void imprimeTabuleiro();
    void imprimeBarreiras();

    Tabuleiro(int x, int y, int qt_bar,vector < vector<Casa> > &cs, vector<Barreira> &bar){
        tamanho_x = x;
        tamanho_y = y;
        qt_barreiras = qt_bar;
        qt_lampadas = 0;
        menor_lampadas = INT_MAX;
        casas = cs;
        barreiras = bar;
        sort(barreiras.begin(), barreiras.end(), maiorTipo());
    }
};

/*
 * Function: Verifica se o resultado foi alcançado
 * Parameters: (null)
 * Return: (bool) ehResultado
 */
bool Tabuleiro::ehResultado() {
    for(int i=0; i<tamanho_x; i++){
        for(int j=0; j<tamanho_x; j++){
            if(estaApagado(i, j)){
                return false;
            }
            if(estaBloqueado(i, j)){
                return false;
            }
            if(ehBarreira(i, j) && !validaBarreira(i, j)){
                return false;
            }
        }
    }
    return true;
}

/*
 * Function: Verifica se as barreiras estão corretas
 * Parameters: (null)
 * Return: (bool) estaoCorretas
 */
bool Tabuleiro::barreirasCorretas() {
    for(int i=0; i<tamanho_x; i++){
        for(int j=0; j<tamanho_x; j++){
            if(ehBarreira(i, j) && !validaBarreira(i, j)){
                return false;
            }
        }
    }
    return true;
}

/*
 * Function: Verifica se a casa é uma barreira
 * Parameters: (int) x, (int) y
 * Return: (bool) ehBarreira
 */
bool Tabuleiro::ehBarreira(int x, int y){
    return casas[x][y].estado == barreira;
}

/*
 * Function: Verifica se a casa está apagada
 * Parameters: (int) x, (int) y
 * Return: (bool) estaApagado
 */
bool Tabuleiro::estaApagado(int x, int y){
    return casas[x][y].estado == apagado;
}

/*
 * Function: Verifica se a casa é uma lâmpada
 * Parameters: (int) x, (int) y
 * Return: (bool) ehLampada
 */
bool Tabuleiro::ehLampada(int x, int y){
    return casas[x][y].estado == lampada;
}

/*
 * Function: Verifica se a casa está acesa
 * Parameters: (int) x, (int) y
 * Return: (bool) estaAceso
 */
bool Tabuleiro::estaAceso(int x, int y){
    return casas[x][y].estado == aceso;
}

/*
 * Function: Verifica se a casa está bloqueada
 * Parameters: (int) x, (int) y
 * Return: (bool) estaBloqueado
 */
bool Tabuleiro::estaBloqueado(int x, int y){
    return casas[x][y].estado == bloqueado;
}

/*
 * Function: Verifica se a barreira tem a quantidade necessária de lâmpadas
 * Parameters: (int) x, int y
 * Return: (bool) ehResultado
 */
bool Tabuleiro::validaBarreira(int x, int y) {
    if(!ehBarreira(x, y)) return false;
    if(casas[x][y].lampadas_adjacentes == -1) return true;

    int qt_lampadas_adjacentes = casas[x][y].lampadas_adjacentes;

    if(x-1 >= 0 && ehLampada(x-1, y)){
        qt_lampadas_adjacentes--;
    }
    if(x+1 < tamanho_x && ehLampada(x+1, y)){
        qt_lampadas_adjacentes--;
    }
    if(y-1 >= 0 && ehLampada(x, y-1)){
        qt_lampadas_adjacentes--;
    }
    if(x+1 < tamanho_y && ehLampada(x, y+1)){
        qt_lampadas_adjacentes--;
    }
    return qt_lampadas_adjacentes == 0;
}

/*
 * Function: Verifica se a posição dada não tem nehuma barreia com valor diferente de -1 ao redor
 * Parameters: (int) x, int y
 * Return: (bool) ehValida
 */
bool Tabuleiro::validaCasaSemBarreira(int x, int y){
    bool ehValida = true;
    if(x-1 >= 0 && ehBarreira(x-1, y) && adjacencia(x-1, y) != -1){
        ehValida = false;
    }
    if(x+1 < tamanho_x && ehBarreira(x+1, y) && adjacencia(x+1, y) != -1){
        ehValida = false;
    }
    if(y-1 >= 0 && ehBarreira(x, y-1) && adjacencia(x, y-1) != -1){
        ehValida = false;
    }
    if(y+1 < tamanho_y && ehBarreira(x, y+1) && adjacencia(x, y+1) != -1){
        ehValida = false;
    }
    return ehValida;
}

/*
 * Function: Verifica se a posição dada é uma posição válida no Tabuleiro
 * Parameters: (int) x, int y
 * Return: (bool) ehValida
 */
bool Tabuleiro::posicaoValida(int x, int y){
    bool ehValida = true;
    if(x<0 || x>=tamanho_x){
        ehValida = false;
    } else if(y<0 || y>=tamanho_y){
        ehValida = false;
    }
    return ehValida;
}

/*
 * Function: Insere uma lâmpada na posição dada
 * Parameters: (int) x, (int) y
 * Return: (bool) retornou
 */
bool Tabuleiro::insereLampada(int x, int y) {
    if(!posicaoValida(x, y)) return false;
    if(!estaApagado(x, y)) return false;
    casas[x][y].estado = lampada;
    qt_lampadas++;

    for(int i=x-1; i>=0; i--){
        if(ehBarreira(i, y)) break;
        acender(i, y);
        casas[i][y].qt_lamp_acesas_lin_col++;
    }

    for(int i=x+1; i<tamanho_x; i++){
        if(ehBarreira(i, y)) break;
        acender(i, y);
        casas[i][y].qt_lamp_acesas_lin_col++;
    }

    for(int i=y-1; i>=0; i--){
        if(ehBarreira(x, i)) break;
        acender(x, i);
        casas[x][i].qt_lamp_acesas_lin_col++;
    }

    for(int i=y+1; i<tamanho_y; i++){
        if(ehBarreira(x, i)) break;
        acender(x, i);
        casas[x][i].qt_lamp_acesas_lin_col++;
    }

    return true;
}

/*
 * Function: Remove uma lâmpada na posição dada
 * Parameters: (int) x, (int) y
 * Return: (bool) removeu
 */
bool Tabuleiro::removeLampada(int x, int y) {
    if(!posicaoValida(x, y)) return false;
    if(!ehLampada(x, y)) return false;
    apagar(x, y);
    qt_lampadas--;

    for(int i=x-1; i>=0; i--) {
        if(ehBarreira(i, y)) break;
        casas[i][y].qt_lamp_acesas_lin_col--;
        if (casas[i][y].qt_lamp_acesas_lin_col == 0) {
            apagar(i, y);
        }
    }

    for(int i=x+1; i<tamanho_x; i++){
        if(ehBarreira(i, y)) break;
        casas[i][y].qt_lamp_acesas_lin_col--;
        if (casas[i][y].qt_lamp_acesas_lin_col == 0) {
            apagar(i, y);
        }
    }

    for(int i=y-1; i>=0; i--){
        if(ehBarreira(x, i)) break;
        casas[x][i].qt_lamp_acesas_lin_col--;
        if (casas[x][i].qt_lamp_acesas_lin_col == 0) {
            apagar(x, i);
        }
    }

    for(int i=y+1; i<tamanho_y; i++){
        if(ehBarreira(x, i)) break;
        casas[x][i].qt_lamp_acesas_lin_col--;
        if (casas[x][i].qt_lamp_acesas_lin_col == 0) {
            apagar(x, i);
        }
    }

    return true;
}

/*
 * Function: Insere 1 lâmpada em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (bool) inseriu
 */
bool Tabuleiro::insereLampadasbarreira1(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere1a1[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere1a1[comb][0][1];
    return insereLampada(posXLamp1, posYLamp1);
}

/*
 * Function: Remove 1 lâmpada em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (void)
 */
void Tabuleiro::removeLampadasbarreira1(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere1a1[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere1a1[comb][0][1];

    removeLampada(posXLamp1, posYLamp1);
}

/*
 * Function: Insere 2 lâmpadas em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (bool) inseriu
 */
bool Tabuleiro::insereLampadasbarreira2(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere2a2[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere2a2[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere2a2[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere2a2[comb][1][1];

    bool insere1 = insereLampada(posXLamp1, posYLamp1),
         insere2 = insereLampada(posXLamp2, posYLamp2),
         insere = insere1 && insere2;

    if(!insere){
        removeLampada(posXLamp1, posYLamp1);
        removeLampada(posXLamp2, posYLamp2);
    }

    return insere;
}

/*
 * Function: Remove 2 lâmpada em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (void)
 */
void Tabuleiro::removeLampadasbarreira2(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere2a2[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere2a2[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere2a2[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere2a2[comb][1][1];

    removeLampada(posXLamp1, posYLamp1);
    removeLampada(posXLamp2, posYLamp2);
}

/*
 * Function: Insere 3 lâmpadas em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (bool) inseriu
 */
bool Tabuleiro::insereLampadasbarreira3(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere3a3[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere3a3[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere3a3[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere3a3[comb][1][1],

        posXLamp3 = barreiras[bar].x + insere3a3[comb][2][0],
        posYLamp3 = barreiras[bar].y + insere3a3[comb][2][1];

    bool insere1 = insereLampada(posXLamp1, posYLamp1),
         insere2 = insereLampada(posXLamp2, posYLamp2),
         insere3 = insereLampada(posXLamp3, posYLamp3),
         insere = insere1 && insere2 && insere3;

    if(!insere){
        removeLampada(posXLamp1, posYLamp1);
        removeLampada(posXLamp2, posYLamp2);
        removeLampada(posXLamp3, posYLamp3);
    }

    return insere;
}

/*
 * Function: Remove 3 lâmpada em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (void)
 */
void Tabuleiro::removeLampadasbarreira3(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere3a3[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere3a3[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere3a3[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere3a3[comb][1][1],

        posXLamp3 = barreiras[bar].x + insere3a3[comb][2][0],
        posYLamp3 = barreiras[bar].y + insere3a3[comb][2][1];

    removeLampada(posXLamp1, posYLamp1);
    removeLampada(posXLamp2, posYLamp2);
    removeLampada(posXLamp3, posYLamp3);
}

/*
 * Function: Insere 4 lâmpadas em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (bool) inseriu
 */
bool Tabuleiro::insereLampadasbarreira4(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere4a4[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere4a4[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere4a4[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere4a4[comb][1][1],

        posXLamp3 = barreiras[bar].x + insere4a4[comb][2][0],
        posYLamp3 = barreiras[bar].y + insere4a4[comb][2][1],

        posXLamp4 = barreiras[bar].x + insere4a4[comb][3][0],
        posYLamp4 = barreiras[bar].y + insere4a4[comb][3][1];

    bool insere1 = insereLampada(posXLamp1, posYLamp1),
            insere2 = insereLampada(posXLamp2, posYLamp2),
            insere3 = insereLampada(posXLamp3, posYLamp3),
            insere4 = insereLampada(posXLamp4, posYLamp4),
            insere = insere1 && insere2 && insere3 && insere4;

    if(!insere){
        removeLampada(posXLamp1, posYLamp1);
        removeLampada(posXLamp2, posYLamp2);
        removeLampada(posXLamp3, posYLamp3);
        removeLampada(posXLamp4, posYLamp4);
    }

    return insere;
}

/*
 * Function: Remove 4 lâmpada em torno da barreira
 * Parameters: (int) bar, (int) posLamp
 * Return: (void)
 */
void Tabuleiro::removeLampadasbarreira4(int bar, int comb) {
    int posXLamp1 = barreiras[bar].x + insere4a4[comb][0][0],
        posYLamp1 = barreiras[bar].y + insere4a4[comb][0][1],

        posXLamp2 = barreiras[bar].x + insere4a4[comb][1][0],
        posYLamp2 = barreiras[bar].y + insere4a4[comb][1][1],

        posXLamp3 = barreiras[bar].x + insere4a4[comb][2][0],
        posYLamp3 = barreiras[bar].y + insere4a4[comb][2][1],

        posXLamp4 = barreiras[bar].x + insere4a4[comb][3][0],
        posYLamp4 = barreiras[bar].y + insere4a4[comb][3][1];

    removeLampada(posXLamp1, posYLamp1);
    removeLampada(posXLamp2, posYLamp2);
    removeLampada(posXLamp3, posYLamp3);
    removeLampada(posXLamp4, posYLamp4);
}

/*
 * Function: Quantidade de casas que a casa atual domina
 * Parameters: (int) x, (int) y
 * Return: (int) qt_dom
 */
int Tabuleiro::conjuntoDominante(int x, int y) {
    int qt_dom=0;
    if(!estaApagado(x, y)) return qt_dom;
    qt_dom++;

    for(int i=x-1; i>=0; i--){
        if(ehBarreira(i, y)) break;
        if(estaApagado(i, y)) qt_dom++;
    }

    for(int i=x+1; i<tamanho_x; i++){
        if(ehBarreira(i, y)) break;
        if(estaApagado(i, y)) qt_dom++;
    }

    for(int i=y-1; i>=0; i--){
        if(ehBarreira(x, i)) break;
        if(estaApagado(x, i)) qt_dom++;
    }

    for(int i=y+1; i<tamanho_y; i++){
        if(ehBarreira(x, i)) break;
        if(estaApagado(x, i)) qt_dom++;
    }

    return qt_dom;
}

/*
 * Function: Casa que Domina um maior número de outras casas
 * Parameters: (null)
 * Return: (vector<Posicao>) casaDominante
 */
vector<Posicao> Tabuleiro::maiorCasaDominante(){
    vector <Posicao> maisDominantes;
    Posicao posicao{};

    for(int i=0; i<tamanho_x; i++){
        for(int j=0; j<tamanho_y; j++){

            if(estaApagado(i, j) && validaCasaSemBarreira(i, j)){
                posicao.x = i; posicao.y = j;
                posicao.dominancia = conjuntoDominante(i, j);

                maisDominantes.push_back(posicao);
                sort(maisDominantes.begin(), maisDominantes.end(), maisDominante());

            }

        }
    }

    if(maisDominantes.empty()){
        posicao.x = -1;
        posicao.y = -1;
        posicao.dominancia = 0;
        maisDominantes.push_back(posicao);
    }

    return maisDominantes;
}

/*
 * Function: Apaga ou bloqueia uma lâmpada dependendo da barreira que faz adjacência
 * Parameters: (int) x, (int) y
 * Return: (void)
 */
void Tabuleiro::apagar(int x, int y){
    if(x-1 >=0 && ehBarreira(x-1, y) && adjacencia(x-1, y) == 0){
        bloquear(x, y);
    }
    else if(x+1 < tamanho_x && ehBarreira(x+1, y) && adjacencia(x+1, y) == 0){
        bloquear(x, y);
    }
    else if(y-1 >=0 && ehBarreira(x, y-1) && adjacencia(x, y-1) == 0){
        bloquear(x, y);
    }
    else if(y+1 < tamanho_y && ehBarreira(x, y+1) && adjacencia(x, y+1) == 0){
        bloquear(x, y);
    } else{
        casas[x][y].estado = apagado;
    }
}

/*
 * Function: Acende uma lâmpada
 * Parameters: (int) x, (int) y
 * Return: (void)
 */
void Tabuleiro::acender(int x, int y){
    casas[x][y].estado = aceso;
}

/*
 * Function: Bloqueia uma lâmpada
 * Parameters: (int) x, (int) y
 * Return: (void)
 */
void Tabuleiro::bloquear(int x, int y){
    casas[x][y].estado = bloqueado;
}

/*
 * Function: Adjacência da barreira
 * Parameters: (int) x, (int) y
 * Return: (int) lampadas_adjacentes
 */
int Tabuleiro::adjacencia(int x, int y){
    return casas[x][y].lampadas_adjacentes;
}

/*
 * Function: Procura a menor quantidade de lâmpadas que atende o requisito do tabuleiro
 * Parameters: (int) x, (int) y
 * Return: (void)
 */
void Tabuleiro::buscaProfundidadeDFS(){
    if(qt_barreiras > 0){
        buscaProfundidadeBarreiras(0);
    } else{
        buscaProfundidadeCasas();
    }
}

/*
 * Function: Procura a menor quantidade de lâmpadas que atende o requisito do tabuleiro começando pelas barreiras
 * Parameters: (int) bar
 * Return: (void)
 */
void Tabuleiro::buscaProfundidadeBarreiras(int bar){

    if(bar >= qt_barreiras){
        if(barreirasCorretas()){
            buscaProfundidadeCasas();
        } else {
            return;
        }
    }

    if(ehResultado()){
        if(menor_lampadas > qt_lampadas){
            menor_lampadas = qt_lampadas;
        }
        return;
    }

    bool inseriu;
    if(barreiras[bar].tipo == 4){

        inseriu = insereLampadasbarreira4(bar, 1);
        buscaProfundidadeBarreiras(bar+1);
        if(inseriu){
            removeLampadasbarreira4(bar, 1);
        }

    } else if(barreiras[bar].tipo == 3){

        for(int i=0; i<4; i++){
            inseriu = insereLampadasbarreira3(bar, i);
            buscaProfundidadeBarreiras(bar+1);
            if(inseriu){
                removeLampadasbarreira3(bar, i);
            }
        }

    } else if(barreiras[bar].tipo == 2){

        for(int i=0; i<6; i++){
            inseriu = insereLampadasbarreira2(bar, i);
            buscaProfundidadeBarreiras(bar+1);

            if(inseriu){
                removeLampadasbarreira2(bar, i);
            }
        }

    } else if(barreiras[bar].tipo == 1){

        for(int i=0; i<4; i++) {
            inseriu = insereLampadasbarreira1(bar, i);
            buscaProfundidadeBarreiras(bar+1);

            if (inseriu){
                removeLampadasbarreira1(bar, i);
            }
        }

    } else {
        buscaProfundidadeCasas();
    }
}

/*
 * Function: Procura a menor quantidade de lâmpadas que atende o requisito do tabuleiro começando pelas casas de maior dominância
 * Parameters: (int) x, (int) y
 * Return: (void)
 */
void Tabuleiro::buscaProfundidadeCasas(){

    if(ehResultado()){
        if(menor_lampadas > qt_lampadas){
            menor_lampadas = qt_lampadas;
        }
        return;
    }

    vector<Posicao> maiorDominancia = maiorCasaDominante();
    int x, y;

    for (auto &i : maiorDominancia) {
        x = i.x; y = i.y;
        if(x != -1 && y != -1){
            insereLampada(x, y);
            buscaProfundidadeCasas();
            removeLampada(x, y);
        }
    }
}

/*
 * Function: Imprime o tabuleiro na tela
 * Parameters: (null)
 * Return: (void)
 */
void Tabuleiro::imprimeTabuleiro(){
    cout << "\nMATRIZ (" << tamanho_x << "x" << tamanho_y << "):\n";
    for(int i = 0; i < tamanho_x; i++) {
        for (int j = 0; j < tamanho_y; j++) {
            if(ehBarreira(i, j)){
                if(adjacencia(i, j) >= 0) cout << " ";
                cout << " " << adjacencia(i, j) << " ";
            }
            if(estaApagado(i, j)) cout << "  D ";
            if(ehLampada(i, j)) cout << "  L ";
            if(estaAceso(i, j)) cout << "  A ";
            if(estaBloqueado(i, j)) cout << "  B ";
        }
        cout << "\n";
    }
}

/*
 * Function: Imprime as Barreiras na tela
 * Parameters: (null)
 * Return: (void)
 */
void Tabuleiro::imprimeBarreiras(){
    cout << "\nBARREIRAS (" << qt_barreiras << "):\n";
    for(int i = 0; i < qt_barreiras; i++) {
        cout << "[" << barreiras[i].x << "][" << barreiras[i].y << "] = " << barreiras[i].tipo << "\n";
    }
}

/*
 * Function: Preenche o tabuleiro com o tamanho definido, com os casas vindos da entrada padrão
 * Parameters: (int) x, (int) y, (int) bar, (vector < vector<Casa> >) &cs, (vector<Barreira>) &bs
 * Return: (void)
 */
void preenche_tabuleiro(int x, int y, int qt_bar,vector < vector<Casa> > &cs, vector<Barreira> &bs){
    int barreira_x, barreira_y;
    vector <Casa> linha;
    Barreira bar{};
    Casa casa{};

    for(int i = 0; i < x; i++){
        for(int j = 0; j < y; j++){
            casa.estado = apagado;
            casa.lampadas_adjacentes = -1;
            casa.qt_lamp_acesas_lin_col = 0;
            linha.push_back(casa);
        }
        cs.push_back(linha);
        linha.clear();
    }

    for(int i = 0; i < qt_bar; i++){
        cin >> barreira_x;
        cin >> barreira_y;
        cs[barreira_x-1][barreira_y-1].estado = barreira;
        cin >> cs[barreira_x-1][barreira_y-1].lampadas_adjacentes;

        bar.x = barreira_x-1;
        bar.y = barreira_y-1;
        bar.tipo = cs[bar.x][bar.y].lampadas_adjacentes;
        bs.push_back(bar);
    }
}

/*
 * Function: Função Principal
 * Parameters: null
 * Return: (int) system_call
 */
int main () {
    int tamanho_x, tamanho_y, qt_barreiras;
    vector < vector<Casa> > casas;
    vector <Barreira> barreiras;

    cin >> tamanho_x;
    cin >> tamanho_y;

    while(tamanho_x != 0 && tamanho_y != 0){
        cin >> qt_barreiras;

        preenche_tabuleiro(tamanho_x, tamanho_y, qt_barreiras, casas, barreiras);
        Tabuleiro tabuleiro(tamanho_x, tamanho_y, qt_barreiras, casas, barreiras);

        tabuleiro.buscaProfundidadeDFS();

        if(tabuleiro.menor_lampadas != INT_MAX){
            cout << tabuleiro.menor_lampadas << "\n";
        } else {
            cout << "No solution\n";
        }

        cin >> tamanho_x;
        cin >> tamanho_y;
        casas.clear();
        barreiras.clear();
    }

    return(0);
}
