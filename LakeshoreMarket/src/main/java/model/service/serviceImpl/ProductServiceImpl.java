package main.java.model.service.serviceImpl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.DAO.ProductDAO;
import main.java.DAO.daoImpl.ProductDAOImpl;
import main.java.model.product.productBean.ProductBean;
import main.java.model.service.service.ProductService;
import main.java.util.ElementUtil;


@Path("/ProductService/")
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();

	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/ProductBean")
	public void add(ProductBean productBean) {
		System.out.println("POST METHOD from Product with ID:.........." + productBean.getId());
		productDAO.add(ElementUtil.buildProduct(productBean));
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/ProductBean/Id")
	public ProductBean get(@PathParam("id")int productId) {
		System.out.println("GET METHOD from product with ID: ......" + productId);
		return ElementUtil.buildProductBean(productDAO.find(productId));
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/Product/id")
	public void delete(@PathParam("id")long productId) {
		productDAO.delete(productId);
		
	}

	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/ProductBean")
	public void update(ProductBean productBean) {
		System.out.println("POST METHOD from product with ID:......" + productBean.getId());
		productDAO.update(ElementUtil.buildProduct(productBean));
		
	}


}
