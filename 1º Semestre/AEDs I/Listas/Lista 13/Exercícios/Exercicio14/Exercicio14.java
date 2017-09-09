import java.util.Scanner;
public class Exercicio14{
   public static void main(String[] args){
      System.out.println("Exercício 14");
      menuPrincipal();
   }
   
   public static void menuPrincipal(){
      PessoaFisica[] pessoasFisicas = new PessoaFisica[100];
      PessoaJuridica[] pessoasJuridicas = new PessoaJuridica[100];
      int opcao;
      do{
         opcao = leOpcaoPrincipal();
         switch(opcao){
            case 0:
               System.out.println("\n Até logo!");
            break;
            case 1:
               novaPessoaFisica(pessoasFisicas);
            break;
            case 2:
               novaPessoaJuridica(pessoasJuridicas);
            break;
            case 3:
               escreveNomeAniversariantesFisicos(pessoasFisicas, leMes());
            break;
            case 4:
               escreveNomeAniversariantesJuridicos(pessoasJuridicas, leMes());
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
      "Qtde. Pessoas Físicas: " + PessoaFisica.getQtde() + "\n" +
      "Qtde. Pessoas Jurídicas: " + PessoaJuridica.getQtde() + "\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Cadastrar Pessoa Física\n" +
      "(2) - Cadastrar Pessoa Jurídica\n" +
      "(3) - Aniversariantes Pessoas Físicas\n" +
      "(4) - Aniversariantes Pessoas Jurídicas\n" +
      "Opção: ";
      System.out.print(menu);
      try{
         opcao = scanner.nextInt();
      }catch(Exception exception){
         opcao = -1;
      }
      return opcao;
   }
   
   public static void novaPessoaFisica(PessoaFisica[] pessoas){
      int qtde = PessoaFisica.getQtde();
      System.out.println("\nNova Pessoa Física pf[" + (qtde+1) + "]: ");
      PessoaFisica pes = new PessoaFisica();
      try{
         pessoas[qtde] = pes;
      }catch(Exception exception){
         pes = null;
         PessoaFisica.setQtde(qtde);
         System.err.printf("Não foi possível cadastrar uma nova Pessoa Física!\n");
      }
   }
   
   public static void novaPessoaJuridica(PessoaJuridica[] pessoas){
      int qtde = PessoaJuridica.getQtde();
      System.out.println("\nNova Pessoa Jurídica pj[" + (qtde+1) + "]: ");
      PessoaJuridica pes = new PessoaJuridica();
      try{
         pessoas[qtde] = pes;
      }catch(Exception exception){
         pes = null;
         PessoaJuridica.setQtde(qtde);
         System.err.printf("Não foi possível cadastrar uma nova Pessoa Jurídica!\n");
      }
   }
   
   public static void escreveNomeAniversariantesFisicos(PessoaFisica[] pessoas, int mes){
      String nomes = "";
      for(int i=0; i<PessoaFisica.getQtde(); i++){
         if(pessoas[i].getNascimento().getMes() == mes){
            nomes += pessoas[i].getNome() + "\n";
         }
      }
      System.out.print(nomes);
   }
   
   public static void escreveNomeAniversariantesJuridicos(PessoaJuridica[] pessoas, int mes){
      String nomes = "";
      for(int i=0; i<PessoaJuridica.getQtde(); i++){
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