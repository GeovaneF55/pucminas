from random import randint
import math
import socket

def get_column():
    x = 'A'
    # Pega o valor da coluna do jogador
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
    # Pega o valor da linha do jogador
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

if __name__ == '__main__':

    # Recebe o ip e a porta do input para criar o endereço
    my_socket = input('Digite o ip do servidor e sua porta: ')
    ip, port = my_socket.split(" ")
    addr = (ip, int(port))

    # Cria o socket:
    # declara a família do protocolo através AF_INET
    # declara o protocolo como tcp/ip através do SOCKET_STREAM
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(addr)

    lets_play = client_socket.recv(1024).decode()
    print(lets_play)

    # Fecha a conexão
    client_socket.close()
    ############=----EOF----###########