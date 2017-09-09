public class MinMaxMatriz{
   public static void main(String[] args){
   
      saudacao();
   
      int[][] matriz1 = new int[][]{{2,3,4},{3,4,5},{4,5,6}};
      int minMax1 = calculaMinMax(matriz1);
      escreveResultado(minMax1);
      
      int[][] matriz2 = new int[][]{{20,20,40,40},{20,20,40,40},{20,20,40,40},{20,20,40,40}};
      int minMax2 = calculaMinMax(matriz2);
      escreveResultado(minMax2);
      
      int[][] matriz3 = new int[][]{{2,3},{3,5}};
      int minMax3 = calculaMinMax(matriz3);
      escreveResultado(minMax3);
   }
   
   public static void saudacao(){
      System.out.println("Cálculo elemento MINMAX da matriz!\n");
   }
   
   public static int calculaMinMax(int[][] matriz){
      int linhaMenorElemento = retornaLinhaMenorElemento(matriz);
      int minMax = matriz[linhaMenorElemento][0];
      for(int j=0; j < matriz[linhaMenorElemento].length; j++){
         if(matriz[linhaMenorElemento][j] > minMax){
            minMax = matriz[linhaMenorElemento][j];
         }
      }
      return minMax;
   }
   
   public static int retornaLinhaMenorElemento(int[][] matriz){
      int menor = matriz[0][0];
      int linha = 0;
      for(int i=0; i < matriz.length; i++){
         for(int j=0; j < matriz.length; j++){
            if(matriz[i][j] < menor){
               menor = matriz[i][j];
               linha = i;
            }
         }
      }
      return linha;
   }
   
   public static void escreveResultado(double minMax){
      System.out.println("O MINMAX é: " + minMax);
   }
}