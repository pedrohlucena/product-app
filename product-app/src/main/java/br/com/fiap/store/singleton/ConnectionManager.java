package br.com.fiap.store.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.fiap.store.utils.DotEnvManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionManager {
	private static ConnectionManager instance;
	
	private String url;
	private String user;
	private String password;
	
	private ConnectionManager() {
		Dotenv dotenv = DotEnvManager.getEnvVars();
		
		this.url = dotenv.get("DATABASE_URL");
		this.user = dotenv.get("DATABASE_USER");
		this.password = dotenv.get("DATABASE_PASSWORD");
	};

	public static ConnectionManager getInstance() {
		if (instance == null)
			instance = new ConnectionManager();
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(
					url, 
					user,
					password
			);

			return connection;
		} catch (SQLException e) {
			System.err.println("Não foi possível conectar no Banco de Dados");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado!");
			e.printStackTrace();
		}
		return connection;
	}
}
