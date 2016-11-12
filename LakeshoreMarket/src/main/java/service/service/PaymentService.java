package main.java.service.service;

import main.java.model.bean.OrderBean;
import main.java.service.model.OrderModel;
import main.java.service.model.PaymentModel;

public interface PaymentService {

	boolean isValid(PaymentModel paymentModel);

}