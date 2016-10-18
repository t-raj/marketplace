package main.java.model.order.orderBean;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.java.model.constant.Constant;
import main.java.model.customer.customerBean.CustomerBean;
import main.java.model.entity.Customer;
import main.java.model.entity.Order;
import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.product.productBean.ProductBean;
import main.java.model.service.service.CustomerService;
import main.java.model.service.service.OrderLineService;
import main.java.model.service.service.OrderService;
import main.java.model.service.service.PartnerService;
import main.java.model.service.service.ProductService;

@Controller
public class OrderManager {
	 
	@Autowired(required=true)
	private static OrderLineService orderLineService; 
	//private static OrderLineBean orderLineBean;
	private static ProductService productService;
	private static OrderService orderService;
	private static CustomerService customerService;
	private static PartnerService partnerService;
	
	boolean successful; 
	boolean shipped;
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public void makePayment(int orderID){
		
		
		
		double totalPrice =0;
		OrderBean order = orderService.get(orderID);
		List<OrderLineBean> orderLines = (List<OrderLineBean>) orderLineService.get();
		List<ProductBean> products = null;
		for(OrderLineBean line : orderLines){
			if(line.getId()==orderID){
			ProductBean product = (productService.get(line.getProductId()));
			products.add(product);
			totalPrice += product.getPrice() * line.getQuantity();
			}
		}
		PartnerBean partner = (partnerService.get(order.getPartnerId()));
		String partnerLogin= partner.getLogin();
		String customerLogin = null;
		CustomerBean customer = (customerService.get(order.getCustomerId()));
		customerLogin = customer.getLogin();
		
		System.out.println("the total amount for this order " + orderID +"is: " + totalPrice);
		
		System.out.println("Customer: " + customerLogin);
		
		System.out.println("vendor: " + partnerLogin);
		

	}

	public void getOrderStatus(long orderId){
		
		String orderStatus;
		
		if(successful == true){
			orderStatus = Constant.paid;
		}
		if(shipped == true){
			orderStatus = Constant.shipped;
			//get the customer object and send notification 
			Order order = new Order(); 
			int customerId = order.getCustomerId();
			Customer customer = new Customer();
			customer.setId(customerId);;
			
			shipNotification(customer);
		}
		else{
			orderStatus = orderService.get(orderId).getStatus();
		}
		
	}
	
	private void shipNotification(Customer customer) {
		
		String from = "publicNotification@gmail.com"; 
		String to = customer.getEmail();
		String host = "localhost";
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Your order has been shipped");
			message.setText("Your payment was successful, and we have shipped your order");
			
			Transport.send(message);
			System.out.println("Shippment notification sent successfully...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	

}
