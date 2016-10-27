package main.java.model.service.serviceImpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.DAO.CustomerDAO;
import main.java.DAO.daoImpl.CustomerDAOImpl;
import main.java.model.customer.customerBean.CustomerBean;
import main.java.model.service.service.CustomerService;
import main.java.util.ElementUtil;

@Path("/CustomerService")
//@Component
public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerBean customerBean;

//	@Autowired
	private CustomerDAO customerDAO = new CustomerDAOImpl();
	
	@Override
	public void add(CustomerBean customerBean) {
		System.out.println("POST METHOD from Customer with ID:.........." + customerBean.getId() +" and First Name: " 
	+ customerBean.getFirstName() + "and Last Name: " + customerBean.getLastName());
		customerDAO.add(ElementUtil.buildCustomer(customerBean));		
	}
	
	@Override
	public CustomerBean get(int customerId) {
		System.out.println("GET METHOD from customer with ID: ......" + customerId);
		return ElementUtil.buildCustomerBean(customerDAO.find(customerId));
	}

	@Override
	public void update(CustomerBean customerBean) {
		System.out.println("POST METHOD from Customer with ID:......" + customerBean.getId());
		customerDAO.update(ElementUtil.buildCustomer(customerBean));
	}


	@Override
	public void delete(@PathParam("id")long customerId) {
		customerDAO.delete(customerId);
	}
	
	@Override
	public List<CustomerBean> get() {
		System.out.println("POST METHOD for all customers.......");
		return ElementUtil.buildCustomerBeanList(customerDAO.find());
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		
		String toEmail = customerBean.getEmail();
		
		return toEmail;
	}

}
