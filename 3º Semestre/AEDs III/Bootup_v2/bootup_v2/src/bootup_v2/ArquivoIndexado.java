package bootup_v2;
import java.io.*;
import java.util.*;

public class ArquivoIndexado<T extends Registro> {

    RandomAccessFile arquivo;
    ArvoreB indice1;
    ArvoreB_Indireto indice2;
    String nomeArquivo;
    Class<T> classe;
    
    public ArquivoIndexado(Class<T> c, String n, String in1, String in2) throws Exception {
        nomeArquivo = n;
        classe = c;
        arquivo = new RandomAccessFile(nomeArquivo, "rw");
        if(arquivo.length()<4)
            arquivo.writeInt(0);
        indice1 = new ArvoreB(10, in1);
        indice2 = new ArvoreB_Indireto(10, in2);
    }
    
    public int incluir(T l) throws Exception {
        arquivo.seek(0);
        int cod = arquivo.readInt();
        cod++;
        arquivo.seek(0);
        arquivo.writeInt(cod);

        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        l.setCodigo(cod);
        arquivo.writeByte(' ');
        byte[] b = l.getByteArray();
        arquivo.writeInt(b.length);
        arquivo.write(b);
        indice1.inserir(cod, endereco);
        indice2.inserir(l.getString(), l.getCodigo()); // o índice é indireto
        
        return cod;
    }
    
    public Object[] listar() throws Exception {
        ArrayList<T> lista = new ArrayList<>();
        arquivo.seek(4);
        byte lapide;
        byte[] b;
        int s;
        T l;
        while(arquivo.getFilePointer()<arquivo.length()) {
            l = classe.newInstance();
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            l.setByteArray(b);
            if(lapide==' ')
                lista.add(l);
        }
        
        Object[] ls = lista.toArray();
        return ls;
    }
    
    public Registro buscarCodigo(int c) throws Exception {
        
        T aux = classe.newInstance();
        byte[] b;
        int s;
        long endereco;
        
        if( (endereco = indice1.buscar(c))>=0 ) {
            arquivo.seek(endereco);
            arquivo.readByte(); // Lapide.
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            aux.setByteArray(b);
            arquivo.seek(endereco);
            return aux;
        }
        else 
            return null;
    }
    
    public Registro buscarString(String t) throws Exception {
        
        T aux = classe.newInstance();
        byte[] b;
        int s;
        long endereco;
        int codigo;
        
        if( (codigo = indice2.buscar(t))>=0 ) {
            endereco = indice1.buscar(codigo);
            arquivo.seek(endereco);
            arquivo.readByte(); // Lapide.
            s = arquivo.readInt();
            b = new byte[s];
            arquivo.read(b);
            aux.setByteArray(b);
            return aux;
        }
        else 
            return null;
    }
    
    public boolean excluir(int c) throws Exception {
        
        byte[] b;
        int s;
        T aux = classe.newInstance();
        long endereco = indice1.buscar(c);
        arquivo.seek(endereco);
        arquivo.write('*');
        s = arquivo.readInt();
        b = new byte[s];
        arquivo.read(b);
        aux.setByteArray(b);
        indice1.excluir(c);
        indice2.excluir(aux.getString());
        
        return true;
    }
    
    public boolean alterar(T l) throws Exception {
        byte[] b;
        int s;
        T aux = classe.newInstance();
        long endereco = indice1.buscar(l.getCodigo());
        arquivo.seek(endereco);
        arquivo.write('*');
        s = arquivo.readInt();
        b = new byte[s];
        arquivo.read(b);
        aux.setByteArray(b);

        arquivo.seek(arquivo.length());
        endereco = arquivo.getFilePointer();
        arquivo.writeByte(' ');
        b = l.getByteArray();
        arquivo.writeInt(b.length);
        arquivo.write(b);
        indice1.atualizar(l.getCodigo(), endereco);
        if(l.getString().compareTo(aux.getString()) != 0) {
            indice2.excluir(aux.getString());
            indice2.inserir(l.getString(), l.getCodigo());
        }
        return true;
    }
    
    public void reorganizar() throws Exception {
        
        int tamanhoBlocoMemoria = 3;
        
        // armazena o cabeçalho
        arquivo.seek(0);
        int cabecalho = arquivo.readInt();
        
        // PRIMEIRA ETAPA - DISTRIBUIÇÃO
        List<T> registrosOrdenados = new ArrayList<>();
        
        int     contador=0, seletor=0;
        boolean fimDeArquivo = false;
        byte    lapide;
        T       r1 = classe.newInstance(), 
                r2 = classe.newInstance(), 
                r3 = classe.newInstance();
        T       rAnt1, rAnt2, rAnt3;
        int     s;
        byte[]  b;
        
        DataOutputStream out1 = new DataOutputStream( new FileOutputStream("temp1.db"));
        DataOutputStream out2 = new DataOutputStream( new FileOutputStream("temp2.db"));
        DataOutputStream out3 = new DataOutputStream( new FileOutputStream("temp3.db"));
        DataOutputStream out = out1;
        
        try {
            contador = 0;
            seletor = 0;
            while(!fimDeArquivo) {
                
                if(arquivo.getFilePointer() == arquivo.length())
                    fimDeArquivo = true;

                // le o registro no arquivo original
                if(!fimDeArquivo) {
                    lapide = arquivo.readByte();
                    s = arquivo.readInt();
                    b = new byte[s];
                    arquivo.read(b);
                    r1.setByteArray(b);
                    if(lapide==' ') {
                        registrosOrdenados.add((T)r1.clone());
                        contador++;
                    }
                }
                
                if((fimDeArquivo&&contador>0) || contador==tamanhoBlocoMemoria) {

                    Collections.sort(registrosOrdenados);
                    
                    switch(seletor) {
                        case 0: out = out1; break;
                        case 1: out = out2; break;
                        case 2: out = out3;
                    }
                    
                    for( T r:registrosOrdenados ) {
                        b = r.getByteArray();
                        out.writeInt(b.length);
                        out.write(b);
                    }
                    registrosOrdenados.clear();
                    seletor = (seletor+1)%3;
                    contador = 0;
                    
                }
                
            }
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        out1.close();
        out2.close();
        out3.close();
        
        // SEGUNDA ETAPA - INTERCALAÇÃO
        boolean sentido = true; // true: 1,2,3 -> 4,5,6  e false: 4,5,6 -> 1,2,3
        DataInputStream in1, in2, in3;
        boolean maisIntercalacoes = true; // há registros em mais de um arquivo temporário
        boolean compara1, compara2, compara3; // indica que há mais registros no bloco do arquivo temporário correspondente
        boolean terminou1, terminou2, terminou3; // indica que a fonte esgotou (fim do arquivo)
        boolean mudou1, mudou2, mudou3; // indica a fonte do último registro copiado para o destino 
        
        while(maisIntercalacoes) {
            
            maisIntercalacoes = false;
            mudou1 = true; mudou2 = true; mudou3 = true;
            compara1 = false; compara2 = false; compara3 = false;
            terminou1 = false; terminou2 = false; terminou3 = false;
            
            // Seleciona fontes e destinos
            if(sentido) {
                in1 = new DataInputStream( new FileInputStream("temp1.db"));
                in2 = new DataInputStream( new FileInputStream("temp2.db"));
                in3 = new DataInputStream( new FileInputStream("temp3.db"));
                out1 = new DataOutputStream( new FileOutputStream("temp4.db"));
                out2 = new DataOutputStream( new FileOutputStream("temp5.db"));
                out3 = new DataOutputStream( new FileOutputStream("temp6.db"));
            } 
            else {
                in1 = new DataInputStream( new FileInputStream("temp4.db"));
                in2 = new DataInputStream( new FileInputStream("temp5.db"));
                in3 = new DataInputStream( new FileInputStream("temp6.db"));
                out1 = new DataOutputStream( new FileOutputStream("temp1.db"));
                out2 = new DataOutputStream( new FileOutputStream("temp2.db"));
                out3 = new DataOutputStream( new FileOutputStream("temp3.db"));
            }
            sentido = !sentido;
            seletor = 0;
            
            // registros para leitura e comparações
            // liberar registros da intercalação anterior
            r1 = classe.newInstance();
            r2 = classe.newInstance();
            r3 = classe.newInstance();

               
            while(!terminou1 || !terminou2 || !terminou3) {
                
                if(!compara1 && !compara2 && !compara3) {
                    switch(seletor) {
                        case 0: out = out1; break;
                        case 1: out = out2; break;
                        case 2: out = out3;
                    }
                    if(seletor>=1)
                        maisIntercalacoes = true;
                    seletor = (seletor+1)%3;
                    
                    if(!terminou1) compara1 = true;
                    if(!terminou2) compara2 = true;
                    if(!terminou3) compara3 = true;
                }
                
                // lê o próximo registro da última fonte usada (de onde o registro anterior saiu)
                if(mudou1) {
                    rAnt1 = (T)r1.clone();
                    try{
                        s = in1.readInt();
                        b = new byte[s];
                        in1.read(b);
                        r1.setByteArray(b);
                        if(r1.compareTo(rAnt1)<0)
                            compara1 = false;
                    } catch(EOFException e) {
                        compara1 = false;
                        terminou1 = true;
                    }
                    mudou1 = false;
                }
                if(mudou2) {
                    rAnt2 = (T)r2.clone();
                    try{
                        s = in2.readInt();
                        b = new byte[s];
                        in2.read(b);
                        r2.setByteArray(b);
                        if(r2.compareTo(rAnt2)<0)
                            compara2 = false;
                    } catch(EOFException e) {
                        compara2 = false;
                        terminou2 = true;
                    }
                    mudou2 = false;
                }
                if(mudou3) {
                    rAnt3 = (T)r3.clone();
                    try{
                        s = in3.readInt();
                        b = new byte[s];
                        in3.read(b);
                        r3.setByteArray(b);
                        if(r3.compareTo(rAnt3)<0)
                            compara3 = false;
                    } catch(EOFException e) {
                        compara3 = false;
                        terminou3 = true;
                    }
                    mudou3 = false;
                }
                
                // Escreve o menor registro
                // Testa se é o r1
                if( compara1 &&
                   (!compara2 || r1.compareTo(r2)<=0 ) &&
                   (!compara3 || r1.compareTo(r3)<=0 )  ) {
                        b = r1.getByteArray();
                        out.writeInt(b.length);
                        out.write(b);
                        mudou1 = true;
                }
                //Testa se é o r2
                else if( compara2 &&
                   (!compara1 || r2.compareTo(r1)<=0 ) &&
                   (!compara3 || r2.compareTo(r3)<=0 ) ) {
                        b = r2.getByteArray();
                        out.writeInt(b.length);
                        out.write(b);
                        mudou2 = true;
                }
                // Testa se é o r3
                else if( compara3 &&
                   (!compara1 || r3.compareTo(r1)<=0 ) &&
                   (!compara2 || r3.compareTo(r2)<=0 ) ) {
                        b = r3.getByteArray();
                        out.writeInt(b.length);
                        out.write(b);
                        mudou3 = true;
                }
            }
            in1.close(); in2.close(); in3.close();
            out1.close(); out2.close(); out3.close();
            
        }
        
        arquivo.close();
        if(sentido)
            in1 = new DataInputStream( new FileInputStream("temp1.db"));
        else
            in1 = new DataInputStream( new FileInputStream("temp4.db"));
        DataOutputStream ordenado = new DataOutputStream(new FileOutputStream(nomeArquivo));
        
        // reconstrói o arquivo original, ordenado
        indice1.apagar();
        indice2.apagar();
        ordenado.writeInt(cabecalho);
        long endereco;
        try{
            while(true) {
                s = in1.readInt();
                b = new byte[s];
                in1.read(b);
                r1.setByteArray(b);
            
                endereco = ordenado.size();
                ordenado.writeByte(' ');
                b = r1.getByteArray();
                ordenado.writeInt(b.length);
                ordenado.write(b);
                indice1.inserir(r1.getCodigo(), endereco);
                indice2.inserir(r1.getString(), r1.getCodigo());
            }
        } catch(EOFException e) {
            // saída normal
        }

        in1.close();
        ordenado.close();
        
        (new File("temp1.db")).delete();
        (new File("temp2.db")).delete();
        (new File("temp3.db")).delete();
        (new File("temp4.db")).delete();
        (new File("temp5.db")).delete();
        (new File("temp6.db")).delete();
        arquivo = new RandomAccessFile(nomeArquivo, "rw");
        
    }
}