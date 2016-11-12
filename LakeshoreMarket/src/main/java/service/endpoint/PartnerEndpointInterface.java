package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@WebService
public interface PartnerEndpointInterface {

	Response registerPartner(int partnerID, String login, String password, String firstName, String lastName,
			String streetAddress, String city, String state, int zip);

}