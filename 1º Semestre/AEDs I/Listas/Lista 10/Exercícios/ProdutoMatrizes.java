public class ProdutoMatrizes{
   public static void main(String[] args){

      saudacao();
   
      int[][] matrizA1 = new int[][]{
               {2,3},
               {1,0},
               {4,5}
      };//matriz 3x2
      int[][] matrizB1 = new int[][]{
               {3,1},
               {1,4}
      };//matriz 2x2
      int[][] produto1 = new int[matrizA1.length][matrizB1[0].length];
      produtoMatriz(matrizA1, matrizB1, produto1);
      escreveResultado(matrizA1, matrizB1, produto1);
      
      int[][] matrizA2 = new int[][]{
              {2,3,4},
              {3,4,5},
              {4,5,6}
      }; //matriz 3X3
      int[][] matrizB2 = new int[][]{
              {2,3,4},
              {3,4,5}
      };//matriz 2X3
      int[][] produto2 = new int[matrizA2.length][matrizB2[0].length];
      produtoMatriz(matrizA2, matrizB2, produto2);
      escreveResultado(matrizA2, matrizB2, produto2);
      
      int[][] matrizA3 = new int[][]{
               {2,3,4},
               {3,4,5},
               {4,5,6},
               {5,6,7}
      };//matriz 4x3
      int[][] matrizB3 = new int[][]{
               {2,3,4},
               {3,4,5},
               {4,5,6}
      };//matriz 3x3
      int[][] produto3 = new int[matrizA3.length][matrizB3[0].length];
      produtoMatriz(matrizA3, matrizB3, produto3);
      escreveResultado(matrizA3, matrizB3, produto3);
   }
   
   public static void saudacao(){
      System.out.println("Produto de matrizes!\n");
   }
   
   public static void produtoMatriz(int[][] matrizA, int[][] matrizB, int[][] produto){
      if(matrizA[0].length == matrizB.length){
         int somaProd=0;
         for(int i=0; i < matrizA.length; i++){
            for(int j=0; j < matrizA[0].length; j++){
               somaProd=0;
               for(int k=0; k < matrizB[0].length; k++){
                  somaProd+=matrizA[i][k]*matrizB[k][j];
               }
               produto[i][j] = somaProd;
            }
         }
      }
   }
   
   public static void escreveResultado(int[][] matrizA, int[][] matrizB, int[][] produto){
      String resp = "";
      if(matrizA[0].length == matrizB.length){
         for(int i=0; i < produto.length; i++){
            for(int j=0; j < produto[0].length; j++){
               resp += "P[" + i + "][" + j + "] = " + produto[i][j] + "\n";
            }
         }
      } else{
         resp = "Formato das matrizes inválidos para multiplicar!\n";
      }
      System.out.println(resp);
   }
}