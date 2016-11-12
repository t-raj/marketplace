package main.java.DAO;

import java.util.List;

import main.java.model.entity.Order;
import main.java.service.service.OrderService.Status;

public interface OrderDAO {

	void add(Order order);
	
	void delete(long orderId);
	
	void update(Order order);
	
	Order get(long orderId);

	List<Order> get(List<Status> status);

	List<Order> get(Status status);

	List<Order> get(Status status, long partnerId);

}
