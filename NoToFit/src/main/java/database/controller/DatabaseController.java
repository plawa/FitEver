package database.controller;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;

import database.entities.Entity;
import database.entities.Shadow;

public class DatabaseController 
{
	
	private static SessionFactory mySessionFactory = null;
	
    public DatabaseController() throws ServiceException {
    	if (mySessionFactory == null)
    		mySessionFactory = new Configuration().configure().buildSessionFactory();
	}
    public void tidyUp(){
    	mySessionFactory.close();
    }
    
    public void startTransaction(){
    	mySessionFactory.openSession().beginTransaction();
    }
    
    public void commitTransaction(){
    	mySessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    public <T extends Entity> void saveEntityToDatabase(T entity) throws RuntimeException{
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	mySession.persist(entity);
    	myTransaction.commit();
    	mySession.close();
    }
    
	public <T extends Entity> void updateEntityToDatabase(T entity) throws RuntimeException {
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	mySession.update(entity);
    	myTransaction.commit();
    	mySession.close();
	}
    public <T extends Entity> void deleteEntityFromDatabase(T entity) throws RuntimeException{
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	mySession.delete(entity);
    	myTransaction.commit();
    	mySession.close();
    }
    
    public <T extends Entity> List<T> getAll(Class<T> entityType) throws RuntimeException {
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	List<T> resultList = mySession.createCriteria(entityType).list();
    	myTransaction.commit();
    	mySession.close();
    	return resultList;
    }
    
    public Shadow getShadowEntityByLogin(String login) throws RuntimeException{
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	String queryText = "SELECT s FROM Shadow s JOIN FETCH s.user WHERE s.login = :login";
    	TypedQuery<Shadow> queryForShadow = mySession.createQuery(queryText);
    	queryForShadow.setParameter("login", login);
    	List<Shadow> resultList = queryForShadow.getResultList();//  list();
    	if (resultList.isEmpty())
    		return null;
    	Shadow result = resultList.get(0);
    	myTransaction.commit();
    	mySession.close();
    	return result;
    }

    public <T extends Entity> T getEntityByID(Class<T> entityType, int ID){
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	T entityFound = mySession.get(entityType, ID);
    	myTransaction.commit();
    	mySession.close();
    	return entityFound;
    }
    
    
	public static void main(String[] args){

    }

}
