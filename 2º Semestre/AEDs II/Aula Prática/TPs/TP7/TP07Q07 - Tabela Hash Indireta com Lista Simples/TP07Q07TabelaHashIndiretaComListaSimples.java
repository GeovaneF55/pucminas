/**
 * TP07Q07 - Tabela Hash Indireta Com Lista Simples
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 11/2016
 */

import java.util.*;

class TP07Q07TabelaHashIndiretaComListaSimples{
	static HashIndiretaComListaSimples hash = new HashIndiretaComListaSimples();
	static final int MATRICULA = 553237;
	static long COMECO,
		    FIM;
	static int COMPARACOES = 0;

	public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF8");
		String entrada;
		Pessoa pessoa;

		for(entrada = MyIO.readLine(); entrada.equals("FIM") == false; entrada = MyIO.readLine()){
			pessoa = new Pessoa();
			pessoa.ler(entrada);
			hash.inserir(pessoa);
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
				try {
					hash.inserir(pessoa);
				} catch(Exception e) { }
			} else {
				entrada = entrada.substring(2);
				try {
					hash.remover(Integer.parseInt(entrada));
				} catch(Exception e) { }
			}
			quantidade--;
		}

		COMECO = now();

		for(entrada = MyIO.readLine(); entrada.equals("FIM") == false; entrada = MyIO.readLine()){
			boolean achou;
			try {
				achou = hash.pesquisar(Integer.parseInt(entrada));
			} catch(Exception e) {
				achou = false;
			}

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
		Arq.openWrite(MATRICULA + "_hashIndireta.txt");
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

class Celula {
	public Pessoa pessoa;
	public Celula prox;

	/**
	 * Construtor da classe.
	 */
	Celula() {
		this(new Pessoa());
	}

	/**
	 * Construtor da classe.
	 * @param pessoa Pessoa inserida na celula.
	 */
	Celula(Pessoa pessoa) {
		this.pessoa = pessoa.clone();
		
		this.prox = null;
	}

	/**
	 * Construtor da classe.
	 * @param pessoa Pessoa inserida na celula.
	 * @param prox Aponta a celula prox.
	 */
	Celula(Pessoa pessoa, Celula prox) {
		this.pessoa = pessoa.clone();
		this.prox = prox;
	}
}

class Lista {
	private Celula primeiro;
	private Celula ultimo;

	/**
	 * Construtor da classe: Instancia uma celula (primeira e ultima).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	/**
	 * Procura uma pessoa e retorna se ela existe.
	 * @param id Pessoa a pesquisar.
	 * @return <code>true</code> se a pessoa existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(int id) {
		boolean retorno = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
			if(i.pessoa.getId() == id){
				retorno = true;
				i = ultimo;
			}
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
		}
		return retorno;
	}

	/**
	 * Procura uma pessoa e retorna a posicao dela na lista.
	 * @param id Pessoa a pesquisar.
	 * @return posicao int
	 */
	public int pesquisarPosicao(int id) {
		boolean retorno = false;
		int posicao = -1;
		for (Celula i = primeiro.prox; i != null && retorno == false; i = i.prox) {
			posicao ++;			
			if(i.pessoa.getId() == id){
				retorno = true;
				i = ultimo;
			}
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
		}
		return retorno? (posicao) : (-1);
	}

	/**
	 * Insere uma pessoa na primeira posicao da sequencia.
	 * @param C a inserir.
	 */
	public void inserirInicio(Pessoa pessoa) {
		Celula tmp = new Celula(pessoa);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			ultimo = tmp;
		}
		tmp = null;
	}

	/**
	 * Remove uma pessoa da ultima posicao da sequencia.
	 * @return pessoa removida.
	 * @throws Exception Se a sequencia nao contiver pessoas.
	 */
	public Pessoa removerFim() throws Exception {
		Pessoa resp = new Pessoa();
		Celula i = null;

		if (primeiro == ultimo) {
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			throw new Exception("Erro ao remover (vazia)!");
		} else {
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			resp = ultimo.pessoa.clone();

			// Caminhar ate a penultima celula:
			for(i = primeiro; i.prox != ultimo; i = i.prox);

			ultimo = i;
			i = ultimo.prox = null;
		}

		return resp;
	}

	/**
	 * Remove pessoa de um indice especifico.
	 * Considera que primeiro elemento esta no indice 0.
	 * @param posicao Meio da remocao.
	 * @return pessoa removida.
	 * @throws Exception Se <code>posicao</code> for incorreta.
	 */
	public Pessoa removerMeio(int id) throws Exception {
		Celula i;
		Pessoa resp = new Pessoa();
		int cont,
		    posicao = pesquisarPosicao(id);
		if (primeiro == ultimo){
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			throw new Exception("Erro ao remover (vazia)!");
		}else{
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			// Caminhar ate chegar na posicao anterior a insercao
			for(i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++);

			// Se indice for incorreto:
			if (posicao < 0 || posicao > cont + 1) {
				TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
				throw new Exception("Erro ao remover (posicao " + posicao + " invalida)!");

			} else if (posicao == cont + 1) {
				TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=2;
				resp = removerFim();
			}else{
				TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=2;
				Celula tmp = i.prox;
				resp = tmp.pessoa.clone();
				i.prox = tmp.prox;
				tmp.prox = null;
				i = tmp = null;
			}
		}

		return resp;
	}
}

class HashIndiretaComListaSimples {
	Lista tabela[];
	int tamanho;
	final Pessoa NULO = null;

	HashIndiretaComListaSimples (){
		this(21);
	}

	HashIndiretaComListaSimples (int tamanho){
		this.tamanho = tamanho;
		tabela = new Lista[tamanho];
		for(int i = 0; i < tamanho; i++){
			tabela[i] = new Lista();
		}
	}

	/**
	 * hash
	 * @param id int
	 * @return posicao int.
	 */
	public int h(int id){
		return id %tamanho;
	}

	/**
	 * Pesquisar pessoa
	 * @param id int
	 * @return boolen - verdadeiro se achou e falso se não achou.
	 */
	boolean pesquisar(int id){
		int pos = h(id);
		return tabela[pos].pesquisar(id);
	}

	/**
	 * Inserir pessoa
	 * @param pessoa Pessoa
	 */
	public void inserir(Pessoa pessoa){
		int pos = h(pessoa.getId());
		tabela[pos].inserirInicio(pessoa);
	}

	/**
	 * Remover pessoa
	 * @param id int da pessoa a remover
	 * @return pessoa Pessoa que removida
	 */
	public Pessoa remover(int id) throws Exception{
		Pessoa resp = NULO;
		if (pesquisar(id) == false){	
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			throw new Exception("Erro ao remover!");
		} else {
			TP07Q07TabelaHashIndiretaComListaSimples.COMPARACOES+=1;
			int pos = h(id);
			resp = tabela[pos].removerMeio(id);
		}
		return resp;
	}
}
