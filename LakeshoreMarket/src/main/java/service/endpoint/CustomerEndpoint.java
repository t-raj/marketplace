package main.java.service.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.model.constant.Constant;
import main.java.service.representation.CustomerRepresentation;
import main.java.service.representation.Link;
import main.java.service.service.CustomerService;
import main.java.service.serviceImpl.CustomerServiceImpl;
import main.java.util.ElementUtil;

@Path("/customers")
public class CustomerEndpoint implements CustomerEndpointInterface {

	private CustomerService customerService = new CustomerServiceImpl();
	
	/**
	 * This method gets a customer based on the login given
	 * @param login
	 * @return CustomerRepresentation
	 */
	@GET
	@Produces({"application/xml"})
	@Path("/{login}")
	@Override
	public CustomerRepresentation get(@PathParam("login") String login) {
		return setLinks(ElementUtil.buildCustomerModel(customerService.get(login)));
	}

	/**
	 * This method registers a customer
	 * @param customer representation
	 * 
	 */
	@POST//Need to register and create customer
	@Consumes({"application/xml"})
	@Produces({"application/xml"})
	@Override
	public CustomerRepresentation register(CustomerRepresentation customer) {
		customerService.add(ElementUtil.buildCustomerBean(customer));
		return setLinks(customer);
	}

	private CustomerRepresentation setLinks(CustomerRepresentation customererRep) {
		if (customererRep != null) {
			Link searchProduct = new Link("searchProduct", Constant.BASE_PATH + "/products/", Constant.BASE_PATH_CONSUMER + "/searchProducts", Constant.MEDIA_TYPE_XML );
			customererRep.setLinks(searchProduct);
		}
		
		return customererRep;
	}

}
