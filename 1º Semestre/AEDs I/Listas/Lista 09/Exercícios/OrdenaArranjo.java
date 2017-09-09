public class OrdenaArranjo{

   public static void main(String[] args){
      saudacao();
   
      System.out.println("\nTeste 1!");
      double[] a1 = {5,6,3,13,6};
      ordenaArranjo(a1);
      escreveResposta(a1);
      
      System.out.println("\n\nTeste 2!");
      double[] a2 = {22,5,24,41,3};
      ordenaArranjo(a2);
      escreveResposta(a2);
      
      System.out.println("\n\nTeste 3!");
      double[] a3 = {3,6,46,9,8};
      ordenaArranjo(a3);
      escreveResposta(a3);
      
      System.out.println("\n\nTeste 4!");
      double[] a4 = {33,16,46,229,98};
      ordenaArranjo(a4);
      escreveResposta(a4);
   }
   
   public static void saudacao(){
      System.out.println("Ordena os elementosde um Arranjo!");
   }
   
   public static void ordenaArranjo(double[] arrayA){
      for(int i=0; i<arrayA.length; i++){
         for(int j=0; j<arrayA.length; j++){
            double aux;
            if(arrayA[i] > arrayA[j]){
               aux = arrayA[j];
               arrayA[j] = arrayA[i];
               arrayA[i] = aux;
            }
         }
      }
   }
   
   public static void escreveResposta(double[] arrayA){
      String ordenado = ""; 
      for(int i=0; i<arrayA.length; i++){
         ordenado += arrayA[i] + ", ";
      }
      System.out.println("Array Ordenado: " + ordenado);
   }
}