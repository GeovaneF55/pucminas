import java.io.*;

class Object{
	private int at1;
	private String at2;

	Object(){
		this(0, "");
	}

	Object(int at1, String at2){
		setAt1(at1);
		setAt2(at2);
	}

	public int getAt1(){
		return this.at1;
	}

	public String getAt2(){
		return this.at2;
	}

	public void setAt1(int at1){
		this.at1 = at1;
	}

	public void setAt2(String at2){
		this.at2 = at2;
	}

	public void writeObject(RandomAccessFile arq) throws IOException{
		ByteArrayOutputStream registro = new ByteArrayOutputStream();
		DataOutputStream saida = new DataOutputStream(registro);

		saida.writeInt(getAt1());
		saida.writeUTF(getAt2());

		arq.writeShort(saida.size());
		arq.write(registro.toByteArray());
	}

	public void readObject(RandomAccessFile arq) throws IOException{
		int tamanho = arq.readShort();
		byte[] ba = new byte[tamanho];
		if(arq.read(ba) != tamanho){
			throw new IOException("Inconsistência nos dados");
		}
		ByteArrayInputStream registro = new ByteArrayInputStream(ba);
		DataInputStream entrada = new DataInputStream(registro);
		
		setAt1(entrada.readInt());
		setAt2(entrada.readUTF());
	}
}
