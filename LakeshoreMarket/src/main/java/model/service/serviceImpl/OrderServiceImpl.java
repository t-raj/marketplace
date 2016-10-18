package main.java.model.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.DAO.OrderDAO;
import main.java.DAO.daoImpl.OrderDAOImpl;
import main.java.model.order.orderBean.OrderBean;
import main.java.model.service.service.OrderService;
import main.java.util.ElementUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	public void submit(OrderBean orderBean) {

	}

	public void cancel(long orderId) {
		orderDAO.delete(orderId);
	}

	public OrderBean get(long orderBeanId) {
		
		return ElementUtil.buildOrderBean(orderDAO.get(orderBeanId));
	}

	public List<OrderBean> get() {
		
		return ElementUtil.buildOrderBeanList(orderDAO.get());
	}

	public void update(OrderBean orderBean) {
		orderDAO.update(ElementUtil.buildOrder(orderBean));

	}

	public void addItem(OrderBean orderBean) {
		orderDAO.add(ElementUtil.buildOrder(orderBean));
		

	}

	public OrderBean get(int orderId) {
		
		return ElementUtil.buildOrderBean(orderDAO.get(orderId));
	}

}
