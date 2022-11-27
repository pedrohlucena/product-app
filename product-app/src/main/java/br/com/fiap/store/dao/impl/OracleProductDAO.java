package br.com.fiap.store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Category;
import br.com.fiap.store.bean.Product;
import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleProductDAO implements ProductDAO {
	
	private Connection connection;
	
	@Override
	public void save(Product product) throws DBException {
		PreparedStatement stmt = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_PRODUTO (cd_produto, nm_produto, qt_produto, vl_produto, dt_fabricacao, cd_categoria) VALUES (SQ_T_PRODUTO.nextval, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setDouble(3, product.getPrice());
			java.sql.Date manufacturingDate = new java.sql.Date(product.getManufacturingDate().getTimeInMillis());
			stmt.setDate(4, manufacturingDate);
			stmt.setInt(5, product.getCategory().getCode());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Product product) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PRODUTO SET nm_produto = ?, qt_produto = ?, vl_produto = ?, dt_fabricacao = ?, cd_categoria = ?  WHERE cd_produto = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setDouble(3, product.getPrice());
			java.sql.Date manufacturingDate = new java.sql.Date(product.getManufacturingDate().getTimeInMillis());
			stmt.setDate(4, manufacturingDate);
			stmt.setInt(5, product.getCategory().getCode());
			stmt.setInt(6, product.getCode());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void remove(int code) throws DBException {
			PreparedStatement stmt = null;
			try {
				connection = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PRODUTO WHERE cd_produto = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, code);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	
	@Override
	public Product fetchById(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_PRODUTO P INNER JOIN T_CATEGORIA C " +
						 "ON (P.cd_categoria = C.cd_categoria)" +
						 "WHERE P.cd_produto = ?";
						 
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int code = rs.getInt("cd_produto");
				String name = rs.getString("nm_produto");
				int quantity = rs.getInt("qt_produto");
				double price = rs.getDouble("vl_produto");
				java.sql.Date manufacturingDateResponse = rs.getDate("dt_fabricacao");
				Calendar manufacturingDate = Calendar.getInstance();
				manufacturingDate.setTimeInMillis(manufacturingDateResponse.getTime());
				
				int categoryCode = rs.getInt("cd_categoria");
				String categoryName = rs.getString("nm_categoria");
			
				Category productCategory = new Category(categoryCode, categoryName);
				
				product = new Product(code, name, quantity, price, manufacturingDate);
				product.setCategory(productCategory);
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
		return product;
	}
	
	@Override
	public List<Product> list() {
		List<Product> list = new ArrayList<Product>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_PRODUTO P INNER JOIN T_CATEGORIA C " +
					     "ON (P.cd_categoria = C.cd_categoria)";
			
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int code = rs.getInt("cd_produto");
				String name = rs.getString("nm_produto");
				int quantity = rs.getInt("qt_produto");
				double price = rs.getDouble("vl_produto");
				java.sql.Date manufacturingDateResponse = rs.getDate("dt_fabricacao");
				Calendar manufacturingDate = Calendar.getInstance();
				manufacturingDate.setTimeInMillis(manufacturingDateResponse.getTime());
				
				int categoryCode = rs.getInt("cd_categoria");
				String categoryName = rs.getString("nm_categoria");
			
				Category productCategory = new Category(categoryCode, categoryName);
				
				Product product = new Product(code, name, quantity, price, manufacturingDate);
				
				product.setCategory(productCategory);
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
