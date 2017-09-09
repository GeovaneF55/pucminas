import java.util.Scanner;
public class Exercicio7{   
   public static void main(String[] args){
      System.out.println("Exercício 7");
      System.out.println("\nA quantidade de números negativos no arquivo teste.dat é: " +
                         ocorreciasNegativos()
      );
   }
   
   public static int ocorreciasNegativos(){
      Arquivo arq = new Arquivo("teste.dat");
      String[] valores = arq.leArquivoLinhas();
      int qtNegativos = 0;
      for(int i=0; i<valores.length; i++){
         if(Double.parseDouble(valores[i]) < 0){
            qtNegativos+=1;
         }
      }
      return qtNegativos;
   }
}