package main.java.model.service.service;

import main.java.model.product.productBean.ProductBean;

public interface ProductService {
	
	void add(ProductBean productBean);
	
	void delete(long productId);
	
	void update(ProductBean productBean);
	
	ProductBean get(int productId);

}
