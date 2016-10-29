package main.java.model.service.service;

import main.java.model.order.OrderModel;

public interface PaymentService {

	boolean isSuccessful();

	void setSuccessful(boolean successful);

	OrderModel processOrder(int orderId);

	void makePayment(int orderID);

	String getOrderStatus(long orderId);

	void cancelOrder(long orderId);

	void shipOrder(long orderId);

}