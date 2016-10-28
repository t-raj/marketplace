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
import main.java.model.service.service.PartnerService;
import main.java.util.ElementUtil;

@Path("/PartnerService/")
public class PartnerServiceImpl implements PartnerService {
	
//	@Autowired
	private PartnerDAO partnerDAO = new PartnerDAOImpl();
	
	@Override
	public void add(PartnerBean partnerBean) {
		System.out.println("POST METHOD from Customer with ID:.........." + partnerBean.getId() +" and First Name: " 
				+ partnerBean.getFirstName() + "and Last Name: " + partnerBean.getLastName());
		partnerDAO.add(ElementUtil.buildPartner(partnerBean));		
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
