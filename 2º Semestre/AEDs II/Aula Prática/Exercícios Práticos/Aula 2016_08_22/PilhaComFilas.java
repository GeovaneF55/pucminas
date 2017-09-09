class PilhaComFilas {
	private Fila fila_entrada, fila_saida;

	PilhaComFilas(){
		this(5);
	}

	PilhaComFilas(int tamanho){
		this.fila_entrada = new Fila(tamanho);
		this.fila_saida = new Fila(tamanho);
	}

	public Fila getFilaEntrada(){
		return this.fila_entrada;
	}

	public Fila getFilaSaida(){
		return this.fila_saida;
	}

	public void inserir(int x) throws Exception{
		getFilaEntrada().inserir(x);
	}

	public int remover() throws Exception{
		int movido, i=0;
		while(!getFilaEntrada().isVazia()){
			movido = getFilaEntrada().remover();
			getFilaSaida().inserir(movido);
			i++;
		}
		while(i > 1){
			movido = getFilaSaida().remover();
			getFilaEntrada().inserir(movido);
			i--;
		}
		return getFilaSaida().remover();
	}

	public void mostrar(){
		getFilaEntrada().mostrar();
	}
}
