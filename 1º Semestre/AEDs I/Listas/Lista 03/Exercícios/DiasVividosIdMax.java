import java.util.Scanner;

public class DiasVividosIdMax{
   public static void main(String[] args){
      int idade;
      Scanner scanner = new Scanner(System.in);
      System.out.print("N�mero de dias vividos\n");
      do{
         System.out.print("\nDigite a idade: ");
         idade = scanner.nextInt();
         if(idade<0){
            System.out.print("\nIdade n�o pode ser nagativa");
         }
         if(idade>120){
            System.out.print("\nIdade n�o pode ser maior que 120");
         }
      }while(idade<0 || idade>120);
      System.out.print("O n�mero de dias vividos �: " + (idade * 365));
   }
}