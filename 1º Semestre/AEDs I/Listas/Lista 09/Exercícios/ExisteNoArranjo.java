public class ExisteNoArranjo{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {0,5,6,3,13,6};
      int valor1 = 0;
      escreveResposta(existeNoArranjo(a1, valor1), valor1);
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {22,5,24,41,3,0};
      int valor2 = 3;
      escreveResposta(existeNoArranjo(a2, valor2), valor2);
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,0,9,8};
      int valor3 = 7;
      escreveResposta(existeNoArranjo(a3, valor3), valor3);
      
      System.out.println("\n\nTeste 4!");
      double[] a4 = {33,16,46,229,98};
      int valor4 = 98;
      escreveResposta(existeNoArranjo(a4, valor4), valor4);
   }
   
   public static void saudacao(){
      System.out.println("Verifica se o valor foi encontrado no arranjo!");
   }
   
   public static boolean existeNoArranjo(double[] arrayA, int valor){
      boolean existe = false;
      for(int i=0; i<arrayA.length; i++){
         if(arrayA[i] == valor){
            existe = true;
         }
      }
      return existe;
   }
   
   public static void escreveResposta(boolean existeNoArranjo, int valor){
      if(existeNoArranjo){
         System.out.print("O valor " + valor + " foi encontrado no arranjo!");
      } else{
         System.out.print("O valor " + valor + " não foi encontrado no arranjo!");
      }
   }
}