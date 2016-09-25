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

import database.entities.User;

public class DatabaseController 
{
	SessionFactory factory;
	Session sesja;
	Transaction transakcja;
	
	
    public DatabaseController() {
    	initialize();
	}

    public void initialize(){
    	Configuration configuration = new Configuration().configure();
    	factory = configuration.buildSessionFactory();
    	sesja = factory.openSession();
    	transakcja = sesja.beginTransaction();
    }
    
    public void closeConnections(){
    	sesja.close();
    	factory.close();
    }
    
    public void saveEntityToDatabase(User userToSave) throws RuntimeException{
    	sesja.persist(userToSave);
    	transakcja.commit();
    	closeConnections();
    }
    
	public static void main( String[] args )
    {
	//main function only for test purposes
    	DatabaseController db = new DatabaseController();
    	DateFormat myDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    	Date data = null;
		try {
			data = myDateFormatter.parse("22-03-1994");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	User us = new User("ktos", "jakis", data, "f", 177, 66.8f, 77.2f, 10, "m");
    	db.saveEntityToDatabase(us);
    	
        System.out.println( "Success!" );
    }
}
