package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.model.ProductModel;

@WebService
public interface ProductEndpointInterface {
		ProductModel search(int productId);
		Response addNewProduct(ProductModel product);
		Response addExistingProduct(int productID, int partnerID, int quantity);
}


