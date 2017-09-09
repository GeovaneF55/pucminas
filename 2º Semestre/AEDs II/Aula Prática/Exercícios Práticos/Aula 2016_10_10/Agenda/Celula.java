class Celula{
	private Contato contato;
	private Celula prox;
	
	public Celula(){
        this(new Contato());
	}

    public Celula(Contato contato){
		setContato(contato);
		setProx(null);
	}

	public void setContato(Contato contato){
		this.contato = contato;
	}

	public Contato getContato(){
		return this.contato;
	}
		
	public void setProx(Celula prox){
		this.prox = prox;
	}
		
	public Celula getProx(){
		return this.prox;
	}
	
}



