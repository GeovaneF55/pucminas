public class ValoresDivisiveisRecursivoArranjo{

   public static void main(String[] args){
      saudacao();
   
      int valorInicial = 0;
   
      System.out.println("\nTeste 1!");
      double[] a1 = {5,6,3,13,6};
      double divisor1 = 4;
      escreveResposta(qtValoresDivisiveis(a1, divisor1, valorInicial), divisor1);
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {22,5,24,41,3};
      double divisor2 = 2;
      escreveResposta(qtValoresDivisiveis(a2, divisor2, valorInicial), divisor2);
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,9,8};
      double divisor3 = 9;
      escreveResposta(qtValoresDivisiveis(a3, divisor3, valorInicial), divisor3);
      
      System.out.println("\n\nTeste 4!");
      double[] a4 = {33,16,46,229,98};
      double divisor4 = 8;
      escreveResposta(qtValoresDivisiveis(a4, divisor4, valorInicial), divisor4);
   }
   
   public static void saudacao(){
      System.out.println("Retorna a quantidade de elementos divisíveis pelo elemento fornecido no Arranjo!");
   }
   
   public static int qtValoresDivisiveis(double[] arrayA, double divisor , int posicao){
      if(arrayA.length == posicao || arrayA[posicao] == 0){
         return 0;
      }
      
      if(arrayA[posicao]%divisor == 0){
         return 1 + qtValoresDivisiveis(arrayA, divisor, posicao+1);
      } else{
         return 0 + qtValoresDivisiveis(arrayA, divisor, posicao+1);
      }
   }
   
   public static void escreveResposta(int qtValoresDivisiveis, double divisor){
      System.out.print("A quantidade de valores divisíveis por " + divisor + " é " + qtValoresDivisiveis);
   }
}