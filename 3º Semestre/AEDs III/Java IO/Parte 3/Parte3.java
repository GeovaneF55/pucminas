import java.io.*;

class Parte3{
	public static void main(String[] args){
		RandomAccessFile arq;

		Object obj1 = new Object(1, "Teste 1");
		Object obj2 = new Object(2, "Teste 2");

		try{
			arq = new RandomAccessFile("dados.db", "rw");
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void escreve(Object obj, RandomAccessFile arq) throws IOException{
		obj.writeObject(arq);
	}

	public void le(Object obj, RandomAccessFile arq) throws IOException{
		obj.readObject(arq);
	}
}
