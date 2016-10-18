package main.java.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;

import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.service.service.PartnerService;

//for testing purposes
public class PartnerEndpoint {

	@Autowired(required=true)
	private static PartnerService partnerService;


}
