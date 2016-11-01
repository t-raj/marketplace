package main.java.model.service.serviceImpl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import main.java.DAO.PartnerDAO;
import main.java.DAO.daoImpl.PartnerDAOImpl;
import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.product.productBean.ProductBean;
import main.java.model.service.service.PartnerService;
import main.java.util.ElementUtil;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class PartnerServiceImpl implements PartnerService {
	
	private PartnerDAO partnerDAO = new PartnerDAOImpl();
	
	@Override
	public void register(int partnerID, String login, String password, String firstName, String lastName, String streetAddress, String city, String state, int zip) {
		PartnerBean partner = new PartnerBean();
		partner.setId(partnerID);
		partner.setLogin(login);
		partner.setPassword(password);
		partner.setFirstName(firstName);
		partner.setFirstName(lastName);
		partner.setStreetAddress(streetAddress);
		partner.setCity(city);
		partner.setState(state);
		partner.setActive(true);
		partner.setZip_code(zip);
		System.out.println("partner " + partner.getFirstName() + " " + partner.getLastName() + " has been registered");
		partnerDAO.add(ElementUtil.buildPartner(partner));		
	}
	
	@Override
	public PartnerBean get(int partnerId) {
		return ElementUtil.buildPartnerBean(partnerDAO.find(partnerId));
	}

	@Override
	public void update(PartnerBean partnerBean) {
		partnerDAO.update(ElementUtil.buildPartner(partnerBean));
	}

	@Override
	public List<PartnerBean> get() {
		return ElementUtil.buildPartnerBeanList(partnerDAO.find());
	}

	@Override
	public void delete(long partnerId) {
		partnerDAO.delete(partnerId);
		
	}


}
