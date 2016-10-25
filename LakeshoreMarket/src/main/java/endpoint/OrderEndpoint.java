package main.java.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import main.java.DAO.OrderDAO;
import main.java.model.order.OrderModel;
import main.java.model.service.service.OrderLineService;
import main.java.model.service.service.OrderService;
import main.java.util.ElementUtil;

public class OrderEndpoint {
	
//	@Autowired(required=true)
	private static OrderService orderService;
	private static OrderLineService orderLineService;
	private OrderDAO orderDAO;
	
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
 

	public void cancelOrder(){
		long orderId = 0;
		try {
			orderDAO.delete(orderId);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The order with order ID:........" + orderId + " has been cancelled");
		
	}
	
	
	
	
}
