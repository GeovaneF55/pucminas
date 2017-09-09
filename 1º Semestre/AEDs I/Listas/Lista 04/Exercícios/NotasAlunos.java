import java.util.Scanner;
public class NotasAlunos{
   public static void main(String[] args){
      int NAlunos, aprovados = 0, reprovados = 0;
      double somaNota, notaProva, notaAluno, somaMedia = 0, maiorNota = 0;
      Scanner scanner = new Scanner(System.in);
      System.out.print("Notas de N Alunos\n\n");
      do{
         System.out.print("\nDigite a quantidade de Alunos na Turma: ");
         NAlunos = scanner.nextInt();
         if(NAlunos < 1){
            System.out.print("\nO número de alunos não pode ser menor que 1");
         }
      }while(NAlunos < 1);
      for(int i = 0; i < NAlunos; i++){
         somaNota = 0;
         for(int j = 0; j<3; j++){
            do{
               System.out.print("\nDigite a nota da prova " + (j+1) + " do aluno " + (i+1) + ": ");
               notaProva = scanner.nextInt();
               if(notaProva > 100 || notaProva < 0){
                  System.out.print("\nA nota deve ser entre 0 e 100");
               }
            }while(notaProva > 100 || notaProva < 0);
            somaNota += notaProva;
         }
         notaAluno = somaNota/3;
         if(notaAluno < 60){
            reprovados++;
            System.out.print("\nAluno REPROVADO");
         }else{
            aprovados++;
            System.out.print("\nAluno APROVADO");
         }
         if(maiorNota < notaAluno){
            maiorNota = notaAluno;
         }
         somaMedia += notaAluno;
      }
      System.out.print("\n\nO número de aprovados foram: " + aprovados);
      System.out.print("\nO número de reprovados foram: " + reprovados);
      System.out.print("\nA média da turma é: " + (somaMedia/NAlunos));
      System.out.print("\nA maior nota foi: " + maiorNota);
   }
}