import java.io.*;

class Parte2{
	public static void main(String[] args){
		String nomeArquivo = "dados.txt";
		Parte2.escritaCompleta(nomeArquivo, "|"+Parte2.completaStringFixa("teste", 30)+"|");
	}

	public static void escritaCompleta(String nomeArquivo, String texto){
		 try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
			bw.write(texto);
			bw.flush();
			bw.close();
                 } catch(IOException e){
                         e.printStackTrace();
                 }
	}

	 public static void escritaRegistro(String nomeArquivo, String texto, int tamanho){
                 try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
                        bw.write("|"+completaStringFixa(texto, tamanho)+"|");
			bw.newLine();
                        bw.flush();
                        bw.close();
                 } catch(IOException e){
                         e.printStackTrace();
                 }
        }

	public static String leituraCompleta(String nomeArquivo){
		String leitura = "", lido;
		try{
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
			lido = br.readLine();
			while(lido != null){
				leitura += lido;
				lido = br.readLine();
			}
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		return leitura;
	}

	public static String leituraRegistro(String nomeArquivo, int tamanho){
		String leitura = "";
		char lido;
		try{
                         BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
                        int i = 0;
			lido = br.read();
                        while(i < tamanho && lido != null){
                                leitura += lido;
                                lido = br.read();
				i++;
                        }
                        br.close();
                } catch(IOException e){
                        e.printStackTrace();
                }
		return leitura;
	}

	public static String completaStringFixa(String string, int tamanho){
		return string + (new String(new char[tamanho - string.length()]).replace('\0', ' '));
	}
}
