import java.util.Scanner;

public class PessoaJuridica extends Pessoa{
      private String cnpj;
   
   private static int qtde;
   
   PessoaJuridica(){
      super();
      setCPF(leCNPJ());
      addQtde();
   }
   
   PessoaJuridica(String nome, Data nascimento, String cnpj){
      super(nome, nascimento);
      setCPF(cnpj);
      addQtde();
   }
   
   public void setCPF(String cnpj){
      this.cnpj = cnpj;
   }
   
   public static void setQtde(int quantidade){
      if(quantidade<0){
         qtde = 0;
      } else{
         qtde = quantidade;
      }
   }
   
   public String getCNPJ(){
      return this.cnpj;
   }
   
   public static int getQtde(){
      return qtde;
   }
   
   public static void addQtde(){
      setQtde(getQtde()+1);
   }
   
   public String leCNPJ(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite o CNPJ: ");
      String cnpj = scanner.next();
      return cnpj;
   }
}