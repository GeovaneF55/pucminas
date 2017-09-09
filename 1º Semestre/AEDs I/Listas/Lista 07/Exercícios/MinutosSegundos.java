import java.util.Scanner;

public class MinutosSegundos {

   public static void introducao(){
      System.out.println("Transforma minutos em segundos");
   }
   
   public static int leMinutos() {
      Scanner scanner = new Scanner(System.in);
      int m;
      do{
         System.out.print("\nDigite os minutos: ");
         m = scanner.nextInt();
         if(m < 0){
            System.out.print("\nO tempo não pode ser negativo!");
         }
      }while(m < 0);
      return m;
   }
   
   public static int minutosParaSegundos(int minutos){
      int segundos = minutos*60;
      return segundos;
   }
   
   public static void mostraResultado(int minutos, int segundos){
      String resposta = "\n"+ minutos + " minuto(s) são " + segundos + " segundo(s)";
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      int minutos = leMinutos();
      mostraResultado(minutos, minutosParaSegundos(minutos));
   }
}