package br.com.fiap.bean;

public class Product {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int quantity;
	private double price;
	
	public Product() {}
	
	public Product(String name, int quantity, double price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
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
