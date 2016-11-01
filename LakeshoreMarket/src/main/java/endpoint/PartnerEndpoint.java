package main.java.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;

import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.partner.partnerBean.PartnerModel;
import main.java.model.service.service.PartnerService;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
public class PartnerEndpoint {

	@Autowired(required=true)
	private static PartnerService partnerService;
	
	@PUT//2.1 Need to register and create profile of partners
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Partner")
	public String createPartner(PartnerModel partnerModel){
		
		if (partnerModel != null) {
			partnerService.register(partnerModel.getId(), partnerModel.getLogin(), partnerModel.getPassword(), partnerModel.getFirstName(), partnerModel.getLastName(), partnerModel.getStreetAddress(), partnerModel.getCity(), partnerModel.getState(), partnerModel.getZip_code());
		}
		
		return "Partner with id: " + partnerModel.getId() + " was created";
	}
	
	
	

}
