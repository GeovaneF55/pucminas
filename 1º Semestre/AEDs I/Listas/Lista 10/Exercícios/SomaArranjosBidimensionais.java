public class SomaArranjosBidimensionais{
   public static void main(String[] args){
      saudacao();
   
      double[][] A1 = new double[][]{{1,2,3},{48,25,16}};
      double[][] B1 = new double[][]{{2,32,53},{14,235,36}};
      double[][] soma1 = new double[A1.length][A1[0].length];
      somaMatriz(A1, B1, soma1);
      escreveResultado(A1, B1, soma1);
      
      double[][] A2 = new double[][]{{1,2,3},{48,25,16,76}};
      double[][] B2 = new double[][]{{14,22,53,22,15},{38,5,1,6,4}};
      double[][] soma2 = new double[A2.length][A2[0].length];
      somaMatriz(A2, B2, soma2);
      escreveResultado(A2, B2, soma2);
      
      double[][] A3 = new double[][]{{13,2},{25,16},{45,6},{23,89},{12,65}};
      double[][] B3 = new double[][]{{3,22},{53,2},{43,123},{34,49}};
      double[][] soma3 = new double[A3.length][A3[0].length];
      somaMatriz(A3, B3, soma3);
      escreveResultado(A3, B3, soma3);
   }
   
   public static void escreveResultado(double[][] a, double[][] b, double[][] res){
      if(a.length == b.length && a[0].length == b[0].length){
         String result = "";
         System.out.println("\nO resultado é: ");
         for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
               result += "(A[" + i + "][" + j + "] = " + a[i][j] + ") + (B[" + i + "][" + j + "] = " + b[i][j] +" ) = Soma[" + i + "][" + j + "] = " + res[i][j] + "\n";
            }
         }
         System.out.print(result);
      }
   }
   
   public static void saudacao(){
      System.out.println("Soma dois arranjos bidimensionais");
   }
   
   public static void somaMatriz(double[][] A, double[][] B, double[][] soma){
      if(A.length == B.length && A[0].length == B[0].length){
         for(int i=0; i<A.length; i++){
            for(int j=0; j<A[i].length; j++){
               soma[i][j] = A[i][j] + B[i][j];
            }
         }
      }
   }
}