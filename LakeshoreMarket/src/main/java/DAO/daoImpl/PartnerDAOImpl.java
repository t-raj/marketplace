package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import main.java.DAO.PartnerDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Partner;

public class PartnerDAOImpl implements PartnerDAO {

	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));

	private SessionFactory buildSessionFactory(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public void add(Partner partner) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(partner);
		session.flush();
		tx.commit();
	}

	public void delete(long partnerId) {
		Session session = sessionFactory.openSession();
		Partner partner = find(partnerId);
		partner.setActive(false);
		Transaction tx = session.beginTransaction();
		update(partner);
		session.flush();
		tx.commit();
	}

	public Partner find(long partnerId) {
		return (Partner) sessionFactory.
			      getCurrentSession().
			      get(Partner.class, partnerId);
	}

	public void update(Partner partner) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(partner);
		session.flush();
		tx.commit();
	}
	
	public List<Partner> find() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Partner.class);
		List<Partner> partners = criteria.list();
		session.close();
		
		return partners;
	}

}
