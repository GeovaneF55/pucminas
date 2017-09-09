/**
 * TP03Q02 - Fila Sequencial
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 08/2016
 */
class TP03Q04FilaSequencial{
	static Fila fila = new Fila(5);

	public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF8");
		Pessoa pessoa;
		for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
			pessoa = new Pessoa();
			pessoa.ler(nomeArquivo);
			fila.inserir(pessoa);
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

		//fila.mostrar();
	}

	/**
	 * OBS: Esse método foi realizado em conjunto com o aluno Gabriel Luciano
	 * Executa os comandos da fila
	 * @param comando String[] String que contém os comandos e os id caso houver
	 * @param atributos String[] String que contém os atributos
	 * @throws Exception
	 */
	public static void executarComando(String[] comando, String[] atributos) throws Exception{
		Pessoa pessoa;

		switch(comando[0]){
			// Remover da fila
			case "R":
				pessoa = fila.remover();
				break;
			// Inserir na pilha
			case "I":
				pessoa = new Pessoa(Integer.parseInt(comando[1]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ","")));
				fila.inserir(pessoa);
				break;
			// Default
			default:
				throw new Exception("Erro ao executar comando!");
		}
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

class Fila {
	private Pessoa[] array;
	private int primeiro; // Remove do indice "primeiro".
	private int ultimo; // Insere no indice "ultimo".


	/**
	 * Construtor da classe.
	 */
	public Fila () {
		this(5);
	}


	/**
	 * Construtor da classe.
	 * @param tamanho Tamanho da fila.
	 */
	public Fila (int tamanho){
		array = new Pessoa[tamanho+1];
		setPrimeiro(0);
		setUltimo(0);
	}

	/**
	 * Seta o atributo primeiro
	 * @param primeiro int
	 */
	public void setPrimeiro(int primeiro){
		this.primeiro = primeiro;
	}

	/**
	 * Retorna o atributo primeiro
	 * @return primeiro int
	 */
	public int getPrimeiro(){
		return this.primeiro;
	}

	/**
	 * Seta o atributo ultimo
	 * @param ultimo int
	 */
	public void setUltimo(int ultimo){
		this.ultimo = ultimo;
	}

	/**
	 * Retorna o atributo ultimo
	 * @return ultimo int
	 */
	public int getUltimo(){
		return this.ultimo;
	}

	/**
	 * Retorna o atributo array
	 * @return array Pessoa[]
	 */
	public Pessoa[] getArray(){
		return this.array;
	}

	/**
	 * Insere um elemento na ultima posicao da fila.
	 * @param pessoa Pessoa elemento a ser inserido.
	 */
	public void inserir(Pessoa pessoa) throws Exception{
				
		//validar insercao
		if (((getUltimo() + 1) % array.length) == getPrimeiro()) {
			remover();
		}

		array[getUltimo()] = pessoa.clone();
		setUltimo((getUltimo() + 1) % array.length);
		
		mediaIdades();
	}


	/**
	 * Remove um elemento da primeira posicao da fila e movimenta 
	 * os demais elementos para o primeiro da mesma.
	 * @return resp Pessoa elemento a ser removido.
	 * @throws Exception Se a fila estiver vazia.
	 */
	public Pessoa remover() throws Exception {

		//validar remocao
		if (getPrimeiro() == getUltimo()) {
			throw new Exception("Erro ao remover!");
		}

		Pessoa resp = array[getPrimeiro()].clone();
		setPrimeiro((getPrimeiro() + 1) % array.length);
		return resp;
	}


	/**
	 * Mostra os elementos do array separados por quebra de linhas.
	 */
	public void mostrar(){
		for(int i = getPrimeiro(); i != getUltimo(); i = ((i + 1) % array.length)) {
			getArray()[i].imprimir();
		}
	}

	/**
	 * Mostra os elementos do array separados por quebra de linhas.
	 */
	public void mostrarRec(){
		mostrarRec(getPrimeiro());
	}

	/**
	 * Método recursivo para mostrar os elementos do array
	 */
	public void mostrarRec(int i){
		if(i != getUltimo()){
			getArray()[i].imprimir();
			mostrarRec((i + 1) % array.length);
		}
	}


	/**
	 * Retorna um boolean indicando se a fila esta vazia
	 * @return boolean indicando se a fila esta vazia
	 */
	public boolean isVazia() {
		return (getPrimeiro() == getUltimo()); 
	}

	/*
	 *
	 *
	 */
	public void mediaIdades(){
		int somaIdades = 0,
		    contador = 0;
		for(int i = getPrimeiro(); i != getUltimo(); i = ((i + 1) % array.length)) {
			somaIdades += getArray()[i].getIdade();
			contador++;
		}
		MyIO.println((int)Math.round((double)somaIdades/(double)contador));
	}
}
