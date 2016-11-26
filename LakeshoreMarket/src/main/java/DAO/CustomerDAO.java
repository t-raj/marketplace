package main.java.DAO;

import java.util.List;

import main.java.model.entity.Customer;


public interface CustomerDAO {
	
	void add(Customer customer);
	
    void delete(int customerId);
    
    Customer find(int customerId);
    
    void update(Customer customer);
    
    List<Customer> find();

	Customer find(String login);
}
