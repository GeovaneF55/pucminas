public class SequenciaDivisao{
   public static void main(String[] args){
      int seq1 = 1, seq2 = 100;
      System.out.print("Dez primeiros termos da sequência\n\n");
      for(int i=0; i<10; i++){
         if(i%2 == 0){
            System.out.print(seq1 + "/" + seq2 + ", ");  
         }else{
            System.out.print(seq2 + "/" + seq1 + ", ");
         }
         seq1 += 1;
         seq2 -= 2;
      }
   }
}