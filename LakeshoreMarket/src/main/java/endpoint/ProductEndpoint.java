package main.java.endpoint;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.java.model.product.productBean.ProductBean;
import main.java.model.product.productBean.ProductModel;
import main.java.model.service.service.ProductService;
import main.java.util.ElementUtil;

@Controller 
public class ProductEndpoint {
	
	
	@Autowired(required=true)
	private static ProductService productService; 
	
	/*
	@Path("{Product}")
	@GET
	@Produces("application/xml")
	
	*/
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
	


}
