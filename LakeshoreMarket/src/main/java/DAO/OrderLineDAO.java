package main.java.DAO;

import java.util.List;

import main.java.model.entity.OrderLine;

public interface OrderLineDAO {
	
	/**
	 * Add order line
	 * @param orderLine
	 */
	void add(List<OrderLine> orderLine);

	/**
	 * Delete order line
	 * @param orderId
	 */
	void delete(int orderId);
	
	/**
	 * Update order line
	 * @param orderLine
	 */
	void update(OrderLine orderLine);
	
	/**
	 * Get order id
	 * @param orderId
	 * @return OrderLine
	 */
	OrderLine get(int orderId);

	/**
	 * Get orders
	 * @return OrderLines
	 */
	List<OrderLine> get();

}
