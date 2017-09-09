public class ElementosPositivosArranjo{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {5,6,3,13,6};
      escreveResposta(ehPositivo(a1));
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {-2,5,24,-1,3};
      escreveResposta(ehPositivo(a2));
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,0};
      escreveResposta(ehPositivo(a3));
   }
   
   public static void saudacao(){
      System.out.println("Verifica se todos os elementos do Arranjos são positivos!");
   }
   
   public static boolean ehPositivo(double[] arrayA){
      boolean positivo = true;
      for(int i=0; i<arrayA.length; i++){
         if(arrayA[i] <= 0){
            positivo = false;
         }
      }
      return positivo;
   }
   
   public static void escreveResposta(boolean ehPositivo){
      if(ehPositivo){
         System.out.print("Todos elementos do Arranjo são positivos!");
      } else{
         System.out.print("Nem todos elementos do Arranjo não são positivos!");
      }
   }
}