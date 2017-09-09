import java.util.Scanner;

public class EleicoesDA{
   public static void main(String[] args){
      int voto = 0,
          v_chapa1 = 0,
          v_chapa2 = 0;
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Eleições DA\n\n");
      // Votações
      do{
         // Opções de Voto
         System.out.println("\n--- Opções");
         System.out.println("1 - Chapa 1");
         System.out.println("2 - Chapa 2");
         System.out.println("0 - Sair");
         System.out.print("Vote em uma das chapas: ");
         voto = scanner.nextInt();
         // Contador de Voto
         switch(voto){
            case 1:
               v_chapa1++;
            break;
            case 2:
               v_chapa2++;
            break;
            case 0:
            break;
            default:
               System.out.println("\nOpção Inválida!");
            break;
         }
      }while(voto != 0);
      
      // Validação do Vencedor
      if(v_chapa1 > v_chapa2){
         if(v_chapa1 != 0){
            System.out.println("\nA chapa 1 foi campeã com: " + v_chapa1 + " votos");
            System.out.print("A chapa 2 ficou em 2 lugar com : " + v_chapa2 + " votos");
         }
      }else{
         if(v_chapa2 != 0){
            System.out.println("\nA chapa 2 foi campeã com: " + v_chapa2 + " votos");
            System.out.print("A chapa 1 ficou em 2 lugar com : " + v_chapa1 + " votos");
         }
      }
   }
}
