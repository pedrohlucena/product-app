package br.com.fiap.store.tests;

import java.util.List;

import br.com.fiap.store.bean.Category;
import br.com.fiap.store.dao.CategoryDAO;
import br.com.fiap.store.factory.DAOFactory;

public class CategoryDAOTest {
	public static void main(String[] args) {
		CategoryDAO dao = DAOFactory.getCategoryDAO();
		
		List<Category> categoryList = dao.list();
		
		for(Category category: categoryList) {
			System.out.println(
					category.getCode() + "\n" +
					category.getName() + "\n"
			);
		}
	}
}
