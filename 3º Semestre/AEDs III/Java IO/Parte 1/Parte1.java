import java.io.*;

class Parte1{
	public static void main(String[] args){
		String nomeArquivo = "dados.txt";
		Parte1.escritaCompleta(nomeArquivo, "|"+Parte1.completaStringFixa("teste", 30)+"|");
	}

	public static void escritaCompleta(String nomeArquivo, String texto){
		 try{
                         FileWriter fw = new FileWriter(nomeArquivo);
                         fw.write(texto);
                         fw.close();
                 } catch(IOException e){
                         e.printStackTrace();
                 }
	}

	public static void escritaRegistro(String nomeArquivo, String texto, int tamanho){
		escritaCompleta(nomeArquivo, "|"+completaStringFixa(texto, tamanho)+"|\n");
        }

	public static String leituraCompleta(String nomeArquivo){
		String leitura = "";
		try{
			FileReader fr = new FileReader(nomeArquivo);
			int c = fr.read();
			while(c!=-1){
				leitura += (char)c;
				c = fr.read();
			}
			fr.close();
			leitura += "\n";
		} catch(IOException e){
			e.printStackTrace();
		}
		return leitura;
	}

	public static String leituraRegistro(String nomeArquivo, int tamanho){
		String leitura = "";
		char[] registro = new char[tamanho];
		try{
                        FileReader fr = new FileReader(nomeArquivo);
                        int c = fr.read(registro);

                        leitura += (char)c;
                        fr.close();

                        leitura += "\n";
                } catch(IOException e){
                        e.printStackTrace();
                }
		return leitura;
	}

	public static String completaStringFixa(String string, int tamanho){
		return string + (new String(new char[tamanho - string.length()]).replace('\0', ' '));
	}
}
