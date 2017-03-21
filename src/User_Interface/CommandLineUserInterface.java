package User_Interface;

import java.io.*;
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
	 dbManager = new DBManager();
	 
	System.out.println("ALEXANDRIA");
	System.out.println("**********");
	System.out.println("\n1.) Create tables.");
	System.out.println("2.) Add new entity.");
	System.out.println("3.) Delete entity.");
	System.out.println("4.) View entity.");
	System.out.println("5.) Modify entity.");
	System.out.println("6.) Exit.");
	System.out.print("\nOption: ");
	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	String read = console.readLine();
	int option = Integer.parseInt(read);
	switch(option){
	case 1: {
		
		break;}
     case 2: {
		
		break;}
     case 3: {
 		
 		break;}
     case 4: {
 		
 		break;}
	}
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
	
	public void addPatient() {
		// Builds a patient object.
		// I think we have to do an add method for each POJO.
	}
	
	

}
