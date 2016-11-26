package main.java.service.endpoint;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.representation.OrderRepresentation;
import main.java.service.representation.PaymentRepresentation;

@WebService
public interface OrderEndpointInterface {

	OrderRepresentation accept(OrderRepresentation order);
	PaymentRepresentation acceptPayment(PaymentRepresentation paymentModel, int orderId);
	Response shipOrder(int orderId);
	Response shipOrders(List<Integer> orderIds);
	OrderRepresentation getOrderStatus(int orderId);
	List<OrderRepresentation> getInProgress();
	Response cancelOrder(int orderId);
	Response pushToPartner(int partnerId);
	List<OrderRepresentation> getOrderStatus();
	
}
