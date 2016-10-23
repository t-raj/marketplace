package main.java.model.service.service;

import main.java.model.entity.Customer;
import main.java.model.entity.Partner;

public interface PaymentService {

	boolean isSuccessful();

	void setSuccessful(boolean successful);

	void makePayment(Customer customer, Partner partner);

	String getOrderStatus(long orderId);

}