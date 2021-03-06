package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
//import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import main.java.DAO.CustomerDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure(Constant.HIBERNATE_FILE_NAME);
	    
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    return configuration.buildSessionFactory(serviceRegistry);
	}
	
	public void add(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.flush();
	}

	public void delete(int customerId) {
		Session session = sessionFactory.openSession();
		Customer customer = find(customerId);
		customer.setActive(false);
		Transaction tx = session.beginTransaction();
		update(customer);
		tx.commit();
		session.flush();
	}

	public Customer find(int customerId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("id", customerId));
		List<Customer> customers = criteria.list();
		//Query query = session.createQuery("FROM customer WHERE customer.id=:customerId");
		//List<Customer> customers = (List<Customer>)query.list();
		session.getTransaction().commit();
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
		//Criteria criteria = session.createCriteria(Customer.class);
		Query query = session.createQuery("FROM Customer");
		List<Customer> customers = query.list();
		tx.commit();
		session.close();
		return customers;
	}

	@Override
	public Customer find(String login) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Customer.class);
//		Query query = session.createQuery("FROM Customer");
		criteria.add(Restrictions.eq("login", login));
		List<Customer> customers = criteria.list();
		//Query query = session.createQuery("FROM customer WHERE customer.id=:customerId");
		//List<Customer> customers = (List<Customer>)query.list();
		session.getTransaction().commit();
		session.close();
		
		Customer customer = null;
		if (customers != null && !customers.isEmpty()) {
			customer = customers.get(0);
		}
		
		return customer;
	}

}
