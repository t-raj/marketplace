package main.java.model.bean;

import java.util.ArrayList;
import java.util.List;

import main.java.model.entity.Partner;

public class OrderBean {
	
	private int id;
	private int partnerId;
	private int customerId;
	private Boolean paid;
	private String status;
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

	public List<Integer> getPartnerIds(List<Partner> partners){
		for(Partner partner: partners){
			partnerIds.add(partner.getId());
		}
		return partnerIds;
	}
	
	public void setPartnerIds(List<Integer> partnerIds){
		this.partnerIds = partnerIds;
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

}
