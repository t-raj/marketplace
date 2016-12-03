package main.java.DAO.daoImpl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import main.java.DAO.ProductDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Product;

public class ProductDAOImpl implements ProductDAO{

	private SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure(Constant.HIBERNATE_FILE_NAME);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public void add(Product product) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
		session.flush();
	}
	
	@Override
	public void delete(int productId) {
		Session session = sessionFactory.openSession();
		Product product = find(productId);
		product.setActive(false);
		Transaction tx = session.beginTransaction();
		update(product);
		tx.commit();
		session.flush();
	}

	@Override
	public Product find(int productId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Product.class);
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

	@Override
	public List<Product> findByPartner(int partnerId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("partner_id", partnerId));
		List<Product> products = criteria.list();
		tx.commit();
		session.close();
		
		return products;
	}

}
