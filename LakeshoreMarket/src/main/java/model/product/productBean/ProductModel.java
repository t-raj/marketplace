package main.java.model.product.productBean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ProductModel {

	private String description;
	private int price;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
