import java.util.Scanner;

public class Menu{

   Triangulo triangulos[];
   int n_triangulos;
   
   Circulo circulos[];
   int n_circulos;
   
   //Construtores
   Menu(){
      this.triangulos = new Triangulo[100];
      this.n_triangulos = 0;
      this.circulos = new Circulo[100];
      this.n_circulos = 0;
   }
   
   Menu(int qtde_triangulos, int qtde_circulos){
      this.triangulos = new Triangulo[qtde_triangulos];
      this.n_triangulos = 0;
      this.circulos = new Circulo[qtde_circulos];
      this.n_circulos = 0;
   }
   
   //setters
   public void setTriangulo(Triangulo triangulo, int i){
      this.triangulos[i] = triangulo;
   }
   
   public void setNTriangulos(int n_triangulos){
      this.n_triangulos = n_triangulos;
   }
   
   public void setCirculo(Circulo circulo, int i){
      this.circulos[i] = circulo;
   }
   
   public void setNCirculos(int n_circulos){
      this.n_circulos = n_circulos;
   }
   
   
   // getters
   public Triangulo getTriangulo(int i){
      return this.triangulos[i];
   }
   
   public int getNTriangulos(){
      return this.n_triangulos;
   }
   
   public Circulo getCirculo(int i){
      return this.circulos[i];
   }
   
   public int getNCirculos(){
      return this.n_circulos;
   }
   
   //Menus
   public void menuPrincipal(){
      int opcao = 0;
      do{
         opcao = leOpcaoPrincipal();
         switch(opcao){
            case 0:
               System.out.println("\n Até logo!");
            break;
            case 1:
               menuTriangulo();
            break;
            case 2:
               menuCirculo();
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   public void menuTriangulo(){
      int opcao = 0;
      do{
         opcao = leOpcaoTriangulo();
         switch(opcao){
            case 0:
               desejaContinuar();
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
                  pesquisarPerimetro(this.getTriangulo(lePosicao(this.getNTriangulos())));
                  desejaContinuar();
               }
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   public void menuCirculo(){
      int opcao = 0;
      do{
         opcao = leOpcaoCirculo();
         switch(opcao){
            case 0:
               desejaContinuar();
            break;
            case 1:
               novoCirculo();
            break;
            case 2:
               if(n_circulos>0){
                  listarCirculos();
                  desejaContinuar();
               }
            break;
            case 3:
               if(n_circulos>0){
                  pesquisarCirculo();
                  desejaContinuar();
               }
            break;
            case 4:
               if(n_circulos>0){
                  pesquisarPerimetro(this.getCirculo(lePosicao(this.getNCirculos())));
                  desejaContinuar();
               }
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   //Lê opções
   public int leOpcaoPrincipal(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu Principal ----\n\n" +
      "Qtde. Triângulos: " + this.getNTriangulos() + "\n" +
      "Qtde. Círculos: " + this.getNCirculos() + "\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Ir para o menu triângulos\n" +
      "(2) - Ir para o menu círculos\n" +
      "Opção: ";
      System.out.print(menu);
      try{
         opcao = scanner.nextInt();
      } catch(Exception e){
         opcao = -1;
      }
      return opcao;
   }
   
   public int leOpcaoTriangulo(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu Triângulo ----\n\n" +
      "Qtde. Triângulos: " + this.getNTriangulos() + "\n\n" +
      "(0) - Voltar menu principal\n" +
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
   
   public int leOpcaoCirculo(){
      Scanner scanner = new Scanner(System.in);
      int opcao;
      String menu = "\n---- Menu Círculo ----\n\n" +
      "Qtde. Círculos: " + this.getNCirculos() + "\n\n" +
      "(0) - Voltar menu principal\n" +
      "(1) - Novo Círculo\n" +
      "(2) - Listar Círculos\n" +
      "(3) - Pesquisar círculo em uma posição\n" +
      "(4) - Verificar perímetro de um círculo em uma posição\n" +
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
   
   public int lePosicao(int qtde_forma){
      Scanner scanner = new Scanner(System.in);
      int posicao;
      do{
         System.out.print("\nDigite a posição [1-" + qtde_forma + "]: ");
         try{
            posicao = scanner.nextInt();
         } catch(Exception e){
            posicao = 0;
            scanner = new Scanner(System.in);
         }
         posicao -= 1;
         if(posicao<0 || posicao>(qtde_forma-1)){
            System.out.println("Posição inválida!");
         }
      }while(posicao<0 || posicao>(qtde_forma-1));
      return posicao;
   }
   
   //OPÇÕES TRIÂNGULO
   //Triângulo Opção 1
   public void novoTriangulo(){
      System.out.println("\nNovo Triângulo t[" + (this.getNTriangulos()+1) + "]: ");
      Triangulo tri = new Triangulo();
      this.setTriangulo(tri, this.getNTriangulos());
      this.setNTriangulos(this.getNTriangulos()+1);
   }
   
   //Triângulo Opção 2
   public void listarTriangulos(){
      for(int i=0; i<this.getNTriangulos();i++){
         System.out.println("\nt[" + (i+1) + "]: ");
         this.getTriangulo(i).escreve();
      }
   }
   
   //Triângulo Opção 3
   public void pesquisarTriangulo(){
      int pos = lePosicao(this.getNTriangulos());
      System.out.print("\nt[" + (pos+1) + "]: ");
      this.getTriangulo(pos).escreve();
   }
   
   //Triângulo Opção 4
   public void pesquisarTipoTriangulo(){
      int pos = lePosicao(this.getNTriangulos());
      System.out.print("\nt[" + (pos+1) + "]: ");
      this.getTriangulo(pos).escreveTipo();
   }
   
   //OPÇÕES CÍRCULOS
   //Círculo Opção 1
   public void novoCirculo(){
      System.out.println("\nNovo Círculo c[" + (this.getNCirculos()+1) + "]: ");
      Circulo cir = new Circulo();
      this.setCirculo(cir, this.getNCirculos());
      this.setNCirculos(this.getNCirculos()+1);
   }
   
   //Círculo Opção 2
   public void listarCirculos(){
      for(int i=0; i<this.getNCirculos();i++){
         System.out.println("\nc[" + (i+1) + "]: ");
         this.getCirculo(i).escreve();
      }
   }
   
   //Círculo Opção 3
   public void pesquisarCirculo(){
      int pos = lePosicao(this.getNCirculos());
      System.out.print("\nc[" + (pos+1) + "]: ");
      this.getCirculo(pos).escreve();
   }
   
   
   //OPÇÕES POLIMÓFICAS
   //Triângulo Opção 5 e Círculo Opção 4
   public static void pesquisarPerimetro(FiguraGeometrica fig){
      System.out.print("\nPerímetro da figura f: " + fig.perimetro() + "\n");
   }
}