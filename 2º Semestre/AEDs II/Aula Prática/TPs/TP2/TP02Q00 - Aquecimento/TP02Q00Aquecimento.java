class TP02Q00Aquecimento {
	public static boolean isMaiuscula (char c){
		return (c >= 'A' && c <= 'Z');
	}

	public static int contarLetrasMaiusculas (String s, int i){
		int resp;
		if(i >= s.length()){
			resp=0;
		}else if(isMaiuscula(s.charAt(i)) == true){
			resp = 1 + contarLetrasMaiusculas(s, i+1);
		} else{
			resp = 0 + contarLetrasMaiusculas(s, i+1);
		}

		return resp;
   	}

	public static int contarLetrasMaiusculas(String s){
		return contarLetrasMaiusculas(s, 0);
	}

	public static void main (String[] args){
      		String[] entrada = new String[1000];
      		String linha;
      		int numEntrada = 0;

      		//Leitura da entrada padrao
      		do {
         		entrada[numEntrada] = MyIO.readLine();
      		} while (entrada[numEntrada++].equals("FIM") == false);
      		numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      		//Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
      		for(int i = 0; i < numEntrada; i++){
         		MyIO.println(contarLetrasMaiusculas(entrada[i]));
      		}
   	}
}
