package main.java.DAO;

import java.util.List;

import main.java.model.entity.Partner;

public interface PartnerDAO {

    void add(Partner partner);
	
    void delete(int partnerId);
    
    Partner find(int partnerId);
    
    void update(Partner partner);
    
    List<Partner> find();
}
