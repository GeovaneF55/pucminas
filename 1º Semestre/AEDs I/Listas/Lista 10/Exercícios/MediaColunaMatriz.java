public class MediaColunaMatriz{
   public static void main(String[] args){
   
      saudacao();
   
      int[][] matriz1 = new int[][]{{2,3,4},{3,4,5},{4,5,6}};
      int coluna1 = 0;
      double media1 = mediaColuna(matriz1, coluna1);
      escreveResultado(media1);
      
      int[][] matriz2 = new int[][]{{14,15,77,78},{20,20,40,40},{12,12,16,16}};
      int coluna2 = 1;
      double media2 = mediaColuna(matriz2, coluna2);
      escreveResultado(media2);
      
      int[][] matriz3 = new int[][]{{2,3},{3,5},{4,7}};
      int coluna3 = 2;
      double media3 = mediaColuna(matriz3, coluna3);
      escreveResultado(media3);
   }
   
   public static void saudacao(){
      System.out.println("Cálculo da média de uma coluna de uma matriz!\n");
   }
   
   public static double mediaColuna(int[][] matriz, int coluna){
      int j, media=0;
      for(j=0; j < matriz[coluna].length; j++){
         media += matriz[coluna][j];
      }
      return (double)media/(double)j;
   }
   
   public static void escreveResultado(double media){
      System.out.println("A média é: " + media);
   }
}