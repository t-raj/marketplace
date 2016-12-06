package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import main.java.DAO.OrderDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.Order;
import main.java.service.service.OrderService.Status;

public class OrderDAOImpl implements OrderDAO {

	private SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure(Constant.HIBERNATE_FILE_NAME);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * This method adds an order to the database 
	 */
	@Override
	public int add(Order order) {	
		// TODO: hack to auto-generate order id. Should auto increment in database.
		int currentMaxId = 0;
		
		try {
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			DetachedCriteria criteria1 = DetachedCriteria.forClass(Order.class).setProjection(Projections.max("id"));
			Integer maxOrderId = (Integer) criteria1.getExecutableCriteria(session).list().get(0);
			currentMaxId = maxOrderId + 1;
			// give the order an id
			order.setId(currentMaxId); 
			session.saveOrUpdate(order);
			tx.commit();
			session.flush();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		
		return currentMaxId;
	}

	/**
	 * This method sets the status of an order to canceled
	 */
	@Override
	public void delete(int orderId) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Order.class);
			criteria.add(Restrictions.eq("id", orderId));
			List<Order> orders = (List<Order>)criteria.list();
			if (orders != null && !orders.isEmpty()) {
				Order order = orders.get(0);
				if (order != null) {
					order.setStatus(Status.CANCELED.toString());
					session.saveOrUpdate(order);
				}

			}
		
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method retrieves an order from the database 
	 * @param orderId
	 */
	@Override
	public Order get(int orderId) {
		Order order = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Order.class);
			criteria.add(Restrictions.eq("id", orderId));
			List<Order> orders = (List<Order>)criteria.list();
			if (orders != null && !orders.isEmpty()) {
				order = orders.get(0);
			}
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return order;
	}

	/**
	 * This method retrieves all orders from the database that meet the status(es) given. If no status given, then returns all orders. 
	 */
	@Override
	public List<Order> get(List<Status> statuses) {
		List<Order> orders = null;
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			if (statuses != null && !statuses.isEmpty()) {
				// add the status restriction(s)
				for (Status status : statuses) {
					criteria.add(Restrictions.eq("status", status.toString()));
				}
			}
			orders = criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orders;
	}

	/**
	 * This method updates an order
	 */
	@Override
	public void update(int orderId, Status status) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			// in hibernate, the same order referenced object must be modified in order to be saved 
			Criteria criteria = session.createCriteria(Order.class);
			criteria.add(Restrictions.eq("id", orderId));
			List<Order> orders = (List<Order>)criteria.list();
			if (orders != null && !orders.isEmpty()) {
				Order order = orders.get(0);
				if (order != null && status != null) {
					order.setStatus(status.toString());
					session.saveOrUpdate(order);
				}

			}

			tx.commit();
			session.flush();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method gets the orders with a certain status and partnerId
	 */
	@Override
	public List<Order> get(Status status, int partnerId) {
		List<Order> orders = null;
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			if (status != null) {
				criteria.add(Restrictions.eq("status", status.toString()));
			}
			criteria.add(Restrictions.eq("partner_id", partnerId));
			orders = criteria.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();			
		}
		return orders;
	}

	@Override
	public List<Order> get(Status status) {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			if (status != null) {
				criteria.add(Restrictions.eq("status", status.toString()));
			}
			orders = criteria.list();
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> getByCustomer(int customerId) {
		List<Order> orders = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Order.class);
			criteria.add(Restrictions.eq("customer_id", customerId));
			orders = criteria.list();
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orders;
	}



}
