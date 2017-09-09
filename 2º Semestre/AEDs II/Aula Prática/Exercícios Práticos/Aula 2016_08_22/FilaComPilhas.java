class FilaComPilhas{
	private Pilha pilha_entrada, pilha_saida;

	FilaComPilhas(){
		this(5);
	}

	FilaComPilhas(int tamanho){
		pilha_entrada = new Pilha(tamanho);
		pilha_saida = new Pilha(tamanho);
	}

	public Pilha getPilhaEntrada(){
		return this.pilha_entrada;
	}

	public Pilha getPilhaSaida(){
		return this.pilha_saida;
	}

	public void inserir(int x) throws Exception{
		getPilhaEntrada().inserir(x);
	}

	public int remover() throws Exception{
		int movido, i=0, resposta;
		while(!getPilhaEntrada().isVazia()){
			movido = getPilhaEntrada().remover();
			getPilhaSaida().inserir(movido);
			i++;
		}
		resposta = getPilhaSaida().remover();
		while(i > 1){
			movido = getPilhaSaida().remover();
			getPilhaEntrada().inserir(movido);
			i--;
		}
		return resposta;
	}

	public void mostrar(){
		getPilhaEntrada().mostrar();
	}
}
