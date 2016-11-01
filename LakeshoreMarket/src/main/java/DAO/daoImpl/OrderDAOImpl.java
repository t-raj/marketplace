package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import main.java.DAO.OrderDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Customer;
import main.java.model.entity.Order;

public class OrderDAOImpl implements OrderDAO {
	
	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));

	private SessionFactory buildSessionFactory(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	@Override
	public void add(Order order) {	
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List result = session.createQuery("FROM Order").list();
			session.saveOrUpdate(order);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long orderId) {
		try {
			Session session = sessionFactory.openSession();
			Order order = get(orderId);
			order.setStatus("Deleted");
			Transaction tx = session.beginTransaction();
			update(order);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order get(long orderId) {
		return (Order) sessionFactory.
			      getCurrentSession().
			      get(Order.class, orderId);
	}

	@Override
	public List<Order> get() {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Order.class);
			orders = criteria.list();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void update(Order order) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(order);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
