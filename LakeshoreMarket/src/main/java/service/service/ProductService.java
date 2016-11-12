package main.java.service.service;

import main.java.model.bean.ProductBean;

public interface ProductService {
	
	ProductBean get(int productId);
	
	void update(ProductBean productBean);	
	
<<<<<<< Upstream, based on branch 'master' of https://lboloyola@bitbucket.org/lboloyola/lakeshoremarket.git
	void add(ProductBean productBean);
=======
	void addNewProduct(ProductBean productBean);
>>>>>>> e47aef9 updated product service
	
	void delete(long productID);
}
