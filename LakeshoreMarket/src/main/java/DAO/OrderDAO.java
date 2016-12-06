package main.java.DAO;

import java.util.List;

import main.java.model.entity.Order;
import main.java.service.service.OrderService.Status;

public interface OrderDAO {

	int add(Order order);
	
	void delete(int orderId);
	
	void update(Order order);
	
	Order get(int orderId);

	List<Order> get(List<Status> status);

	List<Order> get(Status status);

	List<Order> get(Status status, int partnerId);

	List<Order> getByCustomer(int customerId);

}
