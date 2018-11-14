#########=====serv_tuto.py=====########## #!/user/bin/python
# # Coded by: Alisson Menezes [kernelcrash]# #
# # servidor que recebe mensagens de aplicação client parecido com o netsend # #
import socket

# Recebe a porta do input para criar o endereço
my_socket = input()
host = ''
port = my_socket
addr = (host, int(port))

# Cria o socket:
# declara a família do protocolo através AF_INET
# declara o protocolo como tcp/ip através do SOCKET_STREAM
# Zera o tempo de espera do socket ao interromper o programa através do 'setsockopt'
serv_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serv_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
serv_socket.bind(addr)

# Define um limite de 10 conexões
serv_socket.listen(10)
print('aguardando conexao')

# Deixa o servidor escutando possíveis conexões
connection, cliente = serv_socket.accept()
print('conectado')

# Aguarda um dado enviado pela rede de até 1024 Bytes
print('aguardando mensagem de ' + cliente[0] + ':' + str(cliente[1]))
recebe = connection.recv(1024)
print('mensagem recebida: ' + recebe.decode('utf8').strip())

# Fecha a conexão
serv_socket.close()
#############=====EOF======###################