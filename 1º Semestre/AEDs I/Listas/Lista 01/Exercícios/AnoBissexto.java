import java.util.Scanner;

public class AnoBissexto {
   public static void main(String[] args){
      double ano;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Identificar se o ano é bissexto");
      System.out.print("\n\nDigite o ano: ");
      ano = scanner.nextDouble();
      if((ano%4 == 0.0 && ano%100 != 0.0) || (ano%400 == 0.0)){
         System.out.print("\n\nO ano " + ano + " é bissexto");
      } else{
         System.out.print("\n\nO ano " + ano + " não é bissexto");
      }
   }
}