package br.com.fiap.bean;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	private double price;
	private Date expirationDate;
	
	public Product() {}
	
	public Product(String name, int quantity, double price, Date expirationDate) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.expirationDate = expirationDate;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public void setName(String name) {
		this.name = name;
	}
}
