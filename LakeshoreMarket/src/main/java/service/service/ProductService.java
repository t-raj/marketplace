package main.java.service.service;

import java.util.List;

import main.java.model.bean.ProductBean;

public interface ProductService {
	
	/**
	 * Get product
	 * @param productId
	 * @return
	 */
	ProductBean get(int productId);
	
	/**
	 * Update product
	 * @param productBean
	 */
	void update(ProductBean productBean);	
	
	/**
	 * Add product
	 * @param productBean
	 */
	void add(ProductBean productBean);
	
	/**
	 * Delete product
	 * @param productID
	 */
	void delete(int productID);

	/**
	 * Find product by partner
	 * @param partnerId
	 * @return
	 */
	List<ProductBean> findByPartner(int partnerId);
}
