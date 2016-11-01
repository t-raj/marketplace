package main.java.service.service;

import java.util.List;

import main.java.model.bean.OrderBean;

public interface OrderService {

	//void submit(OrderBean orderBean);

	void addItem(OrderBean orderBean);
	
	OrderBean get(long orderId);

	void update(OrderBean orderBean);
	
	void cancel(long orderId);
	
	List<OrderBean> get();

	void sendOrder(int orderId, int partnerId);

}