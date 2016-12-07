package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.ProductRepresentation;

@WebService
public interface ProductEndpointInterface {

	/**
	 * Search product 
	 * @param productId
	 * @return ProductRepresentation
	 */
	ProductRepresentation search(int productId);

	/**
	 * Add product
	 * @param product
	 * @return ProductRepresentation
	 */
	ProductRepresentation add(ProductRepresentation product);

	/**
	 * Update product
	 * @param product
	 * @return ProductRepresentation
	 */
	ProductRepresentation update(ProductRepresentation product);
}


