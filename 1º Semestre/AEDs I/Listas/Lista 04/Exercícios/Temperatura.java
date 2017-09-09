import java.util.Scanner;
public class Temperatura{
   public static void main(String[] args){
      double temp, maior = 1000, menor = -273, somaPos = 0, somaNeg = 0;
      int contPos = 0, contNeg = 0;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Dez temperaturasdez temperaturas\n");
      do{
         System.out.print("\nDigite a 1a Temperatura: ");
         temp = scanner.nextDouble();
         if(temp < -273 || temp > 1000){
            System.out.print("\nTemperatura deve ser maior que -273 e menor que 1000");
         }
      }while(temp < -273 || temp > 1000);
      maior = temp;
      menor = temp;
      if(temp>0){
         somaPos += temp;
         contPos++;
      }else{
         somaNeg += temp;
         contNeg++;
      }
      for(int i = 1; i<10; i++){
         do{
            System.out.print("\nDigite a " + (i+1) + "a Temperatura: ");
            temp = scanner.nextDouble();
            if(temp < -273 || temp > 1000){
               System.out.print("\nTemperatura deve ser maior que -273 e menor que 1000");
            }
         }while(temp < -273 || temp > 1000);
         if(temp>0){
            somaPos += temp;
            contPos++;
         }else{
            somaNeg += temp;
            contNeg++;
         }
         if(temp > maior){
            maior = temp;
         }
         if(temp < menor){
            menor = temp;
         }
      }
      if(contPos > 0) System.out.print("\nA média das temperaturas positivas é: " + (somaPos/contPos));
      if(contNeg > 0) System.out.print("\nA média das temperaturas negativas é: " + (somaNeg/contNeg));
      System.out.print("\nA amplitude térmica é: " + (maior - menor));
   }
}