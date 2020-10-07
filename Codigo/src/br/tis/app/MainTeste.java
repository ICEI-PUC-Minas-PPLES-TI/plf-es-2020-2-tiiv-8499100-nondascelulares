package br.tis.app;





import java.util.List;

import br.tis.dao.EstoqueDAO;

import br.tis.entidades.ListaAgregada;
import br.tis.entidades.OrdemVenda;


public class MainTeste {

	public static void main(String[] args) {

		
	OrdemVenda ov = new OrdemVenda();
	
	System.out.println(ov.getData());
	
	for(ListaAgregada list : ov.getEstoque()) {
		System.out.println(list.toString());
	}
	
	ListaAgregada li1 = ov.getEstoque().get(0);
	ListaAgregada li2 = ov.getEstoque().get(1);
	
	ov.setProduto(li1);
	ov.setProduto(li2);

	for(ListaAgregada list : ov.getProdutos()) {
		System.out.println(list.toString());
	}
	
	ov.setValorTotal();
	System.out.println(ov.getValorTotal());
	


	}//main
}//class
