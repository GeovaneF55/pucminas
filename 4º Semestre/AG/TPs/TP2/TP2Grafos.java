/**
 * TP02 - Grafos
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 09/2017
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

class TP1Grafos{

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		Grafo grafo = leGrafo(in);

		Vertice origem = grafo.getVertice(0);
		inicializaLabirinto(grafo, origem);
		buscaLabirinto(grafo, origem);

		Vertice destino = grafo.getVertice(grafo.tamanhoVertices()-1);
		imprimeCaminho(destino);
	}

	/**
     * @param nomeArquivo String.
     * @return grafo Grafo.
     */
	public static Grafo leGrafo(BufferedReader in) throws IOException{
		Grafo grafo = new Grafo();
		String[] arestaArray;
		Vertice origem,
		        destino;
		int digrafo,
		    qtde_vertices,
		    peso;

		/*LER DADOS DO ARQUIVO*/

		// Seta digrafo ou grafo
		digrafo = Integer.parseInt(in.readLine());
		if(digrafo != 1){
			grafo.setDigrafo(false);
		} else{
			grafo.setDigrafo(true);
		}

		// Adiciona vertices
		qtde_vertices = Integer.parseInt(in.readLine());

		for(int i=0; i<qtde_vertices; i++){
			grafo.adicionaVertice();
		}

		// Adiciona arestas
		for(String aresta = in.readLine(); aresta.equals("FIM") == false; aresta = in.readLine()){
			arestaArray = aresta.split(",");
			origem = grafo.getVertice(Integer.parseInt(arestaArray[0]));
			destino = grafo.getVertice(Integer.parseInt(arestaArray[1]));
			peso = Integer.parseInt(arestaArray[2]);

			grafo.adicionaAresta(origem, destino, peso);
		}

		return grafo;
	}

	/**
	 * @param grafo Grafo.
	 * @param origem Vertice.
	 */
	public static void inicializaLabirinto(Grafo grafo, Vertice origem){
		for(int i=0; i < grafo.tamanhoVertices(); i++){
			Vertice atual = grafo.getVertice(i);
			if(atual.getNome() != origem.getNome()){
				atual.setCor('B');
				atual.setDistancia(Integer.MAX_VALUE);
				atual.setPai(null);
			}
		}
		origem.setCor('C');
		origem.setDistancia(0);
		origem.setPai(null);

		grafo.fila = new Fila<Vertice>();
	}

	/**
	 * @param grafo Grafo.
	 * @param origem Vertice.
	 */
	public static void buscaLabirinto(Grafo grafo, Vertice origem){
		grafo.fila.insere(origem);
		while(!grafo.fila.vazia()){
			Vertice u = grafo.fila.remove();
			ArrayList<Vertice> adjacentes = grafo.adjacencia(u);
			for(int i=0; i< adjacentes.size(); i++){
				Vertice v = adjacentes.get(i);
				if(v.getCor() == 'B'){
					v.setCor('C');
					v.setDistancia(u.getDistancia()+1);
					v.setPai(u);
					grafo.fila.insere(v);
				}
			}
			u.setCor('P');
		}
	}

	/**
	 * @param grafo Grafo.
     */
	public static void saida(Grafo grafo){
		PrintStream out = new PrintStream(System.out, true);
	}

	/**
	 * @param origem Vertice.
	 * @param destino Vertice.
     */
	public static void imprimeCaminho(Vertice destino){
		if(destino.getPai() != null){
			imprimeCaminho(destino.getPai());
			imprimeAresta(destino.getPai(), destino);
		}
	}

	/**
	 * @param origem Vertice.
	 * @param destino Vertice.
     */
	public static void imprimeAresta(Vertice origem, Vertice destino){
		MyIO.println("v" + origem.getNome() + " -> v" + destino.getNome());
	}
}

class Fila<T> {

  private ArrayList<T> objetos = new ArrayList<T>();

  public void insere(T t) {
    this.objetos.add(t);
  }

  public T remove() {
    return this.objetos.remove(0);
  }

  public boolean vazia() {
    return this.objetos.size() == 0;
  }
}

class Grafo {
	private ArrayList<Aresta> arestas;
	private ArrayList<Vertice> vertices;
	private boolean digrafo;

	public Fila<Vertice> fila;

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

		this.fila = new Fila<Vertice>();
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
	 * @param vertice Vertice.
	 * @return adjacentes ArrayList<Vertice>.
     */
	public ArrayList<Vertice> adjacencia(Vertice vertice){
		ArrayList<Vertice> adjacentes;
		Vertice adjacente;
		Aresta aresta;

		adjacentes = new ArrayList<Vertice>();

		if(vertice != null){
			for(int i=0; i < this.tamanhoArestas(); i++){
				aresta = this.getAresta(i);
				if(vertice.getNome().equals(aresta.getV1().getNome())){
					adjacente = aresta.getV2();
					adjacentes.add(adjacente);
				}
			}
		}
		return adjacentes;
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

	private int distancia;
	private Vertice pai;
	private char cor;

	/**
	 * Construtor da classe.
	 * @param nome String.
	 */
	public Vertice(String nome) {
		setNome(nome);
		setGrau(0);

		setDistancia(Integer.MAX_VALUE);
		setPai(null);
		setCor('B');
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
	 * @return distancia int.
	 */
	public int getDistancia() {
		return this.distancia;
	}

	/**
	 * @param distancia String
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	/**
	 * @return pai Vertice.
	 */
	public Vertice getPai() {
		return this.pai;
	}

	/**
	 * @param pai Vertice
	 */
	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	/**
	 * @return cor char.
	 */
	public char getCor() {
		return this.cor;
	}

	/**
	 * @param cor char
	 */
	public void setCor(char cor) {
		this.cor = cor;
	}

	/**
	 * @param novo Vertice
	 */
	public Vertice clone() {
		Vertice novo = new Vertice(this.getNome());
		novo.setGrau(this.getGrau());
		novo.setDistancia(this.getDistancia());
		novo.setPai(this.getPai());
		return novo;
	}
}
