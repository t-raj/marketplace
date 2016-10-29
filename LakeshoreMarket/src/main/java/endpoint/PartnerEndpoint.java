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

//for testing purposes
public class PartnerEndpoint {

	@Autowired(required=true)
	private static PartnerService partnerService;
	
	@PUT//2.1 Need to register and create profile of partners
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Partner")
	public PartnerModel createPartner(int partnerId){
		
		//TO DO for Tara
		return null;
		
	}
	
	
	

}
