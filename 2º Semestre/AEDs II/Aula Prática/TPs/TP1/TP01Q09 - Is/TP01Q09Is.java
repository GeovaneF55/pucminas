class TP01Q09Is{
        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Retorna SIM caso seja verdadeira e NAO caso seja falso as seguintes proposições: Se a frase é composta somente por vogais; Se é composta somente somente por consoantes; Se é um número inteiro; Se é um número real
                        MyIO.println(resposta(soVogais(entrada[i])) + " " + resposta(soConsoantes(entrada[i])) + " " + resposta(ehInteiro(entrada[i])) + " " + resposta(ehReal(entrada[i])));
                }

        }

        // Retorna true se a frase passada por parâmetro for composta somente por vogais
        public static boolean soVogais(String frase){
        	boolean soVogais = true;
		int i=0;
		while(soVogais && i<frase.length()){
			if(vogalOuConsoante(frase.charAt(i)) != 'V'){	
				soVogais = false;
			}
			i++;
		}
		return soVogais;
        }

	// Retorna true se a frase passada por parâmetro for composta somente por consoantes
        public static boolean soConsoantes(String frase){
		boolean soConsoantes = true;
		int i=0;
		while(soConsoantes && i<frase.length()){
			if(vogalOuConsoante(frase.charAt(i)) != 'C'){
				soConsoantes = false;
			}
			i++;
		}
		return soConsoantes;
        }

	// Retorna true se a frase passada por parâmetro for composta por um número inteiro
	public static boolean ehInteiro(String frase){
		boolean ehInteiro = true;
		try{
			Integer.parseInt(frase);
		} catch(NumberFormatException numberFormatException){
			ehInteiro = false;
		}
		return ehInteiro;
	}
	
	// Retorna true se a frase passada por parâmetro for composta por um número real
	public static boolean ehReal(String frase){
		boolean ehReal = true;
		try{
			Double.parseDouble(trocaVirgula(frase));
		} catch(NumberFormatException numberFormatException){
			ehReal = false;
		}
		return ehReal;
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

	// Método que recebe como parâmetro um caractere e retorna (V) caso seja uma vogal, (C) caso seja uma consoante e (N) caso nenhum dos dois
        public static char vogalOuConsoante(char letra){
                char resposta = 'N';
                if(letra >= 'a' && letra <= 'z' || letra >= 'A' && letra <= 'Z'){
                        if(ehVogal(letra)){
                                resposta = 'V';
                        }else {
                                resposta = 'C';
                        }
                }
                return resposta;
        }

	// Método que retorna true caso o caractere passado por parâmetro seja uma vogal
        public static boolean ehVogal(char letra){
                boolean ehVogal = false;
                if((letra == 'a' || letra == 'A' || letra == 'e' || letra == 'E' || letra == 'i' || letra == 'I' || letra == 'o' || letra == 'O' || letra == 'u' || letra == 'U')){
                        ehVogal = true;
                }
                return ehVogal;
        }

	// Método que troca , por . para verificação de número real
	public static String trocaVirgula(String frase){
		String fraseNova = "";
		for(int i=0; i<frase.length(); i++){
			if(frase.charAt(i) == ','){
				fraseNova += ".";
			} else{
				fraseNova += frase.charAt(i);
			}
		}
		return fraseNova;
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
}
