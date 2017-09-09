import java.util.Scanner;

public class Fibonacci{
   public static void main(String[] args){
      saudacao();
      int tamanho = leTamanho();
      resposta(retornaArrayFibonacci(tamanho), tamanho);
   }
   
   public static void saudacao(){
      System.out.println("\n Arranjo com sequência Fibonacci: ");
   }
   
   public static int leTamanho(){
      Scanner scanner = new Scanner(System.in);
      int tamanho;
      System.out.print("\nDigite o tamanho do vetor: ");
      tamanho = scanner.nextInt();
      return tamanho;
   }
   
   public static int []retornaArrayFibonacci(int tamanho){
      int[] fibonacci = new int[tamanho];
      preencheFibonacci(fibonacci);
      return fibonacci;
   }
   
   public static void preencheFibonacci(int[] fibonacci){
      fibonacci[0] = 0;
      fibonacci[1] = 1;
      for(int i=2; i<fibonacci.length; i++){
         fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
      }
   }
   
   public static void resposta(int[] fibonacci, int tamanho){
      String resp = "O fibonacci com tamanho " + tamanho + " é:";
      for(int i=0; i<fibonacci.length; i++){
         resp += "\n f[" + i + "] = " + fibonacci[i];
      }
      System.out.print(resp);
   }
}