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

@Path("/PartnerService/")
public class PartnerServiceImpl implements PartnerService {
	
//	@Autowired
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
	public PartnerBean get(@PathParam("id")int partnerId) {
		System.out.println("GET METHOD from partner with ID: ......" + partnerId);
		return ElementUtil.buildPartnerBean(partnerDAO.find(partnerId));
	}

	@Override
	public void update(PartnerBean partnerBean) {
		System.out.println("POST METHOD from Customer with ID:......" + partnerBean.getId());
		partnerDAO.update(ElementUtil.buildPartner(partnerBean));
	}

	@Override
	public List<PartnerBean> get() {
		System.out.println("POST METHOD for all partners.......");
		return ElementUtil.buildPartnerBeanList(partnerDAO.find());
	}

	@Override
	public void delete(@PathParam("id")long partnerId) {
		partnerDAO.delete(partnerId);
		
	}


}
