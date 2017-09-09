public class ValoresValidosRecursivoArranjo{

   public static void main(String[] args){
      saudacao();
   
      int valorInicial = 0;
   
      System.out.println("\nTeste 1!");
      double[] a1 = {0,5,6,3,13,6};
      escreveResposta(qtValoresValidos(a1, valorInicial));
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {22,5,24,41,3,0};
      escreveResposta(qtValoresValidos(a2, valorInicial));
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,0,9,8};
      escreveResposta(qtValoresValidos(a3, valorInicial));
      
      System.out.println("\n\nTeste 4!");
      double[] a4 = {33,16,46,229,98};
      escreveResposta(qtValoresValidos(a4, valorInicial));
   }
   
   public static void saudacao(){
      System.out.println("Retorna a quantidade de elementos válidos do Arranjo!");
   }
   
   public static int qtValoresValidos(double[] arrayA, int posicao){
      if(arrayA.length == posicao || arrayA[posicao] == 0){
         return 0;
      }
      
      return 1 + qtValoresValidos(arrayA, posicao+1);
   }
   
   public static void escreveResposta(int qtValidos){
      System.out.print("A quantidade de valores válidos é " + qtValidos);
   }
}