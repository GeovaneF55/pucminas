class TP02Q09Is{
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
		return soVogais(frase, 0);
	}        

	public static boolean soVogais(String frase, int i){
                boolean soVogais = true;

		if(i>=frase.length()){
			soVogais = true;
		} else if(vogalOuConsoante(frase.charAt(i)) != 'V'){
			soVogais = false;
		} else{
			soVogais = soVogais(frase, i+1);
		}
                return soVogais;
        }

	// Retorna true se a frase passada por parâmetro for composta somente por consoantes
        public static boolean soConsoantes(String frase){
		return soConsoantes(frase, 0);
	}

	public static boolean soConsoantes(String frase, int i){
                boolean soConsoantes = true;

		if(i>=frase.length()){
			soConsoantes = true;
		} else if(vogalOuConsoante(frase.charAt(i)) != 'C'){
			soConsoantes = false;
		} else{
			soConsoantes = soConsoantes(frase, i+1);
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
		return trocaVirgula(frase, 0);
	}

	public static String trocaVirgula(String frase, int i){
                String fraseNova;
		if(i>=frase.length()){
			fraseNova = "";
		} else if(frase.charAt(i) == ','){
			fraseNova = "." + trocaVirgula(frase, i+1);
		} else {
			fraseNova = frase.charAt(i) + trocaVirgula(frase, i+1);
		}
                return fraseNova;
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

