package main.java.DAO;

import java.util.List;

import main.java.model.entity.Partner;

public interface PartnerDAO {

    void add(Partner partner);
	
    void delete(long partnerId);
    
    Partner find(long partnerId);
    
    void update(Partner partner);
    
    List<Partner> find();
}
