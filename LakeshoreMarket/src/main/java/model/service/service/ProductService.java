package main.java.model.service.service;

import main.java.model.product.productBean.ProductBean;

public interface ProductService {
	
	void add(ProductBean productBean);
	
	ProductBean get(int productId);
	
	void update(ProductBean productBean);	
	
	void delete(long productId);

}
