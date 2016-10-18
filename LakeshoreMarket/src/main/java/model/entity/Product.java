package main.java.model.entity;

import java.io.Serializable;
//import java.math.int;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;

	@Column(name="description")
	private String description;
	
	@Column(name="partnerID")
	private int partnerID;
	
	@Column(name="numAvailable")
	private int numAvailable;
	
	@Column(name="price")
	private int price;

	@Column(name="active")
	private boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPartnerID() {
		return partnerID;
	}
	
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	
	public int getNumAvailable() {
		return numAvailable;
	}
	
	public void setNumAvailable(int numAvailable) {
		this.numAvailable = numAvailable;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
