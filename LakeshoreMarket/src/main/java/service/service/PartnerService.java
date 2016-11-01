package main.java.service.service;

import java.util.List;

import main.java.model.bean.PartnerBean;

public interface PartnerService {
	
	void register(int partnerID, String login, String password, String firstName, String lastName, String streetAddress, String city, String state, int zip);
	
	PartnerBean get(int customerId);
	
	void update(PartnerBean partnerBean);
	
	void delete(long partnerId);
	
	List<PartnerBean> get();
	
	
}
