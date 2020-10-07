package br.tis.entidades;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import br.tis.dao.EstoqueDAO;

public class OrdemVenda {

	private Date data;
	private long codVenda;
	private String cpfCnpjCliente;
	private String observacao;
	public List<ListaAgregada> produtos;
	public List<ListaAgregada> estoque;
	private float valorTotal;
	

	private Estoque lancamento = new Estoque();
	private EstoqueDAO estoqueDao = new EstoqueDAO(lancamento);


	public OrdemVenda() {
		data = Date.valueOf(LocalDate.now());
		codVenda = 0;
		produtos = new ArrayList<ListaAgregada>();
		estoque = new ArrayList<ListaAgregada>();
		valorTotal = 0;
	}

	public String getData() {
		return data.toString();
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ListaAgregada> getProdutos() {
		return this.produtos;
	}


	public List<ListaAgregada> getEstoque(){
		List<ListaAgregada> estoque = null;

		estoque = estoqueDao.getEstoqueAgregado();

		return estoque;
	}

	public boolean produtoExiste(long id) {

		for(ListaAgregada i : this.produtos) {
			if(i.getIdProduto() == id) {
				return true;
			}
		}
		return false;
	}

	public void setProduto(ListaAgregada prod) {
		this.produtos.add(prod);
	}

	public float getValorTotal() {

		return this.valorTotal;
	}

	public void setValorTotalALTERAR() {

		for(ListaAgregada list : produtos) {
			this.valorTotal = this.valorTotal + list.getPrecoVenda();
		}	
	}
	
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(long codVenda) {
		this.codVenda = codVenda;
	}

	public String toString() {
		String tmp = new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy, HH:mm").format(this.data);
		return tmp + ";" + "'" + this.codVenda + ";" +  this.valorTotal;
	}

	public void carrinhoEstoque() {
		Produto aux = new Produto();

		for(ListaAgregada p: produtos) {
			aux.setIdProduto(p.getIdProduto());
			aux.setNome(p.getNomeProduto());
			addEstoque(aux);
		}
		
	}

	public void addEstoque(Produto prod) {

		lancamento.setTipoLancamento(TipoLancamento.valueOf("SAIDA"));
		lancamento.setDataLancamento(this.data);
		lancamento.setDocumento("");
		lancamento.setIdproduto(prod.getIdProduto());
		lancamento.setNomeProduto(prod.getDescricao());
		lancamento.setCustoUnitario(0);
		lancamento.setPrecoVendaUnitario(prod.getPrecoVenda());
		lancamento.setQuantidade(-1);

		estoqueDao.add();
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

}
