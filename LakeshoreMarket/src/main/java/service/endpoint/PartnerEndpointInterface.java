package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.representation.PartnerRepresentation;


@WebService
public interface PartnerEndpointInterface {

	Response registerPartner(PartnerRepresentation partnerModel);

}