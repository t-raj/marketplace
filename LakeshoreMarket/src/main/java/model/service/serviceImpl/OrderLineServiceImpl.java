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

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class OrderLineServiceImpl implements OrderLineService{

	private OrderLineDAO orderLineDAO = new OrderLineDAOImpl();
	
	@Override
	public void addItem(OrderLineBean orderLineBean) {
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}
	
	@Override
	public OrderLineBean get(long orderLineId) {
		return ElementUtil.buildOrderLineBean(orderLineDAO.get(orderLineId));
	}
	
	
	@Override
	public void update(OrderLineBean orderLineBean) {
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}
	
	@Override
	public void cancel(long orderId) {
		orderLineDAO.delete(orderId);
	}
	
	@Override
	public List<OrderLineBean> get() {
		return ElementUtil.buildOrderLineBeanList(orderLineDAO.get());
	}
	

}
