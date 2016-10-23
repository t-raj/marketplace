package main.java.model.service.service;

import java.util.List;

import main.java.model.partner.partnerBean.PartnerBean;

public interface PartnerService {
	
	void add(PartnerBean partnerBean);
	
	PartnerBean get(int customerId);
	
	void update(PartnerBean partnerBean);
	
	void delete(long partnerId);
	
	List<PartnerBean> get();
	
	
}
