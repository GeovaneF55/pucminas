/**
 * TP07Q02 - Árvore Binária de Árvores Binárias
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2016
 */

import java.util.*;

class TP07Q02ArvoreBinariaDeArvoreBinaria{
	  static ArvoreDeArvore arvoreDeArvore = new ArvoreDeArvore();
	static final int MATRICULA = 553237;
	static long COMECO,
	            FIM;
	static int COMPARACOES = 0;
	public static void main(String[] args) throws Exception {
		String entrada;
		Pessoa pessoa;

		COMECO = now();

		for(entrada = MyIO.readLine(); entrada.equals("FIM") == false; entrada = MyIO.readLine()){
			pessoa = new Pessoa();
			pessoa.ler(entrada);
			arvoreDeArvore.inserir(pessoa);
		}

		//Quantidade de comandos após o "FIM"
		int quantidade =  MyIO.readInt();   

		//Executa comandos
		while(quantidade > 0){
			entrada = MyIO.readLine();
			if(entrada.contains("I")) {
				entrada = entrada.substring(2);
				pessoa = new Pessoa();
				pessoa.parsePessoa(entrada);
				arvoreDeArvore.inserir(pessoa);
			} else {
				entrada = entrada.substring(2);
				try {
					arvoreDeArvore.remover(Integer.parseInt(entrada));
				} catch(Exception e) { }
			}
			quantidade--;
		}

		for(entrada = MyIO.readLine(); entrada.equals("FIM") == false; entrada = MyIO.readLine()){
			boolean achou = arvoreDeArvore.pesquisar(Integer.parseInt(entrada));
			if(achou){
				MyIO.println("SIM");
			} else{
				MyIO.println("NAO");
			}
		}

		FIM = now();

		log();
	}

	/**
	* Retorna o timestamp atual
	* @return timestamp atual
	*/
	public static long now(){
		return new Date().getTime();
	}

	/**
	* log
	*/
	public static void log(){
		Arq.openWrite(MATRICULA + "_arvoreBinaria.txt");
		Arq.print(MATRICULA + "\t" + (FIM-COMECO) + "\t" + COMPARACOES);
		Arq.close();
	}
}

class Pessoa {
    private int id;
    private int idade;
    private String nome, nacionalidade, nascimento, localNascimento,
            morte, localMorte;
        
    /**
     * Retorna o atributo id.
     * @return this.id int.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna o atributo idade.
     * @return this.idade int.
     */
    public int getIdade() {
        return this.idade;
    }

    /**
     * Retorna o atributo nome.
     * @return this.nome String.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o atributo nacionalidade.
     * @return this.nacionalidade String.
     */
    public String getNacionalidade() {
        return this.nacionalidade;
    }

    /**
     * Retorna o atributo nascimento.
     * @return this.nascimento String.
     */
    public String getNascimento() {
        return this.nascimento;
    }

    /**
     * Retorna o atributo localNascimento.
     * @return this.localNascimento String.
     */
    public String getLocalNascimento() {
        return this.localNascimento;
    }

    /**
     * Retorna o atributo morte.
     * @return this.morte String.
     */
    public String getMorte() {
        return this.morte;
    }

    /**
     * Retorna o atributo localMorte.
     * @return this.localMorte String.
     */
    public String getLocalMorte() {
        return this.localMorte;
    }

    /**
     * Retorna o ano de nascimento.
     * @return ano int Ano de nascimento.
     */
    public int getAno() {
        int ano = 0;
        if(this.nascimento.contains("a.C")) {
            String array[] = this.nascimento.split(" ");
            ano = 0 - Integer.parseInt(array[1]);  
        } else if(this.nascimento.contains("vivo")) {
            ano = 0;
        } else {
            String array[] = this.nascimento.split(" de ");
            ano = Integer.parseInt(array[2]);
        }
        return ano;
    }

    /**
     * Imprime os atributos da Pessoa.
     */
    public void imprimir(){
        MyIO.println(this.id + " ## " + 
                this.nome + " ## " + 
                this.nacionalidade + " ## " +
                this.nascimento + " ## " +
                this.localNascimento + " ## " +
                this.morte + " ## " +
                this.localMorte + " ## " +
                this.idade + //" " + 
                "");
    }

    /**
     * Cria um clone do objeto Pessoa.
     * @return resp Pessoa clone.
     */
    public Pessoa clone (){
        Pessoa resp = new Pessoa();
        resp.id = this.id;
        resp.idade = this.idade;
        resp.nome = this.nome;
        resp.nacionalidade = this.nacionalidade;
        resp.nascimento = this.nascimento;
        resp.localNascimento = this.localNascimento;
        resp.morte = this.morte;
        resp.localMorte = this.localMorte;
        return resp;
    }

    /**
     * Retorna o mes da data passada por parametro.
     * @return resp int Mes.
     */
    public int getMes(){
        String data = this.nascimento;
        int resp = 0;
        if(data.contains("janeiro") == true){
            resp = 1;
        } else if(data.contains("fevereiro") == true){
            resp = 2;
        } else if(data.contains("março") == true){
            resp = 3;
        } else if(data.contains("abril") == true){
            resp = 4;
        } else if(data.contains("maio") == true){
            resp = 5;
        } else if(data.contains("junho") == true){
            resp = 6;
        } else if(data.contains("julho") == true){
            resp = 7;
        } else if(data.contains("agosto") == true){
            resp = 8;
        } else if(data.contains("setembro") == true){
            resp = 9;
        } else if(data.contains("outubro") == true){
            resp = 10;
        } else if(data.contains("novembro") == true){
            resp = 11;
        } else if(data.contains("dezembro") == true){
            resp = 12;
        }
        return resp;
    }

    /**
     * Recebe uma String e passa os atributos
     * para uma Pessoa.
     * @param s String que contem os atributos.
     */
    public void parsePessoa(String s){
        String array[] = s.split(" ## ");
        this.id = new Integer(array[0]).intValue();
        this.nome = array[1];
        this.nacionalidade = array[2];
        this.nascimento = array[3];
        this.localNascimento = array[4];
        this.morte = array[5];
        this.localMorte = array[6];
        this.idade = new Integer(array[7]).intValue();
    }

    /**
     * Retorna uma substring.
     * @param s String que contem a substring.
     * @param antes String que antecede a substring.
     * @param depois String que sucede a substring.
     * @return resp String substring.
     */
    public String getSubstringEntre(String s, String antes, String depois){
        String resp = "";
        int posInicio , posFim;

        posInicio = s.indexOf(antes) + antes.length();

        if(antes.compareTo(depois) != 0){
            posFim = s.indexOf(depois);
        } else {
            posFim = s.indexOf(depois, posInicio);
        }

        if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
            resp = s.substring(posInicio, posFim);
        }

        return resp;
    }

    /**
     * Retorna um inteiro retirado de uma String.
     * @param s String que contem o inteiro.
     * @param antes String que antecede o inteiro.
     * @param depois String que sucede o inteiro.
     * @return resp int.
     */
    public int getIntEntre(String s, String antes, String depois){
        return (new Integer(getSubstringEntre(s, antes, depois).replace(".","").trim())).intValue();
    }
    
    /**
     * Retorna um double retirado de uma String.
     * @param s String que contem o double.
     * @param antes String que antecede o double.
     * @param depois String que sucede o double.
     * @return resp double.
     */
    public double getDoubleEntre(String s, String antes, String depois){
        return (new Double(getSubstringEntre(s, antes, depois).replace(",",".").trim())).doubleValue();
    }

    /**
     * Remove as tags html de um String.
     * @param s String que tera tags removidas.
     * @return resp String sem as tags.
     */
    public String removeTags(String s){
        String resp = "";

        for (int i = 0; i < s.length(); i++){

            while(i < s.length() && s.charAt(i) == '<'){
                for (i++; s.charAt(i) != '>'; i++);
                i++;

                while(i < s.length() && s.charAt(i) == '&'){
                    for (i++; s.charAt(i) != ';'; i++);
                    i++;
                }
            }

            while(i < s.length() && s.charAt(i) == '&'){
                for (i++; s.charAt(i) != ';'; i++);
                i++;
                resp += ' ';
            }

            if(i < s.length()){
                resp += s.charAt(i);
            }
        }

        while(resp.length() > 0 && resp.charAt(0) == ' '){
            resp = resp.substring(1);
        }

        return resp;
    }

    /**
     * Recebe uma String contendo o nome de um arquivo html
     * para fazer a leitura do mesmo e buscar os atributos
     * da Pessoa.
     * @param nomeArquivo String nome do arquivo html.
     */
    public void ler(String nomeArquivo){
        String linha;

        //Abrir o arquivo para leitura
        Arq.openRead(nomeArquivo, "UTF8");//ISO-8859-1");

        //Definir atributo id
        this.id = (nomeArquivo.charAt(nomeArquivo.length() - 8) - 48) * 100;
        this.id += (nomeArquivo.charAt(nomeArquivo.length() - 7) - 48) * 10;
        this.id += (nomeArquivo.charAt(nomeArquivo.length() - 6) - 48);

        //Definir atributo nome
        for(linha = Arq.readLine(); linha.contains("<h1") == false; linha = Arq.readLine());
        this.nome = getSubstringEntre(linha, "lang=\"pt\">", "</h1>");

        //Definir atributo nacionalidade
        for(linha = Arq.readLine(); linha.contains("Nacionalidade") == false; linha = Arq.readLine());
        this.nacionalidade = removeTags(Arq.readLine());

        //Definir atributo nascimento
        for(linha = Arq.readLine(); linha.contains("Nascimento") == false; linha = Arq.readLine());
        this.nascimento = removeTags(Arq.readLine());

        //Se vivo
        if(this.nascimento.charAt(this.nascimento.length()-1) == ')'){
            String tmp = this.nascimento.substring(this.nascimento.indexOf('(') + 1);
            tmp = tmp.substring(0, tmp.indexOf(' '));
            this.idade = (new Integer(tmp)).intValue();
            this.nascimento = this.nascimento.substring(0, this.nascimento.indexOf('('));
            this.morte = "vivo";
            this.localMorte = "vivo";
        } else {
            this.morte = "";
            this.localMorte = "";
        }

        //Definir atributo local de nascimento
        for(linha = Arq.readLine(); linha.contains("Local") == false; linha = Arq.readLine());
        this.localNascimento = removeTags(Arq.readLine());

        //Definir atributos morte e local de morte
        if (this.morte.length() == 0){
            for(linha = Arq.readLine(); linha.contains("Morte") == false; linha = Arq.readLine());
            this.morte = removeTags(Arq.readLine());

            if(this.morte.contains("(") == true){
                String tmp = this.morte.substring(this.morte.indexOf('(') + 1);
                tmp = tmp.substring(0, tmp.indexOf(' '));
                this.idade = (new Integer(tmp)).intValue();
                this.morte = this.morte.substring(0, this.morte.indexOf('('));
            }

            for(linha = Arq.readLine(); linha.contains("Local") == false; linha = Arq.readLine());
            this.localMorte = removeTags(Arq.readLine());
        }

        //Fechar o arquivo para a leitura
        Arq.close();
    }
}

class No{
	private int mes;
	private No esq, dir;
	private No2 raiz;

	/**
	 * Construtor da classe No1
	 * @param mes int mes do no
	 */
	No(int mes){
		this(mes, null, null, null);      

	}
	/**
	 * Construtor da classe No2
	 * @param mes int mes do no
	 * @param esq No ponteiro esquerda
	 * @param dir No ponteiro direita.
	 * @param raiz No2 raiz da arvore do no
	 */
	No(int mes, No esq, No dir, No2 raiz){
		setMes(mes);
		setEsq(esq);
		setDir(dir);  
		setRaiz(raiz);
	}

	/**
	 * Setar conteudo do int mes
	 * @param mes int Mes do no
	 */
	public void setMes(int mes){
		this.mes = mes;
	}
	/**
	 * Setar ponteiro da Esquerda
	 * @param esq No ponteiro esquerda
	 */
	public void setEsq(No esq){
		this.esq = esq;
	}
	/**
	 * Setar ponteiro da Direita
	 * @param dir No ponteiro direita
	 */
	public void setDir(No dir){
		this.dir = dir;
	}
	/**
	 * Setar o ponteiro raiz da arvore do no
	 * @param raiz No2 raiz de arvore no
	 */
	public void setRaiz(No2 raiz){
		this.raiz = raiz;
	}

	/**
	 * Pegar caracter do no
	 * @return this.mes int contido no no
	 */
	public int getMes(){
		return this.mes;
	}
	/**
	 * Pegar ponteiro a Esquerda do no
	 * @return this.esq ponteiro da esquerda
	 */
	public No getEsq(){
		return this.esq;
	}

	/**
	 * Pegar ponteiro a Direita do no
	 * @return this.dir ponteiro da Direita
	 */
	public No getDir(){
		return this.dir;
	}  

	/**
	 * Pegar ponteiro raiz da arvore do no
	 * @return this.raiz ponteiro da raiz
	 */
	public No2 getRaiz(){
		return this.raiz;
	}
} 

class No2{
	private Pessoa pessoa;
	private No2 esq,dir;

	/**
	 * Construtor da classe No2
	 * @param elemento String palavra do no
	 */
	No2(Pessoa pessoa){
		this(pessoa, null, null);
	}

	/**
	 * Construtor da classe No2
	 * @param pessoa Pessoa pessoa do no
	 * @param esq No2 ponteiro esquerda
	 * @param dir No2 ponteiro direita.
	 */
	No2(Pessoa pessoa, No2 esq, No2 dir){
		setPessoa(pessoa);
		setEsq(esq);
		setDir(dir);
	}

	/**
	 * Setar conteudo da String Elemento
	 * @param pessoa Pessoa pessoa do no
	 */
	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa.clone();
	}
	/**
	 * Setar ponteiro da Esquerda
	 * @param esq No2 ponteiro esquerda
	 */

	public void setEsq(No2 esq){
		this.esq = esq;
	}
	/**
	 * Setar ponteiro da Direita
	 * @param dir No2 ponteiro direita
	 */

	public void setDir(No2 dir){
		this.dir = dir;
	}

	/**
	 * Pegar palavra do no
	 * @return this.pessoa Pessoa pessoa contida no no
	 */
	public Pessoa getPessoa(){
		return this.pessoa;
	}
	/**
	 * Pegar ponteiro a Esquerda do no
	 * @return this.esq ponteiro da esquerda
	 */

	public No2 getEsq(){
		return this.esq;
	}

	/**
	 * Pegar ponteiro a Direita do no
	 * @return this.dir ponteiro da Direita
	 */

	public No2 getDir(){
		return this.dir;
	}

}                     

class ArvoreDeArvore {
	private No raiz;

	/**
	 * Construtor da classe.
	 */
	public ArvoreDeArvore(){
		setRaiz(new No(6));
		inicializar();
	}

	/**
	 * Metodo privado iterativo para inicializar a árvore.
	 */
	private void inicializar() {
		try{		
			inicializar(2);
			inicializar(9);
			inicializar(4);
			inicializar(11);
			inicializar(0);
			inicializar(1);
			inicializar(3);
			inicializar(5);
			inicializar(7);
			inicializar(8);
			inicializar(10);
			inicializar(12);
		} catch(Exception exception){
			MyIO.println("Erro ao inicializar!");
		}		
	}

	/**
	 * Seta raiz
	 * @param raiz No
	 */
	public void setRaiz(No raiz){
		this.raiz = raiz;
	}

	/**
	 * Geta raiz
	 * @param raiz No
	 */
	public No getRaiz(){
		return this.raiz;
	}

	/**
	 * Metodo privado para inicializar a árvore.
	 * @param mes int Mes a ser inserida na agenda.
	 * @return i No em analise, alterado ou nao.
	 */
	private No inicializar(int mes) throws Exception {
		return inicializar(mes, getRaiz());
	}

	/**
	 * Metodo privado recursivo para inicializar a árvore.
	 * @param mes int Mes a ser inserida na agenda.
	 * @param i No em analise.
	 * @return i No em analise, alterado ou nao.
	 */
	private No inicializar(int mes, No i) throws Exception {
		if(i == null) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			i = new No(mes);
		} else if(mes < i.getMes()) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			i.setEsq(inicializar(mes, i.getEsq()));
		} else {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			i.setDir(inicializar(mes, i.getDir()));
		}
		return i;
	}

	/**
	 * Metodo publico iterativo que insere uma pessoa na arvore.
	 * @param pessoa Pessoa pessoa a ser inserida.
	 * @throws Exception se letra inicial for diferente de A~Z.
	 */
	public void inserir(Pessoa pessoa) throws Exception {
		No i = buscarNo(pessoa);
		i.setRaiz(inserir(pessoa, i.getRaiz()));
	}

	/**
	 * Metodo privado recursivo que insere a pessoa na arvore.
	 * @param pessoa Pessoa pessoa a ser inserida.
	 * @param j No2 em analise.
	 * @return j No2 em analise, alterado ou nao.
	 * @throws Exception se a pessoa ja existe.
	 */
	private No2 inserir(Pessoa pessoa, No2 j) throws Exception {
		if(j == null) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			j = new No2(pessoa);
		} else if(pessoa.getId() < j.getPessoa().getId()) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			j.setEsq(inserir(pessoa, j.getEsq()));
		} else if (pessoa.getId() > j.getPessoa().getId()) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			j.setDir(inserir(pessoa, j.getDir()));
		} else {
			throw new Exception("Pessoa já existente!");
		}
		return j;
	}

	/**
	 * Metodo publico iterativo que pesquisa alguma pessoa na arvore e retorna a subarvore.
	 * @param id int id da pessoa a ser pesquisada.
	 * @return no se pessoa existir, null caso contrario.
	 * @throws Exception se não encontrar.
	 */
	public No pesquisarNo(int id) throws Exception {
		return pesquisarNo(id, getRaiz());
	}

	/**
	 * Metodo privado recursivo que pesquisa alguma pessoa na arvore e retorna a subarvore.
	 * @param id int id da pessoa a ser pesquisada.
	 * @param i No em analise.
	 * @return no se a pessoa existir, null caso contrario.
	 */
	private No pesquisarNo(int id, No i) {
		No no = null;
		
		if(i != null) {
			boolean resp = pesquisarNo2(id, i.getRaiz());
			if(resp == true){
				no = i;
			}
			if(no == null){
				
				no = pesquisarNo(id, i.getEsq());
			}			
			if(no == null){
				no = pesquisarNo(id, i.getDir());
			}
		}
		TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=4;
		return no;
	}

	/**
	 * Metodo privado recursivo que pesquisa a existencia
	 * de determinada pessoa na arvore.
	 * @param id in id da pessoa a ser pesquisada.
	 * @param j No2 em analise.
	 * @return true se a pessoa existir, false caso contrario.
	 */
	private boolean pesquisarNo2(int id, No2 j) {
		boolean resp = false;
		if(j == null) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			resp = false;
		} else if(id == j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			resp = true;
		} else if(id < j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			resp = pesquisarNo2(id, j.getEsq());
		} else if(id > j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=4;
			resp = pesquisarNo2(id, j.getDir());
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo que pesquisa alguma pessoa na arvore.
	 * @param id int id da pessoa a ser pesquisada.
	 * @return true se pessoa existir, false caso contrario.
	 * @throws Exception se não encontrar.
	 */
	public boolean pesquisar(int id) throws Exception {
		MyIO.print("raiz ");
		return pesquisar(id, getRaiz());

	}

	/**
	 * Metodo privado recursivo que pesquisa a existencia
	 * de determinada pessoa na arvore.
	 * @param id int id da pessoa a ser pesquisada.
	 * @param i No em analise.
	 * @return true se a pessoa existir, false caso contrario.
	 */
	private boolean pesquisar(int id, No i) {
		boolean resp = false;
		if(i != null) {
			MyIO.print("esq ");
			resp = pesquisar(id, i.getEsq());
			if(resp == false){
				MyIO.print("raiz ");
				resp = pesquisar(id, i.getRaiz());
			}
			if(resp == false){
				MyIO.print("dir ");
				resp = pesquisar(id, i.getDir());
			}
		}
		TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
		return resp;
	}

	/**
	 * Metodo privado recursivo que pesquisa a existencia
	 * de determinada pessoa na arvore.
	 * @param id in id da pessoa a ser pesquisada.
	 * @param j No2 em analise.
	 * @return true se a pessoa existir, false caso contrario.
	 */
	private boolean pesquisar(int id, No2 j) {
		boolean resp = false;
		if(j == null) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			resp = false;
		} else if(id == j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			resp = true;
		} else if(id < j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			MyIO.print("esq ");
			resp = pesquisar(id, j.getEsq());
		} else if(id > j.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=4;
			MyIO.print("dir ");
			resp = pesquisar(id, j.getDir());
		}
		return resp;
	}

	/**
	 * Metodo privado iterativo que encontra o no que contem
	 * a arvore com as as pessoas do mesmo mês que o passado por parâmetro
	 * @param pessoa Pessoa a ser pesquisada.
	 * @return i No que contem arvore a ser analisada.
	 * @throws Exception se o mes for diferente de 0-12.
	 */
	public No buscarNo(Pessoa pessoa) throws Exception {
		int mes = pessoa.getMes();
		return buscarNo(mes, raiz);
	}

	/**
	 * Metodo privado recursivo que realiza a pesquisa do No.
	 * @param mes de nascimento da pessoa que esta sendo pesquisada.
	 * @param i No em analise.
	 * @return resp No que contem mes de nascimento da pessoa pesquisada.
	 * @throws Exception se mes diferente de 0-12.
	 */
	private No buscarNo(int mes, No i) throws Exception {
		No resp = null;
		if(i == null) {
			throw new Exception("Mês inexistente!");
		} else if(mes == i.getMes()) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			resp = i;
		} else if(mes < i.getMes()) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			resp = buscarNo(mes, i.getEsq());
		} else {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			resp = buscarNo(mes, i.getDir());
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo que remove uma pessoa.
	 * @param pessoa Pessoa pessoa a ser removida.
	 * @throws Exception se pessoa nao for encontrada.
	 */
	public void remover(int id) throws Exception{
		No i = pesquisarNo(id);
		if(i == null){
			throw new Exception("Erro ao remover!");
		}
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			i.setRaiz(remover(id, i.getRaiz()));
	}

	/**
	 * Metodo privado recursivo para remover uma pessoa.
	 * @param id int id da pessoa a ser removida.
	 * @param i No2 em analise.
	 * @return i No2 em analise, alterado ou nao.
	 * @throws Exception se a pessoa nao for encontrada.
	 */
	private No2 remover(int id, No2 i){
		if (id < i.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			i.setEsq(remover(id,i.getEsq()));
		} else if(id > i.getPessoa().getId()){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=2;
			i.setDir(remover(id,i.getDir()));
		} else if(i.getEsq() == null){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=3;
			i = i.getDir();
		} else if(i.getDir() == null) {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=4;
			i = i.getEsq();    
		} else {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=4;
			i.setEsq(antecessor(i, i.getEsq()));
		}

		return i;
	}

	/**
	 * Metodo privado que realiza a troca da pessoa removido
	 * pelo maior filho da subarvore a esquerda.
	 * @param i No2 que teve a pessoa removida.
	 * @param j No2 da subarvore esquerda.
	 * @return j No2 em analise, alterado ou nao.
	 */
	private No2 antecessor(No2 i, No2 j){
		if(j.getDir() != null){
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			j.setDir(antecessor(i, j.getDir()));
		} else {
			TP07Q02ArvoreBinariaDeArvoreBinaria.COMPARACOES+=1;
			i.setPessoa(j.getPessoa());
			j = j.getEsq();
		}
		return j;
	}

	/**
	 * Metodo publico que mostra o conteudo da arvore 1
	 */
	public void mostrarMeses(){
		MyIO.print("[ ");
		mostrarMeses(getRaiz());
		MyIO.println("]");
	}

	/**
	 * Metodo privado que mostra o conteudo da arvore 1
	 * @param i No
	 */
	private void mostrarMeses(No i){
		if(i != null){
			mostrarMeses(i.getEsq());
			MyIO.print(i.getMes() + " ");
			mostrarMeses(i.getDir());
		}
	}

	/**
	 * Metodo publico que mostra o conteudo da arvore 1
	 */
	public void mostrarPessoas(){
		mostrarPessoas(getRaiz());
		MyIO.println("=========================================================================");
	}

	/**
	 * Metodo privado que mostra o conteudo da arvore 1
	 * @param i No
	 */
	private void mostrarPessoas(No i){
		if(i != null){
			mostrarPessoas(i.getEsq());
			MyIO.println("\n====== " + i.getMes() + " ======");
			mostrarPessoas(i.getRaiz());
			mostrarPessoas(i.getDir());
		}
	}

	private void mostrarPessoas(No2 j){
		if(j != null){
			mostrarPessoas(j.getEsq());
			j.getPessoa().imprimir();
			mostrarPessoas(j.getDir());
		}
	}
}
