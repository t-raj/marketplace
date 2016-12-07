package main.java.DAO;

import java.util.List;

import main.java.model.entity.Product;

public interface ProductDAO {

	/**
	 * Add product
	 * @param product
	 */
    void add(Product product);
	
    /**
     * Delete product
     * @param productId
     */
    void delete(int productId);
    
    /**
     * Find product
     * @param productId
     * @return
     */
    Product find(int productId);

    /**
     * Update product
     * @param product
     */
    void update(Product product);

    /**
     * Find by partner
     * @param partnerId
     * @return
     */
	List<Product> findByPartner(int partnerId);
}
