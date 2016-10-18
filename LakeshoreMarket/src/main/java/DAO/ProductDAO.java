package main.java.DAO;

import main.java.model.entity.Product;

public interface ProductDAO {

    void add(Product product);
	
    void delete(long productId);
    
    Product find(long productId);
    
    void update(Product product);
}
