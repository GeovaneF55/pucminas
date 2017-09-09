import java.util.Scanner;
public class Exercicio8{   
   public static void main(String[] args){
      System.out.println("Exercício 8");
      Elemento[] elementos = new Elemento[5];
      inicializaElementos(elementos);
      System.out.print("\nA quantidade de Elementos com qualidade com margem de diferença em 1 ponto da média é: " +
                       qtQualidadePertoMedia(elementos)
      );
   }
   
   public static void inicializaElementos(Elemento[] elementos){
      for(int i=0; i<elementos.length; i++){
         System.out.print("\nElemento " + (i+1));
         Elemento elemento = new Elemento(leDescricao(), leQualidade());
         elementos[i] = elemento;
      }
   }
   
   public static String leDescricao(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite uma descrição para o Elemento [20 caracteres]: ");
      String descricao = scanner.next();
      return descricao;
   }
   
   public static int leQualidade(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Digite a qualidade do Elemento [0-10]: ");
      int qualidade = scanner.nextInt();
      return qualidade;
   }
   
   public static int qtQualidadePertoMedia(Elemento[] elementos){
      double qualidadeMedia = mediaQualidade(elementos);
      System.out.print("\nA média é " + qualidadeMedia);
      int qtQualidadePertoMedia = 0;
      for(int i=0; i<elementos.length; i++){
         if(elementos[i].getQualidade() <= qualidadeMedia+1 &&
            qualidadeMedia-1 <= elementos[i].getQualidade()
         ){
            qtQualidadePertoMedia += 1;
         }
      }
      return qtQualidadePertoMedia;
   }
   
   public static double mediaQualidade(Elemento[] elementos){
      int soma = 0;
      int count = 0;
      for(int i=0; i<elementos.length; i++){
         soma += elementos[i].getQualidade();
         count = (i+1);
      }
      return ((double)soma/(double)count);
   }
}