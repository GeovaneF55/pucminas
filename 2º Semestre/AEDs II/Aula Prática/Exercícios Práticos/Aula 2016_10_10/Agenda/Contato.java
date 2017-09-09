class Contato{
	private String nome,telefone,email;
	private int cpf;

	public Contato(){
        this("", "", "", 0);		
	}

	public Contato(String nome, String telefone, String email, int cpf){
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		setCpf(cpf);
	}

	// Métodos Setters
	public void setNome(String nome){
		this.nome = nome;
	}

	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setCpf(int cpf){
		this.cpf = cpf;
	}


	// Métodos Getters
	public String getNome(){
		return this.nome;
	}

	public String getTelefone(){
		return this.telefone;
	}

	public String getEmail(){
		return this.email;
	}

	public int getCpf(){
		return this.cpf;
	}
}

