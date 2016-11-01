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
import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.service.service.OrderService;
import main.java.model.service.service.PartnerService;
import main.java.util.ElementUtil;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public void addItem(OrderBean orderBean) {
		orderDAO.add(ElementUtil.buildOrder(orderBean));
	}

	@Override
	public OrderBean get(long orderBeanId) {
		return ElementUtil.buildOrderBean(orderDAO.get(orderBeanId));
	}
	
	@Override
	public void update(OrderBean orderBean) {
		orderDAO.update(ElementUtil.buildOrder(orderBean));
	}
	
	@Override
	public void cancel(long orderId) {
		orderDAO.delete(orderId);
	}
	
	@Override
	public List<OrderBean> get() {
		return ElementUtil.buildOrderBeanList(orderDAO.get());
	}

	@Override
	public void sendOrder(int orderId, int partnerId) {
		System.out.println("Your order " + orderId + "was sent to partner " + partnerId);
		
	}
	
}
