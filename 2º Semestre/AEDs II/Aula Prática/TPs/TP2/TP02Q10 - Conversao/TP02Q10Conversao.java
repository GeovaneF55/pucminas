class TP02Q10Conversao{

        // Variáveis auxiliares que contém um array com a lista de caracteres especiais maiúsculos e minúsculos para conversão dos mesmos
        static char[] especiaisMaiusculos = {'À','Á','Â','Ã','Ä','Å','Æ','Ç','È','É','Ê','Ë','Ì','Í','Î','Ï','Ð','Ñ','Ò','Ó','Ô','Õ','Ö','Ø','Ù','Ú','Û','Ü','Ý'};
        static char[] especiaisMinusculos = {'à','á','â','ã','ä','å','æ','ç','è','é','ê','ë','ì','í','î','ï','ð','ñ','ò','ó','ô','õ','ö','ø','ù','ú','û','ü','ý'};

        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Frase convertida em maiúsculas
                        MyIO.println(converterParaMaiusculas(entrada[i]));
                        // Frase convertida em minúsculas
                        MyIO.println(converterParaMinusculas(entrada[i]));
                        // Frase sem espaços
                        MyIO.println(removeEspaco(entrada[i]));
                        // Frase sem o caractere referente ao primeiro caractere da própria frase
                        MyIO.println(removeCaractere(entrada[i], entrada[i].charAt(0)));
                }

        }

        // Cria uma string com as letras minúsculas sendo convertidas em maiúsculas da frase passada por parâmetro
	public static String converterParaMaiusculas(String fraseOriginal){
		return converterParaMaiusculas(fraseOriginal, 0);
	}        

	public static String converterParaMaiusculas(String fraseOriginal, int i){
                String fraseMaiuscula;

		if(i>=fraseOriginal.length()){
			fraseMaiuscula = "";
		} else{
			fraseMaiuscula = charMaiuscula(fraseOriginal.charAt(i)) + converterParaMaiusculas(fraseOriginal, i+1);
		}
                return fraseMaiuscula;
        }

	// Cria uma string com as letras maiúsculas sendo convertidas em minúsculas da frase passada por parâmetro
        public static String converterParaMinusculas(String fraseOriginal){
		return converterParaMinusculas(fraseOriginal, 0);
	}

	public static String converterParaMinusculas(String fraseOriginal, int i){
                String fraseMinuscula;

		if(i>=fraseOriginal.length()){
			fraseMinuscula = "";
		} else{
			fraseMinuscula = charMinuscula(fraseOriginal.charAt(i)) + converterParaMinusculas(fraseOriginal, i+1);
		}
                return fraseMinuscula;
        }

        // Cria uma string com os espaços em branco removidos da frase passada por parâmetro
        public static String removeEspaco(String fraseOriginal){
		return removeEspaco(fraseOriginal, 0);
	}

	public static String removeEspaco(String fraseOriginal, int i){
                String fraseSemEspaco;

		if(i>=fraseOriginal.length()){
			fraseSemEspaco = "";
		} else if(fraseOriginal.charAt(i) != ' '){
			fraseSemEspaco = fraseOriginal.charAt(i) + removeEspaco(fraseOriginal, i+1);
		} else{
			fraseSemEspaco = removeEspaco(fraseOriginal, i+1);
		}

                return fraseSemEspaco;
        }

        // Cria uma string sem o caractere que será passado por parâmetro, através de uma string também passada por parâmetro
        public static String removeCaractere(String fraseOriginal, char caractere){
		return removeCaractere(fraseOriginal, caractere, 0);
	}

	public static String removeCaractere(String fraseOriginal, char caractere, int i){
                String fraseSemCaractere;

		if(i>=fraseOriginal.length()){
			fraseSemCaractere = "";
		} else if(fraseOriginal.charAt(i) != caractere){
			fraseSemCaractere = fraseOriginal.charAt(i) + removeCaractere(fraseOriginal, caractere, i+1);
		} else{
			fraseSemCaractere = removeCaractere(fraseOriginal, caractere, i+1);
		}
                return fraseSemCaractere;
        }

        // Converte o caractere passado por parâmetro para maiúscula
        public static char charMaiuscula(char letra){
                int posCharEspecial = posicaoCharEspecialMinuscula(letra);
                if(letra >= 'a' && letra <= 'z'){
                        letra -= 32;
                } else if(posCharEspecial != -1){
                        letra = especiaisMaiusculos[posCharEspecial];
                }
                return letra;
        }

        // Retorna a posição do caractere especial minúsculo, passado por parâmetro, contido no array de caracteres especiais
        public static int posicaoCharEspecialMinuscula(char caractere){
		return posicaoCharEspecialMinuscula(caractere, 0);
	}

	public static int posicaoCharEspecialMinuscula(char caractere, int i){
                int posicao;
		
		if(i>=especiaisMinusculos.length){
			posicao = -1;
		} else if(caractere == especiaisMinusculos[i]){
			posicao = i;
		} else{
			posicao = posicaoCharEspecialMinuscula(caractere, i+1);
		}
                return posicao;
        }

	// Converte o caractere passado por parâmetro para minúscula
        public static char charMinuscula(char letra){
                int posCharEspecial = posicaoCharEspecialMaiuscula(letra);
                if(letra >= 'A' && letra <= 'Z'){
                        letra += 32;
                } else if(posCharEspecial != -1){
                        letra = especiaisMinusculos[posCharEspecial];
                }
                return letra;
        }

        // Retorna a posição do caractere especial minúsculo, passado por parâmetro, contido no array de caracteres especiais
        public static int posicaoCharEspecialMaiuscula(char caractere){
		return posicaoCharEspecialMaiuscula(caractere, 0);
	}

	public static int posicaoCharEspecialMaiuscula(char caractere, int i){
                int posicao = -1;

		if(i>=especiaisMaiusculos.length){
			posicao = -1;
		} else if(caractere == especiaisMaiusculos[i]){
			posicao = i;
		} else{
			posicao = posicaoCharEspecialMaiuscula(caractere, i+1);
		}
                return posicao;
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
