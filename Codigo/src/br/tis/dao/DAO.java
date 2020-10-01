package br.tis.dao;

import java.util.List;

public interface DAO<T> {

	public boolean add() ;
	public boolean update();
	public boolean remove();
	public List<T> getAll();
	
}

