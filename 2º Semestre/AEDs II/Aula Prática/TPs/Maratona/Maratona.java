/**
 * Maratona
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 09/2016
 */

class Maratona{
	public static void main(String[] args){
		MyIO.setCharset("UTF8");
		String risadaSemConsoantes;
		for(String risada = MyIO.readLine(); risada.equals("FIM") == false; risada = MyIO.readLine()){
			if(ehPalindromo(removeConsoantes(risada))){
				MyIO.println("S");
			} else{
				MyIO.println("N");
			}
		}
	}

	/**
	* Remove consoantes de uma String dada
	* @param risada String 
	* @return risadaSemCaractere String
	*/
	public static String removeConsoantes(String risada){
		String risadaSemConsoantes = "";
		for(int i=0; i<risada.length(); i++){
			char carac = risada.charAt(i);
			if(ehVogal(carac)){
				risadaSemConsoantes += carac;
			}
		}
		return risadaSemConsoantes;
	}

	/**
        * Verifica se a String dada é um palindromo
        * @param String frase
        * @return boolean String
        */
	public static boolean ehPalindromo(String frase){
		boolean ehPalindromo = true;
		int ultimoChar = frase.length()-1;
		int i = 0;
		while(i<(double)ultimoChar/2 && ehPalindromo){
			if(frase.charAt(i) != frase.charAt(ultimoChar-i)){
				ehPalindromo = false;
			}
			i++;
		}
		return ehPalindromo; 
	}

	public static boolean ehVogal(char c) {
    		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
