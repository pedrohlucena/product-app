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
	
	public Product() {}
	
	public Product(String name, int quantity, double price, Calendar manufacturingDate) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
	}
	
	public Product(int code, String name, int quantity, double price, Calendar manufacturingDate) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
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
	public void setPrice(double price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
}
