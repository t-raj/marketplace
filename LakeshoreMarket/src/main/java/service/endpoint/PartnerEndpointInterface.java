package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.service.model.PartnerModel;


@WebService
public interface PartnerEndpointInterface {

	Response registerPartner(PartnerModel partnerModel);

}