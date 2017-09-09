import java.util.Scanner;

public class PotenciacaoInterativa{
   public static void main(String[] args){
      saudacao();
      int base = leBase();
      int expoente = leExpoente();
      resposta(base, expoente, retornaPotencia(base, expoente));
   }
   
   public static void saudacao(){
      System.out.println("\n Potenciação Iterativa: ");
   }
   
   public static int leBase(){
      Scanner scanner = new Scanner(System.in);
      int base;
      do{
         System.out.print("\nDigite a base da potência: ");
         base = scanner.nextInt();
         if(base < 0){
            System.out.println("\n Não pode inserir valores negativos!");
         }
      }while(base < 0);
      return base;
   }
   
   public static int leExpoente(){
      Scanner scanner = new Scanner(System.in);
      int expoente;
      do{
         System.out.print("\nDigite o expoente da potência: ");
         expoente = scanner.nextInt();
         if(expoente < 0){
            System.out.println("\n Não pode inserir valores negativos!");
         }
      }while(expoente < 0);
      return expoente;
   }
   
   public static int retornaPotencia(int base, int expoente){
      int potencia = 1;
      for(int i=0; i<expoente; i++){
         potencia *= base;
      }
      return potencia;
   }
   

   
   public static void resposta(int base, int expoente, int potencia){
      System.out.println("\nA base " + base + " elevada à " + expoente + " é " + potencia);
   }
}