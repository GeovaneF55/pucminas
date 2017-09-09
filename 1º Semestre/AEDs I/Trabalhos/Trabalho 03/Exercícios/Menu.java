public class Menu{

   //Atributos do objeto
   
   private Triangulo triangulos[];   
   private Circulo circulos[];
   
   //Atributos de arquivo
   private Arquivo arq_tri = new Arquivo("triangulos.txt");
   private Arquivo arq_cir = new Arquivo("circulos.txt");
   
   //Construtores
   
   Menu(){
      this.triangulos = new Triangulo[100];
      this.circulos = new Circulo[100];
      preencheTriangulos();
      preencheCirculos();
   }
   
   Menu(int qtde_triangulos, int qtde_circulos){
      this.triangulos = new Triangulo[qtde_triangulos];
      this.circulos = new Circulo[qtde_circulos];
   }
   
   //Setters
   
   public void setTriangulo(Triangulo triangulo, int i) throws ArrayIndexOutOfBoundsException{
      this.triangulos[i] = triangulo;
   }
   
   public void setCirculo(Circulo circulo, int i) throws ArrayIndexOutOfBoundsException{
      this.circulos[i] = circulo;
   }
   
   //Getters
   
   public Triangulo getTriangulo(int i) throws ArrayIndexOutOfBoundsException{
      return this.triangulos[i];
   }
   
   public Circulo getCirculo(int i) throws ArrayIndexOutOfBoundsException{
      return this.circulos[i];
   }
   
   //Menus
   
   public void menuPrincipal(){
      int opcao;
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
               Teclado.desejaContinuar();
            break;
            case 1:
               novoTriangulo();
            break;
            case 2:
               listarTriangulos();
               Teclado.desejaContinuar();
            break;
            case 3:
               pesquisarTriangulo();
               Teclado.desejaContinuar();
            break;
            case 4:
               pesquisarTipoTriangulo();
               Teclado.desejaContinuar();
            break;
            case 5:
               try{
                  Triangulo tri = this.getTriangulo(Teclado.lePosicao());
                  pesquisarPerimetro(tri);
               }catch(NullPointerException exception){
                  System.err.printf("\nNão existe triângulo nessa posição!\n");
               }catch(ArrayIndexOutOfBoundsException exception){
                  System.err.printf("\nPosição inválida!\n");
               }
               Teclado.desejaContinuar();
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
               Teclado.desejaContinuar();
            break;
            case 1:
               novoCirculo();
            break;
            case 2:
               listarCirculos();
               Teclado.desejaContinuar();
            break;
            case 3:
                pesquisarCirculo();
                Teclado.desejaContinuar();
            break;
            case 4:
               try{   
                  Circulo circ = this.getCirculo(Teclado.lePosicao());
                  pesquisarPerimetro(circ);
               }catch(NullPointerException exception){
                  System.err.printf("\nNão existe circulo nessa posição!\n");
               }catch(ArrayIndexOutOfBoundsException exception){
                  System.err.printf("\nPosição inválida!\n");
               }
               Teclado.desejaContinuar();
            break;
            default:
               System.out.println("\n Opção inválida!");
            break;
         }
      }while(opcao != 0);
   }
   
   //Lê opções
   
   public int leOpcaoPrincipal(){
      String menu = "\n---- Menu Principal ----\n\n" +
      "Qtde. Triângulos: " + Triangulo.getQtde() + "\n" +
      "Qtde. Círculos: " + Circulo.getQtde() + "\n\n" +
      "(0) - Sair do Programa\n" +
      "(1) - Ir para o menu triângulos\n" +
      "(2) - Ir para o menu círculos\n" +
      "Opção: ";
      System.out.print(menu);
      return Teclado.leOpcao();
   }
   
   public int leOpcaoTriangulo(){
      String menu = "\n---- Menu Triângulo ----\n\n" +
      "Qtde. Triângulos: " + Triangulo.getQtde() + "\n\n" +
      "(0) - Voltar menu principal\n" +
      "(1) - Novo Triângulo\n" +
      "(2) - Listar Triângulos\n" +
      "(3) - Pesquisar triângulo em uma posição\n" +
      "(4) - Verificar tipo de um triângulo em uma posição\n" +
      "(5) - Verificar perímetro de um triângulo em uma posição\n" +
      "Opção: ";
      System.out.print(menu);
      return Teclado.leOpcao();
   }
   
   public int leOpcaoCirculo(){
      String menu = "\n---- Menu Círculo ----\n\n" +
      "Qtde. Círculos: " + Circulo.getQtde() + "\n\n" +
      "(0) - Voltar menu principal\n" +
      "(1) - Novo Círculo\n" +
      "(2) - Listar Círculos\n" +
      "(3) - Pesquisar círculo em uma posição\n" +
      "(4) - Verificar perímetro de um círculo em uma posição\n" +
      "Opção: ";
      System.out.print(menu);
      return Teclado.leOpcao();
   }
   
   //Inicializa valores pelo arquivo
   
   public void preencheTriangulos(){
      Triangulo tri;
      String[] triangulos_arquivo = arq_tri.leArquivoLinhas();
      double lado[];
      int qtde;
      for(int i=0; i<triangulos_arquivo.length; i++){
         try{
            lado = lados(triangulos_arquivo[i]);
            qtde = Triangulo.getQtde();
            tri = new Triangulo(lado[0], lado[1], lado[2]);
            try{
               this.setTriangulo(tri, qtde);
            }catch(Exception exception){
               tri = null;
               Triangulo.setQtde(qtde);
            }
         }catch(NullPointerException nullPointerException){
            //Não usa arquivo para iniciar valores
         }
      }
   }
   
   public double[] lados(String triangulo){
      String[] lados_S = triangulo.split("\\|", -1);
      double[] lados_I = new double[3];
      for(int i=0; i<lados_I.length; i++){
         lados_I[i] = Double.parseDouble(lados_S[i]);
      }
      return lados_I;
   } 
   
   public void preencheCirculos(){
      Circulo cir;
      String[] circulos_arquivo = arq_cir.leArquivoLinhas();
      double raio;
      int qtde;
      
      for(int i=0; i<circulos_arquivo.length; i++){
         try{
            raio = raio(circulos_arquivo[i]);
            qtde = Circulo.getQtde();
            cir = new Circulo(raio);
            try{
               this.setCirculo(cir, qtde);
            }catch(Exception exception){
               cir = null;
               Circulo.setQtde(qtde);
            }
         }catch(NullPointerException nullPointerException){
            //Não usa arquivo para iniciar valores
         } 
      }
   }
   
   public double raio(String circulo){
      return Double.parseDouble(circulo);
   }
      
   //OPÇÕES TRIÂNGULO
   //Triângulo Opção 1
   
   public void novoTriangulo(){
      int qtde = Triangulo.getQtde();
      System.out.println("\nNovo Triângulo t[" + (qtde+1) + "]: ");
      Triangulo tri = new Triangulo();
      try{
         this.setTriangulo(tri, qtde);
         arq_tri.escreveArquivo(Double.toString(tri.getLado1())+"|"+
                                Double.toString(tri.getLado2())+"|"+
                                Double.toString(tri.getLado3())+"\n", true);
      }catch(Exception exception){
         tri = null;
         Triangulo.setQtde(qtde);
         System.err.printf("Não foi possível adicionar o triângulo!\n");
      }
   }
   
   //Triângulo Opção 2
   
   public void listarTriangulos(){
      for(int i=0; i<Triangulo.getQtde(); i++){
         System.out.println("\nt[" + (i+1) + "]: ");
         try{
            this.getTriangulo(i).escreve();
         }catch(NullPointerException exception){
            System.err.printf("\nNão existe triângulo nessa posição!\n");
         }catch(ArrayIndexOutOfBoundsException exception){
            System.err.printf("\nPosição inválida!\n");
         }
      }
   }
   
   //Triângulo Opção 3
   
   public void pesquisarTriangulo(){
      int pos = Teclado.lePosicao();
      System.out.print("\nt[" + (pos+1) + "]: ");
      try{
         this.getTriangulo(pos).escreve();
      }catch(NullPointerException exception){
         System.err.printf("\nNão existe triângulo nessa posição!\n");
      }catch(ArrayIndexOutOfBoundsException exception){
         System.err.printf("\nPosição inválida!\n");
      }
   }
   
   //Triângulo Opção 4
   
   public void pesquisarTipoTriangulo(){
      int pos = Teclado.lePosicao();
      System.out.print("\nt[" + (pos+1) + "]: ");
      try{
         this.getTriangulo(pos).escreveTipo();
      }catch(NullPointerException exception){
         System.err.printf("\nNão existe triângulo nessa posição!\n");
      }catch(ArrayIndexOutOfBoundsException exception){
         System.err.printf("\nPosição inválida!\n");
      }
   }
   
   //OPÇÕES CÍRCULOS
   //Círculo Opção 1
   
   public void novoCirculo(){
      int qtde = Circulo.getQtde();
      System.out.println("\nNovo Círculo c[" + (qtde+1) + "]: ");
      Circulo cir = new Circulo();
      try{
         this.setCirculo(cir, qtde);
         arq_cir.escreveArquivo(Double.toString(cir.getRaio())+"\n", true);
      }catch(Exception exception){
         cir = null;
         Circulo.setQtde(qtde);
         System.err.printf("Não foi possível adicionar o circulo!\n");
      }
   }
   
   //Círculo Opção 2
   
   public void listarCirculos(){
      for(int i=0; i<Circulo.getQtde(); i++){
         System.out.println("\nc[" + (i+1) + "]: ");
         try{
            this.getCirculo(i).escreve();
         }catch(NullPointerException exception){
            System.err.printf("\nNão existe circulo nessa posição!\n");
         }catch(ArrayIndexOutOfBoundsException exception){
            System.err.printf("\nPosição inválida!\n");
         }
      }
   }
   
   //Círculo Opção 3
   
   public void pesquisarCirculo(){
      int pos = Teclado.lePosicao();
      System.out.print("\nc[" + (pos+1) + "]: ");
      try{
         this.getCirculo(pos).escreve();
      }catch(NullPointerException exception){
         System.err.printf("\nNão existe circulo nessa posição!\n");
      }catch(ArrayIndexOutOfBoundsException exception){
         System.err.printf("\nPosição inválida!\n");
      }
   }
   
   
   //OPÇÕES POLIMÓFICAS
   //Triângulo Opção 5 e Círculo Opção 4
   
   public static void pesquisarPerimetro(FiguraGeometrica fig){
      System.out.print("\nPerímetro da figura f: " + fig.perimetro() + "\n");
   }
}