package br.com.fiap.store.bean;

import java.io.Serializable;

import br.com.fiap.store.utils.Encryption;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	public User() { super(); }
	
	public User(String email, String password) {
		super();
		this.email = email;
		setPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		try {
			this.password = Encryption.encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}