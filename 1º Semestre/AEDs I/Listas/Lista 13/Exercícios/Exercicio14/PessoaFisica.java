import java.util.Scanner;

public class PessoaFisica extends Pessoa{
   private String cpf;
   
   private static int qtde;
   
   PessoaFisica(){
      super();
      setCPF(leCPF());
      addQtde();
   }
   
   PessoaFisica(String nome, Data nascimento, String cpf){
      super(nome, nascimento);
      setCPF(cpf);
      addQtde();
   }
   
   public void setCPF(String cpf){
      this.cpf = cpf;
   }
   
   public static void setQtde(int quantidade){
      if(quantidade<0){
         qtde = 0;
      } else{
         qtde = quantidade;
      }
   }
   
   public String getCPF(){
      return this.cpf;
   }
   
   public static int getQtde(){
      return qtde;
   }
   
   public static void addQtde(){
      setQtde(getQtde()+1);
   }
   
   public String leCPF(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite o CPF: ");
      String cpf = scanner.next();
      return cpf;
   }
}