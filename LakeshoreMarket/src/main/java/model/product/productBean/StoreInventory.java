package main.java.model.product.productBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.java.model.service.service.ProductService;

@Controller 
public class StoreInventory {
	
	
	@Autowired(required=true)
	private static ProductService productService; 

//	@RequestMapping(value = "/")
	public void listProducts() {
		int productId = 0;
		List<ProductBean> products = (List<ProductBean>) productService.get(productId );
		int i = 0;
		for (ProductBean product : products) {
			System.out.println("product ID: " + product.getId() + " description: " + product.getDescription() + " price: " + product.getPrice());
			i++;
		}

	}
	public void search(int productID) {
		ProductBean product = productService.get(productID);
		if ((product.equals(null))||(!product.isActive()))
			System.out.println("We do not currently carry any products matching this ID.");
		else
			System.out.println("description: " + product.getDescription() + " price: " + product.getPrice());

	}
	public void search(String description) {
		int productId = 0;
		List<ProductBean> products = (List<ProductBean>) productService.get(productId );
		int i = 0;
		for (ProductBean product : products) {
			if(product.getDescription().contains(description)){
				System.out.println("product ID: " + product.getId() + " price: " + product.getPrice());
				break;
			}
		}

	}
}
