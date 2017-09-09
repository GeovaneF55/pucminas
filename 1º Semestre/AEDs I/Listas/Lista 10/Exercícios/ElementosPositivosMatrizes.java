public class ElementosPositivosMatrizes{
   public static void main(String[] args){
      saudacao();
   
      double[][] A1 = new double[][]{{1,2,3},{48,25,16}};
      escreveResultado(elementosSaoPositivos(A1));
      
      double[][] A2 = new double[][]{{1,2,3},{-48,25,16,76}};
      escreveResultado(elementosSaoPositivos(A2));
      
      double[][] A3 = new double[][]{{-13,2},{-25,16},{45,6},{23,89},{-12,65}};
      escreveResultado(elementosSaoPositivos(A3));
      
      double[][] A4 = new double[][]{{13,2},{25,16},{45,6},{23,89},{12,65}};
      escreveResultado(elementosSaoPositivos(A4));
   }
   
   public static void escreveResultado(boolean elementosSaoPositivos){
      if(elementosSaoPositivos){
         System.out.println("Todos os elementos da matriz são positivos!");
      } else{
         System.out.println("Nem todos os elementos da matriz são positivos!");
      }
   }
   
   public static void saudacao(){
      System.out.println("Verifica se todos os elementos das matrizas são positivos");
   }
   
   public static boolean elementosSaoPositivos(double[][] A){
      boolean elementosSaoPositivos = true;
      for(int i=0; i<A.length; i++){
         for(int j=0; j<A[i].length; j++){
            if(A[i][j] <= 0){
               elementosSaoPositivos = false;
            }
         }
      }
      return elementosSaoPositivos;
   }
}