package main.java.DAO;

import main.java.model.entity.Product;

public interface ProductDAO {

    void add(Product product);
	
    void delete(int productId);
    
    Product find(int productId);
    
    void update(Product product);
}
