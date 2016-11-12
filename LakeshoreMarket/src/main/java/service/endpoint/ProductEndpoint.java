package main.java.service.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.service.model.ProductModel;
import main.java.service.service.ProductService;
import main.java.util.ElementUtil;

@Path("/product")
public class ProductEndpoint implements ProductEndpointInterface{
	
	private static ProductService productService; 
	
	@Path("/{productID}")//1.a search item database by product
	@GET
	@Produces("application/xml")
	
	public ProductModel search(@PathParam("productId") int productId) {
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
	@Path("/add/{productID}")

	public Response addNewProduct(ProductModel product){
		String message;
		try {
			productService.addNewProduct(ElementUtil.buildProductBean(product));
			message = "product successfully added";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error.Product could not be added.";
		}
		return Response.ok(message, MediaType.APPLICATION_XML).build();
	}
	
	@PUT//2.2 Add product or products in market place
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/add/{productID}")
	public Response addExistingProduct(@PathParam("productId") int productID, @PathParam("partnerId") int partnerID, @PathParam("quantity") int quantity){
		String message;
		ProductModel productRepresentation = search(productID);
		try {
			productRepresentation = ElementUtil.buildProductModel(productService.addExistingProduct(productID, partnerID, quantity));
			message = "product successfully added";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error.Product could not be added.";
		}
		return Response.ok(message, MediaType.APPLICATION_XML).build();
	} 

}
