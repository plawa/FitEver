package database.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import database.entities.Entity;
import database.entities.Shadow;
import database.entities.User;

public class DatabaseController 
{
	private static SessionFactory mySessionFactory = null;
	
    public DatabaseController() {
    	if (mySessionFactory == null)
    		mySessionFactory = new Configuration().configure().buildSessionFactory();
	}
    public void tidyUp(){
    	mySessionFactory.close();
    }
    
    public <T extends Entity> void saveEntityToDatabase(T entity) throws RuntimeException{
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	mySession.persist(entity);
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
    
    public <T extends Entity> T getEntityByID(Class<T> type, int ID) throws RuntimeException{
    	Session mySession = mySessionFactory.openSession();
    	Transaction myTransaction = mySession.beginTransaction();
    	T result = mySession.get(type, ID);
    	myTransaction.commit();
    	mySession.close();
    	return result;
    }
    
	public static void main(String[] args){
		//main function only for test purposes
    	DatabaseController db = new DatabaseController();
    	DateFormat myDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	Date data = null;
		try {
			data = myDateFormatter.parse("12-03-1994");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Shadow sh = new Shadow();
		sh.setLogin("mojLogin");
		sh.setPass("md51111111111111111111111111jdhs");
		
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
    	
    	db.saveEntityToDatabase(us);
    	db.saveEntityToDatabase(sh);

    	/*for (Shadow sh : db.getAll(Shadow.class)){
    		System.out.println(sh.getLogin());
    	}*/
    	
    	//db.getEntityByID(User.class, )
    	
        System.out.println( "Success!" );
    }
}
