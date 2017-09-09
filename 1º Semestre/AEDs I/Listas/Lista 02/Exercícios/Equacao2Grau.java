import java.util.Scanner;

public class Equacao2Grau{
   public static void main(String[] args){
      double a, b, c, delta, x, x1;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Equação do 2 grau");
      System.out.print("\n aX² + bX + c = 0");
      System.out.print("\n\nDigite o valor de a: ");
      a = scanner.nextDouble();
      System.out.print("\n\nDigite o valor de b: ");
      b = scanner.nextDouble();
      System.out.print("\n\nDigite o valor de c: ");
      c = scanner.nextDouble();
      
      delta = Math.pow(b, 2) - (4*a*c);
      
      if(delta < 0){
         System.out.print("\n\nResultado não pertence aos números reais");
      }
      if(delta == 0){
         x = -b/(2*a);
         System.out.print("\n\nUma raiz e x é " + x);
      }
      if(delta > 0){
         x = (-b + Math.sqrt(delta))/(2*a);
         x1 = (-b - Math.sqrt(delta))/(2*a);
         System.out.print("\n\nDuas raízes que são x = " + x + " e x1 = " + x1);
      }
   }
}