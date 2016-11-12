package main.java.service.endpoint;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import main.java.service.model.PartnerModel;


@WebService
public interface PartnerEndpointInterface {

	Response registerPartner(PartnerModel partnerModel);

}