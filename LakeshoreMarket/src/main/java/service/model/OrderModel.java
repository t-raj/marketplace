package main.java.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import main.java.service.service.OrderService.Status;


@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderModel {

	private int orderId;
	private long customerId;
	private Status status;
	private List<Long> productIds;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
