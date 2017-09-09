import java.util.Scanner;
public class Exercicio12{
   public static void main(String[] args){
   
      saudacao();
      int[][] matriz = preencheMatriz();
      int minMax = calculaMinMax(matriz);
      escreveResultado(minMax);
   }
   
   public static void saudacao(){
      System.out.println("Cálculo elemento MINMAX da matriz!\n");
   }
   
   public static int[][] preencheMatriz(){
      Arquivo arq = new Arquivo("dados.dat");
      String[] linhas = arq.leArquivoLinhas();
      String[] valores = valores(linhas[0]);
      int matriz[][] = new int[linhas.length][valores.length];
      for(int i=0; i<linhas.length; i++){
         valores = valores(linhas[i]);
         for(int j=0; j<valores.length; j++){
            matriz[i][j] = Integer.parseInt(valores[j]);
         }
      }
      return matriz;
   }
   
   public static String[] valores(String linha){
      return linha.split(",", -1);
   }
   
   public static int calculaMinMax(int[][] matriz){
      int linhaMenorElemento = retornaLinhaMenorElemento(matriz);
      int minMax = matriz[linhaMenorElemento][0];
      for(int j=0; j < matriz[linhaMenorElemento].length; j++){
         if(matriz[linhaMenorElemento][j] > minMax){
            minMax = matriz[linhaMenorElemento][j];
         }
      }
      return minMax;
   }
   
   public static int retornaLinhaMenorElemento(int[][] matriz){
      int menor = matriz[0][0];
      int linha = 0;
      for(int i=0; i < matriz.length; i++){
         for(int j=0; j < matriz[i].length; j++){
            if(matriz[i][j] < menor){
               menor = matriz[i][j];
               linha = i;
            }
         }
      }
      return linha;
   }
   
   public static void escreveResultado(double minMax){
      System.out.println("O MINMAX é: " + minMax);
   }
}