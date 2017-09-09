class TP01Q06Equals{
	public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i+=2){
                        // Verifica pares de Strings duas vezes: A primeira vez se elas são iguais e a segunda se elas são iguais ignorando maiúsculas e minúsculas
                        try{
				MyIO.println(resposta(igual(entrada[i], entrada[i+1])) + " " + resposta(igualIgnoraCapitalizacao(entrada[i], entrada[i+1])));
			} catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
				MyIO.println(resposta(igual(entrada[i], entrada[i])) + " " + resposta(igualIgnoraCapitalizacao(entrada[i], entrada[i])));
			}
                }
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

	// Verifica se duas strings passadas por parâmetro são iguais e retorna true em caso verdadeiro
        public static boolean igualIgnoraCapitalizacao(String fraseA, String fraseB){
                boolean ehIgual = true;
                if(fraseA.length() != fraseB.length()){
                        ehIgual = false;
                } else{
                        int i = 0;
                        while(i<fraseA.length() && ehIgual){
                                if(fraseA.charAt(i) != fraseB.charAt(i) && fraseA.charAt(i) != inverteCapitalizacao(fraseB.charAt(i))){
                                        ehIgual = false;
                                }
                                i++;
                        }
                }
                return ehIgual;
        }

	// Retorna SIM ou NAO dependendo do valor booleano passado por parâmetro
	public static String resposta(boolean valor){
		String retorno = "";
		if(valor){
			retorno = "SIM";
		} else{
			retorno = "NAO";
		}
		return retorno;
	}

	// Retorna letra minúscula caso a letra passada seja maiúscula e retorna maiúscula case seja minúscula
	public static char inverteCapitalizacao(char letra){
		if(letra >= 'A' && letra <= 'Z'){
			letra += 32;
		}

		if(letra >= 'a' && letra <= 'z'){
			letra -= 32;
		}
		return letra;
	}
}
