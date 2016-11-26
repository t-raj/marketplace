package main.java.service.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.service.representation.PartnerRepresentation;
import main.java.service.service.PartnerService;
import main.java.service.serviceImpl.PartnerServiceImpl;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */

@Path("/partner")
public class PartnerEndpoint implements PartnerEndpointInterface {

	private static PartnerService partnerService = new PartnerServiceImpl();
	
	@POST//2.1 Need to register and create profile of partners
	@Consumes({"application/xml"})
	public Response registerPartner(PartnerRepresentation partnerModel){
		String message;
		try {
			partnerService.register(ElementUtil.buildPartnerBean(partnerModel));
			message = "partner successfully registered";
		} catch (NullPointerException e) {
			e.printStackTrace();
			message = "Error. Partner could not be registered";
		}	
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}
	
	@GET//get partner info, testing purpose 
	@Produces({"application/xml"})
	@Path("/{partnerId}")
	public PartnerRepresentation getPartner(@PathParam("partnerId") int orderId){
		return ElementUtil.buildPartnerModel(partnerService.get(orderId));
	}

}
