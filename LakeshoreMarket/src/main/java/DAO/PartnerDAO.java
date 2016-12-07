package main.java.DAO;

import java.util.List;

import main.java.model.entity.Partner;

public interface PartnerDAO {

	/**
	 * Add partner
	 * @param partner
	 */
    void add(Partner partner);
	
    /**
     * Delete partner
     * @param partnerId
     */
    void delete(int partnerId);
    
    /**
     * Find partner
     * @param partnerId
     * @return partner
     */
    Partner find(int partnerId);
    
    /**
     * Update partner
     * @param partner
     */
    void update(Partner partner);
    
    /**
     * Find partner
     * @return partner list
     */
    List<Partner> find();

	Partner find(String login);
}
