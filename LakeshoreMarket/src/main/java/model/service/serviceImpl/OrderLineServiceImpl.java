package main.java.model.service.serviceImpl;

import java.util.List;

import main.java.DAO.OrderLineDAO;
import main.java.DAO.daoImpl.OrderLineDAOImpl;
import main.java.model.order.orderBean.OrderLineBean;
import main.java.model.service.service.OrderLineService;
import main.java.util.ElementUtil;

public class OrderLineServiceImpl implements OrderLineService{

	
	private OrderLineDAO orderLineDAO = new OrderLineDAOImpl();
	
	@Override
	public void cancel(long orderId) {
		orderLineDAO.delete(orderId);
	}

	@Override
	public OrderLineBean get(long orderId) {
		
		return ElementUtil.buildOrderLineBean(orderLineDAO.get(orderId));
	}

	@Override
	public List<OrderLineBean> get() {
		
		return ElementUtil.buildOrderLineBeanList(orderLineDAO.get());
	}

	@Override
	public void update(OrderLineBean orderLineBean) {
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
	}

	@Override
	public void addItem(OrderLineBean orderLineBean) {
		orderLineDAO.update(ElementUtil.buildOrderLine(orderLineBean));
		
	}

	@Override
	public List<OrderLineBean> get(int orderId) {
		return (List<OrderLineBean>) ElementUtil.buildOrderLineBean(orderLineDAO.get(orderId));
	}
	
	

}
