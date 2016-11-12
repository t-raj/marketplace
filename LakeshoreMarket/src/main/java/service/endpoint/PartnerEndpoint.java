package main.java.service.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
<<<<<<< Upstream, based on branch 'master' of https://lboloyola@bitbucket.org/lboloyola/lakeshoremarket.git
import javax.ws.rs.Produces;
=======
>>>>>>> 6713b26 Refactored formatting on various classes
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.service.model.PartnerModel;
import main.java.service.service.PartnerService;
import main.java.service.serviceImpl.PartnerServiceImpl;
import main.java.util.ElementUtil;


/**
 * This endpoint layer class corresponds to the service layer presented in class. 
 * @author lbo
 *
 */
<<<<<<< Upstream, based on branch 'master' of https://lboloyola@bitbucket.org/lboloyola/lakeshoremarket.git

=======
>>>>>>> 6713b26 Refactored formatting on various classes
@Path("/Partner")
public class PartnerEndpoint implements PartnerEndpointInterface {

	private static PartnerService partnerService = new PartnerServiceImpl();
	
	@POST//2.1 Need to register and create profile of partners
	@Consumes({"application/xml"})
<<<<<<< Upstream, based on branch 'master' of https://lboloyola@bitbucket.org/lboloyola/lakeshoremarket.git
	
=======
>>>>>>> 6713b26 Refactored formatting on various classes
	public Response registerPartner(PartnerModel partnerModel){
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

}
