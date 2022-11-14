package br.com.fiap.store.tests;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Product;
import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

public class ProductDAOTest {
	public static void main(String[] args) {
		ProductDAO dao = DAOFactory.getProductDAO();
		
		Product product = new Product("Caderno", 100, 20, Calendar.getInstance());
		
		try {
			dao.save(product);
			System.out.println("Produto cadastrado");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		product = dao.fetchById(1);
		product.setName("Caderno de capa dura");
		product.setPrice(30);
		try {
			dao.update(product);
			System.out.println("Produto atualizado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Product> list = dao.list();
		for(Product item: list) {
			System.out.println(
					item.getCode() + "\n" +
					item.getName() + "\n" +
					item.getQuantity() + "\n" +
					item.getPrice() + "\n"
			);
		}
		
		int id = 5;
		try {
			dao.remove(id);
			System.out.println("Produto de id " + id + " removido");
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
}
