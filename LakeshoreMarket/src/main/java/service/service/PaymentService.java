package main.java.service.service;

import main.java.service.representation.PaymentRepresentation;

public interface PaymentService {

	/**
	 * Validate payment
	 * @param paymentModel
	 * @return
	 */
	boolean isValid(PaymentRepresentation paymentModel);

}