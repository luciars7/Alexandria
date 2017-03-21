package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_manager {
	Connection c = null;
	public void connect(){
	try {
		// Open database connection
		Class.forName("org.sqlite.JDBC"); 
	    c = DriverManager.getConnection("jdbc:sqlite:./db/company.db"); //Indicates the technology and location of the database.
		c.createStatement().execute("PRAGMA foreign_keys=ON"); //Enables the support for foreign key constraints.
		System.out.println("Database connection opened.");
		
		// Here is where I do things with the database
		
		// Close database connection
		c.close();
		System.out.println("Database connection closed.");
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
