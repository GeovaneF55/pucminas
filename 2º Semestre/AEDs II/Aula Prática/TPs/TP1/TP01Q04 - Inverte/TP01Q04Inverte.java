class TP01Q04Inverte{
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
                String fraseInvertida = "";
                for(int i=fraseOriginal.length()-1; i>=0; i--){
                        fraseInvertida += fraseOriginal.charAt(i);
                }
                return fraseInvertida;
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
