import java.util.Random;

class TP02Q05AlteracaoAleatoria{

	static Random gerador = new Random(4);

        public static void main(String[] args){
                //Random gerador = new Random();
                //gerador.setSeed(4);

                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Pega duas letras aleatórias de uma frase passada por parâmetro e altera a incidência de uma pela outra
                        MyIO.println(alterarAleatoriamente(entrada[i]));
                }

        }

        // Retorna a frase passada pelo parâmetro com um caractere sortido alterado por outro caractere sortido 
        public static String alterarAleatoriamente(String fraseOriginal){
		char letra = sorteiaLetra(),
		     trocada = sorteiaLetra();
		return alterarAleatoriamente(fraseOriginal, letra, trocada, 0);
	}

	public static String alterarAleatoriamente(String fraseOriginal, char letra, char trocada, int i){
                String fraseAlterada;
		
		if(i>=fraseOriginal.length()){
			fraseAlterada = "";	
		} else if(fraseOriginal.charAt(i) == letra){
			fraseAlterada = trocada + alterarAleatoriamente(fraseOriginal, letra, trocada, i+1);
		} else{
			fraseAlterada = fraseOriginal.charAt(i) + alterarAleatoriamente(fraseOriginal, letra, trocada, i+1);
		}
                return fraseAlterada;
        }

        // Sorteia uma letra aleatóriamente entre 'a' e 'z'
        public static char sorteiaLetra(){
                return (char)('a' + (Math.abs(gerador.nextInt())) % 26);
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
