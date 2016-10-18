package main.java.model.service.serviceImpl;

import java.util.List;

import main.java.DAO.CustomerDAO;
import main.java.DAO.daoImpl.CustomerDAOImpl;
import main.java.model.customer.customerBean.CustomerBean;
import main.java.model.service.service.CustomerService;
import main.java.util.ElementUtil;

//@Component
public class CustomerServiceImpl implements CustomerService {

//	@Autowired
	private CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public void delete(long customerId) {
		customerDAO.delete(customerId);
	}

	public void add(CustomerBean customerBean) {
		customerDAO.add(ElementUtil.buildCustomer(customerBean));		
	}

	public void update(CustomerBean customerBean) {
		customerDAO.update(ElementUtil.buildCustomer(customerBean));
	}

	public List<CustomerBean> get() {
		return ElementUtil.buildCustomerBeanList(customerDAO.find());
	}

	public CustomerBean get(int customerId) {
		return ElementUtil.buildCustomerBean(customerDAO.find(customerId));
	}

}
