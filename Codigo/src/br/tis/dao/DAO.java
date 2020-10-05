package br.tis.dao;

import java.util.List;

public interface DAO<T,K> {

	public boolean add() ;
	public boolean update();
	public boolean remove(K chave);
	public List<T> getAll();
	
}

