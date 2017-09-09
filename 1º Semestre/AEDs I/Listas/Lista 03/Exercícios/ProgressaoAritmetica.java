import java.util.Scanner;

public class ProgressaoAritmetica
{
   public static void main(String[] args)
   {
      int n, r;
      double An, A1;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Progress�o Aritm�tica: A(n) = A1 + (n-1)r \n");
      System.out.print("\nDigite o primeiro termo da Progress�o 'A1': ");
      A1 = scanner.nextDouble();
      do{
         System.out.print("\nDigite o n�mero de termos da Progress�o 'n': ");
         n = scanner.nextInt();
         if(n<0){
            System.out.print("\nO n�mero de termos da Progress�o n�o pode ser negativo");
         }
      }while(n<0);
      do{
         System.out.print("\nDigite a raz�o da Progress�o 'r': ");
         r = scanner.nextInt();
         if(r<0){
            System.out.print("\nA raz�o da Progress�o n�o pode ser negativa");
         }
      }while(r<0);
      
      for(int i = 0; i < n; i++){
          An = A1 + (((double)i)*(double)r);
          System.out.print("\n A("+i+") = " + An);
      }
   } 
}