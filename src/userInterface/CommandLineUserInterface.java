package userInterface;

import java.sql.Connection;
import java.sql.DriverManager;

import jdbc.DBManager;

import pojos.*;

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
		dbManager = new DBManager();
	}
	
	public void addAuthor() {
		Author a = new Author();// Builds a patient object.
		// I think we have to do an add method for each POJO.
	}
	
	public void showAuthor () {
		
	}
	
	public void addBodyParts () {
		BodyParts bp = new BodyParts();
	}
	
	public void showBodyPart () {
		
	}
	
	public void addDevices () {
		Devices dv = new Devices();
	}
	
	public void showDevices () {
		
	}
	
	public void addDiseases () {
		Diseases ds = new Diseases();
	}
	
	public void showDiseases () {
		
	}
	
	public void addImages () {
		Images i = new Images();
	}
	
	public void showImages () {
		
	}
	
	public void addPaper () {
		Paper pp = new Paper();
	}
	
	public void showPaper () {
		
	}
	
	public void addProcedures () {
		Procedures pc = new Procedures();
	}
	
	public void showProcedures () {
		
	}
	
	public void addSymptoms () {
		Symptoms s = new Symptoms();
	}
	
	public void showSymptoms () {
		
	}
}