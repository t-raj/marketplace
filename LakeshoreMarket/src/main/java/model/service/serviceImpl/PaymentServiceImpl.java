package main.java.model.service.serviceImpl;

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

import main.java.model.constant.Constant;
import main.java.model.entity.Customer;
import main.java.model.entity.Order;
import main.java.model.entity.Partner;
import main.java.model.order.orderBean.OrderLineBean;
import main.java.model.service.service.OrderLineService;
import main.java.model.service.service.OrderService;
import main.java.model.service.service.PaymentService;


public class PaymentServiceImpl implements PaymentService {
	
	@Autowired(required=true)
	private static OrderLineService orderLineService; 
	private static OrderLineBean orderLineBean;
	private static OrderService orderService;
	
	boolean successful; 
	boolean shipped;
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public void makePayment(Customer customer, Partner partner ){
		
		
		
		double totalPrice =0;
		List<OrderLineBean> products = (List<OrderLineBean>) orderLineService.get();
		for(OrderLineBean product : products){
			totalPrice  = + product.getPrice() * product.getQuantity();	
		}
		
		int transactionId = orderLineBean.getLineNumber();
		String partnerLogin = partner.getLogin();
		//List<String> partnerLogins= new ArrayList<String>();
		
		/*
		for(PartnerBean partner : partners){
			partnerLogins.add(partner.getLogin());
		}
		*/
		
		String customerLogin = null;
		customerLogin = customer.getLogin();
		
		System.out.println("the total amount for this order " + transactionId +"is: " + totalPrice);
		
		System.out.println("the customer for this order is : " + customerLogin);
		
		System.out.println("the partner who sells this transaction is: " + partnerLogin);
		

	}

	public String getOrderStatus(long orderId){
		
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
		return orderStatus;
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
			System.out.println("Shipment notification sent successfully...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	

}
