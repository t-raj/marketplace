package main.java.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import main.java.DAO.OrderDAO;
import main.java.model.order.OrderModel;
import main.java.model.order.orderBean.OrderBean;
import main.java.model.order.orderBean.OrderManager;
import main.java.model.service.service.OrderLineService;
import main.java.model.service.service.OrderService;
import main.java.util.ElementUtil;

public class OrderEndpoint {
	
//	@Autowired(required=true)
	private static OrderService orderService;
	private static OrderLineService orderLineService;
	private static OrderManager orderManager;
	private OrderDAO orderDAO;
	
	private static List<OrderBean> orderList;
	
	@GET
	@Consumes("application/xml")
	@Produces("application/xml")
	public String processOrder(OrderModel oderModel) {
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
 
//the order does not actually delete from the database, instead the status changed from previous to cancelled 
	


	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public void shipOrder(long orderId){
		orderManager.shipOrder(orderId); 
	}
	

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public void shipOrders(List<OrderBean> orderBeanList){
		
		for(OrderBean orderBean: orderBeanList){
			this.shipOrder(orderBean.getId());
		}
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public String orderStatus(long orderId){
		return orderManager.getOrderStatus(orderId);
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/Order/id")
	public void cancelOrder(long orderId){
		
		System.out.println("The order ID you have cancelled is...." + orderId);
		
		orderManager.cancelOrder(orderId);
		
		if(orderManager.getOrderStatus(orderId) == "cancelled"){
			
		System.out.println("The order with order ID:........" + orderId + " has been cancelled");
		}
	}
	
	
	
	
	
}
