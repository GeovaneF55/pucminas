class TP02Q08Contar{
        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Conta o número de ocorrências do primeiro caractere da linha na mesma, o número de letras, o número de caracteres não letra e o número de caracteres doidões 
                        MyIO.println(contaCaractere(entrada[i], entrada[i].charAt(0)) + " " + quantidadeLetras(entrada[i]) + " " + quantidadeNaoLetras(entrada[i]) + " " + quantidadeLetrasDoidonas(entrada[i]));
                }

        }

        // Método que conta a quantidade de vezes que o caractere passado por parâmetro aparece na String também passada por parâmetro
        public static int contaCaractere(String frase, char caractere){
		return contaCaractere(frase, caractere, 0);
	}

	public static int contaCaractere(String frase, char caractere, int i){
                int contador;
		
		if(i>=frase.length()){
			contador = 0;
		} else if(frase.charAt(i) == caractere){
			contador = 1 + contaCaractere(frase, caractere, i+1);
		} else{
			contador = 0 + contaCaractere(frase, caractere, i+1);
		}

                return contador;
        }

        // Método que conta a quantidade de letras contidas na frase passada por parâmetro
        public static int quantidadeLetras(String frase){
		return quantidadeLetras(frase, 0);
	}

	public static int quantidadeLetras(String frase, int i){
                int contador;

		if(i>=frase.length()){
			contador = 0;
		} else if(frase.charAt(i) >= 'A' && frase.charAt(i) <= 'Z' || frase.charAt(i) >= 'a' && frase.charAt(i) <= 'z'){
			contador = 1 + quantidadeLetras(frase, i+1);
		} else{
			contador = 0 + quantidadeLetras(frase, i+1);
		}
                return contador;
        }

        // Método que conta a quantidade de letras diferentes, ou seja, contando cada letra somente uma vez, contidas na frase passada por parâmetro
        public static int quantidadeNaoLetras(String frase){
		return quantidadeNaoLetras(frase, 0);
	}

	public static int quantidadeNaoLetras(String frase, int i){
                int contador;

		if(i>=frase.length()){
			contador = 0;
		} else if(!(frase.charAt(i) >= 'A' && frase.charAt(i) <= 'Z') && !(frase.charAt(i) >= 'a' && frase.charAt(i) <= 'z')){
			contador = 1 + quantidadeNaoLetras(frase, i+1);
		} else{
			contador = 0 + quantidadeNaoLetras(frase, i+1);
		}
                return contador;
        }

        // Método que conta a quantidade de letras doidonas numa frase passada por parâmetro
        public static int quantidadeLetrasDoidonas(String frase){
		return quantidadeLetrasDoidonas(frase, 0);
	}

	public static int quantidadeLetrasDoidonas(String frase, int i){
                int contador;

		if(i>=frase.length()){
			contador = 0;
		} else if(ehCaractereDoidao(frase.charAt(i))){
			contador = 1 + quantidadeLetrasDoidonas(frase, i+1);
		} else{
			contador = 0 + quantidadeLetrasDoidonas(frase, i+1);
		}
                return contador;
        }

        // Retorna true caso o caractere passado por parâmetro seja (doidão) e false caso não seja. Um caractere doidão é representado por um conjunto de regras definidas no TP01Q08-Contar
        public static boolean ehCaractereDoidao(char letra){
                boolean ehDoidao = false;
                char vogalOuConsoante = vogalOuConsoante(letra);

                if(((letra >= '0' && letra <= '9') && letra % 2 == 1) ||
                    (vogalOuConsoante == 'C' && letra % 5 == 0 && letra % 2 != 0) ||
                    (vogalOuConsoante == 'V' && letra % 5 != 0 && letra % 8 != 0)){
                        ehDoidao = true;
                }
                return ehDoidao;
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
