package main.java.service.serviceImpl;

import main.java.service.representation.PaymentRepresentation;
import main.java.service.service.PaymentService;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class PaymentServiceImpl implements PaymentService{
		
	/**
	 * This method validates payment 
	 */
	@Override
	public boolean isValid(PaymentRepresentation paymentModel){
		// dummy implementation of payment validation does not do actual 3rd party validation
		return true;
	}
		
}