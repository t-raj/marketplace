package main.java.service.service;

import java.util.List;

import main.java.model.bean.OrderLineBean;

public interface OrderLineService {
	

	void addItem(OrderLineBean orderLineBean);
	
	OrderLineBean get(int orderId);
	
	void update(OrderLineBean orderLineBean);

	void cancel(int orderId);	
	
	List<OrderLineBean> get();

}
