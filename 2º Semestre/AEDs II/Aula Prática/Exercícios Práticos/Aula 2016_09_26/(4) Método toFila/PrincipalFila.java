/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class PrincipalFila {
	public static void main(String[] args) {
		try {
			System.out.println(" ==== FILA DINAMICA ====");
			Pilha pilha = new Pilha();
			Celula primeiro;

         		int x1, x2, x3;

			pilha.inserir(0);
			pilha.inserir(1);
			pilha.inserir(2);
			pilha.inserir(3);
			pilha.inserir(4);
			pilha.inserir(5);

			System.out.print("Apos inserções,\nA pilha (topo -> base): ");
			pilha.mostrar();

			primeiro = toFila(pilha.topo);

			mostrar(primeiro);
		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}

	public static Celula toFila(Celula topo){
		Celula primeiro = new Celula(),
		       ultimo = primeiro,
		       i = topo,
		       j = null;

		while(j != topo){
			for(i = topo; i.prox != j; i = i.prox);

			ultimo.prox = new Celula(i.elemento);
			ultimo = ultimo.prox;
			j = i;
		}
		return primeiro;
	}

	public static void mostrar(Celula primeiro){
		System.out.print("A fila (primeiro -> última):");
		String fila = " [ ";
		for(Celula i = primeiro.prox; i != null; i = i.prox){
			fila += i.elemento + " ";
		}
		fila += "] ";
		System.out.println(fila);
	}
}
