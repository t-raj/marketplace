package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import main.java.DAO.CustomerDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Customer;

//@Service
public class CustomerDAOImpl implements CustomerDAO {

	//	@Autowired
	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));

	private SessionFactory buildSessionFactory(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	// used for now for testing 

	public void add(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		session.flush();
		tx.commit();
	}

	public void delete(long customerId) {
		Session session = sessionFactory.openSession();
		Customer customer = find(customerId);
		customer.setActive(false);
		Transaction tx = session.beginTransaction();
		update(customer);
		session.flush();
		tx.commit();
	}

	public Customer find(long customerId) {
		return (Customer) sessionFactory.
			      getCurrentSession().
			      get(Customer.class, customerId);
	}

	public void update(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		session.flush();
		tx.commit();
	}
	
	public List<Customer> find() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> customers = criteria.list();
		session.close();
		
		return customers;
	}
	


}
