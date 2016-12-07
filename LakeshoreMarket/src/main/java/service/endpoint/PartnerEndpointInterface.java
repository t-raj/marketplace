package main.java.service.endpoint;

import javax.jws.WebService;

import main.java.service.representation.PartnerRepresentation;


@WebService
public interface PartnerEndpointInterface {

	/**
	 * Register partner
	 * @param partnerModel
	 * @return PartnerRepresentation
	 */
	PartnerRepresentation register(PartnerRepresentation partnerModel);
	
	/**
	 * Get partner
	 * @param login
	 * @return PartnerRepresentation
	 */
	PartnerRepresentation get(String login);
}