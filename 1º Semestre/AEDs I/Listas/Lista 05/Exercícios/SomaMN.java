import java.util.Scanner;
public class SomaMN{
   public static void main(String[] args){
      int soma = 0,
          m = 0,
          n = 0;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Soma de um intervalo N e M\n\n");
      // Valor de  N
      System.out.print("\nDigite o valor de N: ");
      n = scanner.nextInt();
      
      // Valor de M que tem que ser maior que N
      do{
         System.out.print("\nDigite o valor de M: ");
         m = scanner.nextInt();
         if(n>=m){
            System.out.print("\nO valor de M deve ser maior que o valor de N");
         }
      }while(n>=m);
      
      // Soma dos valores entre N e M
      for(int i=n;i<=m;i++){
         soma += i;
      }
      System.out.print("\nA soma dos valores entre " + n + " e " + m + " é: " + soma);
   }
}