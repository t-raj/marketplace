package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.CustomerRepresentation;

@WebService
public interface CustomerEndpointInterface {
	
	/**
	 * Register customer
	 * @param customer
	 * @return CustomerRepresentation
	 */
	CustomerRepresentation register(CustomerRepresentation customer);
	
	/**
	 * Get customer
	 * @param login
	 * @return CustomerRepresentation
	 */
	CustomerRepresentation get(String login);
	
}
