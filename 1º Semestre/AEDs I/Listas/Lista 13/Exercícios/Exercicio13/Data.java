import java.util.Scanner;

public class Data{
   private int dia;
   private int mes;
   private int ano;
   
   Data(){
      setDia(leDia());
      setMes(leMes());
      setAno(leAno());
   }
   
   Data(int dia, int mes, int ano){
      setDia(dia);
      setMes(mes);
      setAno(ano);
   }
   
   public void setDia(int dia){
      if(dia<1 || dia>31){
         this.dia = 1;
      } else{
         this.dia = dia;
      }
   }
   
   public void setMes(int mes){
      if(mes<1 || mes>12){
         this.mes = 1;
      } else{
         this.mes = mes;
      }
   }
   
   public void setAno(int ano){
      if(ano<1){
         this.ano = 1;
      } else{
         this.ano = ano;
      }
   }
   
   public int getDia(){
      return this.dia;
   }
   
   public int getMes(){
      return this.mes;
   }
   
   public int getAno(){
      return this.ano;
   }
   
   public int leDia(){
      Scanner scanner = new Scanner(System.in);
      int dia;
      System.out.print("\nDigite o dia do nascimento: ");
      try{
         dia = scanner.nextInt();
      }catch(Exception exception){
         dia = 1;
      }
      return dia;
   }
   
   public int leMes(){
      Scanner scanner = new Scanner(System.in);
      int mes;
      System.out.print("\nDigite o mês do nascimento: ");
      try{
         mes = scanner.nextInt();
      }catch(Exception exception){
         mes = 1;
      }
      return mes;
   }
   
   public int leAno(){
      Scanner scanner = new Scanner(System.in);
      int ano;
      System.out.print("\nDigite o dia do nascimento: ");
      try{
         ano = scanner.nextInt();
      }catch(Exception exception){
         ano = 1;
      }
      return ano;
   }
}