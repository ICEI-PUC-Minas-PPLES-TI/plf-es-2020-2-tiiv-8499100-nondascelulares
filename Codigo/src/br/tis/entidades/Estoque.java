package br.tis.entidades;

import java.sql.Date;

public class Estoque {

	private TipoLancamento tipoLancamento;
	private Date dataLancamento;
	private Produto produto;
	private double custoUnitario;
	private double precoVendaUnitario;
	private int quantidade;
	private String documento; 

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

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataCompra) {
		this.dataLancamento = dataCompra;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

}
