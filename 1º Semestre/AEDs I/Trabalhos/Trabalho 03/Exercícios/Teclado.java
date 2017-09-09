import java.util.Scanner;

public class Teclado{
   public static void desejaContinuar(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDeseja continuar <enter>? ");
      scanner.nextLine();
   }
   
   public static int lePosicao(){
      Scanner scanner = new Scanner(System.in);
      int posicao;
      System.out.print("\nDigite a posição: ");
      try{
         posicao = scanner.nextInt();
      } catch(Exception e){
         posicao = 0;
         scanner = new Scanner(System.in);
      }
      posicao -= 1;
      return posicao;
   }

   public static int leOpcao(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      try{
         opcao = scanner.nextInt();
      } catch(Exception e){
         opcao = -1;
      }
      return opcao;
   }
   
   public static double leDimensao(){
      Scanner scanner = new Scanner(System.in);
      double dimensao;
      try{
         dimensao = scanner.nextDouble();
         if(dimensao < 0){
            dimensao = 0;
         }
      } catch(Exception e){
         dimensao = 0;
      }
      return dimensao;
   }
}