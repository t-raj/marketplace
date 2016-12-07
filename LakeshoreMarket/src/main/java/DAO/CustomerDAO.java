package main.java.DAO;

import java.util.List;

import main.java.model.entity.Customer;


public interface CustomerDAO {
	
	/**
	 * Add customer to db
	 * @param customer
	 */
	void add(Customer customer);
	
	/**
	 * Delete customer 
	 * @param customerId
	 */
    void delete(int customerId);
    
    /**
     * Find customer
     * @param customerId
     * @return
     */
    Customer find(int customerId);
    
    /**
     * Update customer
     * @param customer
     */
    void update(Customer customer);
    
    /**
     * Find customers
     * @return
     */
    List<Customer> find();

    /**
     * Find by login
     * @param login
     * @return
     */
	Customer find(String login);
}
