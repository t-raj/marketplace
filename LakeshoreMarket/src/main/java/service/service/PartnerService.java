package main.java.service.service;

import java.util.List;

import main.java.model.bean.PartnerBean;

public interface PartnerService {
	
	/**
	 * Register partner
	 * @param partnerBean
	 */
	void register(PartnerBean partnerBean);
	
	/**
	 * Get partner
	 * @param customerId
	 * @return
	 */
	PartnerBean get(int customerId);
	
	/**
	 * Update partner
	 * @param partnerBean
	 */
	void update(PartnerBean partnerBean);
	
	/**
	 * Delete partner
	 * @param partnerId
	 */
	void delete(int partnerId);
	
	/**
	 * Get partner
	 * @return
	 */
	List<PartnerBean> get();

	PartnerBean get(String login);
	
	
}
