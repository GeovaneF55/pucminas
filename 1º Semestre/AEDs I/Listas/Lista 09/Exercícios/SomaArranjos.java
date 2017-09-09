public class SomaArranjos{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {5,6,3,13,6};
      double[] b1 = {3,1,43,5,5};
      double[] resp1 = new double[a1.length];
      soma(a1, b1, resp1);
      escreveResposta(a1, b1, resp1);
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {2,5,24,1,3};
      double[] b2 = {23,54,23};
      double[] resp2 = new double[a2.length];
      soma(a2, b2, resp2);
      escreveResposta(a2, b2, resp2);
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {34,34,56};
      double[] b3 = {3,6,46,7,5,2,5};
      double[] resp3 = new double[a3.length];
      soma(a3, b3, resp3);
      escreveResposta(a3, b3, resp3);
   }
   
   public static void saudacao(){
      System.out.println("Soma de dois Arranjos!");
   }
   
   public static void soma(double[] valorA, double[] valorB, double[] resultado){
      if(valorA.length == valorB.length){
         for(int i=0; i<valorA.length; i++){
            resultado[i] = valorA[i] + valorB[i];
         }
      }
   }
   
   public static void escreveResposta(double[] valorA, double[] valorB, double[] resultado){
      if(valorA.length == valorB.length){
         for(int i=0; i<valorA.length; i++){
            System.out.println(valorA[i] + " + " + valorB[i] + " = " + resultado[i]);
         }
      }else{
         System.out.print("\nArranjos com tamanhos diferentes!");
      }
   }
}