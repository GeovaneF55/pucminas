import java.util.Scanner;

public class Triangulo
{
   public static void main(String[] args)
   {
      double ladoA, ladoB, ladoC;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Verificar se os lados consistem em um triângulo");
      System.out.print("\n\nDigite o lado A: ");
      ladoA = scanner.nextDouble();
      System.out.print("\n\nDigite o lado B: ");
      ladoB = scanner.nextDouble();
      System.out.print("\n\nDigite o lado C: ");
      ladoC = scanner.nextDouble();
      
      if(Math.abs(ladoB - ladoC) < ladoA && (ladoA < ladoB + ladoC)
      && Math.abs(ladoA - ladoC) < ladoB && (ladoA < ladoA + ladoC)
      && Math.abs(ladoA - ladoB) < ladoC && (ladoA < ladoA + ladoB))
      {
         System.out.print("\n\nOs lados consistem em um triângulo");  
      }else
      {
         System.out.print("\n\nOs lados não consistem em um triângulo");
      }       
   } 
}