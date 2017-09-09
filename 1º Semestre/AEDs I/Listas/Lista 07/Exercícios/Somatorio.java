import java.util.Scanner;

public class Somatorio {

   public static void introducao(){
      System.out.println("Somatório de dois valores");
   }
   
   public static int leValor() {
      Scanner scanner = new Scanner(System.in);
      int v;
      System.out.print("\nDigite um valor: ");
      v = scanner.nextInt();
      return v;
   }
   
   public static int somatorio(int valor1, int valor2){
      int somatorio = 0;
      if(valor1 > valor2){
         int aux;
         aux = valor1;
         valor1 = valor2;
         valor2 = aux;
      }
      for(int i = valor1; i <= valor2; i++){
         somatorio += i;
      }
      return somatorio;
   }
   
   public static void mostraResultado(int valor1, int valor2){
      int somatorio = somatorio(valor1, valor2);
      String resposta = "\nO somatório de "+ valor1 + " e " + valor2 + " é: " + somatorio;
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      int valor1 = leValor();
      int valor2 = leValor();
      mostraResultado(valor1, valor2);
   }
}