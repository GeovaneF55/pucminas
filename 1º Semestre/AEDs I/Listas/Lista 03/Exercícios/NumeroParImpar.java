import java.util.Scanner;

public class NumeroParImpar{
   public static void main(String[] args){
      char sair;
      int numero;
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nVerifica se o n�mero � par ou �mpar");
      do{
         System.out.print("\nDigite um n�mero: ");
         numero = scanner.nextInt();
         if(numero%2 == 0){
            System.out.print("\nO n�mero � par");
         }else{
            System.out.print("\nO n�mero � �mpar");
         }
         System.out.print("\nDeseja continuar(S|N)? ");
         sair = scanner.next().charAt(0);
         sair = Character.toUpperCase(sair);
      }while(sair != 'S' && sair == 'N');
   }
}