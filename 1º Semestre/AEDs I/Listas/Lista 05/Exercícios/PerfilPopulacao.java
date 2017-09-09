import java.util.Scanner;
public class PerfilPopulacao{
   public static void main(String[] args){
      int idade = 0,
          totalPessoas = 0,
          totalMasculino = 0,
          totalFeminino = 0,
          idadeMaiorIdade = 0;
      char genero = 'M',
           generoMaiorIdade = 'M';
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Perfil de uma popula��o\n\n");
      
      // Digitar Idade
      do{
         System.out.print("\n(0 - Sair)\nDigite a idade (1 at� 150): ");
         idade = scanner.nextInt();
         if(idade < 0 || idade > 150){
            System.out.print("\nA idade deve estar entre 1 e 150");
         }
      }while(idade < 0 || idade > 150);
      
      // Digitar Gênero
      if(idade != 0){
         do{
            System.out.print("\nDigite o g�nero(M/F): ");
            genero = scanner.next().charAt(0);
            genero = Character.toUpperCase(genero);
            if(genero != 'F' && genero != 'M'){
               System.out.print("\nO g�nero deve ser M ou F");
            }
         }while(genero != 'F' && genero != 'M');
      }
      while(idade != 0){
         //Maior Idade
         if(idadeMaiorIdade < idade){
            idadeMaiorIdade = idade;
            generoMaiorIdade = genero;
         }
         
         //Total Masculino
         if(genero == 'M'){
            totalMasculino++;
         }
         
         //Total Feminino
         if(genero == 'F'){
            totalFeminino++;
         }
         
         //Total de Pessoas
         totalPessoas++;
      
         // Digitar Idade
         do{
            System.out.print("\n(0 - Sair)\nDigite a idade (1 at� 150): ");
            idade = scanner.nextInt();
            if(idade < 0 || idade > 150){
               System.out.print("\nA idade deve estar entre 1 e 150");
            }
         }while(idade < 0 || idade > 150);
         
         // Digitar G�nero
         if(idade != 0){
            do{
               System.out.print("\nDigite o genero(M/F): ");
               genero = scanner.next().charAt(0);
               genero = Character.toUpperCase(genero);
               if(genero != 'F' && genero != 'M'){
                  System.out.print("\nO g�nero deve ser M ou F");
               }
            }while(genero != 'F' && genero != 'M');
         }
      }
      
      if(totalMasculino != 0)System.out.print("\nO percentual masculino �: " + ((double)totalMasculino/(double)totalPessoas)*100 + "%");
      if(totalFeminino != 0)System.out.print("\nO percentual feminino �: " + ((double)totalFeminino/(double)totalPessoas)*100 + "%");
      if(idadeMaiorIdade != 0)System.out.print("\nA pessoa mais velha tem: " + idadeMaiorIdade + " e seu g�nero �: " + generoMaiorIdade);
   }
}