package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import main.java.DAO.OrderDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Customer;
import main.java.model.entity.Order;
import main.java.service.service.OrderService.Status;

public class OrderDAOImpl implements OrderDAO {

	private SessionFactory sessionFactory = buildSessionFactory(new Configuration().configure(Constant.HIBERNATE_FILE_NAME));

	private SessionFactory buildSessionFactory(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	/**
	 * This method adds an order to the database 
	 */
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

	/**
	 * This method sets the status of an order to canceled
	 */
	@Override
	public void delete(long orderId) {
		try {
			Session session = sessionFactory.openSession();
			Order order = get(orderId);
			order.setStatus(Status.CANCELED.toString());
			Transaction tx = session.beginTransaction();
			update(order);
			session.flush();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method retrieves an order from the database 
	 * @param orderId
	 */
	@Override
	public Order get(long orderId) {
		return (Order) sessionFactory.
				getCurrentSession().
				get(Order.class, orderId);
	}

	/**
	 * This method retrieves all orders from the database that meet the status(es) given. If no status given, then returns all orders. 
	 */
	@Override
	public List<Order> get(List<Status> statuses) {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Order.class);
			if (statuses != null && !statuses.isEmpty()) {
				// add the status restriction(s)
				for (Status status : statuses) {
					criteria.add(Restrictions.eq("status", status.toString()));
				}
			}
			orders = criteria.list();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * This method updates an order
	 */
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

	/**
	 * This method gets the orders with a certain status and partnerId
	 */
	@Override
	public List<Order> get(Status status, long partnerId) {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Order.class);
			if (status != null) {
				criteria.add(Restrictions.eq("status", status.toString()));
			}
			criteria.add(Restrictions.eq("partner_id", partnerId));
			orders = criteria.list();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> get(Status status) {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Order.class);
			if (status != null) {
				criteria.add(Restrictions.eq("status", status.toString()));
			}
			orders = criteria.list();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}

}
