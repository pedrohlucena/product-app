package br.com.fiap.store.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvManager {
	public static Dotenv getEnvVars() {
		Dotenv dotenv = Dotenv.configure()
			    .directory(System.getProperty("user.dir") + "/git/product-app/product-app")
			    .load();
		
		return dotenv;
	}
}
