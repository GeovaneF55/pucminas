############## client_tuto.py################# #!/usr/bin/python 
# # Coded by: Alisson Menezes [kernelcrash] # # 
import socket

# Recebe o ip e a porta do input para criar o endereço
my_socket = input()
ip, port = my_socket.split(" ")
addr = (ip, int(port))

# Cria o socket:
# declara a família do protocolo através AF_INET
# declara o protocolo como tcp/ip através do SOCKET_STREAM
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect(addr)

# Cria a mensagem e envia para o servidor
mensagem = 'Busque o conhecimento'
client_socket.send(mensagem.encode())
print('mensagem enviada')

# Fecha a conexão
client_socket.close()
############=----EOF----###########