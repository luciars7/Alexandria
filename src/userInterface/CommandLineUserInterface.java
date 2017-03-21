package userInterface;

import java.sql.Connection;
import java.sql.DriverManager;

import jdbc.DBManager;

public class CommandLineUserInterface {
	static Connection c = null;
	static DBManager dbManager = null;
	
	public static void main(String args[]) {
		// Create a connection object.
	 newConnection();
		// Create DB_manager object.
	 newDBManager();
		// Show "patients".
	}

	public static void newConnection(){
	    c = null;
		try{
		Class.forName("org.sqlite.JDBC"); 
		 c = DriverManager.getConnection("jdbc:sqlite:./db/company.db"); //Indicates the technology and location of the database.
		c.createStatement().execute("PRAGMA foreign_keys=ON"); //Enables the support for foreign key constraints.
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newDBManager(){
		this.dbManager = new dbManager;
	}
	
	public void addPatient() {
		// Builds a patient object.
		// I think we have to do an add method for each POJO.
	}
	
	

}
