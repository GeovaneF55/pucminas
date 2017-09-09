import java.util.Scanner;
public class Exercicio6B{   
   public static void main(String[] args){
      System.out.println("Exercício 6 - B");
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite um valor: ");
      int valor = scanner.nextInt();
      System.out.print("Somatório = " + X(valor));
   }

   public static int X(int a){
      int soma=0;
      while(a>=0){
         soma+=a;
         a--;
      }
      return soma;
   }
}