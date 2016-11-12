package main.java.service.endpoint;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.model.OrderModel;
import main.java.service.model.PaymentModel;

@WebService
public interface OrderEndpointInterface {

	Response accept(OrderModel order);
	Response acceptPayment(PaymentModel paymentModel, int orderId);
	Response shipOrder(int orderId);
	Response shipOrders(List<Integer> orderIds);
	OrderModel getOrderStatus(int orderId);
	List<OrderModel> getInProgress();
	Response cancelOrder(int orderId);
	Response pushToPartner(int partnerId);
	List<OrderModel> getOrderStatus();
	
}
