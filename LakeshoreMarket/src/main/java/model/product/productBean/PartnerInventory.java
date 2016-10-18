package main.java.model.product.productBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.java.model.service.service.ProductService;

@Controller 
public class PartnerInventory {
	
	
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
	public void search(int productID, int partnerID) {
		ProductBean product = productService.get(productID);
		if (((product.equals(null))||(!product.isActive()))||(product.getPartnerID() != partnerID))
			System.out.println("You do not currently carry any products matching this ID.");
		else
			System.out.println("description: " + product.getDescription() + " price: " + product.getPrice());

	}

	public void search(String description, int partnerID) {
		int productId = 0;
		List<ProductBean> products = (List<ProductBean>) productService.get(productId );
		int i = 0;
		for (ProductBean product : products) {
			if(((product.getDescription().contains(description))&&(product.getPartnerID() == partnerID))&&(product.isActive())){
				System.out.println("product ID: " + product.getId() + " price: " + product.getPrice());
				break;
			}
		}

	}
	public void addNewProduct(int productID, String description, int partnerID, int numAvailable,int price){
		ProductBean productBean = new ProductBean();
		productBean.setId(productID);
		productBean.setDescription(description);
		productBean.setPartnerID(partnerID);
		productBean.setNumAvailable(numAvailable);
		productBean.setPrice(price);
		productBean.setActive(true);
	}
	public void addExistingProduct(int productID, int partnerID, int quantity){
		ProductBean product = productService.get(productID);
		if ((product.equals(null))||(product.getPartnerID() != partnerID))
			System.out.println("You do not currently carry any products matching this ID.");
		else if (!product.isActive())
			product.setActive(true);
		product.setNumAvailable(product.getNumAvailable() + quantity);
	}
	public void setProductInactive(int productID, int partnerID){
		ProductBean product = productService.get(productID);
		if ((product.equals(null))||(product.getPartnerID() != partnerID))
			System.out.println("You do not currently carry any products matching this ID.");
		product.setActive(false);
	}

}
