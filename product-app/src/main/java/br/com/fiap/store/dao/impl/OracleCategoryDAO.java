package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Category;
import br.com.fiap.store.dao.CategoryDAO;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleCategoryDAO implements CategoryDAO {
	@Override
	public List<Category> list() {
		List<Category> list = new ArrayList<Category>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = ConnectionManager.getInstance().getConnection();
		try {
			stmt = connection.prepareStatement("SELECT * FROM T_CATEGORIA");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int code = rs.getInt("cd_categoria");
				String name = rs.getString("nm_categoria");
				Category category = new Category(code,name);
				list.add(category);
			}
		} catch (SQLException e) {
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
		return list;
	}
}