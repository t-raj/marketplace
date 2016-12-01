package main.java.service.service;

import java.util.List;

import main.java.model.bean.PartnerBean;

public interface PartnerService {
	
	void register(PartnerBean partnerBean);
	
	PartnerBean get(int customerId);
	
	void update(PartnerBean partnerBean);
	
	void delete(int partnerId);
	
	List<PartnerBean> get();

	PartnerBean get(String login);
	
	
}
