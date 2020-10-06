package br.tis.entidades;

public class ListaAgregada {
	
	private long idProduto; 
	private String nomeProduto;
	private int quantidadeDisp;
	private double precoVenda;
	
	
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
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	
}
