package main.java.service.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.model.constant.Constant;
import main.java.model.constant.HTTPVerb;
import main.java.service.representation.Link;
import main.java.service.representation.PartnerRepresentation;
import main.java.service.service.PartnerService;
import main.java.service.serviceImpl.PartnerServiceImpl;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */

@Path("/partners")
public class PartnerEndpoint implements PartnerEndpointInterface {

	private static PartnerService partnerService = new PartnerServiceImpl();
	
	@POST//2.1 Need to register and create profile of partners, add partner
	@Consumes({"application/xml"})
	@Produces({"application/xml"})
	public PartnerRepresentation register(PartnerRepresentation partnerModel){
		try {
			partnerService.register(ElementUtil.buildPartnerBean(partnerModel));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}	
		
		return setLinks(ElementUtil.buildPartnerModel(ElementUtil.buildPartnerBean(partnerModel)));
	}
	
	//the partner can register and after that delete the partner
	@DELETE
	@Consumes({"application/xml"})
	@Path("{partnerId}")
	public Response delete(PartnerRepresentation partnerModel){
		String message;
		
		try{
			partnerService.delete(ElementUtil.buildPartnerBean(partnerModel).getId());
			message = "partner successfully deleted";
		}catch(NullPointerException e){
			e.printStackTrace();
			message = "Error. Partner could not be deleted.";
		}
		return Response.ok(message, MediaType.TEXT_XML_TYPE).build();
	}
	
	@GET//get partner info given login
	@Produces({"application/xml"})
	@Path("/{login}")
	@Override
	public PartnerRepresentation get(@PathParam("login") String login) {
		return setLinks(ElementUtil.buildPartnerModel(partnerService.get(login)));
	}

	private PartnerRepresentation setLinks(PartnerRepresentation partnerRep) {
		if (partnerRep != null) {
			//set push to partner link  
			Link push = new Link(HTTPVerb.GET.toString(), Constant.BASE_PATH + "/orders/pushedOrders/", Constant.BASE_PATH_CONSUMER + "/pushedOrders", Constant.MEDIA_TYPE_XML );
			//add product for partner link
			Link addProduct = new Link(HTTPVerb.POST.toString(), Constant.BASE_PATH + "/products", Constant.BASE_PATH_CONSUMER + "/addProduct", Constant.MEDIA_TYPE_XML);
			Link acknowledge = new Link(HTTPVerb.GET.toString(), Constant.BASE_PATH + "/orders/fulfilled", Constant.BASE_PATH_CONSUMER + "/fulfilledOrders", Constant.MEDIA_TYPE_XML);
			
			partnerRep.setLinks(push, acknowledge, addProduct);
		}
		
		return partnerRep;
	}
}
