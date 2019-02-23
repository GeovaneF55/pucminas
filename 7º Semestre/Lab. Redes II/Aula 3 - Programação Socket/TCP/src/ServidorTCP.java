import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;

public class ServidorTCP
{
	//M�TODO PRINCIPAL DA CLASSE
	public static void main (String args[])
	{
		try
		{
			int PortaServidor = 7000;
			
			//INICIALIZA UM SERVI�O DE ESCUTA POR CONEX�ES NA PORTA ESPECIFICADA
			System.out.println(" -S- Aguardando conexao (P:"+PortaServidor+")...");
			ServerSocket socktServ = new ServerSocket(PortaServidor);
			
			//ESPERA (BLOQUEADO) POR CONEX�ES			
			Socket conSer = socktServ.accept(); //RECEBE CONEX�O E CRIA UM NOVO CANAL (p) NO SENTIDO CONTR�RIO (SERVIDOR -> CLIENTE)
			System.out.println(" -S- Conectado ao cliente ->" + conSer.toString());
		
			//CRIA UM PACOTE DE ENTRADA PARA RECEBER MENSAGENS, ASSOCIADO � CONEX�O (p)
			ObjectInputStream sServIn = new ObjectInputStream(conSer.getInputStream());
			System.out.println(" -S- Recebendo mensagem...");
			Object msgIn = sServIn.readObject(); //ESPERA (BLOQUEADO) POR UM PACOTE
			System.out.println(" -S- Recebido: " + msgIn.toString());
			
			//CRIA UM PACOTE DE SA�DA PARA ENVIAR MENSAGENS, ASSOCIANDO-O � CONEX�O (p)
			ObjectOutputStream sSerOut = new ObjectOutputStream(conSer.getOutputStream());
			sSerOut.writeObject("RETORNO " + msgIn.toString() + " - TCP"); //ESCREVE NO PACOTE
			System.out.println(" -S- Enviando mensagem resposta...");
			sSerOut.flush(); //ENVIA O PACOTE
		
			//FINALIZA A CONEX�O
			socktServ.close();
			conSer.close();
			System.out.println(" -S- Conexao finalizada...");

		}
		catch(Exception e) //SE OCORRER ALGUMA EXCESS�O, ENT�O DEVE SER TRATADA (AMIGAVELMENTE)
		{
			System.out.println(" -S- O seguinte problema ocorreu : \n" + e.toString());
		}
	}
}