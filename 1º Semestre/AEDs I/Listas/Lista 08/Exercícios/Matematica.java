class Matematica{
   public static void decrescente(int num){
      if(num < 0) return;
      System.out.print(num + " ");
      decrescente(num-1);
   }
   
   public static int somaInteiros(int lim, int valor){
      if(valor <= lim) return lim;
      return valor + somaInteiros(lim, valor-1);
   }
   
   public static double somaCubos(int num){
      if(num <= 0) return 0.0;
      return Math.pow(num, 3) + somaCubos(num-1);
   }
   
   public static int fibonacci(int num){
      if(num <= 0) return 0;
      if(num <= 2) return 1;
      return fibonacci(num-1) + fibonacci(num-2);
   }
}