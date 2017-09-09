import java.util.Scanner;

public class Fatorial {

   public static void introducao(){
      System.out.println("Calcula Fatorial");
   }
   
   public static int leValor() {
      Scanner scanner = new Scanner(System.in);
      int v;
      do{
         System.out.print("\nDigite o valor: ");
         v = scanner.nextInt();
         if(v < 0){
            System.out.print("\nO valor não pode ser negativo!");
         }
      }while(v < 0);
      return v;
   }
   
   public static int fatorial(int valor){
      int fatorial = 1;
      for(int i=1; i<=valor; i++){
         fatorial *= i;
      }
      return fatorial;
   }
   
   public static void mostraResultado(int valor, int fatorial){
      String resposta = "\n " + valor + "! = " + fatorial;
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      int valor = leValor();
      mostraResultado(valor, fatorial(valor));
   }
}