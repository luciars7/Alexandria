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
		System.out.println("\n\n\n\nALEXANDRIA");
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
	 		deleteEntity();
	 		break;}
	     case 4: {
	 		showEntity();
	 		break;}
		}
		showMenu();
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
			return;
			}
	     case 2: {
	    	 addBodyParts();
	    	 return;}
	     case 3: {
	    	 addDevices();
	    	 return;}
	     case 4: {
	    	 addDiseases();
	    	 return;}
	     case 5: {
	    	 addImages();
	    	 return;}
	     case 6: {
	    	 addPaper();
	    	 return;}
	     case 7:{
	    	 addProcedures();
	    	 return;
	     }
	     case 8:{
	    	 addSymptoms();
	    	 return;
	     }
	     case 9: {
             return;
	 		}
        }
		
	}
	
	public static void showEntity(){
		System.out.print("\nPlease, select the type of item you want to view: ");
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
			showAuthor();   
			return;
			}
        //Change methods to the show ones. Maybe in another class.
	     case 2: {
	    	 showBodyPart();
	    	 return;}
	     case 3: {
	    	 addDevices();
	    	 return;}
	     case 4: {
	    	 addDiseases();
	    	 return;}
	     case 5: {
	    	 addImages();
	    	 return;}
	     case 6: {
	    	 addPaper();
	    	 return;}
	     case 7:{
	    	 addProcedures();
	    	 return;
	     }
	     case 8:{
	    	 addSymptoms();
	    	 return;
	     }
	     case 9: {
             return;
	 		}
        }
		
	}

	private static  void deleteEntity() {
		System.out.print("\nPlease, select the type of item you want to delete: ");
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
			deleteAuthor();   
			return;
			}
	     case 2: {
	    	 addBodyParts();
	    	 return;}
	     case 3: {
	    	 addDevices();
	    	 return;}
	     case 4: {
	    	 addDiseases();
	    	 return;}
	     case 5: {
	    	 addImages();
	    	 return;}
	     case 6: {
	    	 addPaper();
	    	 return;}
	     case 7:{
	    	 addProcedures();
	    	 return;
	     }
	     case 8:{
	    	 addSymptoms();
	    	 return;
	     }
	     case 9: {
             return;
	 		}
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
		dbManager.insertIntoAuthor(name, origin, association);
		System.out.println("Author inserted correctly.");
	}
	
	public static void showAuthor () {
	System.out.print("Please, provide a name or write �all� to view every author: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		ArrayList<Author> list = dbManager.selectAuthor(name);
		if(list==null){System.out.println("Error searching for the author(s).");}
		else{
			for(Author author:list){
				System.out.println(author);
			}
		}
		
	}
	
	public static void addBodyParts () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Location: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String location=read;
		dbManager.insertIntoBodyPart(name, location);
		System.out.println("Body part inserted correctly.");
	}
	
	public static void showBodyPart () {
		System.out.print("Please, provide a name or write �all� to view every body part: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		ArrayList<BodyPart> list = dbManager.selectBodyPart(name);
		if(list==null){System.out.println("Error searching for the body part(s).");}
		else{
			for(BodyPart bodyPart:list){
				System.out.println(bodyPart);
			}
		}
	}
	
	public static void addDevices () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Type: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type=read;
		System.out.print("Price($): ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		float price=Float.parseFloat(read);
		System.out.print("Brand: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String brand=read;
		//Bodypart missing. The user must select from all the existent.
		
		dbManager.insertIntoDevices(name, type, price, brand);
	}
	
	public void showDevices () {
		
	}
	
	public static void addDiseases () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description=read;
		//Bodypart missing. The user must select from all the existent.
		
		dbManager.insertIntoDevices(name, description);	
		}
	
	public void showDiseases () {
		
	}
	
	public static void addImages () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description=read;
		System.out.print("Type: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type=read;
		System.out.print("Size (Ex.: microscopic): ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String size=read;
		System.out.print("Please, write the adress of the file you want to upload: ");
		try {
			read = console.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageAdress = read;
		//Paper missing. The user must select from all the existent.

		dbManager.insertIntoDevices(name, description);		}
	
	public void showImages () {
		
	}
	
	public static void addPaper () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Title: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String title=read;
		System.out.print("Source: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String source=read;
		dbManager.insertIntoPapers(name, title, source);
	}
	
	public void showPaper () {
		
	}
	
	public static void addProcedures () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Description: ");
		try {
            read=console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description=read;
		dbManager.insertIntoProcedures(name, description);
	}
	
	public void showProcedures () {
		
	}
	
	public static void addSymptoms () {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name=read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description=read;
		dbManager.insertIntoSymptoms(name, description);
	}
	
	public void showSymptoms () {
		
	}
	
	private static void deleteAuthor() {
		ArrayList<Author> list = dbManager.selectAuthor("all");
		if(list==null){System.out.println("Error searching for the authors.");}
		else{
			for(Author author:list){
				System.out.println(author);
			}
			System.out.print("\nPlease, write the ID of the author you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id=Integer.parseInt(read);
			if(id==0){return;}else{
				dbManager.deleteAuthor(id);
			}
		}	
	}

}
