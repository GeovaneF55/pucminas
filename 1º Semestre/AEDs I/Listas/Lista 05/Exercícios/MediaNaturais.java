import java.util.Scanner;
public class MediaNaturais{
   public static void main(String[] args){
      int somaPares = 0,
          somaImpares = 0,
          qtdPares = 0,
          qtdImpares = 0,
          valorLido;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("M�dia de Naturais lidos\n\n");
      
      // Primeiro valor lido
      do{
         System.out.print("\n(0 - Sair)\nDigite um valor: ");
         valorLido = scanner.nextInt();
         if(valorLido < 0){
            System.out.print("\nValor n�o pode ser negativo");
         }
      }while(valorLido < 0);
      
      while(valorLido != 0){
         // Soma Pares ou Impares e adiciona 1 a quantidade
         if(valorLido%2 == 0){
            somaPares += valorLido;
            qtdPares++;
         }else{
            somaImpares += valorLido;
            qtdImpares++;
         }
         // Novo valor lido
         do{
            System.out.print("\n(0 - Sair)\nDigite um valor: ");
            valorLido = scanner.nextInt();
            if(valorLido < 0){
               System.out.print("\nValor n�o pode ser negativo");
            }
         }while(valorLido < 0);
         
      }
      
      if(qtdPares != 0)System.out.print("\nM�dia Pares: " + (double)somaPares/(double)qtdPares);
      if(qtdImpares != 0)System.out.print("\nM�dia Impares: " + (double)somaImpares/(double)qtdImpares);
   }
}