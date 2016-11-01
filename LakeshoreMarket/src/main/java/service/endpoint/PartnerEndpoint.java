package main.java.service.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;

import main.java.model.bean.PartnerBean;
import main.java.service.model.PartnerModel;
import main.java.service.service.PartnerService;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
public class PartnerEndpoint {

	@Autowired(required=true)
	private static PartnerService partnerService;
	
	@POST//2.1 Need to register and create profile of partners
	@Produces({"application/xml", "application/json"})
	@Consumes({"application/xml", "application/json"})
	@Path("/Partner")
	public Response registerPartner(int partnerID, String login, String password, String firstName, String lastName, String streetAddress, String city, String state, int zip){
		PartnerModel partnerRepresentation = new PartnerModel();
		String message;
		try {
			partnerService.register(partnerID, login, password, firstName, lastName, streetAddress, city, state, zip);
			message = "partner successfully registered";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error. Partner could not be registered";
		}	
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
	
	

}
