package br.tis.entidades;

public class ListaAgregada {
	
	private long idProduto; 
	private String nomeProduto;
	private int quantidadeDisp;
	private float precoVenda;
	private double total;
	private double precoMedio;
	private int quantMinima;
	private String situacaoQuant;
	
	
	public String getSituacaoQuant() {
		return situacaoQuant;
	}
	public void setSituacaoQuant(String situacaoQuant) {
		this.situacaoQuant = situacaoQuant;
	}
	public int getQuantMinima() {
		return quantMinima;
	}
	public void setQuantMinima(int quantMinima) {
		this.quantMinima = quantMinima;
	}
	public double getPrecoMedio() {
		return precoMedio;
	}
	public void setPrecoMedio(double precoMedio) {
		this.precoMedio = precoMedio;
	}
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public int getQuantidadeDisp() {
		return quantidadeDisp;
	}
	public void setQuantidadeDisp(int quantidadeDisp) {
		this.quantidadeDisp = quantidadeDisp;
	}
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	@Override 
	public String toString() {
		return getIdProduto() + " - " + getNomeProduto();
		
	}
	public double getTotal() {
		return total = getQuantidadeDisp()*getPrecoVenda();
	}

	
}
