package main.java.service.endpoint;

/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;


import main.java.model.bean.ProductBean;
import main.java.service.model.ProductModel;
import main.java.service.service.ProductService;
import main.java.service.serviceImpl.ProductServiceImpl;
import main.java.util.ElementUtil;

public class ProductEndpoint {
	
	private static ProductService productService; 
	
	@Path("{Product}")//1.a search item database by product
	@GET
	@Produces("application/xml")
	
	public ProductModel search(int productId) {
		ProductModel productRepresentation = new ProductModel();
		
		try {
			productRepresentation = ElementUtil.buildProductModel(productService.get(productId));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productRepresentation;
	}
	
	@POST//2.2 Add product or products in market place
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/Product")

	public Response addNewProduct(int productID, String description, int partnerID, int numAvailable,int price){
		String message;
		try {
			productService.addNewProduct(productID, description, partnerID, numAvailable, price);
			message = "product successfully added";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error.Product could not be added.";
		}
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT//2.2 Add product or products in market place
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/Product")
	public Response addExistingProduct(int productID, int partnerID, int quantity){
		String message;
		ProductModel productRepresentation = search(productID);
		try {
			productRepresentation = ElementUtil.buildProductModel(productService.addExistingProduct(productID, partnerID, quantity));
			message = "product successfully added";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error.Product could not be added.";
		}
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	} 

}
