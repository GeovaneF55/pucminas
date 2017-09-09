import java.util.Scanner;

public class FarenheitCelsius
{
   public static void main(String[] args){
      double celsius, farenheit;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Conversor de Farenheit para grau Celsius");
      System.out.print("\n\nDigite o grau em Farenheit: ");
      farenheit = scanner.nextDouble();
      celsius = (5.0/9.0)*(farenheit-32);
      System.out.print("\n\n" + farenheit + " graus Farenheit são " + celsius + " Celsius");
   }
}