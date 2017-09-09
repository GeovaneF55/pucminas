import java.util.Scanner;

public class Exemplo00{

   Triangulo triangulos[] = new Triangulo[100];
   int n_triangulos = 0;
   
   // Menu
   public void menu(){
      int opcao = 0;
      do{
         opcao = leOpcao();
         switch(opcao){
            case 0:
               System.out.println("\n Até logo!");
            break;
            case 1:
               novoTriangulo();
            break;
            case 2:
               if(n_triangulos>0){
                  listarTriangulos();
                  desejaContinuar();
               }
            break;
            case 3:
               if(n_triangulos>0){
                  pesquisarTriangulo();
                  desejaContinuar();
               }
            break;
            case 4:
               if(n_triangulos>0){
                  pesquisarTipoTriangulo();
                  desejaContinuar();
               }
            break;
            case 5:
               if(n_triangulos>0){
                  pesquisarPerimetroTriangulo();
                  desejaContinuar();
               }
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   public int leOpcao(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu ----\n\n" +
      "Qtde. Triângulos: " + this.n_triangulos + "\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Novo Triângulo\n" +
      "(2) - Listar Triângulos\n" +
      "(3) - Pesquisar triângulo em uma posição\n" +
      "(4) - Verificar tipo de um triângulo em uma posição\n" +
      "(5) - Verificar perímetro de um triângulo em uma posição\n" +
      "Opção: ";
      System.out.print(menu);
      try{
         opcao = scanner.nextInt();
      } catch(Exception e){
         opcao = -1;
      }
      return opcao;
   }
   
   //Métodos auxiliares
   public void desejaContinuar(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("\nDeseja continuar <enter>? ");
      scanner.nextLine();
   }
   
   public int lePosicao(){
      Scanner scanner = new Scanner(System.in);
      int posicao;
      do{
         System.out.print("\nDigite a posição do triângulo [1-" + this.n_triangulos + "]: ");
         posicao = scanner.nextInt();
         posicao -= 1;
         if(posicao<0 || posicao>(this.n_triangulos-1)){
            System.out.println("Posição inválida!");
         }
      }while(posicao<0 || posicao>(this.n_triangulos-1));
      return posicao;
   }
   
   //Opção 1
   public void novoTriangulo(){
      System.out.println("\nNovo Triângulo t[" + (this.n_triangulos+1) + "]: ");
      Triangulo tri = new Triangulo();
      this.triangulos[this.n_triangulos] = tri;
      this.n_triangulos += 1;
   }
   
   //Opção 2
   public void listarTriangulos(){
      for(int i=0; i<this.n_triangulos;i++){
         System.out.println("\nt[" + (i+1) + "]: ");
         this.triangulos[i].escreve();
      }
   }
   
   //Opção 3
   public void pesquisarTriangulo(){
      int pos = lePosicao();
      System.out.print("\nt[" + (pos+1) + "]: ");
      this.triangulos[pos].escreve();
   }
   
   //Opção 4
   public void pesquisarTipoTriangulo(){
      int pos = lePosicao();
      System.out.print("\nt[" + (pos+1) + "]: ");
      this.triangulos[pos].escreveTipo();
   }
   
   //Opção 5
   public void pesquisarPerimetroTriangulo(){
      int pos = lePosicao();
      System.out.print("\nPerímetro de t[" + (pos+1) + "]: " + this.triangulos[pos].perimetro());
   }
}