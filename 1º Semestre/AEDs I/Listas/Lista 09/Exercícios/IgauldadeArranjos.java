public class IgauldadeArranjos{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {5,6,3,13,6};
      double[] b1 = {5,6,3,13,6};
      escreveResposta(ehIgual(a1, b1));
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {2,5,24,1,3};
      double[] b2 = {2,5,24};
      escreveResposta(ehIgual(a2, b2));
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,7};
      double[] b3 = {3,6,46,7,5,2,5};
      escreveResposta(ehIgual(a3, b3));
   }
   
   public static void saudacao(){
      System.out.println("Verifica se dois Arranjos são iguais!");
   }
   
   public static boolean ehIgual(double[] arrayA, double[] arrayB){
      boolean arraysIguais = true;
      if(arrayA.length != arrayB.length){
         arraysIguais = false;
      } else{
         for(int i=0; i<arrayA.length; i++){
            if(arrayA[i] != arrayB[i]){
               arraysIguais = false;
            }
         }
      }
      return arraysIguais;
   }
   
   public static void escreveResposta(boolean ehIgual){
      if(ehIgual){
         System.out.print("Arranjos iguais!");
      } else{
         System.out.print("Arranjos diferentes!");
      }
   }
}