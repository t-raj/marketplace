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
	public ProductModel search(int productId) {
		ProductModel productRepresentation = new ProductModel();
		
		try {
			productRepresentation = ElementUtil.buildProductModel(productService.get(productId));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return productRepresentation;
	}
	
	@PUT//2.2 Add product or products in market place
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Product")
	public String addProduct(ProductModel productModel){
		if (productModel != null) {
			productService.addNewProduct(productModel.getpId(), productModel.getDescription(), productModel.getPartnerId(), productModel.getNumberAvailable(), productModel.getPrice());
		}

		return null;
		
	}
	
	

}
