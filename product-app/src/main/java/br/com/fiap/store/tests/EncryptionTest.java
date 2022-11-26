package br.com.fiap.store.tests;

import br.com.fiap.store.utils.Encryption;

public class EncryptionTest {

	public static void main(String[] args) {
		try {
			String password = "pedro";
			String hashedPassword = Encryption.encrypt(password);
			System.out.println(password + " " + hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
