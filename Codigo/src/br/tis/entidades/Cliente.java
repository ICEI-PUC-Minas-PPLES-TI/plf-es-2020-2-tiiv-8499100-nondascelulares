package br.tis.entidades;

public class Cliente  {

	private String nome;
	private String cpfCnpj;
	private String email;
	private String telefone;
	private String rua;
	private String numero; 
	private String bairro;
	private String cidade;
	private String estado;
	

	public Cliente() {};
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String f) {
		this.cpfCnpj = f;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void print() {
		System.out.println(this.nome + " " + this.cpfCnpj + " " + this.email + " " + this.telefone);
	}

	public String toString() {
		return ("Nome: " + this.nome + " - CPF: " + this.cpfCnpj);
	}

}//class cliente