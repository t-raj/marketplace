package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.PartnerRepresentation;


@WebService
public interface PartnerEndpointInterface {

	PartnerRepresentation registerPartner(PartnerRepresentation partnerModel);
	PartnerRepresentation get(String login);
}