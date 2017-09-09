/**
 * TP07Q02 - Árvore Binária de Árvores Binárias
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2016
 */
class TP07Q02ArvoreBinariaDeArvoreBinaria{
	  static ArvoreDeArvore arvoreDeArvore = new ArvoreDeArvore();
	  public static void main(String[] args) throws Exception {
		MyIO.setCharset("UTF8");
		String entrada;
		Pessoa pessoa;
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
			String[] atributos = entrada.split("##");
			String[] comando = atributos[0].trim().split(" ");
			executarComando(comando, atributos);
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

		int id = Integer.parseInt(comando[1]);

		switch(comando[0]){
			// Remover da pilha
			case "R":
				try{
					arvoreDeArvore.remover(id);
				} catch(Exception exception){}
				break;
				// Inserir na pilha
			case "I":
				pessoa = new Pessoa(id, atributos[1], atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], Integer.parseInt(atributos[7].replace(" ","")));
				arvoreDeArvore.inserir(pessoa);
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
		Arq.openRead(nomeArq, "UTF8");
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

	/**
	 * Retorna o mês em que a pessoa nasceu
	 * @return mês int
	 */
	public int getMes(){
		String mes;
		String data[] = this.getDataNascimento().split(" de ");

		try{
			mes = data[1];
		}catch(ArrayIndexOutOfBoundsException exception){
			mes = "";
		}
		return converteMes(mes);
	}

	public int converteMes(String mesString){
		int mesInt;
		switch (mesString) {
			case "janeiro":
				mesInt = 1;
			break;
			case "fevereiro":
				mesInt = 2;
			break;
			case "março":
				mesInt = 3;
			break;
			// ---- UTF8 não tratou isso
			case "marÃ§o":
				mesInt = 3;
			break;
			// ------
			case "abril":
				mesInt = 4;
			break;
			case "maio":
				mesInt = 5;
			break;
			case "junho":
				mesInt = 6;
			break;
			case "julho":
				mesInt = 7;
			break;
			case "agosto":
				mesInt = 8;
			break;
			case "setembro":
				mesInt = 9;
			break;
			case "outubro":
				mesInt = 10;
			break;
			case "novembro":
				mesInt = 11;
			break;
			case "dezembro":
				mesInt = 12;
			break;
			default:
				mesInt = 0;
			break;
		}
		return mesInt;
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
			i = new No(mes);
		} else if(mes < i.getMes()) {
			i.setEsq(inicializar(mes, i.getEsq()));
		} else {
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
			j = new No2(pessoa);
		} else if(pessoa.getId() < j.getPessoa().getId()) {
			j.setEsq(inserir(pessoa, j.getEsq()));
		} else if (pessoa.getId() > j.getPessoa().getId()) {
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
			resp = false;
		} else if(id == j.getPessoa().getId()){
			resp = true;
		} else if(id < j.getPessoa().getId()){
			resp = pesquisarNo2(id, j.getEsq());
		} else if(id > j.getPessoa().getId()){
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
			resp = false;
		} else if(id == j.getPessoa().getId()){
			resp = true;
		} else if(id < j.getPessoa().getId()){
			MyIO.print("esq ");
			resp = pesquisar(id, j.getEsq());
		} else if(id > j.getPessoa().getId()){
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
			resp = i;
		} else if(mes < i.getMes()) {
			resp = buscarNo(mes, i.getEsq());
		} else {
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
			i.setEsq(remover(id,i.getEsq()));
		} else if(id > i.getPessoa().getId()){
			i.setDir(remover(id,i.getDir()));
		} else if(i.getEsq() == null){
			i = i.getDir();
		} else if(i.getDir() == null) {
			i = i.getEsq();    
		} else {
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
			j.setDir(antecessor(i, j.getDir()));
		} else {
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
