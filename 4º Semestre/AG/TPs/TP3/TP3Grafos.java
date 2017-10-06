/**
 * TP03 - Grafos
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2017
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

class TP3Grafos{

	public static Grafo grafo;
	public static final int MAT = 0;
	public static final int DEP = 1;

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
		grafo = leGrafo(in);

		List<Vertice> ordenado = kahn();
		imprimeOrdenado(ordenado);
	}

	/**
     * @param in BufferedReader
     * @return grafo Grafo.
     */
	public static Grafo leGrafo(BufferedReader in) throws IOException{
		List<String> dependencias = new ArrayList<String>();
		Grafo grafo = new Grafo(true);
		Vertice origem,
		        destino;
		String[] materias,
		         array_dep;

		/*LER DADOS DO ARQUIVO*/
		String frase = in.readLine();
		while(frase != null){
			materias = frase.split(";");
			grafo.adicionaVertice(materias[MAT]);
			try{
				dependencias.add(materias[DEP]);
			} catch(ArrayIndexOutOfBoundsException ex){
				dependencias.add("");
			}

			frase = in.readLine();
		}

		for(int i=0; i < dependencias.size(); i++){
			destino = grafo.getVertice(i);

			array_dep = dependencias.get(i).split(",");
			for(int j=0; j < array_dep.length; j++){
				origem = grafo.getVertice(array_dep[j]);
				if(origem != null){
					grafo.adicionaAresta(origem, destino);
				}
			}
		}

		return grafo;
	}

	/**
	 * @return L List<Vertice>.
	 */
	public static List<Vertice> kahn(){
		Grafo clone = grafo.clone();
		List<Vertice> L = new ArrayList<Vertice>();
		List<Vertice> S = verticesSemEntrada(clone);

		Collections.sort(S, new OrdenaPorNome());
		while(!S.isEmpty()){
			Vertice v = S.remove(0);
			L.add(v);

			List<Aresta> arestasVW = arestasVW(clone, v);
			for(int i = 0; i < arestasVW.size(); i++){
				Aresta removida = clone.getArestas().remove(clone.getArestas().indexOf(arestasVW.get(i)));
				Vertice w = removida.getV2();
				if(clone.naoTemEntrada(w)){
					S.add(w);
				}
			}
		}
		if(!clone.getArestas().isEmpty()){
			L.clear();
		}

		return L;
	}

	/**
	 * @param clone Grafo
	 * @return v_sem_entrada List<Vertice>.
	 */
	public static List<Vertice> verticesSemEntrada(Grafo clone){
		List<Vertice> v_sem_entrada = new ArrayList<Vertice>();
		for(int i=0; i < clone.tamanhoVertices(); i++){
			Vertice atual = clone.getVertice(i);
			if(clone.naoTemEntrada(atual)){
				v_sem_entrada.add(atual);
			}
		}
		return v_sem_entrada;
	}

	/**
	 * @param vertice Vertice.
	 * @return arestasVW List<Aresta>.
	 */
	public static List<Aresta> arestasVW(Grafo clone, Vertice vertice){
		List<Aresta> arestasVW = new ArrayList<Aresta>();
		for(int i=0; i < clone.tamanhoArestas(); i++){
			Aresta atual = clone.getAresta(i);
			if(vertice.getNome().equals(atual.getV1().getNome())){
				arestasVW.add(atual);
			}
		}

		return arestasVW;
	}

	/**
	 * @param ordenado ArrayList<Vertice>.
     */
	 public static void imprimeOrdenado(List<Vertice> ordenado){
		PrintStream out = new PrintStream(System.out, true);
		for(int i = 0; i < ordenado.size(); i++){
			if(i+1 < ordenado.size()){
				imprimeVertice(ordenado.get(i));
				out.print(" - ");
			} else{
				imprimeVertice(ordenado.get(i));
				out.println("");
			}
		}
	}

	public static void imprimeAresta(Aresta aresta){
		PrintStream out = new PrintStream(System.out, true);
		out.println(aresta.getV1().getNome() + " -- " + aresta.getV2().getNome());
	}

	/**
	 * @param origem Vertice.
	 * @param destino Vertice.
     */
	public static void imprimeVertice(Vertice vertice){
		MyIO.print(vertice.getNome());
	}
}

class OrdenaPorNome implements Comparator<Vertice> {
    @Override
    public int compare(Vertice v1, Vertice v2) {
        return v1.getNome().compareTo(v2.getNome());
    }
}

class Fila<T> {
	private List<T> objetos = new ArrayList<T>();

	/**
	 * @param t T.
     */
	public void insere(T t) {
		this.objetos.add(t);
	}

	/**
	 * @return t T.
     */
	public T remove() {
		return this.objetos.remove(0);
	}

	/**
	 * @return t T.
     */
	public boolean vazia() {
		return this.objetos.size() == 0;
	}
}

class Grafo{
	private List<Aresta> arestas;
	private List<Vertice> vertices;
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
	 * @return vertices List<Vertice>.
	 */
	public List<Vertice> getVertices(){
		return this.vertices;
	}

	/**
	 * @param i int (posicao no List).
	 * @return v Vertice.
	 */
	public Vertice getVertice(int i){
		return ((Vertice)vertices.get(i));
	}

	/**
	 * @param nome String (nome no List).
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
	 * @return arestas List<Aresta>.
	 */
	public List<Aresta> getArestas(){
		return this.arestas;
	}

	/**
	 * @param i int (posicao no List).
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
	 * @return temEntrada boolean.
     */
	public boolean naoTemEntrada(Vertice vertice){
		boolean naoTemEntrada = true;
		Aresta aresta;

		if(vertice != null){
			for(int i=0; naoTemEntrada == true && i < this.tamanhoArestas(); i++){
				aresta = this.getAresta(i);
				if(vertice.getNome().equals(aresta.getV2().getNome())){
					naoTemEntrada = false;
				}
			}
		}
		return naoTemEntrada;
	}

	/**
	 * @param vertice Vertice.
	 * @return adjacentes List<Vertice>.
     */
	public List<Vertice> adjacencia(Vertice vertice){
		List<Vertice> adjacentes;
		Vertice adjacente;
		Aresta aresta;

		adjacentes = new ArrayList<Vertice>();

		if(vertice != null){
			for(int i=0; i < this.tamanhoArestas(); i++){
				aresta = this.getAresta(i);
				if(vertice.getNome().equals(aresta.getV1().getNome())){
					adjacente = aresta.getV2();
					adjacentes.add(adjacente);
				} else if(vertice.getNome().equals(aresta.getV2().getNome())){
					adjacente = aresta.getV1();
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

	public Grafo clone() {
		/**
		* @param novo Grafo
		*/
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

	private Vertice pai;
	private char cor;

	/**
	 * Construtor da classe.
	 * @param nome String.
	 */
	public Vertice(String nome) {
		setNome(nome);
		setGrau(0);

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
		novo.setPai(this.getPai());
		return novo;
	}
}
