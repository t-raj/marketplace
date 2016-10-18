package main.java.model.service.service;

import java.util.List;

import main.java.model.order.orderBean.OrderLineBean;

public interface OrderLineService {
	
	
	void cancel(long orderId);

	OrderLineBean get(long orderId);

	List<OrderLineBean> get();

	void update(OrderLineBean orderLineBean);

	void addItem(OrderLineBean orderLineBean);

	List<OrderLineBean> get(int orderId);

}
