import java.util.Scanner;

public class OcorrenciasMatriz{
   public static void main(String[] args){
      int[][] arranjo = new int[][]{{11,12,13},{11,17,18},{11,22,23}};
      saudacao();
      imprimeArranjo(arranjo);
      int pesquisa = lePesquisa();
      int ocorrencias = retornaNumeroOcorrencias(arranjo, pesquisa);
      resposta(pesquisa, ocorrencias);
   }
   
   public static void saudacao(){
      System.out.println("\n Número de ocorrências em um arranjo bidimencional: ");
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

   
   public static int lePesquisa(){
      Scanner scanner = new Scanner(System.in);
      int pesquisa;
      System.out.print("\n Digite o valor a ser pesquisado da matriz: ");
      pesquisa = scanner.nextInt();
      return pesquisa;
   }
   
   public static int retornaNumeroOcorrencias(int[][] arranjo, int pesquisa){
      int numOcorrencias=0;
      for(int i=0; i<arranjo.length; i++){
         for(int j=0; j<arranjo[i].length; j++){
            if(arranjo[i][j] == pesquisa){
               numOcorrencias+=1;
            }
         }
      }
      return numOcorrencias;
   }
   
   public static void resposta(int pesquisa, int ocorrencias){
      System.out.println("\n O número de ocorrencias do valor " + pesquisa + " é: " + ocorrencias);
   }
}