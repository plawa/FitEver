package database.controller;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import database.entities.User;

public class DatabaseController 
{
	//private final String PERSISTENCE_NAME = "notofit";
	//private static EntityManagerFactory myEntityManagerFactory;
	//private static EntityManager myEntityManager;
	SessionFactory factory;
	Session sesja;
	Transaction transakcja;
	
	
    public DatabaseController() {
    	initialize();
	}

    public void initialize(){

    	Configuration configuration = new Configuration().configure();
    	//configuration.addClass(database.entities.User.class);
    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
    	applySettings(configuration.getProperties());
    	factory = configuration.buildSessionFactory(builder.build());
    	sesja = factory.openSession();
    	transakcja = sesja.beginTransaction();
    }
    
    public void closeConnections(){
    	sesja.close();
    	factory.close();
    }
    
    public void saveEntityToDatabase(User userToSave) throws RuntimeException{
    	//sesja.persist(userToSave);
    	sesja.save(userToSave);
    	transakcja.commit();
    	closeConnections();
    }
    
	public static void main( String[] args )
    {
    	DatabaseController db = new DatabaseController();
    	//Date data = new Date(22, 02, 1994);
    	Date data = new Date(321);
    	
    	User us = new User("ktos", "jakis", data, "f", 177, 66.8f, 77.2f, 10, "m");
    	db.saveEntityToDatabase(us);
    	
        System.out.println( "Hello World!" );
    }
}
