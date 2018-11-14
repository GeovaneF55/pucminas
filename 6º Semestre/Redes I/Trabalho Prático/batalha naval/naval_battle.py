from random import randint
import math

def print_game(show_enemy_board, enemy_board, my_board):
    column = 'A'

    print('\nTabuleiro Inimigo\t\t\tMeu Tabuleiro')

    # Imprime os tabuleiros Inimigo e do jogador
    print('  ' + '  '.join(rows()) + '\t\t' + '  ' + '  '.join(rows()))
    for (enemy_row, my_row) in zip(show_enemy_board, my_board):
        print(column + ' ' + '  '.join(enemy_row) + '\t\t' + column + ' ' + '  '.join(my_row))
        column = chr(ord(column) + 1)

    # Imprime a quantidade de navios afundados
    print('Seu inimigo tem ' + sink_ships(enemy_board) + ' afundado(s)')
    print('Você tem ' + sink_ships(my_board) + ' afundado(s)\n')

def sink_ships(board):
    global ships, num_ships
    ships_alive = pa_cells = nt_cells = cp_cells = sb_cells = 0

    # Conta quantidade de peças de cada navio
    for row in board:
        for cell in row:
            if cell == ships['pa']['peca']:
                pa_cells += 1
            elif cell == ships['nt']['peca']:
                nt_cells += 1
            elif cell == ships['cp']['peca']:
                cp_cells += 1
            elif cell == ships['sb']['peca']:
                sb_cells += 1
    
    # Verifica quantos navios não foram afundados
    ships_alive += math.ceil(pa_cells/ships['pa']['tamanho'])
    ships_alive += math.ceil(nt_cells/ships['nt']['tamanho'])
    ships_alive += math.ceil(cp_cells/ships['cp']['tamanho'])
    ships_alive += math.ceil(sb_cells/ships['sb']['tamanho'])

    return str(num_ships - ships_alive)

def rows():
    global num_ships
    row = []

    for i in range(num_ships):
        row.append(str(i+1))

    return row

def print_board(board):
    column = 'A'

    print('  ' + '  '.join(rows()))
    for row in board:
        print(column + ' ' + '  '.join(row))
        column = chr(ord(column) + 1)

def random_orientation():
    return randint(0, 1)

def random_cells(board, ship, orientation):
    while True:
        fit = False
        r = row = randint(0, len(board) - 1)
        c = col = randint(0, len(board[0]) - 1)

        for s in range(ship['tamanho']):
            # Verifica orientação do navio
            if orientation:
                r = row + s
            else:
                c = col + s

            # Verifica controle de posição do navio
            if r >= len(board) or c >= len(board[0]):
                break
            elif board[r][c] != '-':
                break
            elif s == ship['tamanho']-1:
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

    return 1

def random_ships(board):
    global ships

    num_ships = 0
    # Posiciona Portas-Aviões
    num_ships += random_cells(board, ships['pa'] ,random_orientation())

    # Posiciona Navios-Tanque
    num_ships += random_cells(board, ships['nt'] ,random_orientation())
    num_ships += random_cells(board, ships['nt'] ,random_orientation())

    # Posiciona Contratorpedeiros
    num_ships += random_cells(board, ships['cp'] ,random_orientation())
    num_ships += random_cells(board, ships['cp'] ,random_orientation())
    num_ships += random_cells(board, ships['cp'] ,random_orientation())

    # Posiciona Submarinos
    num_ships += random_cells(board, ships['sb'] ,random_orientation())
    num_ships += random_cells(board, ships['sb'] ,random_orientation())
    num_ships += random_cells(board, ships['sb'] ,random_orientation())
    num_ships +=random_cells(board, ships['sb'] ,random_orientation())

    return num_ships

def have_won(board):
    won = True
    
    for row in board:
        for cell in row:
            if cell != '-' and cell != 'x':
                won = False

    return won

def enemy_turn(my_board):
    x = y = None

    while(True):
        if x == None and y == None:
            (x, y) = random_coord(my_board)
        else:
            (x, y) = try_coord(my_board, x, y)

        res = make_move(my_board, x, y)

        # Acerto
        if res == 'hit':
            print('\nAcerto em (' + chr(ord('A') + x) + ',' + str( y+1 ) + ')')
            my_board[x][y] = 'x'
        # Erro
        elif res == 'miss':
            print('\nQue pena, (' + chr(ord('A') + x) + ',' + str( y+1 ) + ') não é um acerto.')
            my_board[x][y] = '*'
        # Tente novamentee
        elif res == 'try again':
            print("\nA coordenada não é válida. Tente Novamente!")

        if res != "try again" and res != 'hit':
            return
        
        if have_won(my_board):
            return

def my_turn(show_enemy_board, enemy_board):
    while(True):
        (x, y) = get_coord(enemy_board)
        res = make_move(enemy_board, x, y)
        
        # Acerto 
        if res == 'hit':
            print('Acerto em (' + chr(ord('A') + x) + ',' + str( y+1 ) + ')')
            enemy_board[x][y] = 'x'
            show_enemy_board[x][y] = 'x'

            print_game(show_board, off_board, my_board)

        # Erro
        elif res == 'miss':
            print('Que pena, (' + chr(ord('A') + x) + ',' + str( y+1 ) + ') não é um acerto.')
            enemy_board[x][y] = '*'
            show_enemy_board[x][y] = '*'
        # Tente novamente
        elif res == 'try again':
            print("A coordenada não é válida. Tente Novamente!")

        if res != "try again" and res != 'hit':
            return

        if have_won(enemy_board):
            return
            
def make_move(board, x, y):

	# Faz a jogada no tabuleiro e retorna o resultado
	if board[x][y] == '-':
		return "miss"
	elif board[x][y] == '*' or board[x][y] == 'x':
		return "try again"
	else:
		return "hit"

def random_coord(board):
    x = y = 0

    x = randint(0, len(board) - 1)
    y = randint(0, len(board[0]) - 1)

    return (x, y)

def try_coord(board, x, y):
    direction = randint(0, 1)
    sum_direction = randint(0, 1)

    if direction == 0:
        x += sum_direction
        y += ( 1 if sum_direction == 0 else 0 )
    else:
        x -= sum_direction
        y -= ( 1 if sum_direction == 0 else 0 )

    return (x, y)

def get_coord(board):
    x = get_column()
    y = get_row()

    x = ord(x) - ord('A')

    return (int(x), int(y)-1)

def get_column():
    x = 'A'

    while True:
        try:
            x = chr(ord(input("Escolha uma linha (A-J): ")))
            x = x.upper()

            if ord(x) < ord('A') or ord(x) > ord('J'):
               raise Exception()

        except Exception:
            print("Caractere Inválido")
            continue
        else:
            break

    return x

def get_row():
    y = 0

    while True:
        try:
            y = int(input("Escolha uma coluna (1-10): "))

            if y < 1 or y > 10:
               raise Exception()

        except ValueError:
            print("Valor Inválido.")
            continue
        except Exception:
            print("Valores fora do Limite.")
        else:
            break

    return y

def play(show_board, off_board, my_board):
    # Loop principal do jogo
    while(1):

        # Rodada do jogador
        print_game(show_board, off_board, my_board)
        my_turn(show_board, off_board)

        # Verifica se o jogador venceu
        if have_won(off_board):
            print("Você VENCEU! :)")
            return
			
        # Encerra rodada do jogador
        print_game(show_board, off_board, my_board)
        input("Para encerrar sua rodada aperte ENTER")

        # Rodada do computador
        enemy_turn(my_board)

        # Verifica se o computador venceu
        if have_won(my_board):
            print("Você PERDEU! :(")
            return
            
        # Encerra rodada inimiga
        print_game(show_board, off_board, my_board)
        input("Para encerrar a rodada do seu inimigo aperte ENTER")

if __name__ == '__main__':

    # Inicializando Tabuleiro
    board_size = 10
    num_ships = 0

    show_board = []
    off_board = []
    my_board = []

    for x in range(board_size):
        show_board.append(['-'] * board_size)
        off_board.append(['-'] * board_size)
        my_board.append(['-'] * board_size)

    # Inicializando Navios (nome_navio: [tamanho, peca])
    ships = {
        'pa': { 'nome': 'Porta Aviões', 'tamanho': 5 , 'peca': 'p' },
        'nt': { 'nome': 'Navio Tanque', 'tamanho': 4 , 'peca': 't' },
        'cp': { 'nome': 'Contratorpedeiro', 'tamanho': 3 , 'peca': 'c' },
        'sb': { 'nome': 'Submarino', 'tamanho': 2 , 'peca': 's' }
    }

    # Definindo onde os navios estarão
    num_ships = random_ships(off_board)
    num_ships = random_ships(my_board)

    # Começando o jogo e imprimindo o tabuleiro
    print("\nVamos Jogar Batalha Naval!\n")

    play(show_board, off_board, my_board)
