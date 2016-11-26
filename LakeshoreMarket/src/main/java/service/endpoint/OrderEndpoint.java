package main.java.service.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.model.constant.Constant;
import main.java.service.representation.Link;
import main.java.service.representation.OrderRepresentation;
import main.java.service.representation.PaymentRepresentation;
import main.java.service.service.OrderService;
import main.java.service.service.OrderService.Status;
import main.java.service.serviceImpl.OrderServiceImpl;
import main.java.util.ElementUtil;

/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
@Path("/orders")
public class OrderEndpoint implements OrderEndpointInterface {
	
	private static OrderService orderService = new OrderServiceImpl();
	
	@POST//1.b accept a new buy order
	@Consumes({"application/xml"})
	public OrderRepresentation accept(OrderRepresentation order) {
		if (order != null) {
			// set the status to accept
			order.setStatus(OrderService.Status.ACCEPTED);
			orderService.accept(ElementUtil.buildOrderBean(order));
		} 
		
		// set accept order links 
		Link cancel = new Link("cancel", Constant.BASE_PATH + "/orders/" + order.getOrderId(), "/", Constant.MEDIA_TYPE_XML);	
		Link pay = new Link("pay", Constant.BASE_PATH + "/orders/payment" + order.getOrderId(), "/payment", Constant.MEDIA_TYPE_XML);		
		order.setLinks(cancel, pay);

		return order;
	}
	
	@PUT//1.c accept credit card payment
	@Produces({"application/xml"})
	@Path("/payment/{orderId}")
	public PaymentRepresentation acceptPayment(PaymentRepresentation paymentModel, @PathParam("orderId") int orderId) {
		boolean paymentAccepted = orderService.acceptPayment(paymentModel, orderId);
		if (paymentAccepted) {
			// set payment links 
			Link cancel = new Link("cancel", Constant.BASE_PATH + "/orders/" + orderId, "/", Constant.MEDIA_TYPE_XML);	
			Link checkStatus = new Link("status", Constant.BASE_PATH + "/orders/status" + orderId, "/", Constant.MEDIA_TYPE_XML);		
			paymentModel.setLinks(cancel, checkStatus);
		}
		
		return paymentModel;
	}
 
	@PUT//1.d. ship order
	@Produces({"application/xml"})
	@Path("/shipment/{orderId}")
	public Response shipOrder(@PathParam("orderId") int orderId){
		orderService.ship(orderId);
		String message = "Order was shipped";

		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}

	@PUT//1.d. ship orders
	@Produces({"application/xml"})
	@Path("/shipment")
	public Response shipOrders(@QueryParam("orderIds") List<Integer> orderIds){
		for (Integer orderId : orderIds) {
			orderService.ship(orderId);
		}
		String message = "Orders were shipped";
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}

	@GET//1.e provide order status  
	@Produces({"application/xml"})
	@Path("/status/{orderId}")
	public OrderRepresentation getOrderStatus(@PathParam("orderId") int orderId){
		OrderRepresentation orderRepresentation = ElementUtil.buildOrderModel(orderService.get(orderId));

		Link cancel = new Link("cancel", Constant.BASE_PATH + "/orders/" + orderRepresentation.getOrderId(), "/", Constant.MEDIA_TYPE_XML);	
		Link push = new Link("push", Constant.BASE_PATH + "/orders/" + orderRepresentation.getPartnerId(), "/", Constant.MEDIA_TYPE_XML);	
		orderRepresentation.setLinks(cancel, push);

		return orderRepresentation;
	}

	@GET//1.e provide status of all orders in progress  
	@Produces({"application/xml"})
	@Path("/inprogress")
	public List<OrderRepresentation> getInProgress(){
		List<OrderRepresentation> orders = ElementUtil.buildOrderModelList(orderService.get());
		for (OrderRepresentation order: orders) {
			Link cancel = new Link("cancel", Constant.BASE_PATH + "/orders/" + order.getOrderId(), "/", Constant.MEDIA_TYPE_XML);	
			Link push = new Link("push", Constant.BASE_PATH + "/orders/" + order.getPartnerId(), "/", Constant.MEDIA_TYPE_XML);	
			order.setLinks(cancel, push);

		}
		return orders;
	}

	@DELETE//1.f. order cancel
	@Produces({"application/xml"})
	@Path("/{orderId}")
	public Response cancelOrder(@PathParam("orderId") int orderId){
		orderService.cancel(orderId);
		Link getInProgress = new Link("progress", Constant.BASE_PATH + "/orders/inprogress" + orderId, "/inprogress", Constant.MEDIA_TYPE_XML);	
		String message = "The order with order ID:........" + orderId + " is now canceled. " + getInProgress.toString();
	
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}

	/**
	 * This method allows partners to use the site to sell product by receiving the orders customers have placed for them. 
	 * @param partnerId
	 * @return list of orders that have been placed for the partner
	 */
	@PUT//2.c push orders that customers made to the partner
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/pushedOrders/{partnerId}")
	public Response pushToPartner(@PathParam("partnerId") int partnerId){
		List<OrderRepresentation> orderModels= ElementUtil.buildOrderModelList(orderService.pushToPartner(partnerId));
		String message = "The order with partnerId" + partnerId + "has been pushed to the partner!";
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}
	
	@GET  //2.d. get acknowledgement of order fulfillment if shipped
	@Produces({"application/xml"})
	@Path("/fulfilled")
	public List<OrderRepresentation> getOrderStatus(){
		return ElementUtil.buildOrderModelList(orderService.get(Status.FULFILLED));
	}

}