import java.util.Scanner;
public class Exercicio9{   
   public static void main(String[] args){
      System.out.println("Exercício 9");
      int linha = leLinha(), coluna = leColuna();
      double[][] matriz1 = new double[][]{{1,2,3,4,5},
                                          {2,3,4,5,6},
                                          {3,4,5,6,7},
                                          {4,5,6,7,8},
                                          {5,6,7,8,9}};
      double[][] matriz2 = new double[][]{{2,4,4},
                                          {4,5,6},
                                          {3,3,3}};
      double[][] matriz3 = new double[][]{{1,2},
                                          {2,1}};
      resposta(linha, coluna, colunasIguais(matriz1, linha, coluna));
      resposta(linha, coluna, colunasIguais(matriz2, linha, coluna));
      resposta(linha, coluna, colunasIguais(matriz3, linha, coluna));
      
   }
   
   public static int leLinha(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Digite a linha: ");
      int linha = scanner.nextInt();
      return linha;
   }
   
   public static int leColuna(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Digite a coluna: ");
      int coluna = scanner.nextInt();
      return coluna;
   }
   
   public static boolean colunasIguais(double[][] matriz, int i, int j){
      boolean colunasIguais = true;
      int tamanho = tamanhoMatriz(matriz, i);
      try{   
         for(int k=0; k<tamanho; k++){
            if(matriz[i][k] != matriz[k][j]){
               colunasIguais = false;
            }
         }
      }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
         colunasIguais = false;
      }
      return colunasIguais;
   }
   
   public static int tamanhoMatriz(double[][] matriz, int i){
      int tamanho;
      try{
         if(matriz.length > matriz[i].length){
            tamanho = matriz.length;
         }else{
            tamanho = matriz[i].length;
         }
      }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
         tamanho = matriz.length;
      }

      return tamanho;
   }
   
   public static void resposta(int linha, int coluna, boolean saoIguais){
      System.out.print("\n A Linha " + linha + " e a coluna " +
                       coluna + " da matriz são iguais: " + saoIguais
      );
   }
}