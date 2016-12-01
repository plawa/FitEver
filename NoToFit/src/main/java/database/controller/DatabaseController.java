package database.controller;

import java.util.List;

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

import database.entities.Entity;
import database.entities.Shadow;
import database.entities.User;
import database.entities.Weighthistory;

public class DatabaseController {

	private static final String ERR_NO_ROWS_IN_WEIGHT_HISTORY = "The user hasn't got any entries in WeightHistory table!";
	private static SessionFactory mySessionFactory;

	public static <T extends Entity> void saveEntityToDatabase(T entity) throws RuntimeException {
		Session session = getNewSessionWithTransaction();
		try {
			session.persist(entity);
			finalizeCurrentTransactionAndSession();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public static <T extends Entity> void updateEntityToDatabase(T entity) throws RuntimeException {
		getNewSessionWithTransaction().update(entity);
		finalizeCurrentTransactionAndSession();
	}

	public static <T extends Entity> void deleteEntityFromDatabase(T entity) throws RuntimeException {
		getNewSessionWithTransaction().delete(entity);
		finalizeCurrentTransactionAndSession();
	}

	public static <T extends Entity> List<T> getAll(Class<T> entityType) throws RuntimeException {
		List<T> resultList = getNewSessionWithTransaction().createCriteria(entityType).list();
		finalizeCurrentTransactionAndSession();
		return resultList;
	}

	public static Shadow getShadowEntityByLogin(String login) throws RuntimeException {
		Shadow result = (Shadow) getNewSessionWithTransaction().createCriteria(Shadow.class)
				.add(Restrictions.eq("login", login)).setFetchMode("user", FetchMode.JOIN).uniqueResult();

		finalizeCurrentTransactionAndSession();
		return result;
	}

	public static <T extends Entity> T getEntityByID(Class<T> entityType, int ID) throws RuntimeException {
		T resultEntity = getNewSessionWithTransaction().get(entityType, ID);
		finalizeCurrentTransactionAndSession();
		return resultEntity;
	}

	public static float getUserWeight(User user, boolean getLatest) {
		if (getRowCount(Weighthistory.class) == 0) {
			throw new IllegalArgumentException(ERR_NO_ROWS_IN_WEIGHT_HISTORY);
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

		finalizeCurrentTransactionAndSession();
		return actualWeight;
	}

	public static <T extends Entity> List<T> getEntitiesByParameter(Class<T> entityType, String paramName,
			String paramValue) {
		String q = String.format("SELECT e FROM %s e WHERE e.%s = '%s'", entityType.getSimpleName(), paramName,
				paramValue);
		List<T> resultList = getNewSessionWithTransaction().createQuery(q).getResultList();
		finalizeCurrentTransactionAndSession();
		return resultList;
	}

	public static <T extends Entity> T getEntityByUniqueParameter(Class<T> entityType, String paramName,
			String paramValue) {
		Session session = getNewSessionWithTransaction();
		T resultEntity = (T) session.createCriteria(entityType).add(Restrictions.eq(paramName, paramValue))
				.uniqueResult();
		finalizeCurrentTransactionAndSession();
		return resultEntity;
	}

	public static <T extends Entity> long getRowCount(Class<T> entityType) {
		long resultCount = (long) getNewSessionWithTransaction().createCriteria(entityType)
				.setProjection(Projections.rowCount()).uniqueResult();
		finalizeCurrentTransactionAndSession();
		return resultCount;
	}

	public static <T extends Entity> List<T> getWeightHistoryByMonth(int userID, int month) {
		String q = String.format("SELECT e FROM Weighthistory e WHERE e.id.user = '%d' AND MONTH(e.id.date) = '%d'",
				userID, month);
		List<T> resultList = getNewSessionWithTransaction().createQuery(q).getResultList();
		finalizeCurrentTransactionAndSession();
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
		mySessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static void finalizeCurrentTransactionAndSession() {
		mySessionFactory.getCurrentSession().getTransaction().commit();
		mySessionFactory.getCurrentSession().close();
	}

	public static void tidyUp() {
		if (mySessionFactory != null) {
			mySessionFactory.getCurrentSession().close();
			mySessionFactory.close();
		}
	}

}
