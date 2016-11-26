package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.representation.ProductRepresentation;

@WebService
public interface ProductEndpointInterface {
		ProductRepresentation search(int productId);
		Response add(ProductRepresentation product);
		Response update(ProductRepresentation product);
}


