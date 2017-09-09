/**
 * TP06Q04 - Quicksort com LISTA DINAMICA DUPLAMENTE ENCADEADA
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2016
 */

import java.util.*;

class TP06Q04QuicksortComListaDinamicaDuplamenteEncadeada{
	static ListaDupla listadupla = new ListaDupla();
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
			listadupla.inserirFim(pessoa);
		}

		COMECO = now();
		listadupla.ordenacaoQuicksort();
		FIM = now();

		listadupla.mostrar();

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
		Arq.openWrite("553237_quicksort2.txt");
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

	/**
	 * Retorna <code>true</code> caso seja mais velho e <code>false</code> caso não seja
	 * @param pessoa Pessoa
	 * @return maisVelho boolean
	 */
	public boolean ehMaisVelho(Pessoa pessoa){
		int anoP1 = anoPadrao();
		int anoP2 = anoPadrao(pessoa.getDataNascimento());
		boolean maisVelho = (anoP2 > anoP1),
			idMaiorMesmaIdade = (anoP1 == anoP2 && this.getId() < pessoa.getId());

		return maisVelho || idMaiorMesmaIdade;
	}

	/**
	 * Retorna a data no padrão do exercício
	 * @return data String
	 */
	public int anoPadrao(){
		return anoPadrao(this.getDataNascimento());
	}

	/**
	 * Retorna a data no padrão do exercício
	 * @param dataNascimento String
	 * @return data String
	 */
	public int anoPadrao(String data){
		int anoPadrao;		
		String []dataArray = data.split(" de ");

		if(data.contains("ca.")){ dataArray = new String[3]; dataArray[2] = "-" + somenteNumeros(data);}

		try{
			anoPadrao = Integer.parseInt(dataArray[2]);
		} catch(ArrayIndexOutOfBoundsException exception){
			anoPadrao = Integer.parseInt("0000");
		}

		return anoPadrao;
	}
}

class CelulaDupla {
	public Pessoa pessoa;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this(new Pessoa());
	}


	/**
	 * Construtor da classe.
	 * @param pessoa Pessoa inserida na celula.
	 */
	public CelulaDupla(Pessoa pessoa) {
		this.pessoa = pessoa;
		this.ant = this.prox = null;
	}
}

class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;

	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	/**
	 * Insere uma pessoa na primeira posicao da lista.
	 * @param pessoa Pessoa pessoa a ser inserida.
	 */
	public void inserirInicio(Pessoa pessoa) {
		CelulaDupla tmp = new CelulaDupla(pessoa);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant = tmp;
		}
		tmp = null;
	}


	/**
	 * Insere uma pessoa na ultima posicao da lista.
	 * @param pessoa Pessoa pessoa a ser inserida.
	 */
	public void inserirFim(Pessoa pessoa) {
		ultimo.prox = new CelulaDupla(pessoa);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}


	/**
	 * Remove uma pessoa da primeira posicao da lista.
	 * @return resp Pessoa pessoa a ser removida.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Pessoa removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Pessoa resp = primeiro.pessoa.clone();
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;
	}


	/**
	 * Remove uma pessoa da ultima posicao da lista.
	 * @return resp Pessoa pessoa a ser removida.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Pessoa removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
		Pessoa resp = ultimo.pessoa.clone();
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		return resp;
	}


	/**
	 * Insere uma pessoa em uma posicao especifica considerando que o 
	 * primeiro elemento valido esta na posicao 0.
	 * @param pessoa Pessoa pessoa a ser inserida.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(Pessoa pessoa, int pos) throws Exception {

		int tamanho = tamanho();

		if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0){
			inserirInicio(pessoa);
		} else if (pos == tamanho){
			inserirFim(pessoa);
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(pessoa);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}
	}


	/**
	 * Remove uma pessoa de uma posicao especifica da lista
	 * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
	 * @return resp Pessoa pessoa a ser removida.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Pessoa remover(int pos) throws Exception {
		Pessoa resp;
		int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

		} else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0){
			resp = removerInicio();
		} else if (pos == tamanho - 1){
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.pessoa.clone();
			i.prox = i.ant = null;
			i = null;
		}

		return resp;
	}


	/**
	 * Mostra as da lista separados por espacos.
	 */
	public void mostrar() {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			i.pessoa.imprimir();
		}
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
	public int tamanho() {
		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
	}

	/**
	 * Calcula e retorna a posicao do elemento na lista.
	 * @return pos int posicao
	 */
	public int posicao(CelulaDupla celula) throws Exception{
		int pos = -1;
		boolean encontrou = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, pos++) {
         		if(i.pessoa.getId() == celula.pessoa.getId()){
				encontrou = true;
            			i = ultimo;
         		}
		}
		if(encontrou == false){
			throw new Exception("Erro!");
		}
		return pos;
	}

	/**
	 * Calcula e retorna o pivo da lista.
	 * @return pos int posicao
	 */
	public CelulaDupla pivo(int esq, int dir){
		CelulaDupla pivo = primeiro.prox;
		int pos = ((esq+dir)/2);

		for (int i = 0; pivo != null && i < pos; pivo = pivo.prox, i++);

		return pivo;
	}	

	/**
	 * Ordenação por quicksort
	*/
	public void ordenacaoQuicksort() throws Exception{
		ordenacaoQuicksort(primeiro.prox, ultimo);
	}

	public void ordenacaoQuicksort(CelulaDupla esq, CelulaDupla dir) throws Exception{
		CelulaDupla i = esq,
		    	    j = dir;
		int pos_esq = posicao(esq),
		    pos_dir = posicao(dir);
		CelulaDupla pivo = pivo(pos_esq, pos_dir);
		int pos_pivo = posicao(pivo),
		    pos_i = pos_esq,
		    pos_j = pos_dir;

		while (pivo.ant != j && pivo.prox != i) {
			while (i != ultimo && i.pessoa.ehMaisVelho(pivo.pessoa)){
				if(i.prox != null)i = i.prox;
				pos_i++;
				TP06Q04QuicksortComListaDinamicaDuplamenteEncadeada.COMPARACOES+=1;
			}
			while (j.ant != primeiro && pivo.pessoa.ehMaisVelho(j.pessoa)){
				if(j.ant != null)j = j.ant;
				pos_j--;
				TP06Q04QuicksortComListaDinamicaDuplamenteEncadeada.COMPARACOES+=1;
			}

			if (pos_i <= pos_j){
				swap(i, j);
				i = i.prox; pos_i++;
				j = j.ant; pos_j--;
			}
		}
		if (pos_esq < pos_j)
			ordenacaoQuicksort(esq, j);
		if (pos_i < pos_dir)
			ordenacaoQuicksort(i, dir);
	}

	/**
	 * Troca o conteudo de duas posicoes do array
	 * @param i CelulaDupla primeira posicao
	 * @param j CelulaDupla segunda posicao
	*/
	public void swap(CelulaDupla i, CelulaDupla j) {
		Pessoa tmp = i.pessoa.clone();
	      	i.pessoa = j.pessoa.clone();
	      	j.pessoa = tmp.clone();  
		TP06Q04QuicksortComListaDinamicaDuplamenteEncadeada.MOVIMENTACOES+=3;
	}
}
