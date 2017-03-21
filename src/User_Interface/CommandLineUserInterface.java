package User_Interface;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommandLineUserInterface {
	Connection c;
	public static void main(String args[]) {
		// Create a connection object.
	 c = newConnection();
	 System.out.println("Database connection opened.");
		// Create DB_manager object.
		// Show "patients".
	}

	public Connection newConnection(){
		Connection c = null;
		try{
		Class.forName("org.sqlite.JDBC"); 
		 c = DriverManager.getConnection("jdbc:sqlite:./db/company.db"); //Indicates the technology and location of the database.
		c.createStatement().execute("PRAGMA foreign_keys=ON"); //Enables the support for foreign key constraints.
		}  catch (Exception e) {
			e.printStackTrace();
		}finally {return c;}
		
	}
	
	public void addPatient() {
		// Builds a patient object.
		// I think we have to do an add method for each POJO.
	}
	
	

}
