package br.com.fiap.store.dao;

import br.com.fiap.store.bean.User;

public interface UserDAO {
	boolean validateUser(User user);
}
