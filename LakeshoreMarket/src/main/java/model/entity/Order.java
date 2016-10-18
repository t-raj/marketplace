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
@Table(name="order")
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="partner_id")
	private int partnerId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="paid")
	private Boolean paid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="cost")
	private double cost;
	
	List<Partner> partners = new ArrayList<Partner>();
	List<Integer> partnerIds = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public List<Integer> getPartnerIds(List<Partner> partners){
		for(Partner partner: partners){
			partnerIds.add(partner.getId());
		}
		return partnerIds;
	}
	
	public void setPartnerIds(List<Integer> partnerIds){
		this.partnerIds = partnerIds;
	}
	
	
}