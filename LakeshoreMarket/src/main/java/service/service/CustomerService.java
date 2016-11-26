  package main.java.service.service;

import java.util.List;

import main.java.model.bean.CustomerBean;

public interface CustomerService {

	void add(CustomerBean customerBean);
	
	CustomerBean get(int customerId);

	CustomerBean get(String login);

	void delete(int customerId);

	void update(CustomerBean customerBean);
	
	List<CustomerBean> get();

	String getEmail();
}
