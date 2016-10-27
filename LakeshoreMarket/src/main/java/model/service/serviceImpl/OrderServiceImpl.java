package main.java.model.service.serviceImpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.DAO.OrderDAO;
import main.java.DAO.daoImpl.OrderDAOImpl;
import main.java.model.order.orderBean.OrderBean;
import main.java.model.service.service.OrderService;
import main.java.util.ElementUtil;

@Service
@Path("/OrderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	/*
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/OrderBean")
	public void submit(OrderBean orderBean) {
		System.out.println("POST METHOD from Order with ID:.........." + orderBean.getId() );
		ElementUtil.buildOrderBean(orderDAO.get(orderBean.getId()));
	}
	*/
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Path("/OrderBean")
	public void addItem(OrderBean orderBean) {
		System.out.println("POST METHOD from Order with ID:.........." + orderBean.getId() );
		orderDAO.add(ElementUtil.buildOrder(orderBean));
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/OrderBean/Id")
	public OrderBean get(@PathParam("id")long orderBeanId) {
		System.out.println("GET METHOD from order with ID: ......" + orderBeanId);
		return ElementUtil.buildOrderBean(orderDAO.get(orderBeanId));
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/OrderBean")
	public void update(OrderBean orderBean) {
		System.out.println("POST METHOD from Order with ID:......" + orderBean.getId());
		orderDAO.update(ElementUtil.buildOrder(orderBean));
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public void cancel(@PathParam("id")long orderId) {
		orderDAO.delete(orderId);
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/OrderBean")
	public List<OrderBean> get() {
		System.out.println("POST METHOD for all orders.......");
		return ElementUtil.buildOrderBeanList(orderDAO.get());
	}
	
	/*
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/CustomerBean/Id")
	public OrderBean get(@PathParam("id")int orderId) {
		System.out.println("GET METHOD from order with ID: ......" + orderId);
		return ElementUtil.buildOrderBean(orderDAO.get(orderId));
	}
	*/
}
