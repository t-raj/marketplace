package main.java.service.service;

import java.util.List;

import main.java.model.bean.OrderBean;
import main.java.service.representation.PaymentRepresentation;

public interface OrderService {
	
	/**
	 * The sequence of order is the following:
	 * 1. The customer submits the order and pays for it: ACCEPTED/PAID
	 * 2. The order is pushed to the partner(s) whose product is in the order: PENDING
	 * 3. The order is shipped or canceled: SHIPPED/CANCELED
	 * 4. The order is received by the customer, and the partner receives an acknowledgement: FULFILLED
	 * 
	 * @author lbo
	 *
	 */
	public static enum Status {
		ACCEPTED, PAID, PENDING, SHIPPED, FULFILLED, CANCELED;
	}

	/**
	 * Accept order 
	 * @param orderBean
	 * @return
	 */
	int accept(OrderBean orderBean);
	
	/**
	 * Get order
	 * @param orderId
	 * @return
	 */
	OrderBean get(int orderId);

	/**
	 * Get order bean
	 * @return
	 */
	List<OrderBean> get();

	/**
	 * Get order by status
	 * @param status
	 * @return
	 */
	List<OrderBean> get(Status status);

	/**
	 * Push order to partner
	 * @param partnerId
	 * @return
	 */
	List<OrderBean> pushToPartner(int partnerId);

	/**
	 * Ship order
	 * @param orderId
	 */
	void ship(int orderId);

	/**
	 * Cancel order
	 * @param orderId
	 */
	void cancel(int orderId);

	/**
	 * Accept payment
	 * @param paymentModel
	 * @param orderId
	 * @return
	 */
	boolean acceptPayment(PaymentRepresentation paymentModel, int orderId);

	/**
	 * Get order by customer
	 * @param customerId
	 * @return
	 */
	List<OrderBean> getByCustomer(int customerId);

	/**
	 * Fulfill order by partner id
	 * @param partnerId
	 * @return
	 */
	List<OrderBean> fulfill(int partnerId);
}
