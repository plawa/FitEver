package database.controller;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import database.entities.Entity;
import database.entities.Shadow;

public class DatabaseController {

	private static SessionFactory mySessionFactory;
	
	public static <T extends Entity> void saveEntityToDatabase(T entity) throws RuntimeException {
		openSessionWithTransaction().persist(entity);
		finalizeTransactionAndSession();
	}

	public static <T extends Entity> void updateEntityToDatabase(T entity) throws RuntimeException {
		openSessionWithTransaction().update(entity);
		finalizeTransactionAndSession();
	}

	public static <T extends Entity> void deleteEntityFromDatabase(T entity) throws RuntimeException {
		openSessionWithTransaction().delete(entity);
		finalizeTransactionAndSession();
	}

	public static <T extends Entity> List<T> getAll(Class<T> entityType) throws RuntimeException {
		List<T> resultList = openSessionWithTransaction().createCriteria(entityType).list();
		finalizeTransactionAndSession();
		return resultList;
	}

	public static Shadow getShadowEntityByLogin(String login) throws RuntimeException {
		String queryText = "SELECT s FROM Shadow s JOIN FETCH s.user WHERE s.login = :login";
		TypedQuery<Shadow> queryForShadow = openSessionWithTransaction().createQuery(queryText);
		queryForShadow.setParameter("login", login);

		List<Shadow> resultList = queryForShadow.getResultList();
		finalizeTransactionAndSession();
		if (resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	public static <T extends Entity> T getEntityByID(Class<T> entityType, int ID) throws RuntimeException {
		T resultEntity = openSessionWithTransaction().get(entityType, ID);
		finalizeTransactionAndSession();
		return resultEntity;
	}

	public static void refreshObject(Object objectToRefresh) {
		openSessionWithTransaction().refresh(objectToRefresh);
		finalizeTransactionAndSession();
	}

	public static <T extends Entity> List<T> getEntitiesByParameter(Class<T> entityType, String paramName,
			String paramValue) {
		String q = String.format("SELECT e FROM %s e WHERE e.%s = '%s'", entityType.getSimpleName(), paramName,
				paramValue);
		List<T> resultList = openSessionWithTransaction().createQuery(q).getResultList();
		finalizeTransactionAndSession();
		return resultList;
	}

	private static Session openSessionWithTransaction() {
		if (mySessionFactory == null)
			tryToInitializeSessionFactory();
		Session mySession = mySessionFactory.getCurrentSession();
		mySession.beginTransaction();
		return mySession;
	}

	public static void tryToInitializeSessionFactory() {
		try {
			mySessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void finalizeTransactionAndSession() {
		mySessionFactory.getCurrentSession().getTransaction().commit();
		mySessionFactory.getCurrentSession().close();
	}

	public static void tidyUp() {
		mySessionFactory.getCurrentSession().close();
		mySessionFactory.close();
	}
}
