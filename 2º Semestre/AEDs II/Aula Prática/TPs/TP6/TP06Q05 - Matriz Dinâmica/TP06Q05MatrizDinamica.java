/**
 * TP06Q05 - Matriz Dinâmica
 * @author Geovane Fonseca de Sousa Santos
 * @version 1 10/2016
 */
class TP06Q05MatrizDinamica {
	public static void main(String[] args){
		Matriz m1, m2, soma, multiplicacao;
		int qtLinhas,
		    qtColuna,
		    qtMatriz = MyIO.readInt();

		for(int c = 0; c < qtMatriz; c++){
			// Matriz 1
			qtLinhas = MyIO.readInt();
			qtColuna = MyIO.readInt();

			m1 = new Matriz(qtLinhas, qtColuna);
			m1.ler();
			m1.mostrarDiagonalPrincipal();
			m1.mostrarDiagonalSecundaria();

			// Matriz 2
			qtLinhas = MyIO.readInt();
			qtColuna = MyIO.readInt();
			
			m2 = new Matriz(qtLinhas, qtColuna);
			m2.ler();

			//Somar as duas matrizes e salvar o resultado na matriz soma
			soma = m1.soma(m2); //verifique se eh possivel somar

			//Imprimir a soma
			soma.mostrar();

			//Multiplicar duas matrizes e salvar o resultado na matriz multiplicacao
			multiplicacao = m1.multiplicacao(m2); //verifique se eh possivel multiplicar

			//Imprimir a matriz 1
			multiplicacao.mostrar();
		}
	}
}

class Celula {
	public int elemento;
	public Celula inf, sup, esq, dir;

	/**
	 * Construtor da classe.
	 */
	public Celula(){
		this(0, null, null, null, null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int
	 */
	public Celula(int elemento){
		this(elemento, null, null, null, null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int
	 * @param inf Celula
	 * @param sup Celula
	 * @param esq Celula
	 * @param dir Celula
	 */
	public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
		this.elemento = elemento;
		this.inf = inf;
		this.sup = sup;
		this.esq = esq;
		this.dir = dir;
	}
}

class Matriz {
	public Celula inicio;

	public int linha, coluna;

	/**
	 * Construtor da classe.
	 */
	public Matriz (){
		this(3, 3);
	}

	/**
	 * Construtor da classe.
	 * @param linha int
	 * @param coluna int
	 */
	public Matriz (int linha, int coluna){
		this.inicio = new Celula();
		this.linha = linha;
		this.coluna = coluna;
		alocarMatriz(this.linha, this.coluna);
	}

	/**
	 * alocar os ponteiros da matriz na variável início
	 * @param linha int
	 * @param coluna int
	 */
	public void alocarMatriz(int linha, int coluna){
		Celula novaY = null,
		       novaX = null;
		int x = 0,
		    y = 0;

		for(Celula j = inicio; x < linha; x++, j = novaX){
			y = 0;
			for(Celula i = j; y < coluna-1; y++, i = novaY){
				novaY = new Celula();
				// ponteiros direita e esquerda				
				i.dir = novaY;
				novaY.esq = i;
				// ponteiros superior e inferior
				if(novaY.esq != null && novaY.esq.sup != null){
					novaY.sup = novaY.esq.sup.dir;
					novaY.esq.sup.dir.inf = novaY;
				}
			}
			if(x < linha-1){
				novaX = new Celula();
				j.inf = novaX;
				novaX.sup = j;
			}
		}
	}


	/**
	 * Soma de matrizes
	 * @param matriz Matriz
	 * @return matriz Matriz
	 */
	public Matriz soma(Matriz matriz) {
		Matriz resp = null;

		if(this.linha == matriz.linha && this.coluna == matriz.coluna){
			resp = new Matriz(this.linha, this.coluna);

			Celula i1 = this.inicio,
			       i2 = matriz.inicio;
			for(Celula ir = resp.inicio; i1 != null && i2 != null && ir != null; i1 = i1.inf, i2 = i2.inf, ir = ir.inf){
				Celula j1 = i1,
			       	       j2 = i2;
				for(Celula jr = ir; j1 != null && j2 != null && jr != null; j1 = j1.dir, j2 = j2.dir, jr = jr.dir){
					jr.elemento = j1.elemento + j2.elemento;
				}
			}
		}

		return resp;
	}

	/**
	 * Multiplicação de matrizes
	 * @param matriz Matriz
	 * @return matriz Matriz
	 */
	public Matriz multiplicacao(Matriz matriz) {
		Matriz resp = null;
		
		if(this.coluna == matriz.linha){
			resp = new Matriz(this.linha, matriz.coluna);

			Celula i1 = this.inicio, j1 = this.inicio,
			       i2 = matriz.inicio, j2 = matriz.inicio;

			for(Celula ir = resp.inicio; ir != null;ir = ir.inf){
				for(Celula jr = ir; jr != null; jr = jr.dir){
					int elemento = 0;
					for(j1 = i1, i2 = j2; i2 != null; i2 = i2.inf, j1 = j1.dir){
						elemento += j1.elemento * i2.elemento;
					}
					jr.elemento = elemento;
					j2 = j2.dir;
				}
				i1 = i1.inf;
				j2 = matriz.inicio;
			}
		}

		return resp;
	}

	/**
	 * Verifica se a matriz é quadrada
	 * @return isQuadrada boolean
	 */
	public boolean isQuadrada(){
		return (this.linha == this.coluna);
	}

	/**
	 * Seta o atributo id
	 * @param id int
	 */
	public void mostrar(){
		for(Celula i = this.inicio; i != null; i = i.inf){
			for(Celula j = i; j != null; j = j.dir){
				MyIO.print(j.elemento + " ");
			}
			MyIO.println("");
		}
	}

	/**
	 * Mostra os elementos da diagonal principal
	 */
	public void mostrarDiagonalPrincipal(){
		if(isQuadrada() == true){
			Celula i = this.inicio;
			while(i != null){
				MyIO.print(i.elemento + " ");
				if(i.inf != null){
					i = i.inf.dir;
				} else {
					i = null;
				}
			}
			MyIO.println("");
		}
	}

	/**
	 * Mostra os elementos da diagonal secundária
	 */
	public void mostrarDiagonalSecundaria(){
		if(isQuadrada() == true){
			Celula i;
			for(i = this.inicio; i.dir != null; i = i.dir);
			while(i != null){
				MyIO.print(i.elemento + " ");
				if(i.inf != null){
					i = i.inf.esq;
				} else {
					i = null;
				}
			}
			MyIO.println("");
		}
	}

	/**
	 * Lê da entrada padrão os elementos de linha e coluna da matriz
	 */
	public void ler(){
		String linha[] = new String[this.linha];

		for(Celula i = this.inicio; i != null; i = i.inf){
			int x=0;			
			String frase = MyIO.readLine();
			linha = frase.split(" ");

			for(Celula j = i; j != null; j = j.dir){
				j.elemento = Integer.parseInt(linha[x]);
				x++;
			}
		}
	}
}
