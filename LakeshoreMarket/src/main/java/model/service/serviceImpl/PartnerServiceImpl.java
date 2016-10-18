package main.java.model.service.serviceImpl;

import java.util.List;

import main.java.DAO.PartnerDAO;
import main.java.DAO.daoImpl.PartnerDAOImpl;
import main.java.model.partner.partnerBean.PartnerBean;
import main.java.model.service.service.PartnerService;
import main.java.util.ElementUtil;

public class PartnerServiceImpl implements PartnerService {
//	@Autowired
	private PartnerDAO partnerDAO = new PartnerDAOImpl();
	
	public void delete(long partnerId) {
		partnerDAO.delete(partnerId);
	}

	public void add(PartnerBean partnerBean) {
		partnerDAO.add(ElementUtil.buildPartner(partnerBean));		
	}

	public void update(PartnerBean partnerBean) {
		partnerDAO.update(ElementUtil.buildPartner(partnerBean));
	}

	public List<PartnerBean> get() {
		return ElementUtil.buildPartnerBeanList(partnerDAO.find());
	}

	public PartnerBean get(int partnerId) {
		return ElementUtil.buildPartnerBean(partnerDAO.find(partnerId));
	}

}
