/**
 * TP01 - Extra
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 08/2017
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.RandomAccessFile;

class TP1Extra{

	public static final int MAT = 0;
	public static final int DEP = 1;

	public static void main(String[] args) throws IOException{
		String nomeArquivo = "materias.in";

		Grafo grafo = leGrafo(nomeArquivo);

		menu(grafo);
	}

	/**
         * @param grafo Grafo
         */
	public static void menu(Grafo grafo){
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n--MENU--" +
			   "\n1 - Consultar matéria" +
			   "\n2 - Disciplinas com dependência" +
			   "\n3 - Sair" +
			   "\nOpção: ");
		int op = scanner.nextInt();
		switch(op){
			case 1:
				System.out.print("Matéria desejada: ");
				String materia = scanner.next();
				System.out.println(dependencias(grafo, materia));
				menu(grafo);
			break;
			case 2:
				System.out.println(disciplinasComDependencia(grafo));
				menu(grafo);
			break;
			case 3:
				System.out.println("fim");
			break;
			default:
				menu(grafo);
			break;
		}
	}

	/**
         * @param nomeArquivo String
         * @return grafo Grafo.
         */
	public static Grafo leGrafo(String nomeArquivo) throws IOException{

		RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "r");
		ArrayList<String> dependencias = new ArrayList<String>();
		Grafo grafo = new Grafo();
		Vertice origem,
		        destino;
		String[] materias,
		         array_dep;

		/*LER DADOS DO ARQUIVO*/
		for(String frase = arquivo.readLine(); frase != null; frase = arquivo.readLine()){
			materias = frase.split(";");
			grafo.adicionaVertice(materias[MAT]);
			try{
				dependencias.add(materias[DEP]);
			} catch(ArrayIndexOutOfBoundsException ex){
				dependencias.add("");
			}
		}

		arquivo.close();

		for(int i=0; i < dependencias.size(); i++){
			origem = grafo.getVertice(i);

			array_dep = dependencias.get(i).split(",");
			for(int j=0; j < array_dep.length; j++){
				destino = grafo.getVertice(array_dep[j]);
				if(destino != null){
					grafo.adicionaAresta(origem, destino);
				}
			}
		}

		return grafo;
	}

	/**
	 * @param grafo Grafo.
	 * @param nome String.
	 * @return resposta String.
     */
	public static String dependencias(Grafo grafo, String nome){
		String resposta = "";

		Aresta aresta;
		Vertice vertice = grafo.getVertice(nome);
		if(vertice != null){
			resposta += vertice.getNome() + ";";
			for(int i=0; i < grafo.tamanhoArestas(); i++){
				aresta = grafo.getAresta(i);
				if(vertice.getNome().equals(aresta.getV1().getNome())){
					Vertice v2 = aresta.getV2();
					resposta += v2.getNome()+",";
					resposta += subDependencias(grafo, v2.getNome());
				}
			}
			resposta = resposta.substring(0, resposta.length()-1);
		}
		return resposta;
	}

	/**
	 * @param grafo Grafo.
	 * @param nome String.
	 * @return resposta String.
         */
	public static String subDependencias(Grafo grafo, String nome){
		String resposta = "";

		Aresta aresta;
		Vertice vertice = grafo.getVertice(nome);
		if(vertice != null){
			for(int i=0; i < grafo.tamanhoArestas(); i++){
				aresta = grafo.getAresta(i);
				if(vertice.getNome().equals(aresta.getV1().getNome())){
					Vertice v2 = aresta.getV2();
					resposta += v2.getNome()+",";
					subDependencias(grafo, v2.getNome());
				}
			}
		}
		return resposta;
	}

	/**
	 * @param grafo Grafo.
	 * @return resposta String.
         */
	public static String disciplinasComDependencia(Grafo grafo){
		String resposta = "";
		boolean dep;
		Vertice v;

		for(int i=0; i < grafo.tamanhoVertices(); i++){
			v = grafo.getVertice(i);
			dep = false;
			for(int j=0; !dep && j < grafo.tamanhoArestas(); j++){
				if(v == grafo.getAresta(j).getV1() || v == grafo.getAresta(j).getV2()){
					resposta += v.getNome() + "\n";
					dep = true;
				}
			}
		}
		resposta = resposta.substring(0, resposta.length()-1);
		return resposta;
	}
}

class Grafo {
	private ArrayList<Aresta> arestas;
	private ArrayList<Vertice> vertices;
	private boolean digrafo;

	/**
	 * Construtor da classe.
	 */
	public Grafo() {
		this(false);
	}

	/**
	 * Construtor da classe.
	 */
	public Grafo(boolean digrafo) {
		setDigrafo(digrafo);
		this.vertices = new ArrayList<Vertice>();
		this.arestas = new ArrayList<Aresta>();
	}

	/**
	 * @return digrafo boolean.
	 */
	public boolean getDigrafo() {
		return this.digrafo;
	}

	/**
	 * @param digrafo int.
	 */
	public void setDigrafo(boolean digrafo) {
		this.digrafo = digrafo;
	}

	/**
	 * @param i int (posicao no ArrayList).
	 * @return v Vertice.
	 */
	public Vertice getVertice(int i){
		return ((Vertice)vertices.get(i));
	}

	/**
	 * @param nome String (nome no ArrayList).
	 * @return v Vertice.
	 */
	public Vertice getVertice(String nome){
		Vertice vertice = null,
			auxiliar;
		for(int i=0; i < tamanhoVertices(); i++){
			auxiliar = ((Vertice)vertices.get(i));
			if(auxiliar.getNome().equals(nome)){
				vertice = auxiliar;
			}
		}
		return vertice;
	}

	/**
	 * @param i int (posicao no ArrayList).
	 * @return a Aresta.
	 */
	public Aresta getAresta(int i){
		return ((Aresta)arestas.get(i));
	}

	/**
	 * @return tamanho int
	 */
	public int tamanhoVertices(){
		return vertices.size();
	}

	/**
	 * @return tamanho int
	 */
	public int tamanhoArestas(){
		return arestas.size();
	}

	/**
	 * (Adiciona vertice sem nome)
	 */
	public void adicionaVertice(){
		this.adicionaVertice(Integer.toString(vertices.size()));
	}

	/**
	 * @param nome String
	 */
	public void adicionaVertice(String nome){
		Vertice v = new Vertice(nome);
		vertices.add(v);
	}

	/**
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 */
	public void adicionaAresta(Vertice v1, Vertice v2){
		this.adicionaAresta(v1, v2, 1);
	}

	/**
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 * @param peso int.
	 */
	public void adicionaAresta(Vertice v1, Vertice v2, int peso){
		if(!existeAresta(v1, v2)){
			Aresta aresta = new Aresta(v1, v2, peso);
			arestas.add(aresta);

			v1.setGrau(v1.getGrau()+1);
			v2.setGrau(v2.getGrau()+1);
		}
	}

	/**
	 * @param v1 Vertice.
	 * @param v2 Vertice.
 	 * @return existe boolean.
	 */
	public boolean existeAresta(Vertice v1, Vertice v2){
		boolean existe = false;
		Aresta aresta;

		int i=0;
		while(existe == false && i < arestas.size()){
			aresta = getAresta(i);
			if(aresta.existeAresta(v1, v2, getDigrafo())){
				existe = true;
			}
			i++;
		}
		return existe;
	}

	/**
 	 * @return qtde int (Quantidade de arestas).
	 */
	public int qtdArestas(){
		return arestas.size();
	}

	/**
 	 * @return qtde int (Quantidade de vertices).
	 */
	public int qtdVertices(){
		return vertices.size();
	}

	/**
 	 * @return completo boolean.
	 */
	public boolean ehCompleto(){
		boolean completo = true;

		for(int i=0; completo && i<vertices.size(); i++){
			for(int j=(i+1); completo && j<vertices.size(); j++){
				if(!existeAresta(getVertice(i), getVertice(j))){
					completo = false;
				}
			}
		}
		return completo;
	}

	/**
 	 * @return qtde int (Quantidade de vertices).
	 */
	public Grafo criaComplementar(){
		Grafo complementar = new Grafo(getDigrafo());
		Vertice v1, v2;

		for(int i=0; i<vertices.size(); i++){
                        for(int j=(i+1); j<vertices.size(); j++){
				v1 = getVertice(i);
				v2 = getVertice(j);
                                if(!existeAresta(v1, v2)){
					complementar.adicionaVertice(v1.getNome());
					complementar.adicionaVertice(v2.getNome());
					complementar.adicionaAresta(v1.clone(), v2.clone());
                                }
                        }
                }
		return complementar;
	}

	/**
	 * @param novo Grafo
	 */
	public Grafo clone() {
		Grafo novo = new Grafo();
		novo.arestas = this.arestas;
		novo.vertices = this.vertices;
		novo.setDigrafo(this.getDigrafo());
		return novo;
	}
}

class Aresta {
	private Vertice v1;
	private Vertice v2;
	private int peso;

	/**
	 * Construtor da classe.
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 */
	public Aresta(Vertice v1, Vertice v2) {
		this(v1, v2, 0);
	}

	/**
	 * Construtor da classe.
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 * @param peso int.
	 */
	public Aresta(Vertice v1, Vertice v2, int peso) {
		setV1(v1);
		setV2(v2);
		setPeso(peso);
	}

	/**
	 * @return v1 Vertice.
	 */
	public Vertice getV1() {
		return this.v1;
	}

	/**
	 * @param v1 Vertice
	 */
	public void setV1(Vertice v1) {
		this.v1 = v1;
	}

	/**
	 * @return v2 Vertice.
	 */
	public Vertice getV2() {
		return this.v2;
	}

	/**
	 * @param v2 Vertice
	 */
	public void setV2(Vertice v2) {
		this.v2 = v2;
	}

	/**
	 * @return peso int.
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * @param peso int
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 * @return existe boolean.
	 */
	public boolean existeAresta(Vertice v1, Vertice v2){
		return existeAresta(v1, v2, false);
	}

	/**
	 * @param v1 Vertice.
	 * @param v2 Vertice.
	 * @param digrafo boolean.
	 * @return existe boolean.
	 */
	public boolean existeAresta(Vertice v1, Vertice v2, boolean digrafo){
		boolean existe = false;

		if((getV1() == v1)&&(getV2() == v2)){
			existe = true;
		}
		if((!digrafo)&&(getV1() == v2)&&(getV2() == v1)){
			existe = true;
		}
		return existe;
	}

	/**
	 * @param v Vertice.
	 * @return contem boolean.
	 */
	public boolean contemVertice(Vertice v){
		boolean contem = false;
		if(getV1() == v){
			contem = true;
		}
		if(getV2() == v){
			contem = true;
		}
		return contem;
	}

	/**
	 * @param novo Aresta
	 */
	public Aresta clone() {
		Aresta novo = new Aresta(this.getV1(), this.getV2());
		novo.setPeso(this.getPeso());
		return novo;
	}
}

class Vertice {
	private String nome;
	private int grau;

	/**
	 * Construtor da classe.
	 * @param nome String.
	 */
	public Vertice(String nome) {
		setNome(nome);
		setGrau(0);
	}

	/**
	 * @return nome String.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return grau int.
	 */
	public int getGrau() {
		return this.grau;
	}

	/**
	 * @param grau int
	 */
	public void setGrau(int grau) {
		this.grau = grau;
	}

	/**
	 * @param novo Vertice
	 */
	public Vertice clone() {
		Vertice novo = new Vertice(this.getNome());
		novo.setGrau(this.getGrau());
		return novo;
	}
}
