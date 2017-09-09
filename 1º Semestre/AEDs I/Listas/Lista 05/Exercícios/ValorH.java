import java.util.Scanner;
public class ValorH{
   public static void main(String[] args){
      int n_termos = 0;
      double h = 0,
             divisor = 5;
      String str = new String("H = ");
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Calcular valor de H\n\n");
      // Número de Termos
      do{
         System.out.print("\nDigite o número de termos: ");
         n_termos = scanner.nextInt();
         if(n_termos <= 0){
            System.out.print("\nO número de termos deve ser positivo");   
         }
      }while(n_termos <= 0);
      // Criando String com os termos
      for(int i=0; i<n_termos; i++){
         if(i == (n_termos-1)){
            str+= "1/" + (int)divisor + " = ";
         }else{
            str+= "1/" + (int)divisor + " + ";
         }
         divisor += 2.0;
         h += (1.0/divisor);
      }
      System.out.print(str + "" + h);
   }
}