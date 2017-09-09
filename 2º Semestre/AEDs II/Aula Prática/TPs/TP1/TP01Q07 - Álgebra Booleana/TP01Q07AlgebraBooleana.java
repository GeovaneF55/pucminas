class TP01Q07AlgebraBooleana{
	static String[] validacoes = {"not(0)", "not(1)",
				      "or(0,0)", "or(0,1)", "or(1,0)", "or(1,1)",
				      "and(0,0)", "and(0,1)", "and(1,0)", "and(1,1)",
				      "or(0,0,0)", "or(0,0,1)", "or(0,1,0)", "or(0,1,1)",
				      "or(1,0,0)", "or(1,0,1)", "or(1,1,0)", "or(1,1,1)",
				      "and(0,0,0)", "and(0,0,1)", "and(0,1,0)", "and(0,1,1)",
                                      "and(1,0,0)", "and(1,0,1)", "and(1,1,0)", "and(1,1,1)",
				      "or(0,0,0,0)", "or(0,0,0,1)", "or(0,0,1,0)", "or(0,0,1,1)",
				      "or(0,1,0,0)", "or(0,1,0,1)", "or(0,1,1,0)", "or(0,1,1,1)",
				      "or(1,0,0,0)", "or(1,0,0,1)", "or(1,0,1,0)", "or(1,0,1,1)",
				      "or(1,1,0,0)", "or(1,1,0,1)", "or(1,1,1,0)", "or(1,1,1,1)",
				      "and(0,0,0,0)", "and(0,0,0,1)", "and(0,0,1,0)", "and(0,0,1,1)",
				      "and(0,1,0,0)", "and(0,1,0,1)", "and(0,1,1,0)", "and(0,1,1,1)",
				      "and(1,0,0,0)", "and(1,0,0,1)", "and(1,0,1,0)", "and(1,0,1,1)",
				      "and(1,1,0,0)", "and(1,1,0,1)", "and(1,1,1,0)", "and(1,1,1,1)"};
	static String[] saidas = {"1", "0",
			       "0", "1", "1", "1",
			       "0", "0", "0", "1",
			       "0", "1", "1", "1",
			       "1", "1", "1", "1",
			       "0", "0", "0", "0",
                               "0", "0", "0", "1",
			       "0", "1", "1", "1",
			       "1", "1", "1", "1",
			       "1", "1", "1", "1",
			       "1", "1", "1", "1",
			       "0", "0", "0", "0",
			       "0", "0", "0", "0",
			       "0", "0", "0", "0",
			       "0", "0", "0", "1"};

        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "0") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
			String fraseSemEspaco = removeEspaco(entrada[i]);
			int[] valoresEntradas = entradas(fraseSemEspaco);
			String fraseExpressao = expressao(fraseSemEspaco);
			MyIO.println(eval(fraseExpressao, valoresEntradas));
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

	// Retorna um array com os valores de entradas que serão colocadas na expressão
	public static int[] entradas(String fraseSemEspaco){
		int qtEntradas = Character.getNumericValue(fraseSemEspaco.charAt(0));
		int[] entradas = new int[qtEntradas];
		String fraseSemExpressao = removeExpressao(fraseSemEspaco, qtEntradas);

		for(int i=0; i<fraseSemExpressao.length(); i++){
			int booleano = Character.getNumericValue(fraseSemExpressao.charAt(i));
			if(booleano == 0 || booleano == 1){
				entradas[i] = booleano;
			}
		}
		return entradas;
	}

	// Retorna a expressão
	public static String expressao(String fraseSemEspaco){
		String fraseExpressao = "";
		for(int i=0; i<fraseSemEspaco.length(); i++){
			if(!(fraseSemEspaco.charAt(i) >= '0' && fraseSemEspaco.charAt(i) <= '9')){
				fraseExpressao += fraseSemEspaco.charAt(i);
			}
		}
		return fraseExpressao;
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

	// Cria uma string com os valores de entrada 
	public static String removeExpressao(String fraseSemEspaco, int qtEntradas){
		String fraseSemExpressao = "";
		for(int i=1; i<=qtEntradas; i++){
			fraseSemExpressao += fraseSemEspaco.charAt(i);
		}
		return fraseSemExpressao;
	}

	// Válida a expressão booleana com as entradas passadas por parâmetro
	public static String eval(String expressao, int[] entradas){
		expressao = colocaValores(expressao, entradas);
		expressao = executaBooleanos(expressao);
		return expressao;
	}

	// Coloca os valores de entrada em seus respectivos lugares
	public static String colocaValores(String expressao, int[] entradas){
		String fraseComValores = "";
		for(int i=0; i<expressao.length(); i++){
			int j=0, booleano=-1;
			while(j<entradas.length){
				if(expressao.charAt(i) == ('A'+j)){
					booleano = entradas[j];
				}
				j++;
			}
			if(booleano != -1){
				fraseComValores += booleano;
			} else{
				fraseComValores += expressao.charAt(i);
			}
		}
		return fraseComValores;
	}

	// Resolve as equações booleanas com seus valores já inseridos
	public static String executaBooleanos(String expressao){
		int indiceFrase, indicePadrao=0;
		while(!igual(expressao, "0") && !igual(expressao, "1")){
			indiceFrase = indiceFrase(expressao, validacoes[indicePadrao]);
			if(indiceFrase != -1){
				expressao = trocaBooleano(expressao, indiceFrase, indicePadrao);
			}
			if(indicePadrao < validacoes.length-1){
				indicePadrao++;
			} else{
				indicePadrao=0;
			}
		}
		return expressao;
	}

	// retorna o índice do padrão booleano da frase
	public static int indiceFrase(String frase, String padrao){
		int indice = -1;
		if(padrao.length() <= frase.length()){
			int i=0;
			while(indice == -1 && i < frase.length()){
				if(frase.charAt(i) == padrao.charAt(0)){
					if(padraoPertence(frase, padrao, i)){
						indice = i;
					}
				}
				i++;
			}
		}
		return indice;
	}

	// Retorna true se há o padrão booleano na frase
	public static boolean padraoPertence(String frase, String padrao, int i){
		boolean padraoPertence = true;
		int j=0;
		while(padraoPertence && i < frase.length() && j < padrao.length()){
			if(frase.charAt(i) != padrao.charAt(j)){
				padraoPertence = false;
			}
			i++; j++;
		}
		return padraoPertence;
	}

	// Troca uma string booleana no padrão da variável validacoes pelo seu respectivo valor booleano, no indice dado
	public static String trocaBooleano(String expressao, int indiceFrase, int indicePadrao){
		String novaExpressao="";
		for(int i=0; i<expressao.length(); i++){
			if(i == indiceFrase){
				novaExpressao += saidas[indicePadrao];
				i = (indiceFrase + validacoes[indicePadrao].length()-1);
			} else{
				novaExpressao += expressao.charAt(i);
			}
		}
		return novaExpressao;
	}
}
