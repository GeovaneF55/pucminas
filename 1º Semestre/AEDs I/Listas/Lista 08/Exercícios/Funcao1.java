public class Funcao1{
   public static void main(String[] args){
      int e1 = 0,
          e2 = 1,
          e3 = 4;
      introducao();
      escreveResultado(e1, f1(e1));
      escreveResultado(e2, f1(e2));
      escreveResultado(e3, f1(e3));
   }
   
   public static void introducao(){
      System.out.println("Resultado da função F1");
   }
   
   public static int f1(int n) {
      if (n == 0) return 1;
      else return (n * f1(n-1));
   }
   
   public static void escreveResultado(int entrada, int saida){
      System.out.println("f(" + entrada + ") = " + saida);
   }
}