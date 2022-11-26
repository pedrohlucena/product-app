package br.com.fiap.store.tests;

import br.com.fiap.store.bean.User;
import br.com.fiap.store.dao.UserDAO;
import br.com.fiap.store.factory.DAOFactory;

public class UserDAOTest {
	
	public static void main(String[] args) {
		UserDAO userDao = DAOFactory.getUserDAO();
		
		String email = "pedrohlucena413@gmail.com";
		String password = "123456";
		User user = new User(email, password);
		
		System.out.println(userDao.validateUser(user));
	}
}
