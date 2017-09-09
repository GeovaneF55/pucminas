public class MediaAcimaDiagonalMatriz{
   public static void main(String[] args){

      saudacao();
   
      int[][] matriz1 = new int[][]{{2,3,4},{3,4,5},{4,5,6}};
      double media1 = mediaAcimaDiagonal(matriz1);
      escreveResultado(media1);
      
      int[][] matriz2 = new int[][]{{20,20,40,40},{20,20,40,40},{20,20,40,40},{20,20,40,40}};
      double media2 = mediaAcimaDiagonal(matriz2);
      escreveResultado(media2);
      
      int[][] matriz3 = new int[][]{{2,3},{3,5}};
      double media3 = mediaAcimaDiagonal(matriz3);
      escreveResultado(media3);
   }
   
   public static void saudacao(){
      System.out.println("Cálculo da média de uma coluna de uma matriz!");
   }
   
   public static double mediaAcimaDiagonal(int[][] matriz){
      int i, j, soma=0, count=0;
      double media=0;
      if(matriz.length == matriz[0].length){
         for(i=0; i < matriz.length; i++){
            for(j=0; j < matriz[i].length; j++){
               if(i <= j){
                  soma += matriz[i][j];
                  count ++;
               }
            }
         }
         media = (double)soma/(double)count;
      }
      return media;
   }
   
   public static void escreveResultado(double media){
      System.out.println("A média é: " + media);
   }
}