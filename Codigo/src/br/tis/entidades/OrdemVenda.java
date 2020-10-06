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
	public List<Produto> produtos;
	private float valorTotal;
	private Estoque lancamento = new Estoque();
	private EstoqueDAO estoqueDao = new EstoqueDAO(lancamento);

	public OrdemVenda() {
		data = new Date(0, 0, 0);
		codVenda = 0;
		produtos = new ArrayList<Produto>();
		valorTotal = 0;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}
	
	public List<Produto> getEstoque(){
		List<Produto> estoque = null;
		
		//chamar metodo do estoque
		
		return estoque;
	}

	public boolean produtoExiste(long id) {

		for(Produto i : this.produtos) {
			if(i.getIdProduto() == id) {
				return true;
			}
		}
		return false;
	}

	public void setProduto(Produto prod) {
		this.produtos.add(prod);
	}

	public float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal() {
		float temp=0;

		this.valorTotal = temp;
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
		
		for(Produto p: produtos) {
			addEstoque(p);
		}
		
	}

	public void addEstoque(Produto prod) {
		
		lancamento.setTipoLancamento(TipoLancamento.valueOf("SAIDA"));
		lancamento.setDataLancamento(data.valueOf(LocalDate.now()));//mexer na data
		lancamento.setDocumento("546546");
		lancamento.setIdproduto(prod.getIdProduto());
		lancamento.setNomeProduto(prod.getDescricao());
		lancamento.setCustoUnitario(50);
		lancamento.setPrecoVendaUnitario(15);
		lancamento.setQuantidade(5);

		estoqueDao.add();
	}

}
