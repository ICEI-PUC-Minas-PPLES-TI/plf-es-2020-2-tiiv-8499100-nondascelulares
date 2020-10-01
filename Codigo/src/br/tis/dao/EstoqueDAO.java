package br.tis.dao;

import java.util.List;

import br.tis.entidades.Estoque;

public class EstoqueDAO implements DAO<Estoque>{

	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Estoque> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getPrecoMedio(long idProduto) {
		double precoMedio = 0; 
		
		return precoMedio;
	}
	
	public double RecalculaPrecoMedio(long idProduto) {
		double precoMedio = 0; 
		
		return precoMedio;
	}
	
	public boolean baixaEstoque(long idProduto, int quantidade) {
		return false;
	}
	
	
}
