import java.util.Scanner;

public class Triangulo
{
   public static void main(String[] args)
   {
      boolean isNotTriangulo;
      double ladoA, ladoB, ladoC;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Verificar se os lados consistem em um triângulo");
      do{
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
            isNotTriangulo = false;
            System.out.print("\n\nOs lados consistem em um triângulo");  
         }else
         {
            isNotTriangulo = true;
            System.out.print("\n\nOs lados não consistem em um triângulo");
         }
      }while(isNotTriangulo);
      if(ladoA == ladoB && ladoA == ladoC && ladoB == ladoC){
         System.out.print("\n\nO triângulo é equilátero");
      } else if(ladoA != ladoB && ladoA != ladoC && ladoB != ladoC){
         System.out.print("\n\nO triângulo é escaleno");
      }else{
         System.out.print("\n\nO triângulo é isóceles");
      }    
   } 
}