import java.util.Scanner;

public class MaiorAbaixoDiagonalPrincipal{
   public static void main(String[] args){
      saudacao();
      int[][] arranjo1 = new int[][]{{11,12,13},{11,17,18},{11,22,23}};
      imprimeArranjo(arranjo1);
      resposta(retornaMaiorAbaixoDiagonalPrincipal(arranjo1));
      
      int[][] arranjo2 = new int[][]{{11,12},{11,17},{11,22}};
      imprimeArranjo(arranjo2);
      resposta(retornaMaiorAbaixoDiagonalPrincipal(arranjo2));
      
      int[][] arranjo3 = new int[][]{{11,12,13,19},{11,17,18,18},{11,22,23,17},{15,15,15,23}};
      imprimeArranjo(arranjo3);
      resposta(retornaMaiorAbaixoDiagonalPrincipal(arranjo3));
   }
   
   public static void saudacao(){
      System.out.println("\n Calcula o maior valor abaixo da diagonal principal: ");
   }
   
   public static void imprimeArranjo(int[][] arranjo){
      String impressao = "\n O arranjo é: ";
      for(int i=0; i<arranjo.length; i++){
         impressao += "\n";
         for(int j=0; j<arranjo[i].length; j++){
            impressao += " - a["+ i +"][" + j + "] = " + arranjo[i][j];
         }
      }
      System.out.println(impressao);
   }
   
   public static int retornaMaiorAbaixoDiagonalPrincipal(int[][] arranjo){
      int maiorAbaixoDiagonalPrincipal=0;
      if(arranjo.length == arranjo[0].length){
         maiorAbaixoDiagonalPrincipal=arranjo[1][0];
         for(int i=0; i<arranjo.length; i++){
            for(int j=0; j<arranjo[i].length; j++){
               if(i>j && arranjo[i][j]>maiorAbaixoDiagonalPrincipal){
                  maiorAbaixoDiagonalPrincipal=arranjo[i][j];
               }
            }
         }
      }
      return maiorAbaixoDiagonalPrincipal;
   }
   
   public static void resposta(int maiorAbaixoDiagonalPrincipal){
      System.out.println("\n O maior valor abaixo da diagonal principal é: " + maiorAbaixoDiagonalPrincipal);
   }
}