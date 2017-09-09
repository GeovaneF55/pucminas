import java.util.Scanner;

public class HipotenusaTriangulo
{
   public static void main(String[] args){
      double catetoA, catetoB, hipotenusa;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Hipotenusa de um triângulo retângulo");
      System.out.print("\n\n Digite o valor do Cateto A: ");
      catetoA = scanner.nextDouble();
      System.out.print("\n\n Digite o valor do Cateto B: ");      
      catetoB = scanner.nextDouble();
      
      hipotenusa = Math.sqrt(Math.pow(catetoA,2)+Math.pow(catetoB,2));
      
      System.out.print("\n\n O valor da hipotenusa é: " + hipotenusa);  
   }
}