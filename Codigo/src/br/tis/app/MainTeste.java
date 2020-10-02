package br.tis.app;



import java.sql.Date;
import java.time.LocalDate;

import br.tis.dao.EstoqueDAO;
import br.tis.entidades.Estoque;
import br.tis.entidades.TipoLancamento;

public class MainTeste {

	public static void main(String[] args) {

		Estoque lancamento = new Estoque();
		
		lancamento.setTipoLancamento(TipoLancamento.valueOf("ENTRADA"));
		lancamento.setDataLancamento(Date.valueOf(LocalDate.now()));
		lancamento.setDocumento("546546");
		lancamento.setIdproduto(200L);
		lancamento.setNomeProduto("Peliculas Iphone");
		lancamento.setCustoUnitario(50);
		lancamento.setPrecoVendaUnitario(15);
		lancamento.setQuantidade(5);
		
		EstoqueDAO estoqueDao = new EstoqueDAO(lancamento);
		
		estoqueDao.add();
		
	//	estoqueDao = new EstoqueDAO();
		
		System.out.println("Custo Médio: " + estoqueDao.getCustoMedio(200L));
		
		System.out.println("Preco venda Médio: " + estoqueDao.getPrecoVendaMedio(100L));
	
		estoqueDao.CloseConnetion();
		
	}

}
