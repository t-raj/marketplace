package main.java.service.service;

import java.util.List;

import main.java.model.bean.OrderBean;
import main.java.service.model.PaymentModel;
import main.java.service.service.OrderService.Status;

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

	void accept(OrderBean orderBean);
	
	OrderBean get(long orderId);

	List<OrderBean> get();

	List<OrderBean> get(Status status);

	List<OrderBean> pushToPartner(long partnerId);

	void ship(long orderId);

	void cancel(long orderId);

	boolean acceptPayment(PaymentModel paymentModel, long orderId);
}
