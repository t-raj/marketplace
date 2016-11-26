package main.java.service.service;

import main.java.model.bean.OrderBean;
import main.java.service.representation.OrderRepresentation;
import main.java.service.representation.PaymentRepresentation;

public interface PaymentService {

	boolean isValid(PaymentRepresentation paymentModel);

}