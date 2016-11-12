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

import main.java.service.model.OrderModel;
import main.java.service.model.PaymentModel;
import main.java.service.service.OrderService;
import main.java.service.service.OrderService.Status;
import main.java.service.serviceImpl.OrderServiceImpl;
import main.java.util.ElementUtil;

/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
@Path("/order")
public class OrderEndpoint implements OrderEndpointInterface {
	
	private static OrderService orderService = new OrderServiceImpl();
	
	@POST//1.b accept a new buy order
	@Consumes({"application/xml"})
	@Path("/")
	public Response accept(OrderModel order) {
		orderService.accept(ElementUtil.buildOrderBean(order));
		String message = "Order was accepted";
		
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}
	
	@GET//1.c accept credit card payment
	@Consumes({"application/xml"})
	@Path("/{orderId}")
	public Response acceptPayment(PaymentModel paymentModel, @PathParam("orderId") int orderId) {
		boolean paymentAccepted = orderService.acceptPayment(paymentModel, orderId);
		String message = "Payment accepted";
		if (!paymentAccepted) {
			message = "Invalid payment information";
		}
		
		return Response.ok(message, MediaType.TEXT_PLAIN).build();
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
	public OrderModel getOrderStatus(@PathParam("orderId") int orderId){
		return ElementUtil.buildOrderModel(orderService.get(orderId));
	}

	@GET//1.e provide status of all orders in progress  
	@Produces({"application/xml"})
	@Path("/inprogress")
	public List<OrderModel> getInProgress(){
		return ElementUtil.buildOrderModelList(orderService.get());
	}

	@DELETE//1.f. order cancel
	@Produces({"application/xml"})
	@Path("/{orderId}")
	public Response cancelOrder(@PathParam("orderId") int orderId){
		orderService.cancel(orderId);
		String message = "The order with order ID:........" + orderId + " is now canceled";
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
	@Path("/pushedOrders")
	public Response pushToPartner(@PathParam("partnerId") int partnerId){
		
		List<OrderModel> orderModels= ElementUtil.buildOrderModelList(orderService.pushToPartner(partnerId));
		String message = "The order with partnerId" + partnerId + "has been pushed to the partner!";
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}
	
	@GET  //2.d. get acknowledgement of order fulfillment if shipped
	@Produces({"application/xml"})
	@Path("/fulfilled")
	public List<OrderModel> getOrderStatus(){
		return ElementUtil.buildOrderModelList(orderService.get(Status.FULFILLED));
	}

}