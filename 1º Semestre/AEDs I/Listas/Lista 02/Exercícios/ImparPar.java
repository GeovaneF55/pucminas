import java.util.Scanner;

public class ImparPar{
   public static void main(String[] args){
      int num;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Ímpar ou par");
      System.out.print("\n\nDigite um número: ");
      num = scanner.nextInt();
      if(num%2 == 0){
         System.out.print("\n\nO número é par");
      }else{
         System.out.print("\n\nO número é ímpar");
      }
   }
}