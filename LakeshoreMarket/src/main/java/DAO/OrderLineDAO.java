package main.java.DAO;

import java.util.List;

import main.java.model.entity.OrderLine;

public interface OrderLineDAO {
	
	void add(OrderLine orderLine);
	
	void delete(long orderId);
	
	void update(OrderLine orderLine);
	
	OrderLine get(long orderId);

	List<OrderLine> get();

}
