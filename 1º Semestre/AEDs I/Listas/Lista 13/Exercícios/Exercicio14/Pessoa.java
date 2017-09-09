import java.util.Scanner;

public class Pessoa{
   private String nome;
   private Data nascimento;
   
   private static int qtde;
   
   Pessoa(){
      setNome(leNome());
      setNascimento(leNascimento());
      addQtde();
   }
   
   Pessoa(String nome, Data nascimento){
      setNome(nome);
      setNascimento(nascimento);
      addQtde();
   }
   
   public void setNome(String nome){
      this.nome = nome;
   }
   
   public void setNascimento(Data nascimento){
      this.nascimento = nascimento;
   }
   
   public static void setQtde(int quantidade){
      if(quantidade<0){
         qtde = 0;
      } else{
         qtde = quantidade;
      }
   }
   
   public String getNome(){
      return this.nome;
   }
   
   public Data getNascimento(){
      return this.nascimento;
   }
   
   public static int getQtde(){
      return qtde;
   }
   
   public static void addQtde(){
      setQtde(getQtde()+1);
   }
   
   public String leNome(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDigite o nome: ");
      String nome = scanner.next();
      return nome;
   }
   
   public Data leNascimento(){
      Scanner scanner = new Scanner(System.in);
      Data nascimento = new Data();
      return nascimento;
   }
   
   public boolean ehAniversariante(int dia, int mes){
      boolean ehAniversariante = true;
      if(this.getNascimento().getDia() != dia || this.getNascimento().getMes() != mes){
         ehAniversariante = false;
      }
      return ehAniversariante;
   }
}