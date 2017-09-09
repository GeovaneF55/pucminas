package bootup;

import java.io.*;

public class Bootup {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) {
        System.out.println("Bem vindo(a) à Bootup :)");
        int opcao;
        
        try {
            do {
                System.out.println("1- Controle de Alunos");
                System.out.println("0- Sair");

                opcao = Integer.parseInt(in.readLine());
                if (opcao == 1) {
                    ControleDeAluno.menuAluno();
                } else if (opcao == 0) {
                    System.out.println("Até mais! :)");
                } else {
                    System.out.println("Opção inválida!");
                }
            } while (opcao != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
