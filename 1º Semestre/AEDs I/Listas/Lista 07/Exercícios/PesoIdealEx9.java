import java.util.Scanner;

public class PesoIdealEx9 {

   public static void introducao(){
      System.out.println("Peso Ideal de Homens e Mulheres Ex.9");
   }
   
   public static double leAltura() {
      Scanner scanner = new Scanner(System.in);
      double a;
      do{
         System.out.print("\nDigite a altura: ");
         a = scanner.nextDouble();
         if(a < 0){
            System.out.print("\nA altura não pode ser negativa!");
         }
      }while(a < 0);
      return a;
   }
   
   public static char leSexo() {
      Scanner scanner = new Scanner(System.in);
      char s;
      do{
         System.out.print("\nDigite o sexo (M|F): ");
         s = scanner.next().charAt(0);
         s = Character.toUpperCase(s);
         if(s != 'M' && s != 'F'){
            System.out.print("\nValor inválido! (F)eminino ou (M)asculino");
         }
      }while(s != 'M' && s != 'F');
      return s;
   }
   
   public static double pesoIdeal(double altura, char sexo){
      double pesoIdeal;
      if(sexo == 'M'){
         pesoIdeal = ((72.7 * altura) - 58.0);
      } else{
         pesoIdeal = ((62.1 * altura) - 44.7);
      }
      return pesoIdeal;
   }
   
   public static void mostraResultado(double altura, char sexo, double pesoIdeal){
      String resposta = "\n O peso Ideal de altura " + altura + " e sexo (" + sexo + ") é: " + pesoIdeal;
      System.out.print(resposta);
   }
    
   public static void main(String[] args) {
      introducao();
      double altura = leAltura();
      char sexo = leSexo();
      mostraResultado(altura, sexo, pesoIdeal(altura, sexo));
   }
}