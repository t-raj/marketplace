package main.java.service.service;

import java.util.List;

import main.java.model.bean.OrderLineBean;

public interface OrderLineService {
	
	/**
	 * Add order line
	 * @param orderLineBean
	 */
	void addItem(OrderLineBean orderLineBean);
	
	/**
	 * Get order line
	 * @param orderId
	 * @return
	 */
	OrderLineBean get(int orderId);
	
	/**
	 * Update order line
	 * @param orderLineBean
	 */
	void update(OrderLineBean orderLineBean);

	/**
	 * cancel
	 * @param orderId
	 */
	void cancel(int orderId);	
	
	/**
	 * Get order lines
	 * @return
	 */
	List<OrderLineBean> get();

}
