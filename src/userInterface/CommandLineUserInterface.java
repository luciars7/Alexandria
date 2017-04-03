package userInterface;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import jdbc.DBManager;
import pojos.*;

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
		showMenu();
	}
	
	private static  void showMenu() {
		System.out.println("ALEXANDRIA");
		System.out.println("***********************");
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
			newTables();
			break;}
	     case 2: {
			newEntity();
			break;}
	     case 3: {
	 		
	 		break;}
	     case 4: {
	 		
	 		break;}
		}
		return;
		}
		
	
	
	private static  void newEntity() {
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
		System.out.print("\nOption: ");
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
	    	 //addBodyParts();
			break;}
	     case 3: {
	    	 //addDevices();
	 		break;}
	     case 4: {
	    	 //addDiseases();
	 		break;}
	     case 5: {
	    	 //addImages();
	 		break;}
	     case 6: {
	    	 //addPaper();
	 		break;}
	     case 7:{
	    	 //addProcedures();
	    	 break;
	     }
	     case 8:{
	    	 //addSymptoms();
	    	 break;
	     }
	     case 9: {
///	 		return;
	 		break;}
        }
		
	}

	private static void newTables() {
		dbManager = new DBManager();
		dbManager.createTables();
		return;
	}

	public static void newConnection(){
	    c = null;
		try{
		Class.forName("org.sqlite.JDBC"); 
		c = DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db"); //Indicates the technology and location of the database.
		c.createStatement().execute("PRAGMA foreign_keys=ON"); //Enables the support for foreign key constraints.
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//These methods should connect to the DBManager
	public static void addAuthor() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Nationality: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String origin=read;
		System.out.print("Association: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String association=read;
		dbManager.insertIntoAuthors(name, origin, association);
	}
	
	public void showAuthor () {
		read=null;
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbManager = new DBManager();
		ArrayList<Author> list = dbManager.selectAuthor(read);
		if(list==null){System.out.println("Error searching for the author(s).");}
		else{
			System.out.println(list);
		}
		
	}
	
	public void addBodyParts () {
		//BodyParts bp = new BodyParts();
	}
	
	public void showBodyPart () {
		
	}
	
	public void addDevices () {
		//Devices dv = new Devices();
	}
	
	public void showDevices () {
		
	}
	
	public void addDiseases () {
		//Diseases ds = new Diseases();
	}
	
	public void showDiseases () {
		
	}
	
	public void addImages () {
		//Images i = new Images();
	}
	
	public void showImages () {
		
	}
	
	public void addPaper () {
		//Paper pp = new Paper(); 
	}
	
	public void showPaper () {
		
	}
	
	public void addProcedures () {
		//Procedures pc = new Procedures();
	}
	
	public void showProcedures () {
		
	}
	
	public void addSymptoms () {
	//	Symptoms s = new Symptoms();
	}
	
	public void showSymptoms () {
		
	}
	
	

}
