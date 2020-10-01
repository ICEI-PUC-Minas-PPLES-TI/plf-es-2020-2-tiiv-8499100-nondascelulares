package br.tis.entidades;

import java.sql.Date;

public class Estoque {

	private Date dataCompra;
	private Produto produto;
	private double custoUnitario;
	private double precoVendaUnitario;
	private int quantidade;
	private double custoMedio;
	private int quantTotalDisponivel;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getCustoUnitario() {
		return custoUnitario;
	}

	public void setCustoUnitario(double custoUnitario) {
		this.custoUnitario = custoUnitario;
	}

	public double getPrecoVendaUnitario() {
		return precoVendaUnitario;
	}

	public void setPrecoVendaUnitario(double precoVendaUnitario) {
		this.precoVendaUnitario = precoVendaUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getCustoMedio() {
		return custoMedio;
	}

	public void setCustoMedio(double custoMedio) {
		this.custoMedio = custoMedio;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getQuantTotalProduto() {
		return quantTotalDisponivel;
	}

	public void setQuantTotalProduto(int quantTotalProduto) {
		this.quantTotalDisponivel = quantTotalProduto;
	}

}
