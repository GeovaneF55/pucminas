import java.util.Scanner;

public class Asteriscos{
   public static void main(String[] args){
      int qt_asteriscos = 0;
      String asteriscos = new String();
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Imprime padrão de asteríscos\n\n");
      // Número de asteríscos a ser desenhado na última linha
      do{
         System.out.print("\nDigite a quantidade de asteríscos da última linha: ");
         qt_asteriscos = scanner.nextInt();
         if(qt_asteriscos <= 0){
            System.out.print("\nA quantidade de asteríscos deve ser maior que 0");
         }
      }while(qt_asteriscos <= 0);
      // Desenhando os asteríscos
      for(int i = 0; i < qt_asteriscos; i++){
         asteriscos += "*";
         System.out.println(asteriscos);
      }
   }
}