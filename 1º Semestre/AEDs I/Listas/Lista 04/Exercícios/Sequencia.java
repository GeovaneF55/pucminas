public class Sequencia{
   public static void main(String[] args){
      int seq = 5;
      System.out.print("Dez primeiros termos da sequ�ncia\n\n");
      for(int i=0; i<10; i++){
         System.out.print(seq + ", ");
         seq += 3;
      }
   }
}