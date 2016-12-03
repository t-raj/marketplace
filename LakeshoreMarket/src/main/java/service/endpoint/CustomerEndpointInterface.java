package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.CustomerRepresentation;

@WebService
public interface CustomerEndpointInterface {
	
	CustomerRepresentation register(CustomerRepresentation customer);
	CustomerRepresentation get(String login);
	
}
