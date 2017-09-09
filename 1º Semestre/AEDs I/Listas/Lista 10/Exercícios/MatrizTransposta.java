public class MatrizTransposta{
   public static void main(String[] args){
      saudacao();
   
      double[][] M1 = new double[][]{{1,2,3},{4,5,6}};
      double[][] T1 = new double[M1[0].length][M1.length];
      geraMatrizTransposta(M1, T1);
      escreveResultado(M1, T1);
      
      double[][] M2 = new double[][]{{3,5},{4,4},{2,7},{0,7}};
      double[][] T2 = new double[M2[0].length][M2.length];
      geraMatrizTransposta(M2, T2);
      escreveResultado(M2, T2);
   }
   
   public static void escreveResultado(double[][] m ,double[][] t){
      String resp = "";
      System.out.println("\nA matriz é: ");
      for(int i=0; i<m.length; i++){
         for(int j=0; j<m[i].length; j++){
            resp += "M[" + i + "][" + j + "] = " + m[i][j] + "\n";
         }
      }
      System.out.print(resp);
      
      resp="";
      
      System.out.println("\nA transposta da matriz é: ");
      for(int i=0; i<t.length; i++){
         for(int j=0; j<t[i].length; j++){
            resp += "T[" + i + "][" + j + "] = " + t[i][j] + "\n";
         }
      }
      System.out.print(resp);
   }
   
   public static void saudacao(){
      System.out.println("Gera a matriz transposta através de uma matriz");
   }
   
   public static void geraMatrizTransposta(double[][] matriz, double[][] transposta){
      for(int i=0; i<matriz.length; i++){
         for(int j=0; j<matriz[i].length; j++){
            transposta[j][i] = matriz[i][j];
         }
      }
   }
}