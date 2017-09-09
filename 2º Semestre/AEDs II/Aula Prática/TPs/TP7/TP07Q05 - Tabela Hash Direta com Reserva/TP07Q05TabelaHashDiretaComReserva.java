/**
 * TP07Q05 - Tabela Hash Direta Com Reserva
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 11/2016
 */

import java.util.*;

class TP07Q05TabelaHashDiretaComReserva{
	static HashDiretaComReserva hash = new HashDiretaComReserva();
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
					//hash.remover(Integer.parseInt(entrada));
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
		Arq.openWrite(MATRICULA + "_hashReserva.txt");
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

class HashDiretaComReserva {
	Pessoa tabela[];
	int m1, m2, m, reserva;
	Pessoa NULO = null;

	public HashDiretaComReserva (){
		this(21, 9);
	}

	public HashDiretaComReserva (int m1, int m2){
		this.m1 = m1;
		this.m2 =  m2;
		this.m = m1 + m2;
		this.tabela = new Pessoa[this.m];
		for(int i = 0; i < m; i++){
			tabela[i] = NULO;
		}
		reserva = 0;
	}

	public int h(int id){
		return id % m1;
	}

	public boolean inserir (Pessoa pessoa) throws Exception{
		boolean resp = false;

		if(pessoa != NULO){
			int pos = h(pessoa.getId());

			if(tabela[pos] == NULO){
				TP07Q05TabelaHashDiretaComReserva.COMPARACOES+=1;
				tabela[pos] = pessoa.clone();
				resp = true;

			} else if (reserva < m2){
				TP07Q05TabelaHashDiretaComReserva.COMPARACOES+=2;
				tabela[m1 + reserva] = pessoa.clone();
				reserva++;
				resp = true;
			}
		}

		return resp;
	}


	public boolean pesquisar(int id) throws Exception{
		boolean resp = false;

		int pos = h(id);

		if(tabela[pos].getId() == id){
			TP07Q05TabelaHashDiretaComReserva.COMPARACOES+=1;
			resp = true;
		} else {
			TP07Q05TabelaHashDiretaComReserva.COMPARACOES+=1;
			for(int i = 0; i < reserva; i++){
				if(tabela[m1 + i].getId() == id){
					resp = true;
					i = reserva;
				}
				TP07Q05TabelaHashDiretaComReserva.COMPARACOES+=1;
			}
		}
		return resp;
	}
}
