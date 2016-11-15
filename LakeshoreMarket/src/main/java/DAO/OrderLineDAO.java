package main.java.DAO;

import java.util.List;

import main.java.model.entity.OrderLine;

public interface OrderLineDAO {
	
	void add(List<OrderLine> orderLine);

	void delete(int orderId);
	
	void update(OrderLine orderLine);
	
	OrderLine get(int orderId);

	List<OrderLine> get();

}
