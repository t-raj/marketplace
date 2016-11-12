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
import main.java.service.model.PaymentModel;
import main.java.service.service.CustomerService;
import main.java.service.service.OrderLineService;
import main.java.service.service.OrderService;
import main.java.service.service.OrderService.Status;
import main.java.service.service.PartnerService;
import main.java.service.service.PaymentService;
import main.java.service.service.ProductService;
/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class PaymentServiceImpl implements PaymentService{
		
	/**
	 * This method validates payment 
	 */
	@Override
	public boolean isValid(PaymentModel paymentModel){
		if (paymentModel == null) {
			return false;
		}
		
		// dummy implementation of payment validation does not do actual 3rd party validation
		return true;
	}
		
}