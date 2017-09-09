import java.util.Scanner;

public class Bissexto {

   public static void introducao(){
      System.out.println("Verifica se o ano bissexto");
   }
   
   public static int leAno() {
      Scanner scanner = new Scanner(System.in);
      int a;
      do{
         System.out.print("\nDigite a base: ");
         a = scanner.nextInt();
         if(a < 0){
            System.out.print("\nO ano não pode ser negativo!");
         }
      }while(a < 0);
      return a;
   }
   
   public static boolean ehBissexto(int ano){
      boolean bissexto = true;
      if(!(ano%4 == 0) || ((ano%100 == 0)&&!(ano%400 == 0))){
         bissexto = false;
      }
      return bissexto;
   }
   
   public static void mostraResultado(int ano, boolean bissexto){
      String resposta = "\n O ano " + ano;
      if(bissexto){
         resposta += " é bissexto";
      } else{
         resposta += " não é bissexto";
      }
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      int ano = leAno();
      mostraResultado(ano, ehBissexto(ano));
   }
}