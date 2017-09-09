public class NumeroDivisoes{
   public static void main(String[] args){
      double dividendo = 1,
             limite = 0.0001;
      int qt_divs = 0;
      
      System.out.print("Número de Divisões por 2 para alcançar limite 0.0001\n\n");
      // Divisões Sucessivas
      while(dividendo > 0.0001){
         dividendo /= 2.0;
         qt_divs ++;
      }
      
      System.out.print("\n A quantidade de divisões por 2 para que 1 fique menor que 0.0001 é: " +
      qt_divs);
   }
}
