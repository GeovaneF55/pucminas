import java.util.Scanner;

public class Calculadora{
   public static void main(String[] args){
      double A, B, resp;
      int opcao;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Calculadora");
      System.out.print("\n\n--Menu"+
                       "\n1- Adição"+
                       "\n2- Subtração"+
                       "\n3- Multiplicação"+
                       "\n4- Divisão"+
                       "\n\n Opção: ");
      opcao = scanner.nextInt();
      System.out.print("Digite o 1 valor: ");
      A = scanner.nextDouble();
      System.out.print("Digite o 2 valor: ");
      B = scanner.nextDouble();
      switch(opcao){
         case 1:
            resp = A + B;
         break;
         case 2:
            resp = A - B;
         break;
         case 3:
            resp = A * B;
         break;
         case 4:
            resp = A / B;
         break;
         default:
            resp = 0;
            System.out.print("\n\nOpção inválida!");
         break;
      }
      if(opcao >= 1 && opcao <=4){
         System.out.print("\n\nO resultado é: " + resp);
      }
   }
}