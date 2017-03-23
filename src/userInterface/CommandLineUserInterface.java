package userInterface;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import jdbc.DBManager;
import pojos.Author;
import pojos.BodyParts;
import pojos.Devices;
import pojos.Diseases;
import pojos.Images;
import pojos.Paper;
import pojos.Procedures;
import pojos.Symptoms;

public class CommandLineUserInterface {
	static Connection c = null;
	static DBManager dbManager = null;
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	static String read = null;
	
	public static void main(String args[]) {
		// Create a connection object.
	 newConnection();
		// Create DB_manager object.
	 dbManager = new DBManager();
	 
	 while(true){showMenu();}
	
	}
	
	private static void showMenu() {
		System.out.println("ALEXANDRIA");
		System.out.println("**********");
		System.out.println("\n1.) Create tables.");
		System.out.println("2.) Add new item.");
		System.out.println("3.) Delete item.");
		System.out.println("4.) View item.");
		System.out.println("5.) Modify item.");
		System.out.println("6.) Exit.");
		System.out.print("\nOption: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			System.out.print("AN ERROR OCURRED READING THE DATA.");
			e.printStackTrace();
		}
		int option = Integer.parseInt(read);
		switch(option){
		case 1: {
			newTable();
			break;}
	     case 2: {
			newEntity();
			break;}
	     case 3: {
	 		
	 		break;}
	     case 4: {
	 		
	 		break;}
		}
		}
		
	
	
	private static void newEntity() {
		System.out.print("\nPlease, select the type of item you want to create: ");
		System.out.println("\n1.) Author");
		System.out.println("2.) Body part");
		System.out.println("3.) Device");
		System.out.println("4.) Disease or pathology");
		System.out.println("5.) Image");
		System.out.println("6.) Paper or article");
		System.out.println("7.) Procedure or treatment");
		System.out.println("8.) Symptom");
		System.out.println("9.) Return to the main menu...");
		
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Integer option = Integer.parseInt(read);
        switch(option){
        case 1: {
			addAuthor();
			break;}
	     case 2: {
	    	 addBodyParts();
			break;}
	     case 3: {
	    	 addDevices();
	 		break;}
	     case 4: {
	    	 addDiseases();
	 		break;}
	     case 5: {
	    	 addImages();
	 		break;}
	     case 6: {
	    	 addPaper();
	 		break;}
	     case 7:{
	    	 addProcedures();
	    	 break;
	     }
	     case 8:{
	    	 addSymptoms();
	    	 break;
	     }
	     case 9: {
	 		showMenu();
	 		break;}
        }
		
	}

	private static void newTable() {
		// TODO Auto-generated method stub
		
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
