public class Binario{
   public static void main(String[] args){
      System.out.print("Potências Binárias\n\n");
      // For para acrecentar o expoente
      for(int i = 0; i<10; i++){
         System.out.print("2 ê(" + i + ") = " + (int)Math.pow(2, i) + "\n");
      }
   }
}