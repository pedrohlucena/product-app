package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.store.bean.User;
import br.com.fiap.store.dao.UserDAO;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleUserDAO implements UserDAO {
	
	private Connection connection;

	@Override
	public boolean validateUser(User user) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			stmt = connection.prepareStatement("SELECT * FROM T_USUARIO");
			rs = stmt.executeQuery();
			
			System.out.println(rs.next());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
