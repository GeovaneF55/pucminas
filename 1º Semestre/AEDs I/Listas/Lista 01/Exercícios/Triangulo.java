import java.util.Scanner;

public class Triangulo{
   public static void main(String[] args){
      double ladoA, ladoB, ladoC;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Descobrir se o triângulo é equilátero, isóscele ou escaleno");
      System.out.print("\n\nDigite o lado A: ");
      ladoA = scanner.nextDouble();
      System.out.print("\n\nDigite o lado B: ");
      ladoB = scanner.nextDouble();
      System.out.print("\n\nDigite o lado C: ");
      ladoC = scanner.nextDouble();
      
      if(ladoA == ladoB && ladoA == ladoC && ladoB == ladoC){
         System.out.print("\n\nO triângulo é equilátero");
      } else if(ladoA != ladoB && ladoA != ladoC && ladoB != ladoC){
         System.out.print("\n\nO triângulo é escaleno");
      }else{
         System.out.print("\n\nO triângulo é isóceles");
      }
   }
}