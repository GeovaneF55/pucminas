import java.util.Scanner;

public class TipoTriangulo {

   public static void introducao(){
      System.out.println("Verifica o tipo de Triângulo");
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
   
   public static int tipoTriangulo(double A, double B, double C){
      int triangulo;
      if(A == B && A == C && B == C){
         triangulo = 1;
      } else if(A != B && A != C && B != C){
         triangulo = 2;
      } else{
         triangulo = 3;
      }
      return triangulo;
   }
   
   public static void mostraResultado(double A, double B, double C){
      int triangulo = tipoTriangulo(A, B, C);
      String resposta = "\n Os lados: " + A + ", " + B + " e " + C;
      if(triangulo == 1){
         resposta += " formam um triângulo equilátero";
      } else if(triangulo == 2){
         resposta += " formam um triângulo escaleno";
      } else{
         resposta += " formam um triângulo isóceles";
      }
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      double ladoA = leLado();
      double ladoB = leLado();
      double ladoC = leLado();
      mostraResultado(ladoA, ladoB, ladoC);
   }
}