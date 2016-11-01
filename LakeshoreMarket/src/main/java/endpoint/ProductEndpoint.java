package main.java.endpoint;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.java.model.product.productBean.ProductBean;
import main.java.model.product.productBean.ProductModel;
import main.java.model.service.service.ProductService;
import main.java.model.service.serviceImpl.ProductServiceImpl;
import main.java.util.ElementUtil;

public class ProductEndpoint {
	
	private static ProductService productService = new ProductServiceImpl(); 
	
	@Path("{Product}")//1.a search item database by product
	@GET
	@Produces("application/xml")
	public Response search(int productId) {
		ProductModel productRepresentation = new ProductModel();
		
		productRepresentation = ElementUtil.buildProductModel(productService.get(productId));
		
		if (productRepresentation == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		String product = productRepresentation.toString();
		return Response.ok(product, MediaType.APPLICATION_JSON).build();

	}
	
	@PUT//2.2 Add product or products in market place
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Product")
	public Response addProduct(ProductModel productModel){
		if (productModel != null) {
			productService.addNewProduct(productModel.getpId(), productModel.getDescription(), productModel.getPartnerId(), productModel.getNumberAvailable(), productModel.getPrice());
		}

		String message = "Product with id " + productModel.getpId() + " was added.";
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
	
	

}
