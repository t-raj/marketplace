package main.java.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import main.java.model.order.OrderModel;
import main.java.model.service.service.OrderService;

public class OrderEndpoint {
	
//	@Autowired(required=true)
	private static OrderService orderService;
	
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
 

}
