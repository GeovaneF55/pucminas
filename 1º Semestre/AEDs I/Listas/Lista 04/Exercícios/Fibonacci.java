public class Fibonacci{
   public static void main(String[] args){
      int seq = 1, a = 0, b = 0;
      System.out.print("Sequência de Fibonacci\n\n");
      for(int i=0; i<15; i++){
         System.out.print(seq + ", ");
         a = b;
         b = seq;
         seq = a + b;
      }
   }
}