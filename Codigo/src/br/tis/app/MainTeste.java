package br.tis.app;



import java.sql.Date;
import java.time.LocalDate;

import br.tis.dao.EstoqueDAO;
import br.tis.entidades.Estoque;
import br.tis.entidades.TipoLancamento;

public class MainTeste {

	public static void main(String[] args) {

		Estoque lancamento = new Estoque();
		
		lancamento.setTipoLancamento(TipoLancamento.valueOf("SAIDA"));
		lancamento.setDataLancamento(Date.valueOf(LocalDate.now()));
		lancamento.setDocumento("ddffd");
		lancamento.setIdproduto(100L);
		lancamento.setNomeProduto("Peliculas Note 8");
		lancamento.setCustoUnitario(0);
		lancamento.setPrecoVendaUnitario(15);
		lancamento.setQuantidade(5);
		
		EstoqueDAO estoqueDao = new EstoqueDAO(lancamento);
		
		estoqueDao.add();
		
		estoqueDao = new EstoqueDAO();
		
		System.out.println(estoqueDao.getPrecoVendaMedio(100L));
	
		
	}

}
