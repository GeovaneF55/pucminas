import java.util.Scanner;
public class SomaDoisNumeros{

   public static void main(String[] args){
      double valorA, valorB, resultado;
      valorA = leiaValor();
      valorB = leiaValor();
      
      resultado = somaValores(valorA, valorB);
      
      escreveResultado(resultado);
   }
   
   public static double leiaValor(){
      double valor;
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite um valor: ");
      valor = scanner.nextDouble();
      return valor;
   }
   
   public static double somaValores(double valorA, double valorB){
      return valorA + valorB;
   }
   
   public static void escreveResultado(double resultado){
      System.out.print("\nResultado: "+ resultado);
   }
}