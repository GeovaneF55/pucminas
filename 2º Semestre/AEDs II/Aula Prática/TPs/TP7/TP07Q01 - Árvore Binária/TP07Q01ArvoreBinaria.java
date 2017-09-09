/**
 * TP07Q01 - Árvore Binária
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2016
 */

import java.util.*;

class TP07Q01ArvoreBinaria {
	static ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
	static final int MATRICULA = 553237;
	static long COMECO,
	            FIM;
	static int COMPARACOES = 0;
	public static void main(String[] args) throws Exception {
		//MyIO.setCharset("UTF8");
		Pessoa pessoa;

		COMECO = now();

		for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
			pessoa = new Pessoa();
			pessoa.ler(nomeArquivo);
			arvoreBinaria.inserir(pessoa);
		}

		//Quantidade de comandos após o "FIM"
		int quantidade =  MyIO.readInt();   

		//Executa comandos
		while(quantidade > 0){
			String leArquivo = MyIO.readLine();
			String[] atributos = leArquivo.split("##");
			String[] comando = atributos[0].trim().split(" ");
			executarComando(comando, atributos);
			quantidade--;
		}

		for(String pesquisar = MyIO.readLine(); pesquisar.equals("FIM") == false; pesquisar = MyIO.readLine()){
			arvoreBinaria.pesquisar(Integer.parseInt(pesquisar));
		}

		FIM = now();

		log();
	}

	/**
	 * OBS: Esse método foi realizado em conjunto com o aluno Gabriel Luciano
	 * Executa os comandos da pilha
	 * @param comando String[] String que contém os comandos e os id caso houver
	 * @param atributos String[] String que contém os atributos
	 * @throws Exception
	 */
	public static void executarComando(String[] comando, String[] atributos) throws Exception{
		Pessoa pessoa;

		switch(comando[0]){
			// Remover da pilha
			case "R":
				int id = Integer.parseInt(comando[1]);
				arvoreBinaria.remover(arvoreBinaria.pessoa(id));
				break;
				// Inserir na pilha
			case "I":
				pessoa = new Pessoa(Integer.parseInt(comando[1]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ","")));
				arvoreBinaria.inserir(pessoa);
				break;
				// Default
			default:
				throw new Exception("Erro ao executar comando!");
		}
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

class Pessoa{
	private int id;
	private String nome;
	private String nacionalidade;
	private String dataNascimento;
	private String localNascimento;
	private String dataMorte;
	private String localMorte;
	private int idade;

	/**
	 * Construtor da classe.
	 */
	public Pessoa(){
		this.setId(0);
		this.setNome("");
		this.setNacionalidade("");
		this.setDataNascimento("");
		this.setLocalNascimento("");
		this.setDataMorte("");
		this.setLocalMorte("");
		this.setIdade(0);
	}

	/**
	 * Construtor da classe.
	 * @param id int
	 * @param nome String
	 * @param nacionalidade String
	 * @param dataNascimento String
	 * @param localNascimento String
	 * @param dataMorte String
	 * @param localMorte String
	 * @param idade int
	 */
	public Pessoa(int id, String nome, String nacionalidade, String dataNascimento, String localNascimento, String dataMorte, String localMorte, int idade){
		this.setId(id);
		this.setNome(nome);
		this.setNacionalidade(nacionalidade);
		this.setDataNascimento(dataNascimento);
		this.setLocalNascimento(localNascimento);
		this.setDataMorte(dataMorte);
		this.setLocalMorte(localMorte);
		this.setIdade(idade);
	}

	/**
	 * Seta o atributo id
	 * @param id int
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Retorna o atributo id
	 * @return id int
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Seta o atributo nome
	 * @param nome String
	 */
	public void setNome(String nome){
		this.nome = nome.trim();
	}

	/**
	 * Retorna o atributo nome
	 * @return nome String
	 */
	public String getNome(){
		return this.nome;
	}

	/**
	 * Seta o atributo nacionalidade
	 * @param nacionalidade String
	 */
	public void setNacionalidade(String nacionalidade){
		this.nacionalidade = nacionalidade.trim();
	}

	/**
	 * Retorna o atributo nacionalidade
	 * @return nacionalidade String
	 */
	public String getNacionalidade(){
		return this.nacionalidade;
	}

	/**
	 * Seta o atributo dataNascimento
	 * @param dataNascimento String
	 */
	public void setDataNascimento(String dataNascimento){
		this.dataNascimento = dataNascimento.trim();
	}

	/**
	 * Retorna o atributo dataNascimento
	 * @return dataNascimento String
	 */
	public String getDataNascimento(){
		return this.dataNascimento;
	}

	/**
	 * Seta o atributo localNascimento
	 * @param localNascimento String
	 */
	public void setLocalNascimento(String localNascimento){
		this.localNascimento = localNascimento.trim();
	}

	/**
	 * Retorna o atributo localNascimento
	 * @return localNascimento String
	 */
	public String getLocalNascimento(){
		return this.localNascimento;
	}

	/**
	 * Seta o atributo setDataMorte
	 * @param setDataMorte String
	 */
	public void setDataMorte(String dataMorte){
		this.dataMorte = dataMorte.trim();
	}

	/**
	 * Retorna o atributo dataMorte
	 * @return dataMorte String
	 */
	public String getDataMorte(){
		return this.dataMorte;
	}

	/**
	 * Seta o atributo localMorte
	 * @param localMorte String
	 */
	public void setLocalMorte(String localMorte){
		this.localMorte = localMorte.trim();
	}

	/**
	 * Retorna o atributo localMorte
	 * @return localMorte String
	 */
	public String getLocalMorte(){
		return this.localMorte;
	}

	/**
	 * Seta o atributo idade
	 * @param idade int
	 */
	public void setIdade(int idade){
		this.idade = idade;
	}

	/**
	 * Retorna o atributo idade
	 * @return idade int
	 */
	public int getIdade(){
		return this.idade;
	}

	/**
	 * Clona o objeto atual em um outro objeto e retorna o seu ponteiro
	 * @return Pessoa Pessoa
	 */
	public Pessoa clone(){
		return new Pessoa(this.getId(), this.getNome(), this.getNacionalidade(), this.getDataNascimento(), this.getLocalNascimento(), this.getDataMorte(), this.getLocalMorte(), this.getIdade());
	}

	/**
	 * Imprime os valores dos atributos do objeto atual na saída padrão
	 */
	public void imprimir(){
		MyIO.println(this.getId() + " ## " + 
				this.getNome() + " ## " + 
				this.getNacionalidade() + " ## " +
				this.getDataNascimento() + " ## " +
				this.getLocalNascimento() + " ## " +
				this.getDataMorte() + " ## " +
				this.getLocalMorte() + " ## " +
				this.getIdade() + 
				"");
	}

	/**
	 * Processa o arquivo HTML e seta os valores processados em um objeto da classe Pessoa
	 * @param nomeArq String nome do arquivo HTML a ser processado
	 */
	public void ler(String nomeArq){
		String arquivo = "", linha;
		Arq.openRead(nomeArq);
		do{	
			linha = Arq.readLine();
			arquivo += linha;
		}while(!linha.equals("</html>"));

		this.setId( Integer.parseInt( nomeArq.substring(5, 8) ) );
		this.setNome( achaNomeArquivo(arquivo) );
		this.setNacionalidade( achaNacionalidade(arquivo) );
		this.setDataNascimento( achaDataNascimento(arquivo) );
		this.setLocalNascimento( achaLocalNascimento(arquivo) );
		this.setDataMorte( achaDataMorte(arquivo) );
		this.setLocalMorte( achaLocalMorte(arquivo) );
		this.setIdade( achaIdade(arquivo) );

		Arq.close();
	}

	/**
	 * Retorna o nome da pessoa presente no arquivo HTML
	 * @param arquivo String
	 * @return split2 String nome da pessoa presente no arquivo HTML
	 */
	public String achaNomeArquivo(String arquivo){
		String split1[] = arquivo.split("<h1 id=\"firstHeading\" class=\"firstHeading\" lang=\"pt\">");
		String split2[] = split1[1].split("</h1>");

		return split2[0];
	}

	/**
	 * Retorna a nacionalidade da pessoa presente no arquivo HTML
	 * @param arquivo String
	 * @return frase String nacionalidade da pessoa presente no arquivo HTML
	 */
	public String achaNacionalidade(String arquivo){
		String frase = procuraHTML(arquivo, "Nacionalidade");

		return retiraMarcacoesHTML(frase).trim();
	}

	/**
	 * Retorna a data de nascimento da pessoa presente no arquivo HTML
	 * @param arquivo String
	 * @return frase String data de nascimento da pessoa presente no arquivo HTML
	 */
	public String achaDataNascimento(String arquivo){
		String frase = procuraHTML(arquivo, "Nascimento");

		return retiraInformacaoParentesis( retiraMarcacoesHTML(frase) );
	}

	/**
	 * Retorna o local de nascimento da pessoa presente no arquivo HTML
	 * @param arquivo String
	 * @return frase String local de nascimento da pessoa presente no arquivo HTML
	 */
	public String achaLocalNascimento(String arquivo){
		String frase = procuraHTML(arquivo, "Local");

		return retiraMarcacoesHTML(frase);
	}

	/**
	 * Retorna a data de morte da pessoa presente no arquivo HTML caso ela tenha morrido, ou a string "vivo" caso ela aindaesteja viva
	 * @param arquivo String
	 * @return frase String data de morte da pessoa presente no arquivo HTML
	 */
	public String achaDataMorte(String arquivo){
		String frase = procuraHTML(arquivo, "Morte");
		if(frase.isEmpty()){
			frase = "vivo";
		}
		return retiraInformacaoParentesis( retiraMarcacoesHTML(frase) );
	}

	/**
	 * Retorna o local de morte da pessoa presente no arquivo HTML caso ela tenha morrido, ou a string "vivo" caso ela ainda esteja viva
	 * @param arquivo String
	 * @return frase String local de nascimento da pessoa presente no arquivo HTML
	 */
	public String achaLocalMorte(String arquivo){
		String localMorte[] = arquivo.split(">Morte</td>");
		String frase = "";
		if(localMorte.length >= 2){
			frase = procuraHTML(localMorte[1], "Local");
		}
		if(frase.isEmpty()){
			frase = "vivo";
		}
		return retiraMarcacoesHTML(frase);
	}

	/**
	 * Retorna a idade da pessoa presente no arquivo HTML
	 * @param arquivo String
	 * @return resposta int idade da pessoa presente no arquivo HTML
	 */
	public int achaIdade(String arquivo){
		String resposta = "";
		String frase1 = procuraHTML(arquivo, "Morte");
		if(frase1.isEmpty()){
			frase1 = procuraHTML(arquivo, "Nascimento");
		}
		String s_idade[] = frase1.split("&#160;");
		if(s_idade.length >=2){
			resposta = s_idade[1];
		} else{
			String fraseAuxiliar[] = frase1.split("\\(");
			s_idade = fraseAuxiliar[1].split("\\)");
			resposta = s_idade[0];
		}
		resposta = somenteNumeros(resposta);
		return Integer.parseInt(resposta);
	}

	/**
	 * Procura o indice no arquivo HTML e retorna seu valor
	 * @param arquivo String
	 * @param indice String indice a ser procurado no HTML tendo seu valor retornado
	 * @return resposta String valor do indice retornado
	 */
	public String procuraHTML(String arquivo, String indice){
		String resposta = "", fraseAuxiliar[];
		try{
			String frase1[] = arquivo.split(">" + indice + "</td>");
			String frase2[]  = frase1[1].split("<br />");
			String frase3[] = frase2[0].split("</td>");
			String frase4[] = frase3[0].split(">");
			for(int i=0; i<frase4.length; i++){
				fraseAuxiliar = frase4[i].split("<");
				resposta+=fraseAuxiliar[0];
			}
		} catch(ArrayIndexOutOfBoundsException err){
			resposta = "";
		}

		return resposta;
	}

	/**
	 * Retorna a string sem a informação inútil presente dentro do parêntesis
	 * @param frase String
	 * @return resposta String 
	 */
	public String retiraInformacaoParentesis(String frase){
		String resposta = "";
		boolean ehMarcacao = false;

		for(int i=0; i<frase.length(); i++){
			if(frase.charAt(i) == '('){
				ehMarcacao = true;
			}

			if(!ehMarcacao){
				resposta+=frase.charAt(i);
			}

			if(frase.charAt(i) == ')'){
				ehMarcacao = false;
			}
		}

		return resposta;
	}

	/**
	 * Retorna a string sem a informação inútil presente em marcações HTML
	 * @param frase String
	 * @return resposta String
	 */
	public String retiraMarcacoesHTML(String frase){
		String resposta = "";
		boolean ehMarcacao = false;

		for(int i=0; i<frase.length(); i++){
			if(frase.charAt(i) == '&'){
				ehMarcacao = true;
			}

			if(!ehMarcacao){
				resposta+=frase.charAt(i);
			}			

			if(frase.charAt(i) == ';'){
				ehMarcacao = false;
			}
		}

		return resposta;
	}

	/**
	 * Retorna somente os valores numéricos de uma string passada através de parâmetro
	 * @param frase String
	 * @return numeros String
	 */
	public String somenteNumeros(String frase){
		String numeros = "";

		for(int i=0; i<frase.length(); i++){
			if(frase.charAt(i) >= '0' && frase.charAt(i) <= '9'){
				numeros += frase.charAt(i);
			}
		}

		return numeros;
	}
}

class No {
	public Pessoa pessoa; // Conteudo do no.
	public No esq, dir;  // Filhos da esq e dir.

	/**
	 * Construtor da classe.
	 * @param pessoa Pessoa Conteudo do no.
	 */
	public No(Pessoa pessoa) {
		this(pessoa, null, null);
	}

	/**
	 * Construtor da classe.
	 * @param pessoa Pessoa Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	public No(Pessoa pessoa, No esq, No dir) {
		this.pessoa = pessoa;
		this.esq = esq;
		this.dir = dir;
	}
}

class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {
		raiz = null;
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param id da Pessoa que sera procurada.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(int id) {
		MyIO.print("raiz ");
		return pesquisar(id, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param id da Pessoa que sera procurada.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(int id, No i) {
		boolean resp;
		if (i == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
			MyIO.println("NAO");
			resp = false;

		} else if (id == i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=2;
			MyIO.println("SIM");			
			resp = true;

		} else if (id < i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			MyIO.print("esq ");
			resp = pesquisar(id, i.esq);

		} else {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			MyIO.print("dir ");
			resp = pesquisar(id, i.dir);
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param id da Pessoa que sera procurada.
	 * @return Pessoa
	 */
	public Pessoa pessoa(int id) {
		return pessoa(id, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param id da Pessoa que sera procurada.
	 * @param i No em analise.
	 * @return Pessoa
	 */
	private Pessoa pessoa(int id, No i) {
		Pessoa resp;
		if (i == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
			resp = null;
		} else if (id == i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=2;
			resp = i.pessoa.clone();
		} else if (id < i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			resp = pessoa(id, i.esq);
		} else {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			resp = pessoa(id, i.dir);
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarCentral() {
		System.out.print("[ ");
		mostrarCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarCentral(No i) {
		if (i != null) {
			mostrarCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.pessoa.getId() + " "); // Conteudo do no.
			mostrarCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPre(No i) {
		if (i != null) {
			System.out.print(i.pessoa.getId() + " "); // Conteudo do no.
			mostrarPre(i.esq); // Elementos da esquerda.
			mostrarPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPos() {
		System.out.print("[ ");
		mostrarPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void mostrarPos(No i) {
		if (i != null) {
			mostrarPos(i.esq); // Elementos da esquerda.
			mostrarPos(i.dir); // Elementos da direita.
			System.out.print(i.pessoa.getId() + " "); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param pessoa Pessoa a ser inserida.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Pessoa pessoa) throws Exception {
		raiz = inserir(pessoa, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param pessoa Pessoa a ser inserida.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(Pessoa pessoa, No i) throws Exception {
		if (i == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
			i = new No(pessoa);
		} else if (pessoa.getId() < i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=2;
			i.esq = inserir(pessoa, i.esq);
		} else if (pessoa.getId() > i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			i.dir = inserir(pessoa, i.dir);
		} else {
			throw new Exception("Erro ao inserir!");
		}

		return i;
	}

	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param pessoa Pessoa a ser removida.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(Pessoa pessoa) throws Exception {
		raiz = remover(pessoa, raiz);
	}

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param pessoa Pessoa a ser removida.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(Pessoa pessoa, No i) throws Exception {

		if (i == null) {
			throw new Exception("Erro ao remover!");
		} else if (pessoa == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
		} else if (pessoa.getId() < i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=2;
			i.esq = remover(pessoa, i.esq);
		} else if (pessoa.getId() > i.pessoa.getId()) {
			TP07Q01ArvoreBinaria.COMPARACOES+=3;
			i.dir = remover(pessoa, i.dir);
			// Sem no a direita.
		} else if (i.dir == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=4;
			i = i.esq;
			// Sem no a esquerda.
		} else if (i.esq == null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=5;
			i = i.dir;
			// No a esquerda e no a direita.
		} else {
			TP07Q01ArvoreBinaria.COMPARACOES+=5;
			i.esq = antecessor(i, i.esq);
		}

		return i;
	}

	/**
	 * Metodo para trocar no removido pelo antecessor.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No antecessor(No i, No j) {

		// Existe no a direita.
		if (j.dir != null) {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
			// Caminha para direita.
			j.dir = antecessor(i, j.dir);
			// Encontrou o maximo da subarvore esquerda.
		} else {
			TP07Q01ArvoreBinaria.COMPARACOES+=1;
			i.pessoa = j.pessoa.clone(); // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.
		}
		return j;
	}
}
