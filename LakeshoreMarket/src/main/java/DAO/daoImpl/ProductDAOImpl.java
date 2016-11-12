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

import main.java.DAO.ProductDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Partner;
import main.java.model.entity.Product;

public class ProductDAOImpl implements ProductDAO{
	
	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));
	
	private SessionFactory buildSessionFactory(Configuration configure) {
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties()).buildServiceRegistry();
			sessionFactory = configure.buildSessionFactory(serviceRegistry);
			return sessionFactory;
	}

	@Override
	public void add(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
		session.flush();
		tx.commit();
		
	}

	
	@Override
	public void delete(long productId) {
		Session session = sessionFactory.openSession();
		Product product = find(productId);
		product.setActive(false);
		Transaction tx = session.beginTransaction();
		update(product);
		tx.commit();
		session.flush();
	}

	@Override
	public Product find(long productId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Partner.class);
		criteria.add(Restrictions.eq("id", productId));
		List<Product> products = criteria.list();
		tx.commit();
		session.close();
		
		Product product = null;
		if (products != null && !products.isEmpty()) {
			product = products.get(0);
		}
		
		return product;
	}
	
	@Override
	public void update(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
		session.flush();
	}

}
