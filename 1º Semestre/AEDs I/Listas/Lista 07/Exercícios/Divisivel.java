import java.util.Scanner;

public class Divisivel {

   public static void introducao(){
      System.out.println("Verifica se um número inteiro é divisível por outro");
   }
   
   public static int leValor(String div) {
      Scanner scanner = new Scanner(System.in);
      int d;
      System.out.print("\nDigite o " + div + ": ");
      d = scanner.nextInt();
      return d;
   }
   
   public static boolean ehDivisivel(int valor1, int valor2){
      boolean divisivel = true;
      if(valor1 % valor2 != 0){
         divisivel = false;
      }
      return divisivel;
   }
   
   public static void mostraResultado(int valor1, int valor2, boolean divisivel){
      String resposta = "\n O " + valor1;
      if(divisivel){
         resposta += " é divisível ";
      } else{
         resposta += " não é divisível ";
      }
      resposta += "pelo " + valor2;
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      int valor1 = leValor("divisor");
      int valor2 = leValor("dividendo");
      mostraResultado(valor1, valor2, ehDivisivel(valor1, valor2));
   }
}