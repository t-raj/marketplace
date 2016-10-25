package main.java.model.service.serviceImpl;

import java.util.List;

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

//	@Autowired
	private CustomerDAO customerDAO = new CustomerDAOImpl();
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/CustomerBean")
	public void add(CustomerBean customerBean) {
		System.out.println("POST METHOD from Customer with ID:.........." + customerBean.getId() +" and First Name: " 
	+ customerBean.getFirstName() + "and Last Name: " + customerBean.getLastName());
		customerDAO.add(ElementUtil.buildCustomer(customerBean));		
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/CustomerBean/Id")
	public CustomerBean get(@PathParam("id")int customerId) {
		System.out.println("GET METHOD from customer with ID: ......" + customerId);
		return ElementUtil.buildCustomerBean(customerDAO.find(customerId));
	}

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/CustomerBean")
	public void update(CustomerBean customerBean) {
		System.out.println("POST METHOD from Customer with ID:......" + customerBean.getId());
		customerDAO.update(ElementUtil.buildCustomer(customerBean));
	}


	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/Customer/id")
	public void delete(@PathParam("id")long customerId) {
		customerDAO.delete(customerId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/CustomerBean")
	public List<CustomerBean> get() {
		System.out.println("POST METHOD for all customers.......");
		return ElementUtil.buildCustomerBeanList(customerDAO.find());
	}

}
