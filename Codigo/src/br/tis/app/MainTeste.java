package br.tis.app;





import br.tis.dao.EstoqueDAO;

import br.tis.entidades.ListaAgregada;


public class MainTeste {

	public static void main(String[] args) {

	
		
		EstoqueDAO estoqueDao = new EstoqueDAO();
	
		
		for(ListaAgregada agreg : estoqueDao.getEstoqueAgregado())
		
			System.out.println(agreg.getNomeProduto() + " Preco " + agreg.getPrecoVenda());
	}

}
