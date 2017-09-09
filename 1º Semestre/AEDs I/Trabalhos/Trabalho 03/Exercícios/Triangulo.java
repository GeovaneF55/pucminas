class Triangulo extends FiguraGeometrica{
   
   //Atributos do objeto
   
   private double lado1;
   private double lado2;
   private double lado3;
   
   //Atributos estáticos
   
   private static int qtde;
   
   //Construtores
   
   Triangulo(){
      this.setLado1(this.le());
      this.setLado2(this.le());
      this.setLado3(this.le());
      addQtde();
   }
   
   Triangulo(double lado1, double lado2, double lado3){
      this.setLado1(lado1);
      this.setLado2(lado2);
      this.setLado3(lado3);
      addQtde();
   }
   
   //Setters
   
   public void setLado1(double lado){
      if(lado<=0){
         this.lado1 = 0.0;
      } else{
         this.lado1 = lado;
      }
   }
   
   public void setLado2(double lado){
      if(lado<=0){
         this.lado2 = 0.0;
      } else{
         this.lado2 = lado;
      }
   }
   
   public void setLado3(double lado){
      if(lado<=0){
         this.lado3 = 0.0;
      } else{
         this.lado3 = lado;
      }
   }
   
   public static void setQtde(int quantidade){
      if(quantidade<0){
         qtde = 0;
      } else{
         qtde = quantidade;
      }
   }
   
   //Getters
   
   public double getLado1(){
      return this.lado1;
   }
   
   public double getLado2(){
      return this.lado2;
   }
   
   public double getLado3(){
      return this.lado3;
   }
   
   public static int getQtde(){
      return qtde;
   }
   
   //Métodos
   
   public static void addQtde(){
      setQtde(getQtde()+1);
   }
   
   public void escreve(){
      String resp;
      if(ehTriangulo()){      
            resp = "Lados(" + this.getLado1() +
            ", " + this.getLado2() +
            ", " + this.getLado3() + ")";
      } else{
         resp ="Os lados não formam um triângulo";
      }
      System.out.println(resp);
   }
   
   public void escreveTipo(){
      String resp;
      int tipoTriangulo = tipo();
      switch(tipoTriangulo){
         case 0:
            resp = "O triângulo não existe";
         break;
         case 1:
            resp = "É um triângulo equilátero";
         break;
         case 2:
            resp = "É um triângulo isóceles";
         break;
         case 3:
            resp = "É um triângulo escaleno";
         break;
         default:
            resp = "Erro de verificação";
         break;
      }
      System.out.println(resp);
   }
   
   public int tipo(){
      int tipo;
      if(!this.ehTriangulo()){
         tipo = 0;
      } else{
         if(this.getLado1() == this.getLado2() && this.getLado2() == this.getLado3() && this.getLado1() == this.getLado3()){
            tipo = 1;
         } else if(this.getLado1() == this.getLado2() || this.getLado2() == this.getLado3()){
            tipo = 2;
         } else{
            tipo = 3;
         }
      }
      return tipo;
   }
   
   public boolean ehTriangulo(){
      boolean ehTriangulo = true;
      if(!((Math.abs(this.getLado2() - this.getLado3()) < this.getLado1() && this.getLado1() < this.getLado2() + this.getLado3()) || (Math.abs(this.getLado1() - this.getLado3()) < this.getLado2() && this.getLado2() < this.getLado1() + this.getLado3()) || (Math.abs(this.getLado1() - this.getLado2()) < this.getLado3() && this.getLado3() < this.getLado1() + this.getLado2()))){
         ehTriangulo = false;
      }
      return ehTriangulo;
   }
   
   public double perimetro(){
      double perimetro = 0.0;
      if(this.ehTriangulo()){
         perimetro = this.getLado1() + this.getLado2() + this.getLado3();
      }
      return perimetro;
   }
   
   public double le(){
      System.out.print("Digite o lado do Triângulo: ");
      return Teclado.leDimensao();
   }
}