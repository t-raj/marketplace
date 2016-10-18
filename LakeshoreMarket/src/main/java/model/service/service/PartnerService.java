package main.java.model.service.service;

import java.util.List;

import main.java.model.partner.partnerBean.PartnerBean;

public interface PartnerService {
	
	void add(PartnerBean partnerBean);

	void delete(long partnerId);

	void update(PartnerBean partnerBean);
	
	List<PartnerBean> get();
	
	PartnerBean get(int customerId);
}
