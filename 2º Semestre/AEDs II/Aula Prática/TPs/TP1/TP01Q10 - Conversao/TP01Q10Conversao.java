class TP01Q10Conversao{

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
                String fraseMaiuscula = "";
		for(int i=0; i<fraseOriginal.length(); i++){
			fraseMaiuscula += charMaiuscula(fraseOriginal.charAt(i));
		}
		return fraseMaiuscula;
        }

	// Cria uma string com as letras maiúsculas sendo convertidas em minúsculas da frase passada por parâmetro
        public static String converterParaMinusculas(String fraseOriginal){
		String fraseMinuscula = "";
		for(int i=0; i<fraseOriginal.length(); i++){
			fraseMinuscula += charMinuscula(fraseOriginal.charAt(i));
                }
		return fraseMinuscula;
        }

	// Cria uma string com os espaços em branco removidos da frase passada por parâmetro
	public static String removeEspaco(String fraseOriginal){
		String fraseSemEspaco = "";
		for(int i=0; i<fraseOriginal.length(); i++){
			if(fraseOriginal.charAt(i) != ' '){
				fraseSemEspaco += fraseOriginal.charAt(i);
			}
		}
		return fraseSemEspaco;
	}

	// Cria uma string sem o caractere que será passado por parâmetro, através de uma string também passada por parâmetro
	public static String removeCaractere(String fraseOriginal, char caractere){
		String fraseSemCaractere = "";
		for(int i=0; i<fraseOriginal.length(); i++){
			if(fraseOriginal.charAt(i) != caractere){
				fraseSemCaractere += fraseOriginal.charAt(i);
			}
		}
		return fraseSemCaractere;
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
		int posicao = -1, i=0;
		while(posicao == -1 && i<especiaisMinusculos.length){
			if(caractere == especiaisMinusculos[i]){
				posicao = i;
			}
			i++;
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
		int posicao = -1, i=0;
                while(posicao == -1 && i<especiaisMaiusculos.length){
                        if(caractere == especiaisMaiusculos[i]){
                                posicao = i;
                        }
                        i++;
                }
                return posicao;
        }
}
