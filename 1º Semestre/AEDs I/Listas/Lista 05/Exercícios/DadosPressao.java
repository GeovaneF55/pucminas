import java.util.Scanner;

public class DadosPressao{
   public static void main(String[] args){
      int n_amostras = 0,
          id_maior = 0;
      double pressao = 0,
             maior_pressao = 0,
             soma_pressao = 0;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Dados da Pressão\n\n");
      // Número de Amostras
      do{
         System.out.print("\nDigite o número de amostras: ");
         n_amostras = scanner.nextInt();
      }while(n_amostras <= 0);
      
      for(int i = 1; i <= n_amostras ; i++){
         System.out.print("\nDigite a pressão da amostra " + i + ": ");
         pressao = scanner.nextDouble();
         // Verifica se é maior
         if(maior_pressao < pressao){
            maior_pressao = pressao;
            id_maior = i;
         }
         // Soma pressão para fazer a média
         soma_pressao += pressao;
      }
      
      System.out.println("\nA amostra " + id_maior + " tem a maior pressão: " + maior_pressao);
      System.out.print("A média das pressões é: " + (soma_pressao/(double)n_amostras));
   }
}
