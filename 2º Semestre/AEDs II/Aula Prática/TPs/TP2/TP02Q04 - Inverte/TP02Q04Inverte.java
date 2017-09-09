class TP02Q04Inverte{
        public static void main(String[] args){
                String[] entrada = new String[1000];
                String linha;
                int numEntrada = 0;

                do{
                        entrada[numEntrada] = MyIO.readLine();
                } while(igual(entrada[numEntrada++], "FIM") == false);
                numEntrada--;

                for(int i=0; i<numEntrada; i++){
                        // Inverte o sentido de uma frase
                        MyIO.println(inverter(entrada[i]));
                }

        }

        // Cria uma string com o sentido da frase passada por parâmetro invertido
        public static String inverter(String fraseOriginal){
		return inverter(fraseOriginal, fraseOriginal.length()-1);
	}

	public static String inverter(String fraseOriginal, int i){
                String fraseInvertida = "";
		if(i<0){
			fraseInvertida = "";
		} else{
			fraseInvertida = fraseOriginal.charAt(i) + inverter(fraseOriginal, i-1);
		}
                return fraseInvertida;
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
