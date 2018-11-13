from random import randint

def print_game(show_enemy_board, enemy_board, my_board):
    print('Tabuleiro Inimigo\tMeu Tabuleiro')
    for (enemy_row, my_row) in zip(show_enemy_board, my_board):
        print(' '.join(enemy_row) + '\t' + ' '.join(my_row))
    print('Seu inimigo tem ' + sink_ships(enemy_board) + ' afundado(s)')
    print('Você tem ' + sink_ships(my_board) + ' afundado(s)')

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
    ships_alive += int(pa_cells/ships['pa']['tamanho'])
    ships_alive += int(nt_cells/ships['nt']['tamanho'])
    ships_alive += int(cp_cells/ships['cp']['tamanho'])
    ships_alive += int(sb_cells/ships['sb']['tamanho'])

    return str(num_ships - ships_alive)

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
            if cell != '-' and cell != 'X':
                won = False

    return won

def enemy_turn(my_board):
    pass

def my_turn(show_enemy_board, enemy_board):
    pass

def play(show_enemy_board, enemy_board, my_board):
    # Loop principal do jogo
	while(1):

		# Rodada do jogador
		print_game(show_board, off_board, my_board)
		my_turn(show_board ,off_board)

		# Verifica se o jogador venceu
		if have_won(off_board):
			print("Você VENCEU! :)")
			break
			
		# Encerra rodada do jogador
		print_game(show_board, off_board, my_board)
		input("Para encerrar sua rodada aperte ENTER")

		# Rodada do computador
		enemy_turn(my_board)
		
		# Verifica se o computador venceu
		if have_won(my_board):
			print("Você PERDEU! :(")
			break
			
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
        'pa': { 'nome': 'Porta Aviões', 'tamanho': 5 , 'peca': 'P' },
        'nt': { 'nome': 'Navio Tanque', 'tamanho': 4 , 'peca': 'T' },
        'cp': { 'nome': 'Contratorpedeiro', 'tamanho': 3 , 'peca': 'C' },
        'sb': { 'nome': 'Submarino', 'tamanho': 2 , 'peca': 'S' }
    }

    # Definindo onde os navios estarão
    num_ships = random_ships(off_board)
    num_ships = random_ships(my_board)

    # Começando o jogo e imprimindo o tabuleiro
    print("\nVamos Jogar Batalha Naval!\n")

    play(show_board, off_board, my_board)
