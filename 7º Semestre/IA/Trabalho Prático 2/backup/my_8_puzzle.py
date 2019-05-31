from copy import deepcopy

class EightPuzzle:
    def __init__ (self, inicial, final, pai):
        self.tabuleiro = inicial
        self.tabuleiro_final = final
        self.pai = pai
        self.f = 0
        self.g = 0
        self.h = 0

    def __eq__(self, outro):
        return self.tabuleiro == outro.tabuleiro

    # Método de distância Manhattan: Utiliza a distância do incremento com o
    #       valor atual do tabuleiro para o cálculo da distância
    def manhattan(self):
        h = 0

        for i, row in enumerate(self.tabuleiro):
            for j, elem in enumerate(row):
                x, y = posicao(self.tabuleiro_final, elem)
                h += abs(x - i) + abs(y - j)

        return h

    # Método Objetivo: Verifica se o tabuleiro atual é a solução
    def objetivo(self):
        return self.tabuleiro == self.tabuleiro_final

# Método posição zero: Retorna o índice da peça vazia
def posicao(estado, elem):
    return [ ( i, row.index(elem) )
            for i, row in enumerate(estado.tabuleiro) 
            if elem in row ][0]

# Método move: Move a peça vazia de posição e cria uma árvore de estados
def move(estado, x, y, i, j):
    n_estado = deepcopy(estado)
    n_estado.tabuleiro[x][y] = n_estado.tabuleiro[i][j]
    n_estado.tabuleiro[i][j] = 0
    return EightPuzzle(n_estado.tabuleiro, estado.tabuleiro_final, estado)

# Método de movimentação do Tabuleiro: Valida a movimentação no tabuleiro
#       e tenta todas as movimentações possíveis do estado atual
def movimentos(atual):
    pos0 = posicao(atual, 0)
    x, y = pos0[0], pos0[1]

    estados = []

    if x-1 >= 0:
        estados.append(move(atual, x, y, x-1, y))
    if x+1 < 3:
        estados.append(move(atual, x, y, x+1, y)) 
    if y-1 >= 0:
        estados.append(move(atual, x, y, x, y-1)) 
    if y+1 < 3:
        estados.append(move(atual, x, y, x, y+1))
    
    return estados

# Método Largura: Aplica o método de busca Largura
def largura(inicial, limite):
    fila, visitados = [inicial], []

    while fila and len(fila) < limite:
        atual = fila.pop(0)
        if atual.objetivo():
            return atual

        if atual in visitados:
            continue
        
        visitados.append(atual)
        for proximo in movimentos(atual):
            if proximo not in visitados:
                fila.append(proximo)
    return None           

# Método Gulosa: Aplica o método de busca Gulosa
def gulosa(inicial):
    filaArvore = []
    filaArvore.append(inicial)

    return None

# Método A estrela: Aplica o método de busca A estrela
def a_estrela(inicial):
    filaArvore = []
    filaArvore.append(inicial)

    return None

# Método imprime tabuleiro
def imprime_tabuleiro(tabuleiro):
    for row in tabuleiro:
        print(row)
    print('\n')

def resultado(resultado):
    if(not resultado):
        print ("Resultado: Sem solução em tempo hábil")
    else:
        t = resultado.pai
        qt_passos = 0
        print('resultado: ')
        imprime_tabuleiro(resultado.tabuleiro)
        while t:
            qt_passos += 1
            print('passo: ', qt_passos)
            imprime_tabuleiro(t.tabuleiro)
            t = t.pai
        print ("Quantidade de Passos: " + str(qt_passos))

if __name__ == "__main__":
    final = [[0, 1, 2], [3, 4, 5], [6, 7, 8]]

    #teste = [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
    #teste = [[1, 2, 0], [3, 4, 5], [6, 7, 8]]
    teste = [[1, 4, 2], [3, 5, 8], [6, 0, 7]]
    #teste = [[2, 3, 6], [0, 1, 8], [4, 5, 7]]
    #teste = [[5, 2, 8], [4, 1, 7], [0, 3, 6]]

    inicial = EightPuzzle(teste, final, None)
    
    print("MÉTODO DE BUSCA EM LARGURA")
    resultadoL = largura(inicial, 2500)
    resultado(resultadoL)

    print("\nMÉTODO DE BUSCA GULOSA")
    resultadoG = gulosa(inicial)
    resultado(resultadoG)

    print("\nMÉTODO DE BUSCA A-ESTRELA")
    resultadoA = a_estrela(inicial)
    resultado(resultadoA)

