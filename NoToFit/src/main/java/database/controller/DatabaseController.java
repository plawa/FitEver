package database.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import database.entities.Entity;
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
    
	public static void main(String[] args){
		//main function only for test purposes
    	DatabaseController db = new DatabaseController();
    	DatabaseController db2 = new DatabaseController();
    	DateFormat myDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	Date data = null;
		try {
			data = myDateFormatter.parse("12-03-1994");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	User us = new User("ktdsaos", "jakis", data, "f", 177, 66.8f, 77.2f, 10, "m");
    	db.saveEntityToDatabase(us);
    	
        System.out.println( "Success!" );
    }
}
