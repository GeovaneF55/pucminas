package bootup;
import java.io.*;

public class ControleDeAluno {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static ArquivoIndexado<Aluno> arquivo;

    public static void menuAluno() {
        try {
            int opcao;
            arquivo = new ArquivoIndexado<>(Aluno.class, "alunos.db", "alunos1.idx", "alunos2.idx");

            do {
                System.out.println("\nControle de Alunos");
                System.out.println("Selecione a opção desejada");
                System.out.println("1- Listar Alunos");
                System.out.println("2- Incluir Aluno");
                System.out.println("3- Alterar Aluno");
                System.out.println("4- Excluir Aluno");
                System.out.println("5- Buscar Aluno por código");
                System.out.println("6- Buscar Aluno por nome");
                System.out.println("7- Reorganizar arquivo");
                System.out.println("8- Povoar BD");
                System.out.println("0- Sair");
                System.out.print("Opção: ");
                opcao = Integer.parseInt(in.readLine());

                if (opcao == 1) {
                    listarAluno();
                } else if (opcao == 2) {
                    incluirAluno();
                } else if (opcao == 3) {
                    alterarAluno();
                } else if (opcao == 4) {
                    excluirAluno();
                } else if (opcao == 5) {
                    buscarAlunoCodigo();
                } else if (opcao == 6) {
                    buscarAlunoNome();
                } else if (opcao == 7) {
                    reorganizar();
                } else if (opcao == 8) {
                    povoar();
                } else if (opcao == 0) {
                    System.out.println("Voltando ao menu principal! :) ");
                } else {
                    System.out.println("Opção inválida! :(");
                }

            } while (opcao != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarAluno() throws Exception {
        System.out.println("\n----- LISTAGEM -----");
        Object[] alunos = arquivo.listar();
        for (Object aluno : alunos) {
            System.out.println((Aluno) aluno);
            System.out.println("");
        }
        System.out.println("");
    }

    private static void incluirAluno() throws Exception {
        String nome, email, senha, matricula, curso;
        String telefone;
        
        System.out.println("\n----- INCLUSÃO -----");
        
        // Lendo atributos do aluno.
        // Falta Regexp em email e telefone.
        System.out.print("Nome: ");
        nome = in.readLine();
        
        System.out.print("E-mail: ");
        email = in.readLine();
        
        System.out.print("Senha: ");
        senha = in.readLine();
        
        System.out.print("Matrícula: ");
        matricula = in.readLine();
        
        System.out.print("Curso: ");
        curso = in.readLine();

        System.out.print("Telefone: ");
        telefone = in.readLine();
        // Inserindo Aluno.
        char confirma = 'N';
        do {
            try {
                System.out.println("Confirma inclusão? (S/N)");
                confirma = Character.toUpperCase(in.readLine().charAt(0));
                if (confirma == 'S') {
                    Aluno novoAluno = new Aluno(-1, nome, email, senha, telefone, matricula, curso);

                    int cod = arquivo.incluir(novoAluno);
                    System.out.println("Aluno incluido com código " + cod + " :)\n");
                } else if (confirma == 'N') {
                    System.out.println("Aluno não incluso :(\n");
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch(Exception e) {
                System.out.println("Opção inválida");
            }
        } while (confirma != 'S' && confirma != 'N');
    }

    private static void alterarAluno() throws Exception {
        System.out.println("\n----- ALTERAÇÂO -----");
        
        int codigo = getCodigo();
        Aluno alunoAlterado = (Aluno) arquivo.buscarCodigo(codigo);
        
        if (alunoAlterado != null) {
            System.out.println(alunoAlterado);
            
            String nome, email, senha, matricula, curso;
            String telefone;

            // Lendo atributos do aluno.
            // Falta Regexp em email e telefone.
            System.out.print("Nome: ");
            nome = in.readLine();

            System.out.print("E-mail: ");
            email = in.readLine();

            System.out.print("Senha: ");
            senha = in.readLine();

            System.out.print("Matrícula: ");
            matricula = in.readLine();

            System.out.print("Curso: ");
            curso = in.readLine();

            System.out.print("Telefone: ");
            telefone = in.readLine();

            // Inserindo Aluno.
            char confirma = 'N';
            do {
                try {
                    System.out.println("Confirma alteração? (S/N)");
                    confirma = Character.toUpperCase(in.readLine().charAt(0));
                    if (confirma == 'S') {
                        alunoAlterado.nome = (nome.length() > 0) ? nome : alunoAlterado.nome;
                        alunoAlterado.email = (email.length() > 0) ? email : alunoAlterado.email;
                        alunoAlterado.senha = (senha.length() > 0) ? senha : alunoAlterado.senha;
                        alunoAlterado.telefone = (telefone.length() > 0) ? telefone : alunoAlterado.telefone;
                        alunoAlterado.matricula = (matricula.length() > 0) ? matricula : alunoAlterado.matricula;
                        alunoAlterado.curso = (curso.length() > 0) ? curso : alunoAlterado.curso;
                        
                        if (arquivo.alterar(alunoAlterado)) {
                            System.out.println("Aluno alterado! :)\n");
                        } else {
                            System.out.println("Aluno não alterado! :(\n");
                        }
                    } else if (confirma == 'N') {
                        System.out.println("Alteração cancelada!\n");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                } catch(Exception e) {
                    System.out.println("Opção inválida");
                }
            } while (confirma != 'S' && confirma != 'N');
        } else {
            System.out.println("Aluno não encontrado! :(\n");
        }
    }

    private static void excluirAluno() throws Exception {   
        System.out.println("\n----- EXCLUSÃO -----");
        
        char confirma = 'N';
        int codigo = getCodigo();
        Aluno alunoExcluir = (Aluno) arquivo.buscarCodigo(codigo);
        
        if (alunoExcluir != null) {
            System.out.println(alunoExcluir);
            
            do {
                try {
                    System.out.println("Confirma inclusão? (S/N)");
                    confirma = Character.toUpperCase(in.readLine().charAt(0));
                    if (confirma == 'S') {
                        if (arquivo.excluir(codigo)) {
                            System.out.println("Aluno excluido! :)\n");
                        } else {
                            System.out.println("Aluno não excluido! :(\n");
                        }
                    } else if (confirma == 'N') {
                        System.out.println("Exclusão cancelada!");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                } catch(Exception e) {
                    System.out.println("Opção inválida");
                }
            } while (confirma != 'S' && confirma != 'N');
        } else {
            System.out.println("Aluno não encontrado!\n");
        }
    }

    private static void buscarAlunoCodigo() throws Exception {
        System.out.println("\n----- BUSCAR POR CÓDIGO -----");
        
        int codigo = getCodigo();
        Aluno alunoBuscado = (Aluno) arquivo.buscarCodigo(codigo);
        
        if (alunoBuscado != null) {
            System.out.println(alunoBuscado);
        } else {
            System.out.println("Aluno não encontrado! :(");
        }
    }

    private static void buscarAlunoNome() throws Exception {
        System.out.println("\n----- BUSCAR POR NOME -----");
        
        String nome = getNome();
        Aluno alunoBuscado = (Aluno) arquivo.buscarString(nome);
        
        if (alunoBuscado != null) {
            System.out.println(alunoBuscado);
        } else {
            System.out.println("Aluno não encontrado! :(");
        }
    }

    private static void reorganizar() throws Exception {
        System.out.println("\n----- REORGANIZAÇÃO -----");
        arquivo.reorganizar();
        System.out.println("Alunos reorganizados! :)\n");
    }

    private static void povoar() throws Exception {
        arquivo.incluir(new Aluno(-1, "Astolfo", "thebarbarian@gmail.com", "grrrrrr", "7070-7070", "55555", "História"));
        arquivo.incluir(new Aluno(-1, "Adenor", "deninho@gmail.com", "idontcare", "8080-8080", "34232", "Música"));
        arquivo.incluir(new Aluno(-1, "Xanaina", "oooohxanaina@gmail.com", "xaxa", "6969-6969", "69696", "Sexologia"));
        arquivo.incluir(new Aluno(-1, "Dollynho", "seuamiguinho@hotmail.com", "guarana", "3483-6753", "00001", "Marketing"));
        arquivo.incluir(new Aluno(-1, "Yudi Presteixo", "presteixo@gmail.com", "jogodavida", "4002-8922", "12325", "Jornalismo"));
        arquivo.incluir(new Aluno(-1, "Lula", "lulapallooza@outlook.com", "lulala", "1313-1313", "13131", "Deretcho"));
        arquivo.incluir(new Aluno(-1, "Temer", "opovonaoebobo@yahoo.com", "foradilmaforalulaforapt", "3171-1713", "15151", "Teatro"));
        arquivo.incluir(new Aluno(-1, "Blond Joseph", "anamaria@gmail.com", "blondjamesblond", "1212-1212", "12121", "Culinária"));
        arquivo.incluir(new Aluno(-1, "Irineu", "vocenaosabe@gmail.com", "nemeu", "0404-0404", "00404", "Filosofia"));
        arquivo.incluir(new Aluno(-1, "Donald Trumpolin", "makeamericagreatagain@cia.us", "lalilulelo", "202456-6213", "49494", "Engenharia Civil"));
    }
    
    private static int getCodigo() {
        int codigo = -1;
        try {
            do {
                System.out.print("Código: ");
                codigo = Integer.parseInt(in.readLine());

                if (codigo <= 0) {
                    System.out.println("Código inválido!");
                }
            } while(codigo <= 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return codigo;
    }
    
    private static String getNome() {
        String nome = "";
        try {
            do {
                System.out.print("Nome: ");
                nome = in.readLine();

                if (nome.equals("")) {
                    System.out.println("Nome vazio!");
                }
            } while(nome.equals(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return nome;
    }
}
