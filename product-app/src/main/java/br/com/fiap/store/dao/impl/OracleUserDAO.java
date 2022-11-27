package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.store.bean.User;
import br.com.fiap.store.dao.UserDAO;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleUserDAO implements UserDAO {
	@Override
	public boolean validateUser(User user) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = ConnectionManager.getInstance().getConnection();
		try {
			stmt = connection.prepareStatement("SELECT ds_email, ds_senha FROM T_USUARIO WHERE ds_email = ? AND ds_senha = ?");
			
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			
			rs = stmt.executeQuery();
			
			if(rs.next())
				return true;
			
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
