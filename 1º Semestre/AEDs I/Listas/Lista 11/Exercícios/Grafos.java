import java.util.Scanner;
 
public class Grafos{
   public static void main(String[] args){
      saudacao();
      menu();
   }
   
   public static void saudacao(){
      System.out.println(" Grafos de amizade!");
   }
   
   public static void menu(){
      int qtPessoas = 3;
      String[] nomes = new String[qtPessoas];
      int[][] amizades = new int[qtPessoas][qtPessoas];
      int opcao = 0;
      do{
         opcao = leOpcao();
         switch(opcao){
            case 0:
               System.out.println("\n At� logo!");
            break;
            case 1:
               cadastrarNomes(nomes);
            break;
            case 2:
               cadastrarAmizades(nomes, amizades);
            break;
            case 3:
               pesquisarAmigos(nomes, amizades);
               desejaContinuar();
            break;
            case 4:
               pesquisarPessoaComMaisAmigos(nomes, amizades);
               desejaContinuar();
            break;
            case 5:
               verificaInconsistencia(nomes, amizades);
               desejaContinuar();
            break;
            case 6:
               verificaAmizadesNaoCorrespondidas(nomes, amizades);
               desejaContinuar();
            break;
            default:
               System.out.println("\n Op��o inv�lida!");
            break;
         }
      }while(opcao != 0);
   }
   
   public static int leOpcao(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu ----\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Cadastrar os nomes de todas as pessoas\n" +
      "(2) - Cadastrar as rela��es de amizade de uma pessoa\n" +
      "(3) - Pesquisar quais s�o os amigos de uma pessoa.\n" +
      "(4) - Pesquisar qual a pessoa com o maior n�mero de amigos\n" +
      "(5) - Verificar inconsist�ncia\n" +
      "(6) - Verificar amizades n�o correspondidas\n" +
      "Op��o: ";
      System.out.print(menu);
      try{
         opcao = scanner.nextInt();
      } catch(Exception e){
         opcao = -1;
      }
      return opcao;
   }
   
   public static void desejaContinuar(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDeseja continuar <enter>? ");
      scanner.nextLine();
   }
   
   // m�dotos do 1
   public static void cadastrarNomes(String[] nomes){
      for(int i=0; i < nomes.length; i++){
         System.out.print("\nDigite o nome da pessoa " + (i+1) + ": ");
         nomes[i] = leNome();
      }
   }
   
   public static String leNome(){
      Scanner scanner = new Scanner(System.in);
      return scanner.next();
   }
   
   // m�dotos do 2
   public static void cadastrarAmizades(String[] nomes, int[][] amizades){
      for(int i=0; i<amizades.length; i++ ){
         System.out.print("\n (" + i + ") - " + nomes[i] + " : ");
         for(int j=0; j<amizades[i].length; j++ ){
            if( i == j){
               amizades[i][j] = 0;
            } else{
               System.out.print("\n � amigo de " + nomes[j] + "?");
               amizades[i][j] = leAmizade();
            }
         }
      }
   }
   
   public static int leAmizade(){
      Scanner scanner = new Scanner(System.in);
      int ehAmigo = 0;
      char amigo;
      do{
         System.out.print("\n � amigo (S|N)? ");
         amigo = scanner.next().charAt(0);
         amigo = Character.toUpperCase(amigo);
         if(amigo != 'S' && amigo != 'N'){
            System.out.print("\n Valor inv�lido!");
         }
      }while(amigo != 'S' && amigo != 'N');
      if(amigo == 'S'){
         ehAmigo = 1;
      }
      return ehAmigo;
   }
   
   // m�dotos do 3
   public static void pesquisarAmigos(String[] nomes, int[][] amizades){
      int codPessoa = escolhePessoa(nomes), cont = 0;
      String resposta = "\nOs amigos da pessoa " + nomes[codPessoa] + " com c�digo " + (codPessoa+1) + " s�o: \n";
      for(int j=0; j<amizades[codPessoa].length; j++){
         if(amizades[codPessoa][j] == 1){
            resposta += (j+1) + " - " + nomes[j] + "\n";
            cont++;
         }
      }
      if(cont == 0){
         resposta += "Nenhum amigo!\n";
      }
      System.out.print(resposta);
   }
   
   public static int escolhePessoa(String[] nomes){
      String listaPessoas = "\nPessoas cadastradas:\n";
      for(int i=0; i<nomes.length; i++){
         listaPessoas += (i+1) + " - " + nomes[i] + "\n";
      }
      System.out.print(listaPessoas);
      return lePessoa(nomes);
   }
   
   public static int lePessoa(String[] nomes){
      Scanner scanner = new Scanner(System.in);
      int codPessoa;
      do{
         System.out.print("\n Digite o c�digo da pessoa de 1 � " + nomes.length + ": ");
         codPessoa = scanner.nextInt();
         if(codPessoa < 1 || codPessoa > nomes.length){
            System.out.print("\n C�digo inv�lido!");
         }
      }while(codPessoa < 1 || codPessoa > nomes.length);
      return (codPessoa-1);
   }
   
   // m�dotos do 4
   public static void pesquisarPessoaComMaisAmigos(String[] nomes, int[][] amizades){
      String resposta;
      int codPessoaMaisAmigos=0;
      int qtAmigosPessoaMaisAmigos=0;
      int qtAmigos;
      for(int i=0; i<amizades.length; i++){
         qtAmigos=0;
         for(int j=0; j<amizades[i].length; j++){
            if(amizades[i][j] == 1){
               qtAmigos++;
            }
         }
         if(qtAmigos > qtAmigosPessoaMaisAmigos){
            codPessoaMaisAmigos = i;
            qtAmigosPessoaMaisAmigos = qtAmigos;
         }
      }
      resposta = "\nA pessoa com mais amigos �: " + (codPessoaMaisAmigos+1) + " - " + nomes[codPessoaMaisAmigos] + " com " + qtAmigosPessoaMaisAmigos + " amigo(s)\n";
      System.out.print(resposta);
   }
   
   // m�dotos do 5
   public static void verificaInconsistencia(String[] nomes, int[][] amizades){
      String resp = "";
      String pessoasInconsistentes = retornaPessoasInconsistentes(nomes, amizades);
      if(pessoasInconsistentes != ""){
         resp = "\n Houve inconsist�ncia. As seguintes pessoas s�o amigas delas mesmas: " + pessoasInconsistentes;
      } else{
         resp = "\n N�o houve inconsist�ncia.";
      }
      System.out.println(resp);
   }
   
   public static String retornaPessoasInconsistentes(String[] nomes, int[][] amizades){
      String pessoasInconsistentes = "";
      for(int i=0; i<amizades.length; i++){
         if(amizades[i][i] == 1){
            pessoasInconsistentes += "\n" + (i+1) + " - " + nomes[i];
         }
      }
      return pessoasInconsistentes;
   }
   
   // m�dotos do 6
   public static void verificaAmizadesNaoCorrespondidas(String[] nomes, int[][] amizades){
      String resp = "";
      String pessoasComAmizadesNaoCorrespondidas = retornaPessoasComAmizadesNaoCorrespondidas(nomes, amizades);
      if(pessoasComAmizadesNaoCorrespondidas != ""){
         resp = "\n As seguintes amizades n�o s�o correspondidas: " + pessoasComAmizadesNaoCorrespondidas;
      } else{
         resp = "\n N�o h� amizades n�o correspondidas.";
      }
      System.out.println(resp);
   }
   
   public static String retornaPessoasComAmizadesNaoCorrespondidas(String[] nomes, int[][] amizades){
      String pessoasComAmizadesNaoCorrespondidas = "";
      for(int i=0; i<amizades.length; i++){
         for(int j=0; j<amizades[i].length; j++){
            if(amizades[i][j] != amizades[j][i]){
            pessoasComAmizadesNaoCorrespondidas += "\n" + (i+1) + " - " + nomes[i] + " com " + (j+1) + " - " + nomes[j];
         }
         }
      }
      return pessoasComAmizadesNaoCorrespondidas;
   }
}