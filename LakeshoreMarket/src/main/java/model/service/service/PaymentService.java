package main.java.model.service.service;

import main.java.model.order.OrderModel;
import main.java.model.order.orderBean.OrderBean;

public interface PaymentService {

	boolean isSuccessful();

	void setSuccessful(boolean successful);

	OrderBean processOrder(int orderId);

	void makePayment(int orderID);

	String getOrderStatus(long orderId);

	void cancelOrder(long orderId);

	OrderBean shipOrder(long orderId);

}