/**
 * TP04Q01 - Arquivo em Java
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 09/2016
 */

import java.io.RandomAccessFile;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.util.Locale;

class TP04Q01ArquivoEmJava{
	public static void main(String[] args) throws Exception{
		String nomeArquivo = "numeros.txt";
		int n = MyIO.readInt();
 
		long tamanhoArquivo = escreveArquivo(nomeArquivo, n);
		leContrarioArquivo(nomeArquivo, tamanhoArquivo);
	}

	/**
	* Pega os valores Double da entrada padrão e coloca no arquivo passado por parâmetro retornando a posição do cursor no final
        * @param String nomeArquivo
	* @param int n
        * @return ponteiro long
        */
	public static long escreveArquivo(String nomeArquivo, int n) throws Exception{
		// Alterando o locale para pegar o Double com (.) e não (,)
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');

		DecimalFormat df = new DecimalFormat("###.###", simbolos);
		RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "rw");
		int i=0;
		long ponteiro;

		while(i < n){
			double real = Double.parseDouble(MyIO.readLine());
			arquivo.writeBytes(df.format(real) + "\n");
			i++;
		}
		ponteiro = arquivo.getFilePointer();
		arquivo.close();

		return ponteiro;
	}

	/**
	* Retorna a posição do último '\n' do arquivo passado até o limite definido
        * @param String nomeArquivo
	* @param long limite
        * @return ponteiro long
        */
	public static long posicaoUltimaQuebraLinha(String nomeArquivo, long limite) throws Exception{
		long i=0,
		     ponteiro=-1;
		RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "r");

		while(i < limite){
			int codeChar = Integer.parseInt("" + arquivo.readByte());
			char caractere = (char)codeChar;

			if(caractere == '\n'){
				ponteiro = arquivo.getFilePointer();
			}
			i+=1;
		}
		arquivo.close();

		return ponteiro;
	}

	/**
	* Lê as linhas do arquivo passado da última até a primeira
        * @param String nomeArquivo
	* @param long tamanhoArquivo
        */
	public static void leContrarioArquivo(String nomeArquivo, long tamanhoArquivo) throws Exception{
		RandomAccessFile arquivo;
		long ultimaQuebralinha = posicaoUltimaQuebraLinha(nomeArquivo, tamanhoArquivo-1);

		while(ultimaQuebralinha > 0){
			arquivo = new RandomAccessFile(nomeArquivo, "r");
			arquivo.seek(ultimaQuebralinha);
			MyIO.println(arquivo.readLine());
			arquivo.close();

			ultimaQuebralinha = posicaoUltimaQuebraLinha(nomeArquivo, ultimaQuebralinha-1);
		}
		// Lê mais uma vez para a primeira linha
		arquivo = new RandomAccessFile(nomeArquivo, "r");
		arquivo.seek(0);
		MyIO.println(arquivo.readLine());
		arquivo.close();
	}
}
