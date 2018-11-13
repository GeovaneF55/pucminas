from random import randint

def print_game(enemy_board, my_board):
    print('Tabuleiro Inimigo\tMeu Tabuleiro')
    for (enemy_row, my_row) in zip(enemy_board, my_board):
        print(' '.join(enemy_row) + '\t' + ' '.join(my_row))

def print_board(board):
    for row in board:
        print(' '.join(row))

def random_orientation():
    return randint(0, 1)

def random_cells(board, ship, orientation):
    while True:
        fit = False
        r = row = randint(0, len(board) - 1)
        c = col = randint(0, len(board[0]) - 1)

        for s in range(ship['tamanho']):
            # Verifica orientação do navio
            if(orientation):
                r = row + s
            else:
                c = col + s

            # Verifica controle de posição do navio
            if(r >= len(board) or c >= len(board[0])):
                break
            elif(board[r][c] != '-'):
                break
            elif(s == ship['tamanho']-1):
                fit = True

        # Se o navio couber na posição dada, ele é posicionado nela
        if fit:
            r = row
            c = col
            for s in range(ship['tamanho']):
                if(orientation):
                    r = row + s
                else:
                    c = col + s
                board[r][c] = ship['peca']
            break

def random_ships(board, ships):
    # Posiciona Portas-Aviões
    random_cells(board, ships['pa'] ,random_orientation())

    # Posiciona Navios-Tanque
    random_cells(board, ships['nt'] ,random_orientation())
    random_cells(board, ships['nt'] ,random_orientation())

    # Posiciona Contratorpedeiros
    random_cells(board, ships['cp'] ,random_orientation())
    random_cells(board, ships['cp'] ,random_orientation())
    random_cells(board, ships['cp'] ,random_orientation())

    # Posiciona Submarinos
    random_cells(board, ships['sb'] ,random_orientation())
    random_cells(board, ships['sb'] ,random_orientation())
    random_cells(board, ships['sb'] ,random_orientation())
    random_cells(board, ships['sb'] ,random_orientation())

def have_won(board):
    won = True
    for row in board:
        for cell in row:
            if cell != '-' and cell != 'X':
                won = False
    
    return won

def enemy_turn(board):
    pass

def my_turn(board):
    pass

if __name__ == '__main__':

    # Inicializando Tabuleiro
    board_size = 10

    show_board = []
    off_board = []
    my_board = []

    for x in range(board_size):
        show_board.append(['-'] * board_size)
        off_board.append(['-'] * board_size)
        my_board.append(['-'] * board_size)

    # Inicializando Navios (nome_navio: [tamanho, peca])
    ships = {
        'pa': { 'nome': 'Porta Aviões', 'tamanho': 5 , 'peca': 'P'},
        'nt': { 'nome': 'Navio Tanque', 'tamanho': 4 , 'peca': 'T'},
        'cp': { 'nome': 'Contratorpedeiro', 'tamanho': 3 , 'peca': 'C'},
        'sb': { 'nome': 'Submarino', 'tamanho': 2 , 'peca': 'S'}
    }

    # Definindo onde os navios estarão
    random_ships(off_board, ships)
    random_ships(my_board, ships)

    # Começando o jogo e imprimindo o tabuleiro
    print("\nVamos Jogar Batalha Naval!\n")
    print_game(show_board, my_board)

