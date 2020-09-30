package Entidades;

import java.io.Serializable;
import java.util.Random;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpfCnpj;
	private String email;
	private String telefone;
	private String rua;
	private String numero; 
	private String bairro;
	private String cidade;
	private String estado;
	
	Random random = new Random();

	public Cliente() {};
	
	public Cliente(String cpfCnpj, String nome, String email, String telefone,String rua, String numero, String bairro, String cidade, String estado) {

		setCpfCnpj(cpfCnpj);
		setNome(nome);
		setEmail(email);
		setTelefone(telefone);
		setRua(rua);
		setNumero(numero);
		setBairro(bairro);
		setCidade(cidade);
		setEstado(estado);
	}
	
	
	
	
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