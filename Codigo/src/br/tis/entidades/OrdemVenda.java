package br.tis.entidades;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class OrdemVenda {

	private Date data;
	private long idOrdemVenda;
	private String cpfCnpjCliente;
	private String observacao;
	private float valorTotal;
	private List<Estoque> lancamentosSaida;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValorTotal() {

		return this.valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public long getIdOrdemVenda() {
		return idOrdemVenda;
	}

	public void setIdOrdemVenda(Long idOrdemVenda) {

		this.idOrdemVenda = idOrdemVenda;
	}

	public long geraNumOrdemVenda() {

		Random ramdom;
		Long resultado = 0L;
		try {
			ramdom = SecureRandom.getInstanceStrong();
			resultado = Long.valueOf(ramdom.nextInt(999999));
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.idOrdemVenda = resultado;
		return resultado;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Estoque> getLancamentosSaida() {
		return lancamentosSaida;
	}

	public void setLancamentosSaida(List<Estoque> lancamentosSaida) {
		this.lancamentosSaida = lancamentosSaida;
	}
}
