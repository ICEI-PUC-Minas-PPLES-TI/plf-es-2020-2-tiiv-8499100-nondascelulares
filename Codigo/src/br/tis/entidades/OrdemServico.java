package br.tis.entidades;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class OrdemServico {

	private Date data;
	private long idOrdemServico;
	private String marca;
	private String modelo;
	private String numSerie;
	private String status;
	private boolean semChip;
	private boolean semCartaoMemoria; 
	private boolean semBateria; 
	private boolean semTampaTraseira;
	private boolean desbloqueio;
	private boolean trocaBateria; 
	private boolean desoxidacao; 
	private boolean atualizacao; 
	private boolean limpeza; 
	private boolean slotChip;
	private boolean conectorCarga; 
	private boolean trocaTouch;
	private double valorTotal;
	private String idCliente;
	private String defeitos;
	private String observacao;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public long getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(long idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isSemChip() {
		return semChip;
	}
	public void setSemChip(boolean semChip) {
		this.semChip = semChip;
	}
	public boolean isSemCartaoMemoria() {
		return semCartaoMemoria;
	}
	public void setSemCartaoMemoria(boolean semCartaoMemoria) {
		this.semCartaoMemoria = semCartaoMemoria;
	}
	public boolean isSemBateria() {
		return semBateria;
	}
	public void setSemBateria(boolean semBateria) {
		this.semBateria = semBateria;
	}
	public boolean isSemTampaTraseira() {
		return semTampaTraseira;
	}
	public void setSemTampaTraseira(boolean semTampaTraseira) {
		this.semTampaTraseira = semTampaTraseira;
	}
	public boolean isDesbloqueio() {
		return desbloqueio;
	}
	public void setDesbloqueio(boolean desbloqueio) {
		this.desbloqueio = desbloqueio;
	}
	public boolean isTrocaBateria() {
		return trocaBateria;
	}
	public void setTrocaBateria(boolean trocaBateria) {
		this.trocaBateria = trocaBateria;
	}
	public boolean isDesoxidacao() {
		return desoxidacao;
	}
	public void setDesoxidacao(boolean desoxidacao) {
		this.desoxidacao = desoxidacao;
	}
	public boolean isAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(boolean atualizacao) {
		this.atualizacao = atualizacao;
	}
	public boolean isLimpeza() {
		return limpeza;
	}
	public void setLimpeza(boolean limpeza) {
		this.limpeza = limpeza;
	}
	public boolean isSlotChip() {
		return slotChip;
	}
	public void setSlotChip(boolean slotChip) {
		this.slotChip = slotChip;
	}
	public boolean isConectorCarga() {
		return conectorCarga;
	}
	public void setConectorCarga(boolean conectorCarga) {
		this.conectorCarga = conectorCarga;
	}
	public boolean isTrocaTouch() {
		return trocaTouch;
	}
	public void setTrocaTouch(boolean trocaTouch) {
		this.trocaTouch = trocaTouch;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getDefeitos() {
		return defeitos;
	}
	public void setDefeitos(String defeitos) {
		this.defeitos = defeitos;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public long geraNumOrdemServico() {

		Random ramdom;
		Long resultado = 0L;
		try {
			ramdom = SecureRandom.getInstanceStrong();
			resultado = Long.valueOf(ramdom.nextInt(999999));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultado;
	}


}
