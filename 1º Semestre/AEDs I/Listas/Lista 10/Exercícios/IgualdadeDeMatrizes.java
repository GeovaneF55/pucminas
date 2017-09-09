public class IgualdadeDeMatrizes{
   public static void main(String[] args){
      saudacao();
   
      double[][] A1 = new double[][]{{1,2,3},{48,25,16}};
      double[][] B1 = new double[][]{{1,2,3},{48,25,16}};
      escreveResultado(matrizesIguais(A1, B1));
      
      double[][] A2 = new double[][]{{1,2,3},{48,25,16,76}};
      double[][] B2 = new double[][]{{14,22,53,22,15},{38,5,1,6,4}};
      escreveResultado(matrizesIguais(A2, B2));
      
      double[][] A3 = new double[][]{{13,2},{25,16},{45,6},{23,89},{12,65}};
      double[][] B3 = new double[][]{{3,22},{53,2},{43,123},{34,49}};
      escreveResultado(matrizesIguais(A3, B3));
      
      double[][] A4 = new double[][]{{13,2},{25,16},{45,6},{23,89},{12,65}};
      double[][] B4 = new double[][]{{13},{25},{45},{23},{12}};
      escreveResultado(matrizesIguais(A4, B4));
   }
   
   public static void escreveResultado(boolean matrizesIguais){
      if(matrizesIguais){
         System.out.println("As matrizes são iguais!");
      } else{
         System.out.println("As matrizes não são iguais!");
      }
   }
   
   public static void saudacao(){
      System.out.println("Verifica se duas matrizas são iguais");
   }
   
   public static boolean matrizesIguais(double[][] A, double[][] B){
      boolean matrizesIguais = true;
      if(A.length != B.length || A[0].length != B[0].length){
         matrizesIguais = false;
      } else{
         for(int i=0; i<A.length; i++){
            for(int j=0; j<A[i].length; j++){
               if(A[i][j] != B[i][j]){
                  matrizesIguais = false;
               }
            }
         }
      }
      return matrizesIguais;
   }
}