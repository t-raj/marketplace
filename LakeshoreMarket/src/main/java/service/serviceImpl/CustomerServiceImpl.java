package main.java.service.serviceImpl;

import java.util.List;

import main.java.DAO.CustomerDAO;
import main.java.DAO.daoImpl.CustomerDAOImpl;
import main.java.model.bean.CustomerBean;
import main.java.service.service.CustomerService;
import main.java.util.ElementUtil;
/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerBean customerBean;

	private CustomerDAO customerDAO = new CustomerDAOImpl();
	
	@Override
	public void add(CustomerBean customerBean) {
		customerDAO.add(ElementUtil.buildCustomer(customerBean));		
	}
	
	@Override
	public CustomerBean get(int customerId) {
		return ElementUtil.buildCustomerBean(customerDAO.find(customerId));
	}

	@Override
	public void update(CustomerBean customerBean) {
		customerDAO.update(ElementUtil.buildCustomer(customerBean));
	}

	@Override
	public void delete(int customerId) {
		customerDAO.delete(customerId);
	}
	
	@Override
	public List<CustomerBean> get() {
		return ElementUtil.buildCustomerBeanList(customerDAO.find());
	}

	@Override
	public String getEmail() {
		String toEmail = customerBean.getEmail();
		
		return toEmail;
	}

	@Override
	public CustomerBean get(String login) {
		return ElementUtil.buildCustomerBean(customerDAO.find(login));
	}

}
