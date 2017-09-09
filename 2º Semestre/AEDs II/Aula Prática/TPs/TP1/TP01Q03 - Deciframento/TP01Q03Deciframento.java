class TP01Q03Deciframento{
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
                String fraseOriginal = "";
                int variacao = 3;
                for(int i=0; i<fraseCifrada.length(); i++){
                        fraseOriginal += (char)((int)fraseCifrada.charAt(i) - variacao);
                }
                return fraseOriginal;
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
