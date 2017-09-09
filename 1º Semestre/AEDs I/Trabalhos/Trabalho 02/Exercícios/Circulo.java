import java.util.Scanner;

class Circulo extends FiguraGeometrica{
   private double raio;
   
   //Construtores
   
   Circulo(){
      this.setRaio(this.le());
   }
   
   Circulo(double raio){
      this.setRaio(raio);
   }
   
   //Setters
   
   public void setRaio(double raio){
      if(raio<=0){
         this.raio = 0.0;
      } else{
         this.raio = raio;
      }
   }
   
   //Getters
   
   public double getRaio(){
      return this.raio;
   }
   
   //Métodos
   
   public void escreve(){
      System.out.println("Raio: " + this.getRaio());
   }
   
   public double perimetro(){
      return 2*Math.PI*this.getRaio();
   }
   
   public double le(){
      Scanner scanner = new Scanner(System.in);
      double raio;
      System.out.print("Digite o raio do Círculo: ");
      try{
         raio = scanner.nextDouble();
      } catch(Exception e){
         raio = 0;
      }
      return raio;
   }
}