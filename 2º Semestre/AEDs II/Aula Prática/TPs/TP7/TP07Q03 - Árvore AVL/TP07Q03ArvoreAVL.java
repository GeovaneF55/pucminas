/**
 * TP07Q03 - Árvore AVL
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 11/2016
 */

import java.util.*;

class TP07Q03ArvoreAVL{
	static AVL avl = new AVL();
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
			avl.inserir(pessoa);
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
				avl.inserir(pessoa);
			} else {
				entrada = entrada.substring(2);
				try {
					avl.remover(Integer.parseInt(entrada));
				} catch(Exception e) { }
			}
			quantidade--;
		}

		for(entrada = MyIO.readLine(); entrada.equals("FIM") == false; entrada = MyIO.readLine()){
			boolean achou = avl.pesquisar(Integer.parseInt(entrada));
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
		Arq.openWrite(MATRICULA + "_arvoreAVL.txt");
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
	 * Seta o atributo id.
	 * @param id int.
	 */
	public void setId(int id) {
		this.id = id;
	}

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

class No {
	public Pessoa pessoa;
	public No esq;
	public No dir;
	public int nivel;

	/**
	 * Construtor da classe.
	 * @param pessoa do no.
	 */
	No(Pessoa pessoa) {
		this(pessoa, null, null, 1);
	}

	/**
	 * Construtor da classe.
	 * @param pessoa do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	No(Pessoa pessoa, No esq, No dir, int nivel) {
		this.pessoa = pessoa.clone();
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

	// Cálculo do número de níveis a partir de um vértice
	public No setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq),getNivel(dir));
		return this;
	}

	// Retorna o número de níveis a partir de um vértice 
	public static int getNivel(No no) {
		return (no == null) ? 0 : no.nivel;
	}
}

class AVL {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public AVL() {
		raiz = null;
	}

	public int getAltura() {
		return nivel(raiz) - 1;
	}

	private int nivel(No no){
		return (no == null) ? 0 : (1 + Math.max(nivel(no.esq), nivel(no.dir)));
	}

	/**
	 * Metodo publico iterativo para pesquisar id.
	 * @param id ID que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(int id) {
		MyIO.print("raiz ");
		return pesquisar(raiz, id);
	}

	/**
	 * Metodo privado recursivo para pesquisar id.
	 * @param no No em analise.
	 * @param id ID que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(No no, int id) {
		boolean resp;
		if (no == null) {
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			resp = false;
		} else if (id == no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=2;
			resp = true; 
		} else if (id < no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=3;
			MyIO.print("esq ");
			resp = pesquisar(no.esq, id); 
		} else {
			TP07Q03ArvoreAVL.COMPARACOES+=3;
			MyIO.print("dir ");
			resp = pesquisar(no.dir, id); 
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo para inserir a pessoa.
	 * @param pessoa Pessoa a ser inserida.
	 * @throws Exception Se a pessoa existir.
	 */
	public void inserir(Pessoa pessoa) throws Exception {
		raiz = inserir(raiz, pessoa);
	}

	/**
	 * Metodo privado recursivo para inserir pessoa.
	 * @param no No em analise.
	 * @param pessoa Pessoa a ser inserida.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se a pessoa existir.
	 */
	private No inserir(No no, Pessoa pessoa) throws Exception {
		if (no == null) {
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			no = new No(pessoa);
		} else if (pessoa.getId() < no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=2;
			no.esq = inserir(no.esq, pessoa); 

		} else if (pessoa.getId() > no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=3;
			no.dir = inserir(no.dir, pessoa); 

		} else { 
			throw new Exception("Erro ao inserir elemento (" + pessoa.getId() + ")! "); 
		}

		no = balancear(no);
		return no;
	}

	private No balancear(No no) throws Exception {
		if(no != null){
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			int fator = No.getNivel(no.dir) - no.getNivel(no.esq);

			//Se balanceada
			if (Math.abs(fator) <= 1){
				TP07Q03ArvoreAVL.COMPARACOES+=1;
				no = no.setNivel();

				//Se desbalanceada para a direita
			}else if (fator == 2){
				TP07Q03ArvoreAVL.COMPARACOES+=2;
				int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);

				//Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					TP07Q03ArvoreAVL.COMPARACOES+=1;
					no.dir = rotacionarDir(no.dir);
				}
				no = rotacionarEsq(no);

				//Se desbalanceada para a esquerda
			}else if (fator == -2){
				TP07Q03ArvoreAVL.COMPARACOES+=3;
				int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);

				//Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					TP07Q03ArvoreAVL.COMPARACOES+=1;
					no.esq = rotacionarEsq(no.esq);
				}
				no = rotacionarDir(no);

			}else{
				throw new Exception("Erro fator de balanceamento (" + fator + ") invalido!"); 
			}
		}

		return no;
	}

	private No rotacionarDir(No no) {
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;

		no.setNivel();
		noEsq.setNivel();

		return noEsq;
	}

	private No rotacionarEsq(No no) {
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel();
		noDir.setNivel();
		return noDir;
	}

	/**
	 * Metodo publico iterativo para remover pessoa.
	 * @param id da Pessoa a ser removida.
	 * @throws Exception Se nao encontrar pessoa.
	 */
	public void remover(int id) throws Exception {
		raiz = remover(raiz, id);
	}

	/**
	 * Metodo privado recursivo para remover a pessoa.
	 * @param no No em analise.
	 * @param id Pessoa a ser removida.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar a pessoa.
	 */
	private No remover(No no, int id) throws Exception {

		if (no == null) {
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			throw new Exception("Erro ao remover!"); 

		} else if (id < no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=2;
			no.esq = remover(no.esq, id); 
		} else if (id > no.pessoa.getId()) {
			TP07Q03ArvoreAVL.COMPARACOES+=3;
			no.dir = remover(no.dir, id); 

			// Sem no a direita.
		} else if (no.dir == null) {
			TP07Q03ArvoreAVL.COMPARACOES+=4;
			no = no.esq;

			// Sem no a esquerda.
		} else if (no.esq == null) {
			TP07Q03ArvoreAVL.COMPARACOES+=5;
			no = no.dir;

			// No a esquerda e no a direita.
		} else {
			TP07Q03ArvoreAVL.COMPARACOES+=5;
			no.esq = antecessor(no, no.esq); 
		}

		no = balancear(no);
		return no;
	}

	/**
	 * Metodo para trocar no removido pelo antecessor.
	 * @param n1 No que teve o elemento removido.
	 * @param n2 No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No antecessor(No n1, No n2) {

		// Existe no a direita.
		if (n2.dir != null) {
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			// Caminha para direita.
			n2.dir = antecessor(n1, n2.dir); 

			// Encontrou o maximo da subarvore esquerda.
		} else {
			TP07Q03ArvoreAVL.COMPARACOES+=1;
			n1.pessoa.setId(n2.pessoa.getId()); // Substitui N1 por N2.
			n2 = n2.esq; // Substitui N2 por N2.ESQ.
		}
		return n2;
	}
}
