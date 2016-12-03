package main.java.service.service;

import java.util.List;

import main.java.model.bean.ProductBean;

public interface ProductService {
	
	ProductBean get(int productId);
	
	void update(ProductBean productBean);	
	
	void add(ProductBean productBean);
	
	void delete(int productID);

	List<ProductBean> findByPartner(int partnerId);
}
