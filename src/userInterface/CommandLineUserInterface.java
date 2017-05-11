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

	private static void showMenu() {
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
		switch (option) {
		case 1: {
			// Missing: checking if tables are created or not
			// try-catch w select or insert and the exception that's thrown
			// means tables aren't created
			newTables();
			break;
		}
		case 2: {
			newEntity();
			break;
		}
		case 3: {
			deleteEntity();
			break;
		}
		case 4: {
			showEntity();
			break;
		}
		case 5: {
			updateMenu();
			break;
		}
		case 6: {
			dbManager.disconnect();
			System.out.println("BYE!");
			System.exit(0);
			break;
		}

		}
		showMenu();
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
		System.out.print("\nOption: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer option = Integer.parseInt(read);
		switch (option) {
		case 1: {
			addAuthor();
			return;
		}
		case 2: {
			addBodyPart();
			return;
		}
		case 3: {
			addDevice();
			return;
		}
		case 4: {
			addDisease();
			return;
		}
		case 5: {
			addImage();
			return;
		}
		case 6: {
			addPaper();
			return;
		}
		case 7: {
			addProcedure();
			return;
		}
		case 8: {
			addSymptom();
			return;
		}
		case 9: {
			return;
		}
		}

	}

	public static void showEntity() {
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
		switch (option) {
		case 1: {
			showAuthor(askForName());
			return;
		}
		case 2: {
			showBodyPart(askForName());
			return;
		}
		case 3: {
			showDevice(askForName());
			return;
		}
		case 4: {
			showDisease(askForName());
			return;
		}
		case 5: {
			showImage(askForName());
			return;
		}
		case 6: {
			showPaper(askForName());
			return;
		}
		case 7: {
			showProcedure(askForName());
			return;
		}
		case 8: {
			showSymptom(askForName());
			return;
		}
		case 9: {
			return;
		}
		}

	}

	private static void deleteEntity() {
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
		switch (option) {
		case 1: {
			deleteAuthor();
			return;
		}
		case 2: {
			deleteBodyPart();
			return;
		}
		case 3: {
			deleteDevice();
			return;
		}
		case 4: {
			deleteDisease();
			return;
		}
		case 5: {
			deleteImage();
			return;
		}
		case 6: {
			deletePaper();
			return;
		}
		case 7: {
			deleteProcedure();
			return;
		}
		case 8: {
			deleteSymptom();
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

	public static void newConnection() {
		c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db"); // Indicates
																				// the
																				// technology
																				// and
																				// location
																				// of
																				// the
																				// database.
			c.createStatement().execute("PRAGMA foreign_keys=ON"); // Enables
																	// the
																	// support
																	// for
																	// foreign
																	// key
																	// constraints.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String askForName(){
	System.out.print("Please, provide a name or write �all� to view: ");
	try {
		read = console.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String name = read;
	return name;
	}
	
	// These methods should connect to the DBManager
	public static void addAuthor() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Nationality: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String origin = read;
		System.out.print("Association: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String association = read;
		Author author = new Author(name, origin, association);
		dbManager.insertIntoAuthor(author);
		System.out.println("Author inserted correctly.");
	}

	public static void showAuthor(String name) {
		ArrayList<Author> list = dbManager.selectAuthor(name);
		if (list == null) {
			System.out.println("Error searching for the author(s).");
		} else {
			for (Author author : list) {
				System.out.println(author);
			}
		}

	}

	public static void addBodyPart() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Location: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String location = read;
		BodyPart bodyPart = new BodyPart(name, location);
		dbManager.insertIntoBodyPart(bodyPart);
		System.out.println("Body part inserted correctly.");
	}

	public static void showBodyPart(String name) {
		ArrayList<BodyPart> list = dbManager.selectBodyPart(name);
		if (list == null) {
			System.out.println("Error searching for the body part(s).");
		} else {
			for (BodyPart bodyPart : list) {
				System.out.println(bodyPart);
			}
		}
	}

	public static void addDevice() {
		Procedure procedure_ = null;
		Paper paper_ = null;
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Type: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = read;
		System.out.print("Price($): ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		float price = Float.parseFloat(read);
		System.out.print("Brand: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String brand = read;
		//ask for procedure
		ArrayList<Procedure> list = dbManager.selectProcedure("all");
		if (list == null) {
			System.out.println("Error searching for the medical procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure.getID() + ": " + procedure.getName());
			}
			System.out.print("Please, select which of the medical procedures is related (�none� for none): ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String NAME = read;
			if (!NAME.equals("none")) {
				ArrayList<Procedure> procedure = dbManager.selectProcedure(NAME);
				procedure_ = procedure.get(0);
			}
			ArrayList<Paper> list2 = dbManager.selectPaper("all");
			if (list2 == null) {
				System.out.println("Error searching for the paper(s).");
			} else {
				for (Paper paper : list2) {
					System.out.println(paper.getID() + ": " + paper.getTitle());
				}
				System.out.print("Please, select which of the papers is related (�none� for none): ");
				try {
					read = console.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					String NAME2 = read;
					if (!NAME2.equals("none")) {
						ArrayList<Paper> paper = dbManager.selectPaper(NAME2);
						paper_ = paper.get(0);
					}

				}
			}
		}
		Device device = new Device(name, type, price, brand, procedure_, paper_);
		dbManager.insertIntoDevice(device);
	}

	public static void showDevice(String name) {
		ArrayList<Device> list = dbManager.selectDevice(name);
		if (list == null) {
			System.out.println("Error searching for the device(s).");
		} else {
			for (Device device : list) {
				System.out.println(device);
			}
		}
	}

	public static void addDisease() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description = read;
		ArrayList<BodyPart> list = dbManager.selectBodyPart("all");
		if (list == null) {
			System.out.println("Error searching for the body part(s).");
		} else {
			for (BodyPart bodypart : list) {
				System.out.println(bodypart.getID() + ": " + bodypart.getName());
			}
			System.out.print("Please, select wich of the listed body parts is affected (�none� for none): ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String NAME = read;
			if (NAME.equals("none")) {
				Disease disease1 = new Disease(name, description, null);
				dbManager.insertIntoDisease(disease1);
			} else {
				ArrayList<BodyPart> bodyPart = dbManager.selectBodyPart(NAME);
				Disease disease1 = new Disease(name, description, bodyPart.get(0).getID());
				dbManager.insertIntoDisease(disease1);
			}
		}
	}

	public static void showDisease(String name) {
		ArrayList<Disease> list = dbManager.selectDisease(name);
		if (list == null) {
			System.out.println("Error searching for the disease(s).");
		} else {
			for (Disease disease : list) {
				System.out.println(disease);
			}
		}
	}

	public static void addImage() { 
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description = read;
		System.out.print("Type: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = read;
		System.out.print("Size (Ex.: microscopic): ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String size = read;
		System.out.print("Please, write the address of the file you want to upload: ");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageAdress = read;
		File photo = new File(imageAdress);
		byte[] p = dbManager.stringtobyte(photo);
		
		
		showPaper("all");
		System.out.println("Paper name:");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Paper> list = dbManager.selectPaper(name);		
		Paper paper=null;
		for(Paper pap: list){
			paper = pap;
		}
		
		
		showDisease("all");
		System.out.print("Disease name:");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String disease_name = read;
		ArrayList<Disease> disease_list = dbManager.selectDisease(disease_name);		
		Disease disease = null;
		for(Disease dis: disease_list){
			disease = dis;
		}
		
		// Paper missing. The user must select from all the existent
		
		Image image1 = new Image(description, type, size, p, paper, disease);
		dbManager.insertIntoImage(image1);
	}

	public static void showImage(String name) {
		ArrayList<Image> list = dbManager.selectImage(name);
		if (list == null) {
			System.out.println("Error searching for the image(s).");
		} else {
			for (Image image : list) {
				System.out.println(image);
			}
		}
	}

	public static void addPaper() {
		System.out.print("Title: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String title = read;
		System.out.print("Source: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String source = read;
		showPaper("all");
		System.out.println("Author name:");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String authorName = read;
		ArrayList<Author> authorList = dbManager.selectAuthor(authorName);		
		Author author=null;
		for(Author aut: authorList){
			author = aut;
		}
		showDisease("all");
		System.out.print("Disease name:");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String disease_name = read;
		ArrayList<Disease> disease_list = dbManager.selectDisease(disease_name);		
		Disease disease = null;
		for(Disease dis: disease_list){
			disease = dis;
		}
		Paper paper = new Paper(title, source, author, disease);
		dbManager.insertIntoPaper(paper);
	}

	public static void showPaper(String name) {
		ArrayList<Paper> list = dbManager.selectPaper(name);
		if (list == null) {
			System.out.println("Error searching for the paper(s).");
		} else {
			for (Paper paper : list) {
				System.out.println(paper);
			}
		}
	}

	public static void addProcedure() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description = read;
		Procedure procedure = new Procedure(name, description);
		dbManager.insertIntoProcedure(procedure);
	}

	public static void showProcedure(String name) {
		ArrayList<Procedure> list = dbManager.selectProcedure(name);
		if (list == null) {
			System.out.println("Error searching for the procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure);
			}
		}
	}

	public static void addSymptom() {
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		System.out.print("Description: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String description = read;
		Symptom symptom = new Symptom(name, description);
		dbManager.insertIntoSymptom(symptom);
	}

	public static void showSymptom(String name) {
		ArrayList<Symptom> list = dbManager.selectSymptom(name);
		if (list == null) {
			System.out.println("Error searching for the author(s).");
		} else {
			for (Symptom symptom : list) {
				System.out.println(symptom);
			}
		}
	}

	private static void deleteAuthor() {
		ArrayList<Author> list = dbManager.selectAuthor("all");
		if (list == null) {
			System.out.println("Error searching for the authors.");
		} else {
			for (Author author : list) {
				System.out.println(author);
			}
			System.out.print("\nPlease, write the ID of the author you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteAuthor(id);
			}
		}
	}

	private static void deleteBodyPart() {
		ArrayList<BodyPart> list = dbManager.selectBodyPart("all");
		if (list == null) {
			System.out.println("Error searching for the body parts.");
		} else {
			for (BodyPart bodypart : list) {
				System.out.println(bodypart);
			}
			System.out.print("\nPlease, write the ID of the body part you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteBodyPart(id);
			}
		}
	}

	private static void deleteDevice() {
		ArrayList<Device> list = dbManager.selectDevice("all");
		if (list == null) {
			System.out.println("Error searching for the devices.");
		} else {
			for (Device device : list) {
				System.out.println(device);
			}
			System.out.print("\nPlease, write the ID of the device you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteDevice(id);
			}
		}
	}

	private static void deleteDisease() {
		ArrayList<Disease> list = dbManager.selectDisease("all");
		if (list == null) {
			System.out.println("Error searching for the diseases.");
		} else {
			for (Disease disease : list) {
				System.out.println(disease);
			}
			System.out.print("\nPlease, write the ID of the disease you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteDisease(id);
			}
		}
	}

	private static void deleteImage() {
		ArrayList<Image> list = dbManager.selectImage("all");
		if (list == null) {
			System.out.println("Error searching for the images.");
		} else {
			for (Image image : list) {
				System.out.println(image);
			}
			System.out.print("\nPlease, write the ID of the image you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteImage(id);
			}
		}
	}

	private static void deletePaper() {
		ArrayList<Paper> list = dbManager.selectPaper("all");
		if (list == null) {
			System.out.println("Error searching for the papers.");
		} else {
			for (Paper paper : list) {
				System.out.println(paper);
			}
			System.out.print("\nPlease, write the ID of the paper you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deletePaper(id);
			}
		}
	}

	private static void deleteProcedure() {
		ArrayList<Procedure> list = dbManager.selectProcedure("all");
		if (list == null) {
			System.out.println("Error searching for the procedures.");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure);
			}
			System.out.print("\nPlease, write the ID of the procedure you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteProcedure(id);
			}
		}
	}

	private static void deleteSymptom() {
		ArrayList<Symptom> list = dbManager.selectSymptom("all");
		if (list == null) {
			System.out.println("Error searching for the symptoms.");
		} else {
			for (Symptom symptom : list) {
				System.out.println(symptom);
			}
			System.out.print("\nPlease, write the ID of the symptom you want to delete. Write �0� to go back: ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int id = Integer.parseInt(read);
			if (id == 0) {
				return;
			} else {
				dbManager.deleteSymptom(id);
			}
		}
	}

	private static void updateMenu() {
		System.out.print("\nPlease, select the type of item you want to modify: ");
		System.out.println("\n1.) Author");
		System.out.println("2.) Device");
		System.out.println("3.) Disease or pathology");
		System.out.println("4.) Image");
		System.out.println("5.) Procedure or treatment");
		System.out.println("6.) Return to the main menu...");
		System.out.print("\nOption: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer option = Integer.parseInt(read);
		switch (option) {
		case 1: {
			modifyAuthor();
			return;
		}
		case 2: {
			modifyDevice();
			return;
		}
		case 3: {
			modifyDisease();
			return;
		}
		case 4: {
			modifyImage();
			return;
		}
		case 5: {
			modifyProcedure();
			return;
		}
		case 6: {
			return;
		}
		}

	}

	private static void modifyAuthor() {
		try {
			// change lists to 'show_()' methods
			ArrayList<Author> list = dbManager.selectAuthor("all");
			if (list == null) {
				System.out.println("Error searching for the authors.");
			} else {
				for (Author author : list) {
					System.out.println(author);
				}
				showAuthor("all");//Change the show methods to receive the argument. Make the queries to the user in the previuos method.
				System.out.println("Which is the author that you want to modify?" + "\nWrite its ID number:");
				String read = console.readLine();
				Integer authorId = Integer.parseInt(read);
				System.out.println("Write the new author's association:");
				String newAssociation = console.readLine();
				dbManager.updateAuthor(authorId, newAssociation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyDevice() {
		try {
			ArrayList<Device> list = dbManager.selectDevice("all");
			if (list == null) {
				System.out.println("Error searching for the symptoms.");
			} else {
				for (Device device : list) {
					System.out.println(device);
				}
			System.out.println("Which is the device that you want to modify?" + "\nWrite its ID number:");
			String read = console.readLine();
			Integer deviceId = Integer.parseInt(read);
			System.out.println("Write the new devices's price:");
			read = console.readLine();
			Float newPrice = Float.parseFloat(read);
			System.out.println("Write the new device's brand name:");
			String newBrand = console.readLine();
			dbManager.updateDevice(deviceId, newPrice, newBrand);}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyDisease() {
		try {
			ArrayList<Disease> listD = dbManager.selectDisease("all");
			if (listD == null) {
				System.out.println("Error searching for the symptoms.");
			} else {
				for (Disease disease : listD) {
					System.out.println(disease);
				}
			System.out.println("Which is the disease that you want to modify?" + "\nWrite its ID number:");
			String read = console.readLine();
			Integer diseaseId = Integer.parseInt(read);
			System.out.println("Write the new disease's procedure:");
			String newProcedure = console.readLine();
			System.out.println("Write the new disease's body part:");
			ArrayList<BodyPart> listBP = dbManager.selectBodyPart("all");
			if (listBP == null) {
				System.out.println("Error searching for the symptoms.");
			} else {
				for (BodyPart bodypart : listBP) {
					System.out.println(bodypart);
				}
			System.out.println("Which is the body part related to this disease?" + "\nWrite its ID number:");
			read = console.readLine();
			Integer newBodyPart = Integer.parseInt(read);
			dbManager.updateDisease(diseaseId, newProcedure, newBodyPart);}}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyImage() {
		try {
			ArrayList<Image> listI = dbManager.selectImage("all");
			if (listI == null) {
				System.out.println("Error searching for the symptoms.");
			} else {
				for (Image image : listI) {
					System.out.println(image);
				}
			System.out.println("Which is the image that you want to modify?" + "\nWrite its ID number:");
			String read = console.readLine();
			Integer imageId = Integer.parseInt(read);
			System.out.println("Write the new image's description:");
			String newDescription = console.readLine();
			System.out.println("Write the new disease's paper:");
				ArrayList<Paper> listP = dbManager.selectPaper("all");
			System.out.println("Which is the paper related to this disease?" + "\nWrite its ID number:");
			read = console.readLine();
			Integer newPaper = Integer.parseInt(read);
			dbManager.updateImage(imageId, newDescription, newPaper);}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyProcedure() {
		try {
			ArrayList<Procedure> list = dbManager.selectProcedure("all");
			if (list == null) {
				System.out.println("Error searching for the symptoms.");
			} else {
				for (Procedure procedure : list) {
					System.out.println(procedure);
				}
			System.out.println("Which is the procedure that you want to modify?" + "\nWrite its ID number:");
			String read = console.readLine();
			Integer procedureId = Integer.parseInt(read);
			System.out.println("Write the new procedure's description:");
			String newDescription = console.readLine();
			dbManager.updateProcedure(procedureId, newDescription);}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
