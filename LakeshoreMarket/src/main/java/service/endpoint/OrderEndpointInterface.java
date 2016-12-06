package main.java.service.endpoint;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.representation.OrderRepresentation;

@WebService
public interface OrderEndpointInterface {

	OrderRepresentation accept(OrderRepresentation order);
	OrderRepresentation acceptPayment(int orderId);
	Response shipOrder(int orderId);
	Response shipOrders(List<Integer> orderIds);
	OrderRepresentation getOrderStatus(int orderId);
	List<OrderRepresentation> getInProgress();
	void cancelOrder(int orderId);
	List<OrderRepresentation> pushToPartner(int partnerId);
	List<OrderRepresentation> getOrderStatus();
	
}
