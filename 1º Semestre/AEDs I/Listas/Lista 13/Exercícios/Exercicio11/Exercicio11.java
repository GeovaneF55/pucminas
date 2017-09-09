import java.util.Scanner;
public class Exercicio11{   
   public static void main(String[] args){
      System.out.println("Exercício 11");
      double[][] matriz1 = new double[][]{{1,2,3,4,5},
                                          {2,3,4,5,6},
                                          {3,4,10,6,7},
                                          {4,5,6,7,8},
                                          {5,6,7,8,9}};
      double[] vetor1 = new double[matriz1.length];
      double[][] matriz2 = new double[][]{{2,4,4},
                                          {4,5,6},
                                          {3,3,3}};
      double[] vetor2 = new double[matriz2.length];
      double[][] matriz3 = new double[][]{{1,2},
                                          {2,5}};
      double[] vetor3 = new double[matriz3.length];
      
      copiaLinhaMaiorValorMatriz(matriz1, vetor1);
      copiaLinhaMaiorValorMatriz(matriz2, vetor2);
      copiaLinhaMaiorValorMatriz(matriz3, vetor3);
      
      resposta(vetor1);
      resposta(vetor2);
      resposta(vetor3);
      
   }
   
   public static void copiaLinhaMaiorValorMatriz(double[][] matriz, double[] vetor){
      int linhaMaiorValor = linhaMaiorValor(matriz);
      try{   
         for(int j=0; j<vetor.length; j++){
            vetor[j] = matriz[linhaMaiorValor][j];
         }
      }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
         vetor = null;
      }
   }
   
   public static int linhaMaiorValor(double[][] matriz){
      int linhaMaiorValor=0;
      int colunaMaiorValor=0;
      try{   
         for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
               if(matriz[i][j] > matriz[linhaMaiorValor][colunaMaiorValor]){
                  linhaMaiorValor = i;
                  colunaMaiorValor = j;
               }
            }
         }
      }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
         linhaMaiorValor=0; 
      }
      return linhaMaiorValor;
   }
   
   public static void resposta(double[] vetor){
      String resp = "\n";
      try{
         for(int i=0; i<vetor.length; i++){
            resp += "v[" + (i+1) + "] = " + vetor[i] + " ";
         }
      }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
         resp = "\nErro no vetor";
      }catch(NullPointerException nullPointerException){
         resp = "\nVetor não foi copiado";
      }finally{
         System.out.println(resp);
      }
   }
}