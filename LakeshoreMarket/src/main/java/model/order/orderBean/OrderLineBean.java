package main.java.model.order.orderBean;

import java.util.ArrayList;
import java.util.List;

import main.java.model.entity.Product;

public class OrderLineBean {
	
	
	private int id;
	private int lineNumber;
	private int productId;
	private int quantity;
	private double price;
	List<Integer> productIds = new ArrayList<Integer>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Integer> getProductIds(List<Product> products) {
		for(Product product : products){
			productIds.add(product.getId());
		}
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}
	
	
	

}
