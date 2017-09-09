/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class Pilha {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}


	/**
	 * Insere elemento na pilha (politica FILO).
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}


	/**
	 * Remove elemento da pilha (politica FILO).
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		int resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}


	/**
	 * Ex1: Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrarEx1() {
		System.out.print("[ ");

		for(Celula apontador = topo; apontador != null; apontador = apontador.prox){
			System.out.print(apontador.elemento + " ");
		}

		System.out.println("] ");
	}

	/**
	 * Ex2: Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrarEx2() {
		System.out.print("[ ");
		mostrarEx2(topo);
		System.out.println("] ");
	}

	public void mostrarEx2(Celula apontador) {
		if(apontador != null){
			System.out.print(apontador.elemento + " ");
			mostrarEx2(apontador.prox);
		}
	}

	/**
	 * Ex3: Mostra os elementos separados por espacos, comecando do fim.
	 */
	public void mostrarEx3() {
		System.out.print("[ ");
		mostrarEx3(topo);
		System.out.println("] ");
	}

	public void mostrarEx3(Celula apontador) {
		if(apontador != null){
			mostrarEx3(apontador.prox);
			System.out.print(apontador.elemento + " ");
		}
	}

	/**
	 * Ex4: Mostra os elementos separados por espacos, comecando do fim.
	 */
	public void mostrarEx4() {
		System.out.print("[ ");
		Celula i = topo, j = topo, tmp = null;
		while(tmp != topo){
			for(i = topo; i.prox != tmp; i = i.prox);
			for(j = topo; j != i; j = j.prox);
			tmp = j;
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}

	/**
	 * Ex1: Soma os elementos
	 * @return soma int
	 */
	public int somarEx1() {
		int soma = 0;

		for(Celula apontador = topo; apontador != null; apontador = apontador.prox){
			soma += apontador.elemento;
		}
		return soma;
	}

	/**
	 * Ex2: Soma os elementos
	 * @return soma int
	 */
	public int somarEx2() {
		return somarEx2(topo);
	}

	public int somarEx2(Celula apontador){
		int soma = 0;
		if(apontador != null){
			soma = apontador.elemento + somarEx2(apontador.prox);
		}
		return soma;
	}

	/**
	 * Ex3: Maior elemento
	 * @return maior int
	 */
	public int maiorEx3() {
		int maior = topo.elemento;

		for(Celula apontador = topo.prox; apontador != null; apontador = apontador.prox){
			if(maior < apontador.elemento){
				maior = apontador.elemento;
			}
		}
		return maior;
	}

	/**
	 * Ex4: Maior elemento
	 * @return maior int
	 */
	public int maiorEx4() {
		return maiorEx4(topo);
	}

	public int maiorEx4(Celula apontador){
		int maior = 0;
		if(apontador != null){
			if(maior < apontador.elemento){
				maior = apontador.elemento;
			}else if(apontador.prox != null){
				maior = maiorEx4(apontador.prox);
			}
		}
		return maior;
	}
}
