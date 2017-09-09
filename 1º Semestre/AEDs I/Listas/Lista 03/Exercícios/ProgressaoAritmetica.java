import java.util.Scanner;

public class ProgressaoAritmetica
{
   public static void main(String[] args)
   {
      int n, r;
      double An, A1;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Progressão Aritmética: A(n) = A1 + (n-1)r \n");
      System.out.print("\nDigite o primeiro termo da Progressão 'A1': ");
      A1 = scanner.nextDouble();
      do{
         System.out.print("\nDigite o número de termos da Progressão 'n': ");
         n = scanner.nextInt();
         if(n<0){
            System.out.print("\nO número de termos da Progressão não pode ser negativo");
         }
      }while(n<0);
      do{
         System.out.print("\nDigite a razão da Progressão 'r': ");
         r = scanner.nextInt();
         if(r<0){
            System.out.print("\nA razão da Progressão não pode ser negativa");
         }
      }while(r<0);
      
      for(int i = 0; i < n; i++){
          An = A1 + (((double)i)*(double)r);
          System.out.print("\n A("+i+") = " + An);
      }
   } 
}