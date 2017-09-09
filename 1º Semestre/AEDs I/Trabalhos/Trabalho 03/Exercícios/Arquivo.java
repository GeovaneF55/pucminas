import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Arquivo{
   
   //Atributos do objeto
   
   private File arquivo;
   
   //Construtores
   
   Arquivo(String nome){
      this.setArquivo(nome);
   }
   
   //Setters
   
   public void setArquivo(String nome){
      this.arquivo = this.validaArquivo(nome);
   }
   
   //Getters
   
   public File getArquivo(){
      return this.arquivo;
   }
   
   //Métodos
   
   public File validaArquivo(String nome){
      File arquivo = new File(nome);
      if(!arquivo.exists()){
         criaArquivo(arquivo);
      }
      return arquivo;
   }

   public void criaArquivo(File arquivo){
      try{
         arquivo.createNewFile();
      }catch(IOException ex){
         System.out.print(ex);
      }
   }
   
   public String leArquivo(){
      String conteudo = "";
      try(FileReader filereader = new FileReader(this.getArquivo())){
         int c = filereader.read();
         while(c != -1){
            conteudo += (char)c;
            c = filereader.read();
         }
      }catch(IOException ex){
         ex.printStackTrace();
      }
      return conteudo;
   }
   
   public String[] leArquivoLinhas(){
      String[] linhas = new String[this.qtNumLinhas()];
      try(Scanner scanner = new Scanner(this.getArquivo())){
         int count = 0;
         while(scanner.hasNext()){
            linhas[count] = scanner.nextLine();
            count++;
         }
         scanner.close();
      }catch(FileNotFoundException ex){
         ex.printStackTrace();
      }
      return linhas;
   }
   
   public void escreveArquivo(String escrever){
      try(FileWriter filewriter = new FileWriter(this.getArquivo())){
         filewriter.write(escrever);
         filewriter.flush();
      }catch(IOException ex){
         ex.printStackTrace();
      }
   }
   
   public void escreveArquivo(String escrever, boolean escreverApos){
      try(FileWriter filewriter = new FileWriter(this.getArquivo(), escreverApos)){
         filewriter.write(escrever);
         filewriter.flush();
      }catch(IOException ex){
         ex.printStackTrace();
      }
   }
   
   public int qtNumLinhas(){
      int qtLineNumber = 0;
      try(LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(this.getArquivo()))){
         linhaLeitura.skip(this.getArquivo().length());
         qtLineNumber = linhaLeitura.getLineNumber();
      }catch(IOException ex){
         ex.printStackTrace();
      }
      return (qtLineNumber+1);
   }
}