package br.com.fiap.store.factory;

import br.com.fiap.store.dao.CategoryDAO;
import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.dao.UserDAO;
import br.com.fiap.store.dao.impl.OracleCategoryDAO;
import br.com.fiap.store.dao.impl.OracleProductDAO;
import br.com.fiap.store.dao.impl.OracleUserDAO;

public class DAOFactory {
	public static ProductDAO getProductDAO() {
		return new OracleProductDAO();
	}
	public static CategoryDAO getCategoryDAO() {
		return new OracleCategoryDAO();
	}
	public static UserDAO getUserDAO() {
		return new OracleUserDAO();
	}
}