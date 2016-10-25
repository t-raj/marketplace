package main.java.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_line")
public class OrderLine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;

	@Id
	@Column(name="line_number")
	private int lineNumber;

	@Column(name="product_id")
	private int productId;

	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private double price;
	
	@Column(name="Status")
	private String status;
	
	List<Product> products = new ArrayList<Product>();
	List<Integer> productIds = null;

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
	
	public List<Integer> getProductIds(List<Product> products) {
		for(Product product : products){
			productIds.add(product.getId());
		}
		return productIds;
	}
	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
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

	public void setCost(double price) {
		this.price = price;
	}

	public void setStatus(String string) {
		this.status = string;	
	}
	
	public String getStatus(){
		return status;
	}
	
}
