package main.java.service.serviceImpl;

import java.util.List;

import main.java.DAO.OrderLineDAO;
import main.java.DAO.daoImpl.OrderLineDAOImpl;
import main.java.model.bean.OrderLineBean;
import main.java.service.service.OrderLineService;
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
	public OrderLineBean get(int orderLineId) {
		return ElementUtil.buildOrderLineBean(orderLineDAO.get(orderLineId));
	}
	
	
	@Override
	public void update(OrderLineBean orderLineBean) {
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}
	
	@Override
	public void cancel(int orderId) {
		orderLineDAO.delete(orderId);
	}
	
	@Override
	public List<OrderLineBean> get() {
		return ElementUtil.buildOrderLineBeanList(orderLineDAO.get());
	}
	

}
