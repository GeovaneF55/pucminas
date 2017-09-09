class TP01Q01Palindromo {
	public static void main(String[] args){
		String[] entrada = new String[1000];
		String linha;
		int numEntrada = 0;

		do{
			entrada[numEntrada] = MyIO.readLine();
		} while(igual(entrada[numEntrada++], "FIM") == false);
		numEntrada--;

		for(int i=0; i<numEntrada; i++){
			// Verifica se a frase entregue pelo parâmetro é um palindromo
			if(ehPalindromo(entrada[i])){
				MyIO.println("SIM");
			} else{
				MyIO.println("NAO");
			}
		}
	}

	// Verifica se os caracteres em sentido normal correspondem aos caracteres em sentido inverso e retorna true em caso verdadeiro
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

	// Verifica se duas strings passadas por parâmetro são iguais e retorna true em caso verdadeiro
	public static boolean igual(String fraseA, String fraseB){
		boolean ehIgual = true;
		if(fraseA.length() != fraseB.length()){
			ehIgual = false;
		} else{
			int i = 0;
			while(i<fraseA.length() && ehIgual){
				if(fraseA.charAt(i) != fraseB.charAt(i)){
					ehIgual = false;
				}
				i++;
			}
		}
		return ehIgual;
	}
}
