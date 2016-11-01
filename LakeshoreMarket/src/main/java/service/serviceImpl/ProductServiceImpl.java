package main.java.service.serviceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.DAO.ProductDAO;
import main.java.DAO.daoImpl.ProductDAOImpl;
import main.java.model.bean.ProductBean;
import main.java.model.entity.Product;
import main.java.service.service.ProductService;
import main.java.util.ElementUtil;


/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public ProductBean get(@PathParam("id")int productId) {
		return ElementUtil.buildProductBean(productDAO.find(productId));
	}

	@Override
	public void update(ProductBean productBean) {
		productDAO.update(ElementUtil.buildProduct(productBean));
		
	}
	
	@Override
	public void addNewProduct(int productID, String description, int partnerID, int numAvailable,int price){
		ProductBean productBean = new ProductBean();
		productBean.setId(productID);
		productBean.setDescription(description);
		productBean.setPartnerID(partnerID);
		productBean.setNumAvailable(numAvailable);
		productBean.setPrice(price);
		productBean.setActive(true);
		System.out.println("product " + productBean.getId() + " has been added to the database");
		productDAO.add(ElementUtil.buildProduct(productBean));
	}
	
	@Override
	public ProductBean addExistingProduct(int productID, int partnerID, int quantity){
		ProductBean product = this.get(productID);
		if ((product.equals(null))||(product.getPartnerID() != partnerID))
			System.out.println("You do not currently carry any products matching this ID.");
		else if (!product.isActive())
			product.setActive(true);
		product.setNumAvailable(product.getNumAvailable() + quantity);
		productDAO.update(ElementUtil.buildProduct(product));
		Product productEntity =  productDAO.find(productID);
		product = ElementUtil.buildProductBean(productEntity);
		return product;
	}
	
	@Override
	public void setProductInactive(int productID, int partnerID){
		ProductBean product = this.get(productID);
		if ((product.equals(null))||(product.getPartnerID() != partnerID))
			System.out.println("You do not currently carry any products matching this ID.");
		product.setActive(false);
		productDAO.update(ElementUtil.buildProduct(product));
	}

}
