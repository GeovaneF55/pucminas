class TP02Q01Palindromo {
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
		return ehPalindromo(frase, 0);
	}

	public static boolean ehPalindromo(String frase, int i){
                boolean ehPalindromo = true;
                int ultimoChar = frase.length()-1;
                
		if(i>=(double)ultimoChar/2){
			ehPalindromo = true;
		} else if(frase.charAt(i) != frase.charAt(ultimoChar-i)){
			ehPalindromo = false;
		} else{
			ehPalindromo = ehPalindromo(frase, i+1);
		}
                return ehPalindromo;
        }

	// Verifica se duas strings passadas por parâmetro são iguais e retorna true em caso verdadeiro
	public static boolean igual(String fraseA, String fraseB){
		return igual(fraseA, fraseB, 0);
	}        

	public static boolean igual(String fraseA, String fraseB, int i){
                boolean ehIgual = true;
                if(fraseA.length() != fraseB.length()){
                        ehIgual = false;
                } else if(i<fraseA.length()){
			if(fraseA.charAt(i) != fraseB.charAt(i)){
				ehIgual = false;
			} else{
				ehIgual = igual(fraseA, fraseB, i+1);
			}
		} else{
			ehIgual = true;
		}
                return ehIgual;
        }
}
                    
