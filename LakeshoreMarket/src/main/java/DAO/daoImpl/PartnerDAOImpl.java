package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import main.java.DAO.PartnerDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Partner;

public class PartnerDAOImpl implements PartnerDAO {

	private SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure(Constant.HIBERNATE_FILE_NAME);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public void add(Partner partner) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		session.saveOrUpdate(partner);
//		tx.commit();
//		session.flush();
	}

	public void delete(int partnerId) {
//		Session session = sessionFactory.openSession();
//		Partner partner = find(partnerId);
//		partner.setActive(false);
//		Transaction tx = session.beginTransaction();
//		update(partner);
//		tx.commit();
//		session.flush();
	}

	public Partner find(int partnerId) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(Partner.class);
//		criteria.add(Restrictions.eq("id", partnerId));
//		List<Partner> partners = criteria.list();
//		tx.commit();
//		session.close();
//		
//		Partner partner = null;
//		if (partners != null && !partners.isEmpty()) {
//			partner = partners.get(0);
//		}
//		
//		return partner;

		return null;
	}

	public void update(Partner partner) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		session.saveOrUpdate(partner);
//		tx.commit();
//		session.flush();
	}
	
	public List<Partner> find() {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(Partner.class);
//		List<Partner> partners = criteria.list();
//		tx.commit();
//
//		session.close();
//		
//		return partners;
		return null;
	}

}
