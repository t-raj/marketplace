package main.java.service.endpoint;

import main.java.service.representation.CustomerRepresentation;

public interface CustomerEndpointInterface {
	
	void register(CustomerRepresentation customer);
	CustomerRepresentation get(String login);
	
}
