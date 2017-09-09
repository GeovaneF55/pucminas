import java.util.Scanner;
public class Exercicio13{
   public static void main(String[] args){
      System.out.println("Exercício 13");
      menuPrincipal();
   }
   
   public static void menuPrincipal(){
      Pessoa[] pessoas = new Pessoa[100];
      int opcao;
      do{
         opcao = leOpcaoPrincipal();
         switch(opcao){
            case 0:
               System.out.println("\n Até logo!");
            break;
            case 1:
               novaPessoa(pessoas);
            break;
            case 2:
               escreveNomeAniversariantes(pessoas, leMes());
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   public static int leOpcaoPrincipal(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu Principal ----\n\n" +
      "Qtde. Pessoas: " + Pessoa.getQtde() + "\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Cadastrar Pessoa\n" +
      "(2) - Aniversariantes\n" +
      "Opção: ";
      System.out.print(menu);
      try{
         opcao = scanner.nextInt();
      }catch(Exception exception){
         opcao = -1;
      }
      return opcao;
   }
   
   public static void novaPessoa(Pessoa[] pessoas){
      int qtde = Pessoa.getQtde();
      System.out.println("\nNova Pessoa p[" + (qtde+1) + "]: ");
      Pessoa pes = new Pessoa();
      try{
         pessoas[qtde] = pes;
      }catch(Exception exception){
         pes = null;
         Pessoa.setQtde(qtde);
         System.err.printf("Não foi possível cadastrar a Pessoa!\n");
      }
   }
   
   public static void escreveNomeAniversariantes(Pessoa[] pessoas, int mes){
      String nomes = "";
      for(int i=0; i<Pessoa.getQtde(); i++){
         if(pessoas[i].getNascimento().getMes() == mes){
            nomes += pessoas[i].getNome() + "\n";
         }
      }
      System.out.print(nomes);
   }
   
   public static int leMes(){
      Scanner scanner = new Scanner(System.in);
      int mes;
      System.out.print("\nDigite o mês dos aniversariantes: ");
      try{
         mes = scanner.nextInt();
      }catch(Exception exception){
         mes = 1;
      }
      return mes;
   }
}