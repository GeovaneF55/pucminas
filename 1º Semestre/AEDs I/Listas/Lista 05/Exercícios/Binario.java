public class Binario{
   public static void main(String[] args){
      System.out.print("Pot�ncias Bin�rias\n\n");
      // For para acrecentar o expoente
      for(int i = 0; i<10; i++){
         System.out.print("2 �(" + i + ") = " + (int)Math.pow(2, i) + "\n");
      }
   }
}