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
		lancamento.setDocumento("84564");
		lancamento.setIdproduto(100L);
		lancamento.setNomeProduto("Peliculas Note 8");
		lancamento.setCustoUnitario(0);
		lancamento.setPrecoVendaUnitario(25);
		lancamento.setQuantidade(50);
		
		EstoqueDAO estoqueDao = new EstoqueDAO(lancamento);
		
		estoqueDao.addLancamento();
		
		estoqueDao = new EstoqueDAO();
		
		System.out.println(estoqueDao.getQuantidadeDisponivel(100L));
	
		
	}

}
