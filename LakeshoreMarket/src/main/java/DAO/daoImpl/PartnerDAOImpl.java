package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
		tx.commit();
		session.flush();
	}

	public void delete(int partnerId) {
		Session session = sessionFactory.openSession();
		Partner partner = find(partnerId);
		partner.setActive(false);
		Transaction tx = session.beginTransaction();
		update(partner);
		tx.commit();
		session.flush();
	}

	public Partner find(int partnerId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Partner.class);
		criteria.add(Restrictions.eq("id", partnerId));
		List<Partner> partners = criteria.list();
		tx.commit();
		session.close();
		
		Partner partner = null;
		if (partners != null && !partners.isEmpty()) {
			partner = partners.get(0);
		}
		
		return partner;
	}

	public void update(Partner partner) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(partner);
		tx.commit();
		session.flush();
	}
	
	public List<Partner> find() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Partner.class);
		List<Partner> partners = criteria.list();
		tx.commit();

		session.close();
		
		return partners;
	}

}
