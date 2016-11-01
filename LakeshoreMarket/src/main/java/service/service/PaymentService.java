package main.java.service.service;

import main.java.model.bean.OrderBean;
import main.java.service.model.OrderModel;

public interface PaymentService {

	boolean isSuccessful();

	void setSuccessful(boolean successful);

	OrderBean processOrder(int orderId);

	void makePayment(int orderID);

	String getOrderStatus(long orderId);

	void cancelOrder(long orderId);

	OrderBean shipOrder(long orderId);

}