package main.java.DAO.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import main.java.DAO.OrderLineDAO;
import main.java.model.constant.Constant;
import main.java.model.entity.OrderLine;

public class OrderLineDAOImpl implements OrderLineDAO {

	private SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure(Constant.HIBERNATE_FILE_NAME);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@Override
	public void add(List<OrderLine> orderLineList) {
		if (orderLineList != null && !orderLineList.isEmpty()) {
			for (OrderLine orderLine : orderLineList) {
				add(orderLine);
			}
		}
	}

	private void add(OrderLine orderLine) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(orderLine);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int orderLineId) {
		try {
			Session session = sessionFactory.openSession();
			OrderLine orderLine = get(orderLineId);
			Transaction tx = session.beginTransaction();
			update(orderLine);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(OrderLine orderLine) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(orderLine);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderLine get(int orderId) {
		OrderLine orderLine = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(OrderLine.class);
			criteria.add(Restrictions.eq("order_id", orderId));
			List<OrderLine> orderLines = (List<OrderLine>)criteria.list();
			if (orderLines != null && !orderLines.isEmpty()) {
				orderLine = orderLines.get(0);
			}
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return orderLine;
	}

	@Override
	public List<OrderLine> get() {
		List<OrderLine> orderLines = null;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(OrderLine.class);
			orderLines = criteria.list();
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return orderLines;
	}
	

}
