package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Category;

public interface CategoryDAO {
	List<Category> list();
}