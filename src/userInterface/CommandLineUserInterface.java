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

			break;
		}
		case 6: {
			dbManager.disconnect();
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
			addBodyParts();
			return;
		}
		case 3: {
			addDevices();
			return;
		}
		case 4: {
			addDiseases();
			return;
		}
		case 5: {
			addImages();
			return;
		}
		case 6: {
			addPaper();
			return;
		}
		case 7: {
			addProcedures();
			return;
		}
		case 8: {
			addSymptoms();
			return;
		}
		case 9: {
			return;
		}
		default: {
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
			showAuthor();
			return;
		}
		case 2: {
			showBodyPart();
			return;
		}
		case 3: {
			showDevices();
			return;
		}
		case 4: {
			showDiseases();
			return;
		}
		case 5: {
			showImages();
			return;
		}
		case 6: {
			showPaper();
			return;
		}
		case 7: {
			showProcedures();
			return;
		}
		case 8: {
			showSymptoms();
			return;
		}
		case 9: {
			return;
		}
		default: {
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
		default: {
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
		dbManager.insertIntoAuthor(name, origin, association);
		System.out.println("Author inserted correctly.");
	}

	public static void showAuthor() {
		System.out.print("Please, provide a name or write «all» to view every author: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Author> list = dbManager.selectAuthor(name);
		if (list == null) {
			System.out.println("Error searching for the author(s).");
		} else {
			for (Author author : list) {
				System.out.println(author);
			}
		}

	}

	public static void addBodyParts() {
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
		dbManager.insertIntoBodyPart(name, location);
		System.out.println("Body part inserted correctly.");
	}

	public static void showBodyPart() {
		System.out.print("Please, provide a name or write «all» to view every body part: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<BodyPart> list = dbManager.selectBodyPart(name);
		if (list == null) {
			System.out.println("Error searching for the body part(s).");
		} else {
			for (BodyPart bodyPart : list) {
				System.out.println(bodyPart);
			}

		}
	}

	public static void addDevices() {
		int procedure_id = 0;
		int paper_id = 0;
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
		ArrayList<Procedure> list = dbManager.selectProcedure("all");
		if (list == null) {
			System.out.println("Error searching for the medical procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure.getID() + ": " + procedure.getName());
			}
			System.out.print("Please, select wich of the medical procedures is related («none» for none): ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String NAME = read;
			if (NAME.equals("none")) {
				procedure_id = (Integer) null;
			} else {
				ArrayList<BodyPart> bodyPart = dbManager.selectBodyPart(NAME);
				procedure_id = bodyPart.get(0).getID();
			}
		}
		ArrayList<Procedure> list2 = dbManager.selectProcedure("all");
		if (list == null) {
			System.out.println("Error searching for the medical procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure.getID() + ": " + procedure.getName());
			}
			System.out.print("Please, select wich of the medical procedures is related («none» for none): ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String NAME = read;
			if (NAME.equals("none")) {
				procedure_id = (Integer) null;
			} else {
				ArrayList<BodyPart> bodyPart = dbManager.selectBodyPart(NAME);
				procedure_id = bodyPart.get(0).getID();
			}
		}

	}

	public static void showDevices() {
		System.out.print("Please, provide a name or write «all» to view every device: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Device> list = dbManager.selectDevice(name);
		if (list == null) {
			System.out.println("Error searching for the device(s).");
		} else {
			for (Device device : list) {
				System.out.println(device);
			}
		}
	}

	public static void addDiseases() {
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
			System.out.print("Please, select wich of the listed body parts is affected («none» for none): ");
			try {
				read = console.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String NAME = read;
			if (NAME.equals("none")) {
				dbManager.insertIntoDisease(name, description, null);
			} else {
				ArrayList<BodyPart> bodyPart = dbManager.selectBodyPart(NAME);
				dbManager.insertIntoDisease(name, description, bodyPart.get(0).getID());
			}
		}
		// TODO Continue working here.
	}

	public static void showDiseases() {
		System.out.print("Please, provide a name or write «all» to view every disease: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Disease> list = dbManager.selectDisease(name);
		if (list == null) {
			System.out.println("Error searching for the disease(s).");
		} else {
			for (Disease disease : list) {
				System.out.println(disease);
			}
		}
	}

	public static void addImages() {
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
		System.out.print("Please, write the adress of the file you want to upload: ");
		try {
			read = console.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageAdress = read;
		// Paper missing. The user must select from all the existent

		dbManager.insertIntoDevice(name, description);
	}

	public static void showImages() {
		System.out.print("Please, provide a name or write «all» to view every image: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
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
		System.out.print("Name: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
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
		dbManager.insertIntoPapers(name, title, source);
	}

	public static void showPaper() {
		System.out.print("Please, provide a name or write «all» to view every paper: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Paper> list = dbManager.selectPaper(name);
		if (list == null) {
			System.out.println("Error searching for the paper(s).");
		} else {
			for (Paper paper : list) {
				System.out.println(paper);
			}
		}
	}

	public static void addProcedures() {
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
		dbManager.insertIntoProcedures(name, description);
	}

	public static void showProcedures() {
		System.out.print("Please, provide a name or write «all» to view every procedure: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		ArrayList<Procedure> list = dbManager.selectProcedure(name);
		if (list == null) {
			System.out.println("Error searching for the procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure);
			}
		}
	}

	public static void addSymptoms() {
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
		dbManager.insertIntoSymptoms(name, description);
	}

	public static void showSymptoms() {
		System.out.print("Please, provide a name or write «all» to view every symptom: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
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
			System.out.print("\nPlease, write the ID of the author you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the body part you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the device you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the disease you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the image you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the paper you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the procedure you want to delete. Write «0» to go back: ");
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
			System.out.print("\nPlease, write the ID of the symptom you want to delete. Write «0» to go back: ");
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
}
