package br.tis.app;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.tis.dao.ClienteDAO;
import br.tis.dao.EstoqueDAO;
import br.tis.dao.OrdemVendaDAO;
import br.tis.entidades.Cliente;
import br.tis.entidades.Estoque;
import br.tis.entidades.ListaAgregada;
import br.tis.entidades.OrdemVenda;
import br.tis.entidades.TipoLancamento;


public class MainTeste {

	public static void main(String[] args) {

		OrdemVenda ordem = new OrdemVenda();
		Estoque lancamentos = new Estoque();
		List<Estoque> lancamentosSaida = new ArrayList<>();
		
	
		
		ordem.setData(Date.valueOf(LocalDate.now()));
		ordem.setCpfCnpjCliente("121172276");
		ordem.geraNumOrdemVenda();
		ordem.setObservacao("testando");
		ordem.setValorTotal(100);
		


			lancamentos.setTipoLancamento(TipoLancamento.SAIDA);
			lancamentos.setDataLancamento(ordem.getData());
			lancamentos.setDocumento(String.valueOf(ordem.getIdOrdemVenda()));
			lancamentos.setIdproduto(1000);
			lancamentos.setNomeProduto("Peliculas Note 8");
			lancamentos.setPrecoVendaUnitario(10);
			lancamentos.setQuantidade(10);
			
			lancamentosSaida.add(lancamentos);
			
			ordem.setLancamentosSaida(lancamentosSaida);
			
			
		OrdemVendaDAO OV = new OrdemVendaDAO(ordem);
		OV.add();
	
		EstoqueDAO estoque = new EstoqueDAO();
		
		System.out.println(estoque.addAll(ordem.getLancamentosSaida()));
		
	}//main
}//class
