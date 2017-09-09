public class Funcao2{
   public static void main(String[] args){
      int e1 = 0,
          e2 = 1,
          e3 = 5;
      introducao();
      escreveResultado(e1, f2(e1));
      escreveResultado(e2, f2(e2));
      escreveResultado(e3, f2(e3));
   }
   
   public static void introducao(){
      System.out.println("Resultado da função F2");
   }
   
   public static int f2(int n) {
      if (n == 0) return 1;
      if (n == 1) return 1;
      else return(f2(n-1)+ 2 * f2(n-2));
}
   
   public static void escreveResultado(int entrada, int saida){
      System.out.println("f(" + entrada + ") = " + saida);
   }
}