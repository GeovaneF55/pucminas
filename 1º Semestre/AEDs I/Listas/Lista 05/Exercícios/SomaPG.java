import java.util.Scanner;

public class SomaPG{
   public static void main(String[] args){
      int n = 0;
      double a1,
             q,
             soma = 0;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Soma de uma PG S(n) = a1 + a1*q + ... + a1*(q ê(n))\n\n");
      // Termo inicial
      System.out.print("\nDigite o termo inicial (a1): ");
      a1 = scanner.nextDouble();
      // Razão
      System.out.print("\nDigite a razão (q): ");
      q = scanner.nextDouble();
      // Número de Termos
      do{
         System.out.print("\nDigite o número de termos (n):");
         n = scanner.nextInt();
         if(n <= 0){
            System.out.print("\nO número de termos deve ser maior que 0");
         }
      }while(n <= 0);
      // Cálculo da Soma da PG com for
      for(int i = 0; i < n; i++){
         soma += a1*Math.pow(q,i);
      }
      System.out.print("\nS(" + n + ") = " + soma);
   }
}