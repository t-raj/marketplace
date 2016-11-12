package main.java.service.endpoint;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.model.OrderModel;
import main.java.service.model.PaymentModel;

@WebService
public interface OrderEndpointInterface {

	Response accept(OrderModel order);
	Response acceptPayment(PaymentModel paymentModel, long orderId);
	Response shipOrder(long orderId);
	Response shipOrders(List<Long> orderIds);
	OrderModel getOrderStatus(long orderId);
	List<OrderModel> getInProgress();
	Response cancelOrder(long orderId);
	List<OrderModel> pushToPartner(long partnerId);
	List<OrderModel> getOrderStatus();
	
}
