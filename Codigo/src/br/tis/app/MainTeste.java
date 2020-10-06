package br.tis.app;



import java.sql.Date;
import java.time.LocalDate;

import br.tis.dao.EstoqueDAO;
import br.tis.entidades.Estoque;
import br.tis.entidades.ListaAgregada;
import br.tis.entidades.TipoLancamento;

public class MainTeste {

	public static void main(String[] args) {

	
		
		EstoqueDAO estoqueDao = new EstoqueDAO();
	
		
		for(ListaAgregada agreg : estoqueDao.getEstoqueAgregado())
		
			System.out.println(agreg.getNomeProduto() + " Preco " + agreg.getPrecoVenda());
	}

}
