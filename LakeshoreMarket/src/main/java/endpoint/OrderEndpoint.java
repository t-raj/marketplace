package main.java.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import main.java.DAO.OrderDAO;
import main.java.model.order.OrderModel;
import main.java.model.order.orderBean.OrderBean;
import main.java.model.service.service.*;
import main.java.model.service.service.OrderLineService;
import main.java.model.service.service.OrderService;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
public class OrderEndpoint {
	
	private static OrderService orderService;
	private static OrderLineService orderLineService;
	private static PaymentService paymentService;
	private OrderDAO orderDAO;
	
	private static List<OrderBean> orderList;
	
	@PUT//1.b accept buy order
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Order")
	public OrderModel processOrder(int orderId) {
		return ElementUtil.buildOrderModel(paymentService.processOrder(orderId));
	}
 
//the order does not actually delete from the database, instead the status changed from previous to cancelled 
	

	@GET//1.d. ship orders
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public OrderModel shipOrder(int orderId){
		 return ElementUtil.buildOrderModel(paymentService.shipOrder(orderId)); 
	}
	

	@GET//1.d. ship orders
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public void shipOrders(List<OrderModel> orderRepresentationList){
		
		for(OrderModel orderRepresentation: orderRepresentationList){
			shipOrder(orderRepresentation.getCustomerId());
		}
	}
	
	@GET//1.e provide order status, provide status of orders in progress  //2.d. get acknowledgement of order fulfillment if shipped
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public String orderStatus(OrderModel orderModel){
		return paymentService.getOrderStatus(orderModel.getCustomerId());
	}
	
	@DELETE//1.f. order cancel
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public String cancelOrder(OrderModel orderModel){
		StringBuilder sb = new StringBuilder();
		
		OrderModel orderRepresentation = new OrderModel();
		
		orderRepresentation.setStatus(paymentService.getOrderStatus(orderModel.getCustomerId()));
		
		paymentService.cancelOrder(orderModel.getCustomerId());
		
		if(paymentService.getOrderStatus(orderModel.getCustomerId()) == "cancelled"){
			sb.append("The order with customer ID:........" + orderModel.getCustomerId() + " has status updated to: " + orderRepresentation.getStatus());
		}

		return sb.toString();
	}

	@PUT//2.c push orders that customers made to partners
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Order")
	public String sendOrderToPartner(int orderId, int partnerId){
		orderService.sendOrder(orderId, partnerId);
		return orderId + " was sent to partner " + partnerId;
	}
	
	
}
