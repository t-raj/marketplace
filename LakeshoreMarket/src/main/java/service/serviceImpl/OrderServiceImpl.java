package main.java.service.serviceImpl;

import java.util.ArrayList;
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
import main.java.model.bean.OrderBean;
import main.java.model.bean.PartnerBean;
import main.java.service.model.PaymentModel;
import main.java.service.service.OrderService;
import main.java.service.service.PartnerService;
import main.java.service.service.PaymentService;
import main.java.util.ElementUtil;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO = new OrderDAOImpl();
	private PaymentService paymentService = new PaymentServiceImpl();
	
	/**
	 * This method accepts a buy order and saves it in the database
	 * @param orderBean
	 */
	@Override
	public void accept(OrderBean orderBean) {
		orderDAO.add(ElementUtil.buildOrder(orderBean));
	}

	/**
	 * This method retrieves an order from the database, including its status
	 * @param orderId
	 */
	@Override
	public OrderBean get(long orderId) {
		return ElementUtil.buildOrderBean(orderDAO.get(orderId));
	}
	
	/**
	 * This method cancels an order
	 * @param orderId
	 */
	@Override
	public void cancel(long orderId) {
		orderDAO.delete(orderId);
	}
	
	/**
	 * This method retrieves all orders in progress
	 */
	@Override
	public List<OrderBean> get() {
		// make a single database call to retrieve all in progress, i.e. not fulfilled 
		List<Status> statusInProgress = new ArrayList<Status>();
		statusInProgress.add(Status.ACCEPTED);
		statusInProgress.add(Status.PAID);
		statusInProgress.add(Status.PENDING);
		statusInProgress.add(Status.SHIPPED);
		return ElementUtil.buildOrderBeanList(orderDAO.get(statusInProgress));
	}

	/**
	 * This method pushes orders that have been placed to the appropriate partner
	 * @param orderId
	 * @param partnerId
	 * @return pushedOrders
	 */
	@Override
	public List<OrderBean> pushToPartner(long partnerId) {
		List<OrderBean> pushedOrders = new ArrayList<OrderBean>();
		// retrieve the orders that customer have placed and that have been paid for a specific partner. These are the orders ready to be pushed.
		List<OrderBean> paidOrders = ElementUtil.buildOrderBeanList(orderDAO.get(Status.PAID, partnerId));
		// push them to partners and update their status to pushed to the partner: pending
		for (OrderBean paidOrder : paidOrders) {
			paidOrder.setStatus(Status.PENDING);
			orderDAO.update(ElementUtil.buildOrder(paidOrder));
			pushedOrders.add(paidOrder);
		}
		
		return pushedOrders;
	}

	/**
	 * This method ships the order and sets the order status to shipped 
	 */
	@Override
	public void ship(long orderId) {
		OrderBean order = ElementUtil.buildOrderBean(orderDAO.get(orderId));
		// in the real world, this may call other 3rd party apis to perform the actual shipping, e.g. shippingService
		order.setStatus(Status.SHIPPED);
		orderDAO.update(ElementUtil.buildOrder(order));
		// exceptions during shipping will be caught as runtime Exceptions for now
	}

	/**
	 * This method accepts credit card payments and updates the order status accordingly
	 */
	// 1c Accept credit card payment
	@Override
	public boolean acceptPayment(PaymentModel paymentModel, long orderId) {
		boolean paymentAccepted = false;
		if (paymentService.isValid(paymentModel)) {
			OrderBean order = get(orderId);
			order.setStatus(Status.PAID);
			paymentAccepted = true;
		} 

		return paymentAccepted;
	}

	/**
	 * This method get orders with one status
	 */
	@Override
	public List<OrderBean> get(Status status) {
		return ElementUtil.buildOrderBeanList(orderDAO.get(status));
	}
	
}
