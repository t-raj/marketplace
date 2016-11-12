package main.java.service.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.model.bean.PartnerBean;
import main.java.service.model.PartnerModel;
import main.java.service.service.PartnerService;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
public class PartnerEndpoint implements PartnerEndpointInterface {

	private static PartnerService partnerService;
	
	@POST//2.1 Need to register and create profile of partners
	@Produces({"application/xml"})
	@Consumes({"application/xml"})
	@Path("/Partner")
	public Response registerPartner(PartnerModel partnerModel){
		//PartnerModel partnerRepresentation = new PartnerModel();
		PartnerBean partnerBean = ElementUtil.buildPartnerBean(partnerModel);
		String message;
		try {
			partnerService.register(partnerBean);
			message = "partner successfully registered";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error. Partner could not be registered";
		}	
		return Response.ok(message, MediaType.APPLICATION_JSON).build();
	}
	
	

}
