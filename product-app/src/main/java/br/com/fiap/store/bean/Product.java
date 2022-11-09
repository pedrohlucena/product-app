package br.com.fiap.store.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String name;
	private int quantity;
	private double price;
	private Calendar manufacturingDate;
	private Calendar expirationDate;
	
	public Product() {}
	
	public Product(String name, int quantity, double price, Calendar manufacturingDate, Calendar expirationDate) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
		this.expirationDate = expirationDate;
	}
	
	public Product(int code, String name, int quantity, double price, Calendar manufacturingDate, Calendar expirationDate) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
		this.expirationDate = expirationDate;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Calendar getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Calendar manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
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
