package br.tis.entidades;

import java.sql.Date;

public class Estoque {

	private long idEstoque;
	private TipoLancamento tipoLancamento;
	private Date dataLancamento;
	private long idproduto;
	private String nomeProduto;
	private double custoUnitario;
	private double precoVendaUnitario;
	private int quantidade;
	private String documento; 

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
	
		//No cado da Ordem de venda a quantidade deve ser sempre negativa
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

	public long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public long getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(long idproduto) {
		this.idproduto = idproduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

}
