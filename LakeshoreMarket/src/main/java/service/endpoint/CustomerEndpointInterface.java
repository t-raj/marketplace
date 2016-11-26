package main.java.service.endpoint;

import main.java.service.representation.CustomerRepresentation;

public interface CustomerEndpointInterface {
	
	CustomerRepresentation get(String login, String password);
	
}
