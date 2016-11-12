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

import main.java.DAO.CustomerDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));

	private SessionFactory buildSessionFactory(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public void add(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.flush();
	}

	public void delete(long customerId) {
		Session session = sessionFactory.openSession();
		Customer customer = find(customerId);
		customer.setActive(false);
		Transaction tx = session.beginTransaction();
		update(customer);
		tx.commit();
		session.flush();
	}

	public Customer find(long customerId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("id", customerId));
		List<Customer> customers = criteria.list();
		tx.commit();
		session.close();
		
		Customer customer = null;
		if (customers != null && !customers.isEmpty()) {
			customer = customers.get(0);
		}
		
		return customer;
	}

	public void update(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.flush();
	}
	
	public List<Customer> find() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> customers = criteria.list();
		tx.commit();
		session.close();
		return customers;
	}

}
