package main.java.service.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import main.java.DAO.OrderDAO;
import main.java.model.bean.OrderBean;
import main.java.service.model.OrderModel;
import main.java.service.service.OrderLineService;
import main.java.service.service.OrderService;
import main.java.service.service.PaymentService;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
@Path("/service/Order")
public class OrderEndpoint {
	
	private static OrderService orderService;
	private static OrderLineService orderLineService;
	private static PaymentService paymentService;
	private OrderDAO orderDAO;
	
	private static List<OrderBean> orderList;
	
	@GET//1.b accept buy order
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/")
	public String test() {
		String message = "Orders were shipped";
		
//		return Response.ok(message, MediaType.APPLICATION_JSON).build();
		return message;
	}
 
	@GET//1.b accept buy order
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/")
	public Response processOrder(int orderId) {
		ElementUtil.buildOrderModel(paymentService.processOrder(orderId));
		String message = "Orders were shipped";
		
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
 
	@PUT//1.d. ship orders
	@Produces({"application/xml"})
	@Path("/shipment")
	public Response shipOrder(int orderId){
		ElementUtil.buildOrderModel(paymentService.shipOrder(orderId)); 
		String message = "Orders were shipped";

		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}

/*	@GET//1.d. ship orders
	@Produces({"application/xml"})
	@Path("/Order/id")
	public Response shipOrders(List<OrderModel> orderRepresentationList){
		for(OrderModel orderRepresentation: orderRepresentationList){
			shipOrder(orderRepresentation.getCustomerId());
		}
		
		String message = "Orders were shipped";
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
	*/
	@PUT//1.e provide order status, provide status of orders in progress  //2.d. get acknowledgement of order fulfillment if shipped
	@Produces({"application/xml"})
	@Path("/status")
	public Response orderStatus(int customerId){
		String message = paymentService.getOrderStatus(customerId);
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	
	}
	
	@DELETE//1.f. order cancel
	@Produces({"application/xml"})
	@Path("/")
	public Response cancelOrder(OrderModel orderModel){
		StringBuilder sb = new StringBuilder();
		
		OrderModel orderRepresentation = new OrderModel();
		
		orderRepresentation.setStatus(paymentService.getOrderStatus(orderModel.getCustomerId()));
		
		paymentService.cancelOrder(orderModel.getCustomerId());
		
		if(paymentService.getOrderStatus(orderModel.getCustomerId()) == "cancelled"){
			sb.append("The order with customer ID:........" + orderModel.getCustomerId() + " has status updated to: " + orderRepresentation.getStatus());
		}

		String message = sb.toString();
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}

	@PUT//2.c push orders that customers made to partners
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/partnermessage")
	public Response sendOrderToPartner(int orderId, int partnerId){
		orderService.sendOrder(orderId, partnerId);
		String message = orderId + " was sent to partner " + partnerId;
		return Response.ok(message, MediaType.APPLICATION_JSON).build();

	}
	
}