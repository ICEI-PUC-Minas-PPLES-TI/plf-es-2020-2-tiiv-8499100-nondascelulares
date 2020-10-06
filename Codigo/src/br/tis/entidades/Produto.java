package br.tis.entidades;

public class Produto {

	private Long idProduto;
	private String nome;
	private String descricao;
	private double precoVenda;
	
	public Produto(){}

	public String getNome() {
		return nome;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long id) {
		this.idProduto = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return " Cod " + " " + idProduto +" Nome: " + nome + " ";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

}//class