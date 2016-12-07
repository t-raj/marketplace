  package main.java.service.service;

import java.util.List;

import main.java.model.bean.CustomerBean;

public interface CustomerService {

	/**
	 * Add customer
	 * @param customerBean
	 */
	void add(CustomerBean customerBean);
	
	/**
	 * Get customer
	 * @param customerId
	 * @return
	 */
	CustomerBean get(int customerId);

	/**
	 * Get customer by login
	 * @param login
	 * @return
	 */
	CustomerBean get(String login);

	/**
	 * Delete customer
	 * @param customerId
	 */
	void delete(int customerId);

	/**
	 * Update customer
	 * @param customerBean
	 */
	void update(CustomerBean customerBean);
	
	/**
	 * List customers
	 * @return
	 */
	List<CustomerBean> get();

	String getEmail();
}
