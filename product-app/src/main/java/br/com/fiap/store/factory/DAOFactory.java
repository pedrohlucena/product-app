package br.com.fiap.store.factory;

import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.dao.impl.OracleProductDAO;

public class DAOFactory {
	public static ProductDAO getProdutoDAO() {
		return new OracleProductDAO();
	}	
}