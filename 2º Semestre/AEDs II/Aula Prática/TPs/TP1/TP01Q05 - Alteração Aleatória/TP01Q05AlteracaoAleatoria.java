import java.util.Random;

class TP01Q05AlteracaoAleatoria{

        public static void main(String[] args){
		Random gerador = new Random();
		gerador.setSeed(4);
		
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Pega duas letras aleatórias de uma frase passada por parâmetro e altera a incidência de uma pela outra
                        MyIO.println(alterarAleatoriamente(entrada[i], gerador));
                }

        }

        // Retorna a frase passada pelo parâmetro com um caractere sortido alterado por outro caractere sortido 
        public static String alterarAleatoriamente(String fraseOriginal, Random gerador){
		String fraseAlterada = "";
		char letra = sorteiaLetra(gerador),
		     trocada = sorteiaLetra(gerador);
                for(int i=0; i<fraseOriginal.length(); i++){
			if(fraseOriginal.charAt(i) == letra){
                        	fraseAlterada += trocada;
			} else{
				fraseAlterada += fraseOriginal.charAt(i);
			}
                }
                return fraseAlterada;
        }

	// Sorteia uma letra aleatóriamente entre 'a' e 'z'
	public static char sorteiaLetra(Random gerador){
		return (char)('a' + (Math.abs(gerador.nextInt())) % 26);
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
