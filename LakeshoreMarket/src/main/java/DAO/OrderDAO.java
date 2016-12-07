package main.java.DAO;

import java.util.List;

import main.java.model.entity.Order;
import main.java.service.service.OrderService.Status;

public interface OrderDAO {

	/**
	 * Add order to db
	 * @param order
	 * @return orderNumber
	 */
	int add(Order order);
	
	/**
	 * Delete order
	 * @param orderId
	 */
	void delete(int orderId);
	
	/**
	 * Get order
	 * @param orderId
	 * @return
	 */
	Order get(int orderId);

	/**
	 * Get order by statuses
	 * @param status list
	 * @return Orders
	 */
	List<Order> get(List<Status> status);

	/**
	 * Get order by status
	 * @param status
	 * @return Orders
	 */
	List<Order> get(Status status);

	/**
	 * Get order by status and partner
	 * @param status
	 * @param partnerId
	 * @return Orders
	 */
	List<Order> get(Status status, int partnerId);

	/**
	 * Get order by partner
	 * @param customerId
	 * @return Orders
	 */
	List<Order> getByCustomer(int customerId);

	/**
	 * Update order
	 * @param orderId
	 * @param status
	 */
	void update(int orderId, Status status);

}
