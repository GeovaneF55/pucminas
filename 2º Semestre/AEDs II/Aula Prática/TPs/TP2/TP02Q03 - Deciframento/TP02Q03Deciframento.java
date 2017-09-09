class TP02Q03Deciframento{
        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Decifra uma frase passada por parâmetro utilizando a cifra de césar
                        MyIO.println(decifrar(entrada[i]));
                }

        }

        // Altera letra por letra a frase percorrendo-a com um laço for
	public static String decifrar(String fraseCifrada){
		return decifrar(fraseCifrada, 0);
	}

	public static String decifrar(String fraseCifrada, int i){
                String fraseOriginal = "";
                int variacao = 3;

                if(i>=fraseCifrada.length()){
                        fraseOriginal = "";
                } else{
                        fraseOriginal = ((char)((int)fraseCifrada.charAt(i) - variacao)) + decifrar(fraseCifrada, i+1);
                }
                return fraseOriginal;
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
