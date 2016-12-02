package main.java.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation extends AbstractRepresentation {
	
	private int productId;
	private String description;
	private int price;
	private int partnerId;
	private int numberAvailable;
	
	public int getproductId() {
		return productId;
	}
	public void setproductId(int productId) {
		this.productId = productId;
	}	
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
	public int getNumberAvailable() {
		return numberAvailable;
	}
	public void setNumberAvailable(int numberAvailable) {
		this.numberAvailable = numberAvailable;
	}
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	
}
