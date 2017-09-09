class Circulo extends FiguraGeometrica{
   //Atributos do objeto
   
   private double raio;
   
   //Atributos estáticos
   
   private static int qtde;
   
   //Construtores
   
   Circulo(){
      this.setRaio(this.le());
      addQtde();
   }
   
   Circulo(double raio){
      this.setRaio(raio);
      addQtde();
   }
   
   //Setters
   
   public void setRaio(double raio){
      if(raio<0){
         this.raio = 0.0;
      } else{
         this.raio = raio;
      }
   }
   
   public static void setQtde(int quantidade){
      if(quantidade<=0){
         qtde = 0;
      } else{
         qtde = quantidade;
      }
   }
   
   //Getters
   
   public double getRaio(){
      return this.raio;
   }
   
   public static int getQtde(){
      return qtde;
   }
   
   //Métodos
   public static void addQtde(){
      setQtde(getQtde()+1);
   }
   
   public void escreve(){
      System.out.println("Raio: " + this.getRaio());
   }
   
   public double perimetro(){
      return 2*Math.PI*this.getRaio();
   }
   
   public double le(){
      System.out.print("Digite o raio do Círculo: ");
      return Teclado.leDimensao();
   }
}