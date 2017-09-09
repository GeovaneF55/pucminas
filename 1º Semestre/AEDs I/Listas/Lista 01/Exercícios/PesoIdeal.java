import java.util.Scanner;

public class PesoIdeal
{
   public static void main(String[] args){
      char genero;
      double altura, pesoIdeal = 0.0;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Cálculo do peso ideal por gênero");
      System.out.print("\n\nDigite o gênero (m) para masculino e (f) para feminino: ");
      genero = scanner.next().charAt(0);
      System.out.print("\n\nDigite a altura: ");
      altura = scanner.nextDouble();
      
      if(genero == 'm'){
         pesoIdeal = (72.7*altura)-58.0;
      }else if(genero == 'f'){
         pesoIdeal = (62.1*altura)-44.7;
      }
      System.out.print("\n\nSeu peso Ideal é: " + pesoIdeal);
   }
}