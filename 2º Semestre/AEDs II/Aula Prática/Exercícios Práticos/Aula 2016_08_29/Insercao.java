/**
 * Algoritmo de ordenacao por insercao
 * @author Max do Val Machado
 * @version 2 01/2015
 */

class Insercao extends Geracao {

	/**
	 * Construtor.
	 */
	public Insercao(){
		super();
	}


	/**
	 * Construtor.
	 * @param int tamanho do array de numeros inteiros.
	 */
	public Insercao(int tamanho){
		super(tamanho);
	}


	/**
	 * Algoritmo de ordenacao por insercao.
	 */
	public static void insercao() {
		for (int i = 1; i < n; i++) {
			int tmp = array[i];
			int j = i - 1;

			while ((j >= 0) && (array[j] > tmp)) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = tmp;
		}
	}

	/**
	 * Algoritmo de ordenacao parcial por insercao.
	 * @params k int
	 */
	public static void insercao (int k){
		int j;
		for (int i = 1; i < n; i++) {
			int tmp = array[i];
			if(i > k){
				j = k;
				if(tmp < array[k]){
					array[i] = array[k];
				}
			} else{
				j = i-1;
			}
			while (j >= 0 && tmp < array[j]) {
				if(j < k){
					array[j + 1] = array[j];
				}
				j--;
			}
			if(j < k){ 
				array[j + 1] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		Insercao insercao = new Insercao(20);
		insercao.aleatorio();
		insercao.mostrar();

		long comeco = now();
		insercao.insercao(5);
		long fim = now();

		insercao.mostrar();
		System.out.println("Tempo para ordenar parcialmente: " + (fim-comeco)/1000.0 + " s.");


	}
}
