package main.java.service.serviceImpl;

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
import main.java.model.bean.PartnerBean;
import main.java.model.bean.ProductBean;
import main.java.service.service.PartnerService;
import main.java.util.ElementUtil;

/**
 * This service layer class corresponds to the activity layer presented in class. 
 * @author lbo
 *
 */
public class PartnerServiceImpl implements PartnerService {
	
	private PartnerDAO partnerDAO = new PartnerDAOImpl();
	
	@Override
	public void register(PartnerBean partnerBean) {
		
		partnerDAO.add(ElementUtil.buildPartner(partnerBean));		
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
