public class ValoresValidosArranjo{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {0,5,6,3,13,6};
      escreveResposta(qtValoresValidos(a1));
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {22,5,24,41,3,0};
      escreveResposta(qtValoresValidos(a2));
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,0,9,8};
      escreveResposta(qtValoresValidos(a3));
      
      System.out.println("\n\nTeste 4!");
      double[] a4 = {33,16,46,229,98};
      escreveResposta(qtValoresValidos(a4));
   }
   
   public static void saudacao(){
      System.out.println("Retorna a quantidade de elementos válidos do Arranjo!");
   }
   
   public static int qtValoresValidos(double[] arrayA){
      boolean passouZero = false;
      int qtValidos = 0;
      for(int i=0; i<arrayA.length; i++){
         if(arrayA[i] == 0){
            passouZero = true;
         }
         if(!passouZero){
            qtValidos++;
         }
      }
      return qtValidos;
   }
   
   public static void escreveResposta(int qtValidos){
      System.out.print("A quantidade de valores válidos é " + qtValidos);
   }
}