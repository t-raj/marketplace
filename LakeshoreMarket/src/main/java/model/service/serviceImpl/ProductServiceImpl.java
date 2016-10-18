package main.java.model.service.serviceImpl;

import main.java.DAO.ProductDAO;
import main.java.DAO.daoImpl.ProductDAOImpl;
import main.java.model.product.productBean.ProductBean;
import main.java.model.service.service.ProductService;
import main.java.util.ElementUtil;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void add(ProductBean productBean) {
		productDAO.add(ElementUtil.buildProduct(productBean));
	}

	@Override
	public void delete(long productId) {
		productDAO.delete(productId);
		
	}

	@Override
	public void update(ProductBean productBean) {
		productDAO.update(ElementUtil.buildProduct(productBean));
		
	}

	@Override
	public ProductBean get(int productId) {
		
		return ElementUtil.buildProductBean(productDAO.find(productId));
	}
	
	

}
