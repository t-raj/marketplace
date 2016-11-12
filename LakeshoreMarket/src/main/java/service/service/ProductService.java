package main.java.service.service;

import main.java.model.bean.ProductBean;

public interface ProductService {
	
	
	
	ProductBean get(int productId);
	
	void update(ProductBean productBean);	
	
	void addNewProduct(ProductBean productBean);
	
	ProductBean addExistingProduct(int productID, int partnerID, int quantity);
	
	void setProductInactive(int productID, int partnerID);
}
