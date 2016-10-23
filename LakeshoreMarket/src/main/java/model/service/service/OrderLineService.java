package main.java.model.service.service;

import java.util.List;

import main.java.model.order.orderBean.OrderLineBean;

public interface OrderLineService {
	

	void addItem(OrderLineBean orderLineBean);
	
	OrderLineBean get(long orderId);
	
	void update(OrderLineBean orderLineBean);

	void cancel(long orderId);	
	
	List<OrderLineBean> get();

	//List<OrderLineBean> get(int orderId);

}
