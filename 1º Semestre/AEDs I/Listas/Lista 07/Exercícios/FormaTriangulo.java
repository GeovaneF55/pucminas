import java.util.Scanner;

public class FormaTriangulo {

   public static void introducao(){
      System.out.println("Verifica se os lados formam um Triângulo");
   }
   
   public static double leLado() {
      Scanner scanner = new Scanner(System.in);
      double l;
      do{
         System.out.print("\nDigite o lado: ");
         l = scanner.nextDouble();
         if(l < 0){
            System.out.print("\nO lado não pode ser negativo!");
         }
      }while(l < 0);
      return l;
   }
   
   public static boolean ehTriangulo(double A, double B, double C){
      boolean triangulo = true;
      if(!(((Math.abs(B-C) < A) && (A < (B+C))) || ((Math.abs(A-C) < B) && (B < (A+C))) || ((Math.abs(A-B) < C) && (C < (A+B))))){
         triangulo = false;
      }
      return triangulo;
   }
   
   public static void mostraResultado(double A, double B, double C, boolean triangulo){
      String resposta = "\n Os lados: " + A + ", " + B + " e " + C;
      if(triangulo){
         resposta += " formam um triângulo";
      } else{
         resposta += " não formam um triângulo";
      }
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      double ladoA = leLado();
      double ladoB = leLado();
      double ladoC = leLado();
      mostraResultado(ladoA, ladoB, ladoC, ehTriangulo(ladoA, ladoB, ladoC));
   }
}