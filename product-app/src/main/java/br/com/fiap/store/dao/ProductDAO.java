package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Product;
import br.com.fiap.store.exception.DBException;

public interface ProductDAO {
	void save(Product product) throws DBException;
	void update(Product product) throws DBException;
	void remove(int code) throws DBException;
	Product save(int id);
	List<Product> list();
}