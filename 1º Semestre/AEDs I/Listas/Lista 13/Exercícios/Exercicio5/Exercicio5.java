import java.util.Scanner;
public class Exercicio5{   
   public static void main(String[] args){
      System.out.println("Exercício 5");
      Elemento[] elementos = new Elemento[5];
      inicializaElementos(elementos);
      System.out.print("\nA quantidade de Elementos com a maior qualidade é: " +
                       qtMaiorQualidade(elementos)
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
   
   public static int qtMaiorQualidade(Elemento[] elementos){
      int qtMaxQuali = 0;
      int maxQuali = maiorQualidade(elementos);
      for(int i=0; i<elementos.length; i++){
         if(elementos[i].getQualidade() == maxQuali){
            qtMaxQuali+=1;
         }
      }
      return qtMaxQuali;
   }
   
   public static int maiorQualidade(Elemento[] elementos){
      int posicao = 0;
      for(int i=1; i<elementos.length; i++){
         if(elementos[i].getQualidade() > elementos[i-1].getQualidade()){
            posicao = i;
         }
      }
      return elementos[posicao].getQualidade();
   }
}