package main.java.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import main.java.DAO.OrderDAO;
import main.java.DAO.OrderLineDAO;
import main.java.DAO.daoImpl.OrderDAOImpl;
import main.java.DAO.daoImpl.OrderLineDAOImpl;
import main.java.model.bean.OrderBean;
import main.java.model.entity.OrderLine;
import main.java.service.representation.PaymentRepresentation;
import main.java.service.service.OrderService;
import main.java.service.service.PaymentService;
import main.java.util.ElementUtil;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO = new OrderDAOImpl();
	private OrderLineDAO orderLineDAO = new OrderLineDAOImpl();
	private PaymentService paymentService = new PaymentServiceImpl();
	
	/**
	 * This method accepts a buy order and saves it in the database
	 * @param orderBean
	 */
	@Override
	public void accept(OrderBean orderBean) {
		orderDAO.add(ElementUtil.buildOrder(orderBean));
		orderLineDAO.add(ElementUtil.buildOrderLineList(orderBean));
	}

	/**
	 * This method retrieves an order from the database, including its status
	 * @param orderId
	 */
	@Override
	public OrderBean get(int orderId) {
		OrderBean orderBean = ElementUtil.buildOrderBean(orderDAO.get(orderId));
		orderBean.setProductIds(getProductIds(orderId));
		
		return orderBean;
	}
	
	/**
	 * This method cancels an order
	 * @param orderId
	 */
	@Override
	public void cancel(int orderId) {
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

		List<OrderBean> orderBeanList = ElementUtil.buildOrderBeanList(orderDAO.get(statusInProgress)); 
		for (OrderBean orderBean : orderBeanList) {
			orderBean.setProductIds(getProductIds(orderBean.getId()));
		}
		
		return orderBeanList;
	}
	
	private List<Integer> getProductIds(int orderId) {
		List<Integer> productIds = new ArrayList<Integer>();
		OrderLine orderLine = orderLineDAO.get(orderId);
		if (orderLine != null) {
			productIds.add(orderLine.getProductId());
		}
		
		return productIds;
	}

	/**
	 * This method pushes orders that have been placed to the appropriate partner
	 * @param orderId
	 * @param partnerId
	 * @return pushedOrders
	 */
	@Override
	public List<OrderBean> pushToPartner(int partnerId) {
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
	public void ship(int orderId) {
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
	public boolean acceptPayment(PaymentRepresentation paymentModel, int orderId) {
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
