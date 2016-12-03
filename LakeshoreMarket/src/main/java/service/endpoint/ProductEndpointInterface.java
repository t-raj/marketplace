package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.ProductRepresentation;

@WebService
public interface ProductEndpointInterface {
		ProductRepresentation search(int productId);
		ProductRepresentation add(ProductRepresentation product);
		ProductRepresentation update(ProductRepresentation product);
}


