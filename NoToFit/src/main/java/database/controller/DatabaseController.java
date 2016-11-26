package database.controller;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.spi.ServiceException;

import database.entities.Entity;
import database.entities.Shadow;

public class DatabaseController {

	private static SessionFactory mySessionFactory;

	public static <T extends Entity> void saveEntityToDatabase(T entity) throws RuntimeException {
		getNewSessionWithTransaction().persist(entity);
		finalizeCurrentTransactionAndSession();
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
		String queryText = "SELECT s FROM Shadow s JOIN FETCH s.user WHERE s.login = :login";
		TypedQuery<Shadow> queryForShadow = getNewSessionWithTransaction().createQuery(queryText);
		queryForShadow.setParameter("login", login);

		List<Shadow> resultList = queryForShadow.getResultList();
		finalizeCurrentTransactionAndSession();
		if (resultList.size() == 1) {
			return resultList.get(0);
		}
		return null;
	}

	public static <T extends Entity> T getEntityByID(Class<T> entityType, int ID) throws RuntimeException {
		T resultEntity = getNewSessionWithTransaction().get(entityType, ID);
		finalizeCurrentTransactionAndSession();
		return resultEntity;
	}

	public static void refreshObject(Object objectToRefresh) {
		getNewSessionWithTransaction().refresh(objectToRefresh);
		finalizeCurrentTransactionAndSession();
	}

	public static <T extends Entity> List<T> getEntitiesByParameter(Class<T> entityType, String paramName,
			String paramValue) {
		String q = String.format("SELECT e FROM %s e WHERE e.%s = '%s'", entityType.getSimpleName(), paramName,
				paramValue);
		List<T> resultList = getNewSessionWithTransaction().createQuery(q).getResultList();
		finalizeCurrentTransactionAndSession();
		return resultList;
	}

	public static <T extends Entity> long getRowCount(Class<T> entityType){
		long resultCount = (long) getNewSessionWithTransaction().createCriteria(entityType).setProjection(Projections.rowCount()).uniqueResult();
		finalizeCurrentTransactionAndSession();
		return resultCount;
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
