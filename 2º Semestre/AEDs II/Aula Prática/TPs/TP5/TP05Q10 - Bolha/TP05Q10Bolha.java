/**
 * TP05Q10 - Bolha
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 09/2016
 */

import java.util.*;

class TP05Q10Bolha{
	static Lista lista = new Lista(1000);
	static final int MATRICULA = 553237;
	static long COMECO,
	            FIM;
	static int COMPARACOES = 0,
		   MOVIMENTACOES = 0;

	public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF8");
		Pessoa pessoa;
		for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
			pessoa = new Pessoa();
			pessoa.ler(nomeArquivo);
			lista.inserirFim(pessoa);
		}

		COMECO = now();
		lista.ordenacaoBolha();
		FIM = now();

		for(int i=0; i<lista.getQtde(); i++){
			lista.getArray()[i].imprimir();
		}

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
		Arq.openWrite("553237.txt");
		Arq.print(MATRICULA + "\t" + (FIM-COMECO) + "\t" + COMPARACOES + "\t" + MOVIMENTACOES);
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
	 * @param id
	 * @param nome
	 * @param nacionalidade
	 * @param dataNascimento
	 * @param localNascimento
	 * @param dataMorte
	 * @param localMorte
	 * @param idade
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
		this.nome = nome;
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
		this.nacionalidade = nacionalidade;
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
		this.dataNascimento = dataNascimento;
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
		this.localNascimento = localNascimento;
	}

	/**
	 * Retorna o atributo localNascimento
	 * @return localNascimento
	 */
	public String getLocalNascimento(){
		return this.localNascimento;
	}

	/**
	 * Seta o atributo setDataMorte
	 * @param setDataMorte String
	 */
	public void setDataMorte(String dataMorte){
		this.dataMorte = dataMorte;
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
		this.localMorte = localMorte;
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
		MyIO.println(this.getId() + " ## " + this.getNome() + " ## " + this.getNacionalidade() + " ## " +  this.getDataNascimento() + " ## " +  this.getLocalNascimento() + " ## " +  this.getDataMorte() + " ## " +  this.getLocalMorte() + " ## " +  this.getIdade());
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

class Lista {
	private Pessoa[] array;
	private int qtde;


	/**
	 * Construtor da classe.
	 */
	public Lista () {
		this(1000);
	}


	/**
	 * Construtor da classe.
	 * @param tamanho Tamanho da lista.
	 */
	public Lista (int tamanho){
		array = new Pessoa[tamanho];
		setQtde(0);
	}

	/**
	 * Seta o atributo qtde
	 * @param qtde int
	 */
	public void setQtde(int qtde){
		this.qtde = qtde;
	}

	/**
	 * Retorna o atributo qtde
	 * @return qtde int
	 */
	public int getQtde(){
		return this.qtde;
	}

	/**
	 * Retorna o atributo array
	 * @return array Pessoa[]
	 */
	public Pessoa[] getArray(){
		return this.array;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e move os demais
	 * elementos para o fim da lista.
	 * @param pessoa Pessoa elemento a ser inserido.
	 * @throws Exception Se a lista estiver cheia.
	 */
	public void inserirInicio(Pessoa pessoa) throws Exception {

		//validar insercao
		if(getQtde() >= array.length){
			throw new Exception("Erro ao inserir!");
		} 

		//levar elementos para o fim do array
		for(int i = getQtde(); i > 0; i--){
			array[i] = array[i-1].clone();
		}

		array[0] = pessoa.clone();
		setQtde(getQtde()+1);
	}


	/**
	 * Insere um elemento na ultima posicao da lista.
	 * @param pessoa Pessoa elemento a ser inserido.
	 * @throws Exception Se a lista estiver cheia.
	 */
	public void inserirFim(Pessoa pessoa) throws Exception {

		//validar insercao
		if(getQtde() >= array.length){
			throw new Exception("Erro ao inserir!");
		}

		array[getQtde()] = pessoa.clone();
		setQtde(getQtde()+1);
	}


	/**
	 * Insere um elemento em uma posicao especifica e move os demais
	 * elementos para o fim da lista.
	 * @param pessoa Pessoa elemento a ser inserido.
	 * @param pos Posicao de insercao.
	 * @throws Exception Se a lista estiver cheia ou a posicao invalida.
	 */
	public void inserir(Pessoa pessoa, int pos) throws Exception {

		//validar insercao
		if(getQtde() >= array.length || pos < 0 || pos > getQtde()){
			throw new Exception("Erro ao inserir!");
		}

		//levar elementos para o fim do array
		for(int i = getQtde(); i > pos; i--){
			array[i] = array[i-1].clone();
		}

		array[pos] = pessoa.clone();
		setQtde(getQtde()+1);
	}


	/**
	 * Remove um elemento da primeira posicao da lista e movimenta 
	 * os demais elementos para o inicio da mesma.
	 * @return resp Pessoa Posicao elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public Pessoa removerInicio() throws Exception {

		//validar remocao
		if (getQtde() == 0) {
			throw new Exception("Erro ao remover!");
		}

		Pessoa resp = array[0].clone();
		setQtde(getQtde()-1);

		for(int i = 0; i < getQtde(); i++){
			array[i] = array[i+1].clone();
		}

		return resp;
	}


	/**
	 * Remove um elemento da ultima posicao da lista.
	 * @return resp Pessoa elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public Pessoa removerFim() throws Exception {

		//validar remocao
		if (getQtde() == 0) {
			throw new Exception("Erro ao remover!");
		}

		setQtde(getQtde()-1);

		return array[getQtde()];
	}


	/**
	 * Remove um elemento de uma posicao especifica da lista e 
	 * movimenta os demais elementos para o inicio da mesma.
	 * @param pos Posicao de remocao.
	 * @return resp Pessoa elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
	 */
	public Pessoa remover(int pos) throws Exception {

		//validar remocao
		if (getQtde() == 0 || pos < 0 || pos >= getQtde()) {
			throw new Exception("Erro ao remover!");
		}

		Pessoa resp = array[pos].clone();
		setQtde(getQtde()-1);

		for(int i = pos; i < getQtde(); i++){
			array[i] = array[i+1].clone();
		}

		return resp;
	}


	/**
	 * Mostra os elementos da lista separados por quebra de linha.
	 */
	public void mostrar(){
		for(int i = 0; i < getQtde(); i++){
			getArray()[i].imprimir();            
		}
	}


	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param pessoa Pessoa elemento a ser pesquisado.
	 * @return <code>true</code> se o array existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisaSequencial(int id) {
		boolean retorno = false;
		for (int i = 0; i < getQtde() && retorno == false; i++) {
			retorno = (array[i].getId() == id);
		}
		return retorno;
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param id int elemento a ser pesquisado.
	 * @return <code>true</code> se o array existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisaBinaria(int id) {
		boolean retorno = false;
		int dir = getQtde() - 1, esq = 0, meio;
		while (esq <= dir) {
			meio = (esq + dir) / 2;
			if (id == array[meio].getId()){
				retorno = true;
				esq = getQtde();
			} else if (id > array[meio].getId()){
				esq = meio + 1;
			} else {
				dir = meio - 1;
			}
		}
		return retorno;
	}

	/**
	 * Ordenação por bolha
	 */
	public void ordenacaoBolha(){
		for(int i=getQtde()-1; i>=0; i--){
			for(int j=0; j<i; j++){
				if(array[j].getLocalNascimento().compareTo(array[j+1].getLocalNascimento()) > 0){
					swap(j, j+1);
				}
				TP05Q10Bolha.COMPARACOES+=1;
			}
		}
	}

	/**
	 * Troca o conteudo de duas posicoes do array
	 * @param i int primeira posicao
	 * @param j int segunda posicao
	 */
	public void swap(int menor, int i) {
		Pessoa temp = array[i].clone();
		array[i] = array[menor].clone();
		array[menor] = temp.clone();
		TP05Q10Bolha.MOVIMENTACOES+=3;
	}
}
