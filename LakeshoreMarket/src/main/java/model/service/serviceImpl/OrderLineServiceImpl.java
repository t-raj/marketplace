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

import main.java.DAO.OrderLineDAO;
import main.java.DAO.daoImpl.OrderLineDAOImpl;
import main.java.model.order.orderBean.OrderLineBean;
import main.java.model.service.service.OrderLineService;
import main.java.util.ElementUtil;

@Path("/OrderLineService")
public class OrderLineServiceImpl implements OrderLineService{

	private OrderLineDAO orderLineDAO = new OrderLineDAOImpl();
	
	@Override
	public void addItem(OrderLineBean orderLineBean) {
		System.out.println("POST METHOD from OrderLine with ID:.........." + orderLineBean.getId());
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}
	
	@Override
	public OrderLineBean get(@PathParam("id")long orderLineId) {
		System.out.println("GET METHOD from orderLine with ID: ......" + orderLineId);
		return ElementUtil.buildOrderLineBean(orderLineDAO.get(orderLineId));
	}
	
	
	@Override
	public void update(OrderLineBean orderLineBean) {
		System.out.println("POST METHOD from OrderLine with ID:......" + orderLineBean.getId() + "has been updated");
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}
	
	@Override
	public void cancel(@PathParam("id")long orderId) {
		orderLineDAO.delete(orderId);
	}
	
	@Override
	public List<OrderLineBean> get() {
		System.out.println("POST METHOD for all orderLines are.......");
		return ElementUtil.buildOrderLineBeanList(orderLineDAO.get());
	}
	
	/*
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/OrderLineBean")
	public List<OrderLineBean> get(@PathParam("id")int orderId) {
		System.out.println("POST METHOD for all orderLines are.......");
		return (List<OrderLineBean>) ElementUtil.buildOrderLineBean(orderLineDAO.get(orderId));
	}
	*/
	
	

}
