package main.java.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.model.bean.CustomerBean;
import main.java.model.bean.OrderBean;
import main.java.model.bean.OrderLineBean;
import main.java.model.bean.PartnerBean;
import main.java.model.bean.ProductBean;
import main.java.model.constant.Constant;
import main.java.service.service.CustomerService;
import main.java.service.service.OrderLineService;
import main.java.service.service.OrderService;
import main.java.service.service.PartnerService;
import main.java.service.service.PaymentService;
import main.java.service.service.ProductService;
/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired(required=true)
	private static OrderLineService orderLineService; 
	private static OrderService orderService;
	private static ProductService productService;
	private static PartnerService partnerService;
	private static CustomerService customerService;
	
	boolean transaction = false;
	boolean successful = false; 
	boolean shipped =false;
	boolean preshipping =false;
	
	private static String orderStatus;

	
	public static String getOrderStatus() {
		return orderStatus;
	}

	public static void setOrderStatus(String orderStatus) {
		PaymentServiceImpl.orderStatus = orderStatus;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	
	@Override
	public OrderBean processOrder(int orderId){
		
		OrderBean orderBean = new OrderBean();
		
		try {
			orderBean = orderService.get(orderId);
			
			orderService.addItem(orderService.get(orderId));
			
			System.out.println("Your order with Id....." + orderId+ "has been created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderBean;
	}

	@Override
	public void makePayment(int orderID){
		
		if(transaction == true){
			successful = true;
		}
	
		if(successful == true){
		
		double totalPrice =0;
		OrderBean order = orderService.get(orderID);
		List<OrderLineBean> orderLines = (List<OrderLineBean>) orderLineService.get();
		List<ProductBean> products = new ArrayList<ProductBean>();;
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
		else{
			System.out.println("Your payment was not successful, please try again!");
		}
	
	}

	@Override
	public String getOrderStatus(long orderId){
		
		if(successful == true){
			orderStatus = Constant.paid;
		}
		if(successful == true && preshipping == true){
			orderStatus = Constant.preshipping;
		
		}
		if(successful == true && preshipping == true && shipped == true){
			orderStatus = Constant.shipped;
			shipNotification(customerService);
		}
		else{
			orderStatus = orderService.get(orderId).getStatus();
		}
		
		List<OrderLineBean> orderLines = (List<OrderLineBean>) orderLineService.get();
		List<ProductBean> products = new ArrayList<ProductBean>();
		for(OrderLineBean line : orderLines){
			if(line.getId()==orderId){
			ProductBean product = (productService.get(line.getProductId()));
			products.add(product);
			}
		}
		System.out.println("Your order with product(s)......"+ products+ "has the order status..."+ orderStatus);
		
		return orderStatus;
		
	}

	private void shipNotification(CustomerService customerService) {
		
		String from = "publicNotification@gmail.com"; 
		String to = customerService.getEmail();
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
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelOrder(long orderId) {
		//update the status from previous to cancelled for a given order
		
		try {
			orderStatus = orderService.get(orderId).getStatus();
				
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		if(orderService.get(orderId) == null){
			return;
		}
		else{
			orderService.get(orderId).setStatus("cancelled");
		}
		
		
	}

	@Override
	public OrderBean shipOrder(long orderId) {
		
		OrderBean orderBean = new OrderBean();
		
		try {
			orderBean.setStatus( orderService.get(orderId).getStatus());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if(successful && orderStatus == "ordered"){
			
			orderBean.setStatus("in progress");
			
			System.out.println("Your payment is successful, and the shippment is in progress");

			if(shipped){
				orderBean.setStatus( "shipped");
			}
			System.out.println("Your order has been shipped! Please wait patiently for your package.");
			}
		
		else{
			System.out.println("Your order has not been shipped yet");
		}
		return orderBean;
	}
	
}