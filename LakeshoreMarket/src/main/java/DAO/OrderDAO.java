package main.java.DAO;

import java.util.List;

import main.java.model.entity.Order;

public interface OrderDAO {

	void add(Order order);
	
	void delete(long orderId);
	
	void update(Order order);
	
	Order get(long orderId);

	List<Order> get();

	//List<Order> get(int customerId);
	
	
	
	

	
}
