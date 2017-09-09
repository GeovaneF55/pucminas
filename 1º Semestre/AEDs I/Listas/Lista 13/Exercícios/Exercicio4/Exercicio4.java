import java.util.Scanner;
public class Exercicio4{   
   public static void main(String[] args){
      System.out.println("Exercício 4");
      int divisor = leDivisor();
      System.out.print("O número de dívisiveis por " + divisor +
                       " no arquivo teste.dat é: " + ocorreciasDivisiveis(divisor)
      );
   }
   
   public static int leDivisor(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite o valor do divisor X: ");
      int divisor = scanner.nextInt();
      return divisor;
   }
   
   public static int ocorreciasDivisiveis(int divisor){
      Arquivo arq = new Arquivo("teste.dat");
      String[] valores = arq.leArquivoLinhas();
      int qtDivisiveis = 0;
      for(int i=0; i<valores.length; i++){
         if(Integer.parseInt(valores[i])%divisor == 0){
            qtDivisiveis+=1;
         }
      }
      return qtDivisiveis;
   }
}