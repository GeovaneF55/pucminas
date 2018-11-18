#Externo
import socket
import struct
import json

# Local
import util

def prepare_system(server):
    msg, _ = server.recvfrom(1024)
    j_msg = json.loads(msg.decode())
    
    if j_msg['tipo'] == 'D':
        pass
    
    else:
        pass

def start_server():
    """ Inicializa o servidor e espera por conexões. Quando
    uma conexão é feita, cria uma nova thread específica
    para a nova conexão, dando início a uma nova partida.
    """

    host = util.get_address()
    port = int(input('Insira a porta para conexão: '))

    # Cria o socket do servnameor, declarando a família do protocolo
    # através do parâmetro AF_INET, bem como o protocolo UDP,
    # através do parâmetro SOCKET_DGRAM.
    server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server.bind((host, port))

    print('Servidor iniciado. Aguardando conexões...')
    print('Host: {}\t Porta: {}'.format(host, port))

    # Inicia a escuta por possíveis conexões
    while True:
        _, client = server.recvfrom(1024)
        print('{} conectado. Preparando novo sistema...'.format(client[0]))
        prepare_system(server)

    server.close()

if __name__ == '__main__':
    start_server()