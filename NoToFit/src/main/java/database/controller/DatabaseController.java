package database.controller;

import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.spi.ServiceException;
import org.hibernate.type.IntegerType;

import database.entities.Entity;
import database.entities.Shadow;
import database.entities.User;
import database.entities.Weighthistory;

public class DatabaseController {

	private static final String EXCEPTION_NO_ROWS_IN_WEIGHT_HISTORY = "The user hasn't got any entries in WeightHistory table!";
	
	private static SessionFactory mySessionFactory;
	private static Configuration hibernateConfiguration;

	public static <T extends Entity> Object saveEntityToDatabase(T entity) throws RuntimeException {
		Session session = getNewSessionWithTransaction();
		try {
			session.persist(entity);
			commitCurrentTransactionAndCloseSession();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackCurrentTransactionAndCloseSession();
			throw e;
		}
		return entity.getId();
	}

	public static <T extends Entity> void updateEntityToDatabase(T entity) throws RuntimeException {
		getNewSessionWithTransaction().update(entity);
		commitCurrentTransactionAndCloseSession();
	}

	public static <T extends Entity> void deleteEntityFromDatabase(T entity) throws RuntimeException {
		getNewSessionWithTransaction().delete(entity);
		commitCurrentTransactionAndCloseSession();
	}

	public static <T extends Entity> List<T> getAll(Class<T> entityType) throws RuntimeException {
		List<T> resultList = getNewSessionWithTransaction().createCriteria(entityType).list();
		commitCurrentTransactionAndCloseSession();
		return resultList;
	}

	public static Shadow getShadowEntityByLogin(String login) throws RuntimeException {
		Shadow result = (Shadow) getNewSessionWithTransaction().createCriteria(Shadow.class)
				.add(Restrictions.eq("login", login)).setFetchMode("user", FetchMode.JOIN).uniqueResult();

		commitCurrentTransactionAndCloseSession();
		return result;
	}

	public static <T extends Entity> T getEntityByID(Class<T> entityType, int ID) throws RuntimeException {
		T resultEntity = getNewSessionWithTransaction().get(entityType, ID);
		commitCurrentTransactionAndCloseSession();
		return resultEntity;
	}

	public static float getUserWeight(User user, boolean getLatest) {
		if (getRowCount(Weighthistory.class) == 0) {
			throw new IllegalArgumentException(EXCEPTION_NO_ROWS_IN_WEIGHT_HISTORY);
		}

		Session session = getNewSessionWithTransaction();
		DetachedCriteria maxQuery = DetachedCriteria.forClass(Weighthistory.class);
		maxQuery.add(Restrictions.eq("id.user.id", user.getId()));
		maxQuery.setProjection(getLatest ? Projections.max("id.date") : Projections.min("id.date"));

		Criteria query = session.createCriteria(Weighthistory.class);
		query.add(Restrictions.eq("id.user.id", user.getId()));
		query.add(Property.forName("id.date").eq(maxQuery));
		query.setProjection(Projections.property("weight"));
		query.setMaxResults(1);
		float actualWeight = (float) query.uniqueResult();

		commitCurrentTransactionAndCloseSession();
		return actualWeight;
	}

	public static <T extends Entity> List<T> getEntitiesByParameter(Class<T> clazz, String paramName,
			Object paramValue) {
		Session session = getNewSessionWithTransaction();
		List<T> resultList = session.createCriteria(clazz).add(Restrictions.eq(paramName, paramValue)).list();
		commitCurrentTransactionAndCloseSession();
		return resultList;
	}

	public static <T extends Entity> long getRowCount(Class<T> entityType) {
		long resultCount = (long) getNewSessionWithTransaction().createCriteria(entityType)
				.setProjection(Projections.rowCount()).uniqueResult();
		commitCurrentTransactionAndCloseSession();
		return resultCount;
	}

	public static <T extends Entity> List<T> getWeightHistoryOfMonth(int userID, int monthNumber) {
		Session session = getNewSessionWithTransaction();
		List<T> resultList = session.createCriteria(Weighthistory.class).add(Restrictions.eq("id.user.id", userID))
				.add(Restrictions.sqlRestriction("MONTH(date) = ?", monthNumber, new IntegerType())).list();
		commitCurrentTransactionAndCloseSession();
		return resultList;
	}

	public static Session getNewSessionWithTransaction() throws RuntimeException {
		if (mySessionFactory == null) {
			tryToInitializeSessionFactory();
		}
		Session mySession = mySessionFactory.getCurrentSession();
		mySession.beginTransaction();
		return mySession;
	}

	public static void tryToInitializeSessionFactory() throws ServiceException {
		if (hibernateConfiguration == null) {
			hibernateConfiguration = new Configuration().configure();
		}
		mySessionFactory = hibernateConfiguration.buildSessionFactory();
	}

	public static void commitCurrentTransactionAndCloseSession() {
		mySessionFactory.getCurrentSession().getTransaction().commit();
		mySessionFactory.getCurrentSession().close();
	}

	public static void rollbackCurrentTransactionAndCloseSession() {
		mySessionFactory.getCurrentSession().getTransaction().rollback();
		mySessionFactory.getCurrentSession().close();
	}

	public static void tidyUp() {
		if (mySessionFactory != null) {
			mySessionFactory.getCurrentSession().close();
			mySessionFactory.close();
		}
	}

	public static void setDatabaseConnectionConfiguration(String databaseUrl, String login, String password){
		mySessionFactory = null;
		hibernateConfiguration = new Configuration().configure();
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", databaseUrl);
		properties.setProperty("hibernate.connection.username", login);
		properties.setProperty("hibernate.connection.password", password);
		
		hibernateConfiguration.setProperties(properties);
	}
	
}
