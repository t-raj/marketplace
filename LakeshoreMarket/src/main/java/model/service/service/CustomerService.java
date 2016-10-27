  package main.java.model.service.service;

import java.util.List;

import main.java.model.customer.customerBean.CustomerBean;

public interface CustomerService {

	void add(CustomerBean customerBean);
	
	CustomerBean get(int customerId);
	
	void delete(long customerId);

	void update(CustomerBean customerBean);
	
	List<CustomerBean> get();

	String getEmail();
}
