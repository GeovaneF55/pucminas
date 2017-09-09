/**
 * Algoritmo de ordenacao por selecao
 * @author Max do Val Machado
 * @version 2 01/2015
 */

class Selecao extends Geracao {

	/**
	 * Construtor.
	 */
	public Selecao(){
		super();
	}


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
	public Selecao(int tamanho){
		super(tamanho);
	}


	/**
	 * Algoritmo de ordenacao por selecao.
	 */
	public static void selecao() {
		for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n; j++){
				if (array[menor] > array[j]){
					menor = j;
				}
			}
			swap(menor, i);
		}
	}

	/**
	 * Algoritmo de ordenacao parcial por selecao parcial.
	 * @params k int
	 */
	public static void selecao(int k) throws Exception{
		if(k>n){
			throw new Exception("Erro!");
		}
		for (int i = 0; i <= (k - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n; j++){
				if (array[menor] > array[j]){
					menor = j;
				}
			}
			swap(menor, i);
		}
	}


	public static void main(String[] args) throws Exception{
		Selecao selecao = new Selecao(20);
		selecao.aleatorio();
		selecao.mostrar();

		long comeco = now();
		selecao.selecao();
		long fim = now();

		selecao.mostrar();
		System.out.println("Tempo para ordenar tudo: " + (fim-comeco)/1000.0 + " s.");

		selecao.aleatorio();

		selecao.mostrar();

		comeco = now();
		selecao.selecao(5);
		fim = now();

		selecao.mostrar();
		System.out.println("Tempo para ordenar parcialmente: " + (fim-comeco)/1000.0 + " s.");
	}
}
