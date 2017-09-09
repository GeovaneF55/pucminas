import java.util.Scanner;

public class Triangulo
{
   public static void main(String[] args)
   {
      double ladoA, ladoB, ladoC;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Verificar se os lados consistem em um tri�ngulo");
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
         System.out.print("\n\nOs lados consistem em um tri�ngulo");  
      }else
      {
         System.out.print("\n\nOs lados n�o consistem em um tri�ngulo");
      }       
   } 
}