import java.util.Scanner;

public class NumeroParImpar{
   public static void main(String[] args){
      char sair;
      int numero;
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nVerifica se o número é par ou ímpar");
      do{
         System.out.print("\nDigite um número: ");
         numero = scanner.nextInt();
         if(numero%2 == 0){
            System.out.print("\nO número é par");
         }else{
            System.out.print("\nO número é ímpar");
         }
         System.out.print("\nDeseja continuar(S|N)? ");
         sair = scanner.next().charAt(0);
         sair = Character.toUpperCase(sair);
      }while(sair != 'S' && sair == 'N');
   }
}