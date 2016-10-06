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
    
	public <T extends Entity> void updateEntityToDatabase(T entity) {
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
    
    public <T extends Entity> List<T> getAll(Class<T> type) throws RuntimeException {
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	List<T> resultList = mySession.createCriteria(type).list();
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

    
	public static void main(String[] args){
		//main function only for test purposes
    	DatabaseController db = new DatabaseController();
    	/*DateFormat myDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	Date data = null;
		try {
			data = myDateFormatter.parse("12-03-1994");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Shadow sh = new Shadow();
		sh.setLogin("mojLogin");
		sh.setPass("md511111111111jdhs");
		
    	User us = new User();
    	us.setName("Marek");
    	us.setSurname("Kondrat");
    	us.setDateOfBirth(data);
    	us.setSex('m');
    	us.setHeight(170);
    	us.setStartWeight(60f);
    	us.setGoalWeight(70f);
    	
    	us.setShadow(sh);
    	sh.setUser(us);
    	
    	db.saveEntityToDatabase(sh);*/

    	Shadow cred = db.getShadowEntityByLogin("pidanciwo");
    	cred.getUser().setSurname("Jakowyż");
    	System.out.print(cred.getUser().getName());

    }

}
