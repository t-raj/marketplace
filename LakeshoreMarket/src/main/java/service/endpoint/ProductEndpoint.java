package main.java.service.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.model.constant.Constant;
import main.java.service.representation.Link;
import main.java.service.representation.ProductRepresentation;
import main.java.service.service.ProductService;
import main.java.service.serviceImpl.ProductServiceImpl;
import main.java.util.ElementUtil;

@Path("/products")
public class ProductEndpoint implements ProductEndpointInterface{
	
	private static ProductService productService = new ProductServiceImpl(); 
	
	@Path("/{productId}")//1.a search item database by product
	@GET
	@Produces("application/xml")
	public ProductRepresentation search(@PathParam("productId") int productId) {
		ProductRepresentation productRepresentation = new ProductRepresentation();
		try {
			productRepresentation = ElementUtil.buildProductModel(productService.get(productId));
			Link buy = new Link("buy", Constant.BASE_PATH + "/orders/", "buy", Constant.MEDIA_TYPE_XML);
			productRepresentation.setLinks(buy);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return productRepresentation;
	}

	@Path("/{partnerId}/{isActive}")//1.a search item database by product's partner
	@GET
	@Produces("application/xml")
	public List<ProductRepresentation> retrieve(@PathParam("partnerId") String partnerId, @PathParam("isActive")boolean isActive) {
		List<ProductRepresentation> productRepresentationList = new ArrayList<ProductRepresentation>();
		try {
			productRepresentationList = ElementUtil.buildProductModelList(productService.findByPartner(Integer.parseInt(partnerId)));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return productRepresentationList;
	}

	@POST//2.2 Add product or products in market place
	@Consumes({"application/xml"})
	public ProductRepresentation add(ProductRepresentation product){
		ProductRepresentation productRepresentation = new ProductRepresentation();
		try {
			productService.add(ElementUtil.buildProductBean(product));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		//set links 
		return productRepresentation;
		
	}
	
	@PUT//2.2 Update product or products in market place
	@Consumes({"application/xml"})
	public ProductRepresentation update(ProductRepresentation productModel){
		ProductRepresentation productRepresentation = new ProductRepresentation();
		try {
			productService.update(ElementUtil.buildProductBean(productModel));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		//set links 
		return productRepresentation;
	}

}
