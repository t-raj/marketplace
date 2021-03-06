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
import main.java.model.constant.HTTPVerb;
import main.java.service.representation.Link;
import main.java.service.representation.OrderRepresentation;
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
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	public OrderRepresentation accept(OrderRepresentation order) {
		int orderId = 0;
		if (order != null) {
			// set the status to accept
			order.setStatus(OrderService.Status.ACCEPTED);
			orderId = orderService.accept(ElementUtil.buildOrderBean(order));
		} 
		
		// set accept order links 
		Link cancel = new Link(HTTPVerb.DELETE.toString(), Constant.BASE_PATH + "/orders/" + order.getOrderId(), Constant.BASE_PATH_CONSUMER + "/cancel", Constant.MEDIA_TYPE_XML);	
		Link pay = new Link(HTTPVerb.PUT.toString(), Constant.BASE_PATH + "/orders/payment" + order.getOrderId(), Constant.BASE_PATH_CONSUMER + "/payment", Constant.MEDIA_TYPE_XML);		
		order.setLinks(cancel, pay);
		order.setOrderId(orderId);
		
		return order;
	}
	
	@PUT//1.c accept credit card payment
	@Produces({"application/xml"})
	@Path("/payment/{orderId}")
	public OrderRepresentation acceptPayment(@PathParam("orderId") int orderId) {
		boolean paymentAccepted = orderService.acceptPayment(null, orderId);
		OrderRepresentation order = ElementUtil.buildOrderModel(orderService.get(orderId));
		if (paymentAccepted) {
			// set payment links 
			Link cancel = new Link(HTTPVerb.DELETE.toString(), Constant.BASE_PATH + "/orders/" + orderId, Constant.BASE_PATH_CONSUMER + "/cancel", Constant.MEDIA_TYPE_XML);	
			order.setLinks(cancel);
		}
		
		return order;
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

		Link cancel = new Link(HTTPVerb.DELETE.toString(), Constant.BASE_PATH + "/orders/" + orderRepresentation.getOrderId(), Constant.BASE_PATH_CONSUMER + "/cancel", Constant.MEDIA_TYPE_XML);	
		orderRepresentation.setLinks(cancel);

		return orderRepresentation;
	}

	@GET//1.e provide status of all orders in progress  
	@Produces({"application/xml"})
	@Path("/inprogress")
	public List<OrderRepresentation> getInProgress(){
		List<OrderRepresentation> orders = ElementUtil.buildOrderModelList(orderService.get());
		for (OrderRepresentation order: orders) {
			Link cancel = new Link(HTTPVerb.DELETE.toString(), Constant.BASE_PATH + "/orders/" + order.getOrderId(), Constant.BASE_PATH_CONSUMER + "/cancel", Constant.MEDIA_TYPE_XML);	
			order.setLinks(cancel);

		}
		return orders;
	}

	@DELETE//1.f. order cancel
	@Produces({"application/xml"})
	@Path("/{orderId}")
	public void cancelOrder(@PathParam("orderId") int orderId){
		orderService.cancel(orderId);
	}

	/**
	 * This method allows partners to use the site to sell product by receiving the orders customers have placed for them. 
	 * @param partnerId
	 * @return list of orders that have been placed for the partner
	 */
	@GET//2.c push orders that customers made to the partner
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/pushedOrders/{partnerId}")
	public List<OrderRepresentation> pushToPartner(@PathParam("partnerId") int partnerId){
		List<OrderRepresentation> orderModels= ElementUtil.buildOrderModelList(orderService.pushToPartner(partnerId));

		//set link to check order status
		for(OrderRepresentation order : orderModels){
			Link fulfill = new Link(HTTPVerb.PUT.toString(), Constant.BASE_PATH + "/orders/fulfilled", Constant.BASE_PATH_CONSUMER + "/fulfilledOrders", Constant.MEDIA_TYPE_XML);
			order.setLinks(fulfill);
		}

		return orderModels;
	}
	
	@GET  //2.d. get acknowledgement of order fulfillment if pending
	@Path("/fulfilled/{partnerId}")
	@Produces({"application/xml"})
	public List<OrderRepresentation> fulfill(@PathParam("partnerId") int partnerId){
		// change pending orders to shipped and then fulfill
		return ElementUtil.buildOrderModelList(orderService.fulfill(partnerId));
	}
	
	@GET
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/{customerId}")
	public List<OrderRepresentation> retrieve(@PathParam("customerId") int customerId){
		List<OrderRepresentation> orderModels = ElementUtil.buildOrderModelList(orderService.getByCustomer(customerId));

		//set link to check order status
		for(OrderRepresentation order : orderModels){
			setLinkByOrderStatus(order);
		}

		return orderModels;
	}
	
	private void setLinkByOrderStatus(OrderRepresentation order) {
		if (order != null) {
			Link cancel = new Link(HTTPVerb.DELETE.toString(), Constant.BASE_PATH + "/orders", Constant.BASE_PATH_CONSUMER + "/cancel", Constant.MEDIA_TYPE_XML);
			Link pay = new Link(HTTPVerb.PUT.toString(), Constant.BASE_PATH + "/orders/payment", Constant.BASE_PATH_CONSUMER + "/payment", Constant.MEDIA_TYPE_XML);		

			Status status = order.getStatus();
			
			switch(status) {
			case PAID:
				order.setLinks(cancel);
			case CANCELED:
				//no-op
				return;
			case ACCEPTED:
				order.setLinks(pay, cancel);
				return;
			case PENDING:
				// fall through to shipped
			case SHIPPED:
				order.setLinks(cancel);
				return;
			case FULFILLED:
				// no-op
				return;
			default:
				break;
			}
		}
	}
	

}