package main.java.model.service.service;

import java.util.List;

import main.java.model.order.orderBean.OrderBean;

public interface OrderService {

	void submit(OrderBean orderBean);

	void cancel(long orderId);

	OrderBean get(long orderId);

	List<OrderBean> get();

	void update(OrderBean orderBean);

	void addItem(OrderBean orderBean);

	OrderBean get(int orderId);

}
