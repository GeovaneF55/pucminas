class Principal {
    public static void main(String[] args) throws Exception {
        // Criando agenda.
        Agenda agenda = new Agenda();
        
        // Criando contatos.
        Contato c1 = new Contato("Luigi", "97777-7777", "luigi@gmail.com", 1);
        Contato c2 = new Contato("Gabriel", "98888-8888", "gabriel@gmail.com", 2);
        Contato c3 = new Contato("Nathalia", "99999-9999", "nathalia@gmail.com", 3);
        Contato c4 = new Contato("Bruno", "96666-6666", "bruno@gmail.com", 6);

        // Inserindo contatos na agenda.
        agenda.inserir(c1);
        agenda.inserir(c2);
        agenda.inserir(c3);
        
        // Pesquisando contatos na agenda atraves do nome.
        MyIO.println(" ===== PESQUISANDO PELO NOME =====");
        if(agenda.pesquisar(c1.getNome()) == true) {
            MyIO.println("Contato " + c1.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c1.getNome() + " nao cadastrado");
        }

        if(agenda.pesquisar(c2.getNome()) == true) {
            MyIO.println("Contato " + c2.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c2.getNome() + " nao cadastrado");
        }

        if(agenda.pesquisar(c3.getNome()) == true) {
            MyIO.println("Contato " + c3.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c3.getNome() + " nao cadastrado");
        }

        if(agenda.pesquisar(c4.getNome()) == true) {
            MyIO.println("Contato " + c4.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c4.getNome() + " nao cadastrado");
        }
    
        // Removendo contato e verificando se foi removido.
        MyIO.println("\n ===== REMOVENDO CONTATO =====");
        Contato removido = agenda.remover(c3.getNome());
        if(agenda.pesquisar(removido.getNome()) == true) {
            MyIO.println("Contato " + removido.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + removido.getNome() + " foi removido");
        }

            
        // Pesquisando contatos na agenda atraves do cpf.
        MyIO.println("\n ===== PESQUISANDO PELO CPF =====");
        if(agenda.pesquisar(c1.getCpf()) == true) {
            MyIO.println("Contato " + c1.getNome() + " encontrado\t CPF: " + c1.getCpf());
        } else {
            MyIO.println("Contato " + c1.getNome() + " nao cadastrado\t CPF: " + c1.getCpf());
        }

        if(agenda.pesquisar(c2.getCpf()) == true) {
            MyIO.println("Contato " + c2.getNome() + " encontrado\t CPF: " + c2.getCpf());
        } else {
            MyIO.println("Contato " + c2.getNome() + " nao cadastrado\t CPF: " + c2.getCpf());
        }
        
        if(agenda.pesquisar(c3.getCpf()) == true) {
            MyIO.println("Contato " + c3.getNome() + " encontrado\t CPF: " + c3.getCpf());
        } else {
            MyIO.println("Contato " + c3.getNome() + " nao cadastrado\t CPF: " + c3.getCpf());
        }
        if(agenda.pesquisar(c4.getCpf()) == true) {
            MyIO.println("Contato " + c4.getNome() + " encontrado\t CPF: " + c4.getCpf());
        } else {
            MyIO.println("Contato " + c4.getNome() + " nao cadastrado\t CPF: " + c4.getCpf());
        }

        // Concatenando agendas.
        Agenda a2 = new Agenda();
        Contato c5 = new Contato("dollynho", "3421328932", "dollynho@dollynho.com", 9);
        a2.inserir(c5);

        Agenda concatenada = Agenda.concatenar(agenda, a2);
        MyIO.println("\n ===== CONCATENANDO AGENDA =====");
        if(concatenada.pesquisar(c2.getNome()) == true) {
            MyIO.println("Contato " + c2.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c2.getNome() + " nao cadastrado");
        }

        if(concatenada.pesquisar(c5.getNome()) == true) {
            MyIO.println("Contato " + c5.getNome() + " encontrado");
        } else {
            MyIO.println("Contato " + c5.getNome() + " nao cadastrado");
        }
    }
}
