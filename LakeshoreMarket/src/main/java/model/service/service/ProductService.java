package main.java.model.service.service;

import main.java.model.product.productBean.ProductBean;

public interface ProductService {
	
	
	ProductBean get(int productId);
	
	void update(ProductBean productBean);	
	
	void addNewProduct(int productID, String description, int partnerID, int numAvailable,int price);
	
	void addExistingProduct(int productID, int partnerID, int quantity);
	
	void setProductInactive(int productID, int partnerID);

}
