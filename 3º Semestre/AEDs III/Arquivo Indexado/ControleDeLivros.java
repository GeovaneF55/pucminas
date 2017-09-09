package aed3;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class ControleDeLivros {
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Livro> arquivo;

   public static void main(String[] args) {

       try {
           arquivo = new ArquivoIndexado<>(Livro.class, "livros.db", "livros1.idx", "livros2.idx");
           
           // menu
           int opcao;
           do {
               System.out.println("\n\nCADASTRO DE LIVROS\n");
               System.out.println("1 - Listar livros");
               System.out.println("2 - Incluir livro");
               System.out.println("3 - Alterar livro");
               System.out.println("4 - Excluir livro");
               System.out.println("5 - Buscar livro por código");
               System.out.println("6 - Buscar livro por título");
               System.out.println("7 - Reorganizar arquivo");
               System.out.println("9 - Povoar BD");
               System.out.println("0 - Sair");
               System.out.print("\nOpcao: ");
               opcao = Integer.valueOf(console.nextLine());
               
               switch(opcao) {
                   case 1: listarLivro(); break;
                   case 2: incluirLivro(); break;
                   case 3: alterarLivro(); break;
                   case 4: excluirLivro(); break;
                   case 5: buscarLivroCodigo(); break;
                   case 6: buscarLivroTitulo(); break;
                   case 7: reorganizar(); break;
                   case 9: povoar(); break;
                   case 0: break;
                   default: System.out.println("Opção inválida");
               }
               
           } while(opcao!=0);
       } catch(Exception e) {
           e.printStackTrace();
       }
       
   }
   
   // LIVROS
   
   public static void listarLivro() throws Exception {
       
       Object[] livros = arquivo.listar();
       
       for(int i=0; i<livros.length; i++) {
           System.out.println((Livro)livros[i]);
       }
       
   }
   
   public static void incluirLivro() throws Exception {
       
       String titulo, autor;
       float preco;
       
       System.out.println("\nINCLUSÃO");
       System.out.print("Título: ");
       titulo = console.nextLine();
       System.out.print("Autor: ");
       autor = console.nextLine();
       System.out.print("Preço: ");
       preco = Float.valueOf(console.nextLine());
       
       System.out.print("\nConfirma inclusão? ");
       char confirma = console.nextLine().charAt(0);
       if(confirma=='s' || confirma=='S') {
           Livro l = new Livro(-1, titulo, autor, preco);
           int cod = arquivo.incluir(l);
           System.out.println("Livro incluído com código: "+cod);
       }
   }

   
   public static void alterarLivro() throws Exception {
       
       System.out.println("\nALTERAÇÃO");

       int codigo;
       System.out.print("Código: ");
       codigo = Integer.valueOf(console.nextLine());
       if(codigo <=0) 
           return;
       
       Livro l;
       if( (l = (Livro)arquivo.buscarCodigo(codigo))!=null ) {
            System.out.println(l);
            
            String titulo, autor, preco;
            
            System.out.print("\nNovo título: ");
            titulo = console.nextLine();
            System.out.print("Novo autor: ");
            autor = console.nextLine();
            System.out.print("Novo preço: ");
            preco = console.nextLine();

            System.out.print("\nConfirma alteração? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S') {
                
                l.titulo = (titulo.length()>0?titulo:l.titulo);
                l.autor = (autor.length()>0?autor:l.autor);
                l.preco = (preco.length()>0?Float.valueOf(preco):l.preco);
                
                if( arquivo.alterar(l) ) 
                    System.out.println("Livro alterado.");
                else
                    System.out.println("Livro não pode ser alterado.");
            }
       }
       else
           System.out.println("Livro não encontrado");
       
   }
  
   
   public static void excluirLivro() throws Exception {
       
       System.out.println("\nEXCLUSÃO");

       int codigo;
       System.out.print("Código: ");
       codigo = Integer.valueOf(console.nextLine());
       if(codigo <=0) 
           return;
       
       Livro l;
       if( (l = (Livro)arquivo.buscarCodigo(codigo))!=null ) {
            System.out.println(l);
            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S') {
                if( arquivo.excluir(codigo) ) {
                    System.out.println("Livro excluído.");
                }
            }
       }
       else
           System.out.println("Livro não encontrado");
       
   }
   
   
   public static void buscarLivroCodigo() throws Exception {
       
       System.out.println("\nBUSCA POR CÓDIGO");
       
       int codigo;
       System.out.print("Código: ");
       codigo = Integer.valueOf(console.nextLine());
       if(codigo <=0) 
           return; 
       
       Livro l;
       if( (l = (Livro)arquivo.buscarCodigo(codigo))!=null )
           System.out.println(l);
       else
           System.out.println("Livro não encontrado");
   }

   public static void buscarLivroTitulo() throws Exception {
       
       System.out.println("\nBUSCA POR TÍTULO");
       
       String titulo;
       System.out.print("Título: ");
       titulo = console.nextLine();
       if(titulo == "") 
           return;
       
       Livro l;
       if( (l = (Livro)arquivo.buscarString(titulo))!=null )
           System.out.println(l);
       else
           System.out.println("Livro não encontrado");
   }

   public static void reorganizar() throws Exception {

        System.out.println("\nREORGANIZAÇÃO");
        arquivo.reorganizar();
        System.out.println("\nArquivo de livros reorganizado");
    
   }
   
   public static void povoar() throws Exception {
        arquivo.incluir(new Livro(-1,"O Pequeno Príncipe","Antoine de Saint-Exupéry",(float)27.9));
        arquivo.incluir(new Livro(-1,"Número Zero","Humberto Eco",(float)14.9));
        arquivo.incluir(new Livro(-1,"A Garota no Trem","Paula Hawkins",(float)20.9));
        arquivo.incluir(new Livro(-1,"A Rainha Vermelha","Victoria Aveyard",(float)22.1));
        arquivo.incluir(new Livro(-1,"O Sol É Para Todos","Harper Lee",(float)27));
        arquivo.incluir(new Livro(-1,"1984","George Orwell",(float)32.8));
        arquivo.incluir(new Livro(-1,"A Odisséia","Homero",(float)35.9));
        arquivo.incluir(new Livro(-1,"Sherlock Holmes","Arthur Conan Doyle",(float)24));
        arquivo.incluir(new Livro(-1,"Joyland","Stephen King",(float)17.9));
        arquivo.incluir(new Livro(-1,"Objetos Cortantes","Gillian Flynn",(float)16.9));
        arquivo.incluir(new Livro(-1,"A Lista Negra","Jennifer Brown",(float)16.9));
        arquivo.incluir(new Livro(-1,"Garota Exemplar","Gillian Flynn",(float)14.9));
        arquivo.incluir(new Livro(-1,"O Iluminado","Stephen King",(float)14.9));
        arquivo.incluir(new Livro(-1,"Queda de Gigantes","Ken Follett",(float)23.67));
        arquivo.incluir(new Livro(-1,"Eternidade Por Um Fio","Ken Follett",(float)30.1));
        arquivo.incluir(new Livro(-1,"Inverno do Mundo","Ken Follett",(float)29.99));
        arquivo.incluir(new Livro(-1,"A Guerra dos Tronos","George R. R. Martin",(float)22.76));
        arquivo.incluir(new Livro(-1,"A Revolução dos Bichos","George Orwell",(float)27.36));
        arquivo.incluir(new Livro(-1,"O Mundo de Sofia","Jostein Gaarder",(float)28.2));
        arquivo.incluir(new Livro(-1,"O Velho e o Mar","Ernest Hemingway",(float)16.9));
        arquivo.incluir(new Livro(-1,"Escuridão Total Sem Estrelas","Stephen King",(float)28.4));
        arquivo.incluir(new Livro(-1,"O Pintassilgo","Donna Tartt",(float)21.63));
        arquivo.incluir(new Livro(-1,"Se Eu Ficar","Gayle Forman",(float)13.54));
        arquivo.incluir(new Livro(-1,"Toda Luz Que Não Podemos Ver","Anthony Doerr",(float)24.38));
        arquivo.incluir(new Livro(-1,"Eu, Você e a Garota Que Vai Morrer","Jesse Andrews",(float)14.9));
        arquivo.incluir(new Livro(-1,"Na Natureza Selvagem","Jon Krakauer",(float)14.9));
        arquivo.incluir(new Livro(-1,"Eu, Robô","Isaac Asimov",(float)20.15));
        arquivo.incluir(new Livro(-1,"O Demonologista","Andrew Pyper",(float)32.47));
        arquivo.incluir(new Livro(-1,"O Último Policial","Ben Winters",(float)27.6));
        arquivo.incluir(new Livro(-1,"A Febre","Megan Abbott",(float)27.9));        
       
   }
   
}
