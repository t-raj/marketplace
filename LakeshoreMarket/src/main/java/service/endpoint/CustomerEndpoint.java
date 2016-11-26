package main.java.service.endpoint;

import main.java.service.representation.CustomerRepresentation;
import main.java.service.service.CustomerService;
import main.java.service.serviceImpl.CustomerServiceImpl;
import main.java.util.ElementUtil;

public class CustomerEndpoint implements CustomerEndpointInterface {

	private CustomerService customerService = new CustomerServiceImpl();
	
	@Override
	public CustomerRepresentation get(String login, String password) {
		return ElementUtil.buildCustomerModel(customerService.get(login));
	}

}
