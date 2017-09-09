public class CopiaMatriz{
   public static void main(String[] args){
      saudacao();
   
      double[][] M1 = new double[][]{{1,2,3},{4,5,6}};
      double[][] CP1 = new double[M1.length][M1[0].length];
      copiaMatriz(M1, CP1);
      escreveResultado(M1, CP1);
      
      double[][] M2 = new double[][]{{3,5},{4,4},{2,7},{0,7}};
      double[][] CP2 = new double[M2.length][M2[0].length];
      copiaMatriz(M2, CP2);
      escreveResultado(M2, CP2);
   }
   
   public static void escreveResultado(double[][] m ,double[][] cp){
      String resp = "";
      System.out.println("\nA cópia da matriz é: ");
      for(int i=0; i<m.length; i++){
         for(int j=0; j<m[i].length; j++){
            resp += "M[" + i + "][" + j + "] = " + m[i][j] + " -> CP[" + i + "][" + j + "] = " + cp[i][j] + "\n";
         }
      }
      System.out.print(resp);
   }
   
   public static void saudacao(){
      System.out.println("Cópia uma matriz em outra");
   }
   
   public static void copiaMatriz(double[][] matriz, double[][] copiaMatriz){
      for(int i=0; i<matriz.length; i++){
         for(int j=0; j<matriz[i].length; j++){
            copiaMatriz[i][j] = matriz[i][j];
         }
      }
   }
}