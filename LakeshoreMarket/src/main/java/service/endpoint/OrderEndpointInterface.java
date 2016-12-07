package main.java.service.endpoint;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.representation.OrderRepresentation;

@WebService
public interface OrderEndpointInterface {

	/**
	 * Accept order
	 * @param order
	 * @return OrderRepresentation
	 */
	OrderRepresentation accept(OrderRepresentation order);

	/**
	 * Accept payment
	 * @param orderId
	 * @return OrderRepresentation
	 */
	OrderRepresentation acceptPayment(int orderId);
	
	/**
	 * ship order
	 * @param orderId
	 * @return OrderRepresentation
	 */
	Response shipOrder(int orderId);
	
	/**
	 * Ship orders
	 * @param orderIds
	 * @return OrderRepresentation
	 */
	Response shipOrders(List<Integer> orderIds);
	
	/**
	 * Get order status
	 * @param orderId
	 * @return OrderRepresentation
	 */
	OrderRepresentation getOrderStatus(int orderId);
	
	/**
	 * Get in progress
	 * @return OrderRepresentation
	 */
	List<OrderRepresentation> getInProgress();

	/**
	 * Cancel order
	 * @param orderId
	 */
	void cancelOrder(int orderId);
	
	/**
	 * Push to partner
	 * @param partnerId
	 * @return OrderRepresentation
	 */
	List<OrderRepresentation> pushToPartner(int partnerId);
	
	/**
	 * Fulfill order
	 * @param partnerId
	 * @return OrderRepresentation
	 */
	List<OrderRepresentation> fulfill(int partnerId);
	
}
