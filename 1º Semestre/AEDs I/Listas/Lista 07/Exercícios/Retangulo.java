import java.util.Scanner;

public class Retangulo {

   public static void introducao(){
      System.out.println("Per�metro de um ret�ngulo");
   }
   
   public static double leBase() {
      Scanner scanner = new Scanner(System.in);
      double b;
      do{
         System.out.print("\nDigite a base: ");
         b = scanner.nextDouble();
         if(b < 0){
            System.out.print("\nA base n�o pode ser negativo!");
         }
      }while(b < 0);
      return b;
   }
   
   public static double leAltura() {
      Scanner scanner = new Scanner(System.in);
      double a;
      do{
         System.out.print("\nDigite a altura: ");
         a = scanner.nextDouble();
         if(a < 0){
            System.out.print("\nA altura n�o pode ser negativo!");
         }
      }while(a < 0);
      return a;
   }
   
   public static double perimetro(double base, double altura){
      double perimetro = ((2*base) + (2*altura));
      return perimetro;
   }
   
   public static void mostraResultado(double base, double altura, double perimetro){
      String resposta = "\nO per�metro com base " + base + " e altura " + altura + " �: " + perimetro;
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      double base = leBase();
      double altura = leAltura();
      mostraResultado(base, altura, perimetro(base, altura));
   }
}