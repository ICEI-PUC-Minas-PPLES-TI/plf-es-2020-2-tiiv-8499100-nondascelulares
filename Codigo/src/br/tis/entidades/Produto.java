package br.tis.entidades;

public class Produto {

	private Long idProduto;
	private String nome;
	private String descricao;
	private double precoVenda;
	private int quantMinima;
	
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

	@Override 
	public String toString() {
		return getIdProduto() + " - " + getNome();
	}

	public int getQuantMinima() {
		return quantMinima;
	}

	public void setQuantMinima(int quantMinima) {
		this.quantMinima = quantMinima;
	}
	
}//class