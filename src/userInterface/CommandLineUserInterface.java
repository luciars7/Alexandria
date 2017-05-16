package userInterface;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBManager;
import jpa.JpaManager;
import pojos.*;

//Sustituir en OSWD.
public class CommandLineUserInterface {
	static Connection c = null;
	static DBManager dbManager = null;
	static JpaManager jpaManager = null;
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	static String read = null;

	public static void main(String args[]) {
		System.out.println("Establishing a connection with ALEXANDRIA...");
		dbManager = new DBManager();
		dbManager.connect(c);
		checkTables();
		// Needs to be done before JPA creates the tables as it
		// wishes.
		System.out.println("New connection stablished.");
		jpaManager = new JpaManager();
		jpaManager.connect();
		showMenu();
	}

	public static void checkTables() {
		System.out.println("Checking for the tables...");
		boolean result = dbManager.checkTables();
		if (result == true) {
			System.out.println("Tables already exist.");
		} else {
			System.out.println("Tables didn't exist. They have been created.");
		}
	}

	private static void showMenu() {
		System.out.println("\n\n\n\nALEXANDRIA");
		System.out.println("***********************");
		System.out.println("1.) Add new item.");
		System.out.println("2.) Delete item.");
		System.out.println("3.) View item.");
		System.out.println("4.) Modify item.");
		System.out.println("5.) Exit.");
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
			newEntity();
			break;
		}
		case 2: {
			deleteEntity();
			break;
		}
		case 3: {
			showEntity();
			break;
		}
		case 4: {
			updateMenu();
			break;
		}
		case 5: {
			Exit();
			break;
		}
		}
		showMenu();
	}

	private static void Exit() {
		System.out.println("Proceeding to close the connection to ALEXANDRIA.");
		System.out.println("Are you sure you want to continue? (Y/N)");
		System.out.print("Option: ");
		try {
			read = console.readLine().toUpperCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String option = read;
		switch (option) {
		case "Y": {
			// dbManager.connect(c);
			dbManager.disconnect();
			// jpaManager.connect();
			jpaManager.disconnect();
			try {
				c.close();
			} catch (SQLException e) {
				System.out.println("Error encountered when closing the conection.");
				e.printStackTrace();
			}
			System.out.println("Connection closed.");
			System.exit(0);
		}
		case "N": {
			return;
		}
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
			System.out.println("Which author do you want to see?");
			String name = askForName();
			showAuthor(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToAuthor(name);
			}
			return;
		}
		case 2: {
			System.out.println("Which body part do you want to see?");
			String name = askForName();
			showBodyPart(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToBodyPart(name);
			}
			return;
		}
		case 3: {
			System.out.println("Which device do you want to see?");
			String name = askForName();
			showDevice(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToDevice(name);
			}
			return;
		}
		case 4: {
			System.out.println("Which disease do you want to see?");
			String name = askForName();
			showDisease(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToDisease(name);
			}
			return;
		}
		case 5: {
			System.out.println("Which image do you want to see?");
			String name = askForName();
			showImage(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToImage(name);
			}
			return;
		}
		case 6: {
			System.out.println("Which paper do you want to see?");
			String name = askForName();
			showPaper(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToPaper(name);
			}
			return;
		}
		case 7: {
			System.out.println("Which procedure do you want to see?");
			String name = askForName();
			showProcedure(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToProcedure(name);
			}
			return;
		}
		case 8: {
			System.out.println("Which symptom do you want to see?");
			String name = askForName();
			showSymptom(name);
			if (!name.equalsIgnoreCase("all")) {
				showRelatedToSymptom(name);
			}
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

	/*
	 * public static void newConnection() { try {
	 * Class.forName("org.sqlite.JDBC"); c =
	 * DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db");
	 * c.createStatement().execute("PRAGMA foreign_keys=ON"); } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */
	public static String askForName() {
		System.out.print("Please, provide a name or write �all� to view: ");
		try {
			read = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = read;
		return name;
	}

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
		System.out.println("1");
		Author author = new Author(name, origin, association);
		System.out.println("2");
		// dbManager.connect(c);
		System.out.println("3");
		dbManager.insertIntoAuthor(author);
		System.out.println("4");
		//// dbManager.disconnect();
		System.out.println("5");
		System.out.println("Author inserted correctly.");

		System.out.println("\nProceeding to show all available papers...");

		showPaper("all");

		System.out.println("Please, select the id of the papers you want to relate this author with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		ArrayList<Integer> id = new ArrayList<Integer>();

		int opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			jpaManager.insertpaperauthor(id2, author.getID());

		}
		// jpaManager.disconnect();
	}

	public static void showAuthor(String name) {
		// dbManager.connect(c);
		ArrayList<Author> list = dbManager.selectAuthor(name);
		if (list == null) {
			System.out.println("Error searching for the author(s).");
		} else {
			for (Author author : list) {
				System.out.println(author);
			}
		}
		//// dbManager.disconnect();
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
		// dbManager.connect(c);
		dbManager.insertIntoBodyPart(bodyPart);
		//// dbManager.disconnect();
		System.out.println("Body part inserted correctly.");

		System.out.println("\nProceeding to show all available diseases...");
		showDisease("all");
		System.out.println("Please, select the id of the diseases you want to relate this body part with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		ArrayList<Integer> id = new ArrayList<Integer>();
		int opcion = 1;
		while (opcion != 0) {
			try {
				read = console.readLine();
				opcion = Integer.parseInt(read);
				if (opcion != 0) {
					id.add(opcion);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// jpaManager.connect();
		for (Integer id2 : id) {
			bodyPart.addDisease(jpaManager.readDisease(id2)); // Tal vez haya
																// que recuperar
																// la bodyPart
																// con el
																// jpaManager.
		}
		// jpaManager.disconnect();
	}

	public static void showBodyPart(String name) {

		// dbManager.connect(c);
		ArrayList<BodyPart> list = dbManager.selectBodyPart(name);
		if (list == null) {
			System.out.println("Error searching for the body part(s).");
		} else {
			for (BodyPart bodyPart : list) {
				System.out.println(bodyPart);
			}
		}
		//// dbManager.disconnect();
	}

	public static void addDevice() {

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
		System.out.print("Price: ");
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
		Device device = new Device(name, type, price, brand);
		// dbManager.connect(c);
		dbManager.insertIntoDevice(device);
		//// dbManager.disconnect();
		System.out.println("Device inserted correctly.");

		System.out.println("\nProceeding to show all available procedures...");
		showProcedure("all");
		System.out.println("Please, select the id of the procedures you want to relate this device with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		ArrayList<Integer> id = new ArrayList<Integer>();
		int opcion = 1;
		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Procedure procedure = jpaManager.readProcedure(id2);

			procedure.addDevice(device);

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available papers...");

		showPaper("all");

		System.out.println("Please, select the id of the papers you want to relate this device with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		int opcion2 = 1;

		while (opcion2 != 0) {

			try {

				read = console.readLine();

				opcion2 = Integer.parseInt(read);

				if (opcion2 != 0) {

					id.add(opcion2);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Paper paper = jpaManager.readPaper(id2);

			device.addPaper(paper);

		}
		// jpaManager.disconnect();
	}

	/*
	 * ask for procedure
	 * 
	 * ArrayList<Procedure> list = dbManager.selectProcedure("all");
	 * 
	 * if (list == null) {
	 * 
	 * System.out.println("Error searching for the medical procedure(s).");
	 * 
	 * } else {
	 * 
	 * for (Procedure procedure : list) {
	 * 
	 * System.out.println(procedure.getID() + ": " + procedure.getName());
	 * 
	 * }
	 * 
	 * System.out.
	 * print("Please, select which of the medical procedures is related (�none� for none): "
	 * );
	 * 
	 * try {
	 * 
	 * read = console.readLine();
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * String NAME = read;
	 * 
	 * if (!NAME.equals("none")) {
	 * 
	 * ArrayList<Procedure> procedure = dbManager.selectProcedure(NAME);
	 * 
	 * procedure_ = procedure.get(0);
	 * 
	 * }
	 * 
	 * ArrayList<Paper> list2 = dbManager.selectPaper("all");
	 * 
	 * if (list2 == null) {
	 * 
	 * System.out.println("Error searching for the paper(s).");
	 * 
	 * } else {
	 * 
	 * for (Paper paper : list2) {
	 * 
	 * System.out.println(paper.getID() + ": " + paper.getTitle());
	 * 
	 * }
	 * 
	 * System.out.
	 * print("Please, select which of the papers is related (�none� for none): "
	 * );
	 * 
	 * try {
	 * 
	 * read = console.readLine();
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * String NAME2 = read;
	 * 
	 * if (!NAME2.equals("none")) {
	 * 
	 * ArrayList<Paper> paper = dbManager.selectPaper(NAME2);
	 * 
	 * paper_ = paper.get(0);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * Device device = new Device(name, type, price, brand, procedure_, paper_);
	 * 
	 * dbManager.insertIntoDevice(device);
	 */

	public static void showDevice(String name) {
		// dbManager.connect(c);
		ArrayList<Device> list = dbManager.selectDevice(name);

		if (list == null) {

			System.out.println("Error searching for the device(s).");

		} else {

			for (Device device : list) {

				System.out.println(device);

			}

		}
		//// dbManager.disconnect();
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

		Disease disease = new Disease(name, description);
		// dbManager.connect(c);
		dbManager.insertIntoDisease(disease);
		//// dbManager.disconnect();
		System.out.println("Disease inserted correctly.");

		System.out.println("\nProceeding to show all available body parts...");

		showBodyPart("all");

		System.out.println("Please, select the id of the body parts you want to relate this disease with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		ArrayList<Integer> id = new ArrayList<Integer>();

		int opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			BodyPart bodyPart = jpaManager.readBodyPart(id2);

			bodyPart.addDisease(disease);

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available symptoms...");

		showSymptom("all");

		System.out.println("Please, select the id of the symptoms you want to relate this disease with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Symptom symptom = jpaManager.readSymptom(id2);

			jpaManager.insertsymtomdisease(disease.getID(), symptom.getID());

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available papers...");

		showPaper("all");

		System.out.println("Please, select the id of the papers you want to relate this disease with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Paper paper = jpaManager.readPaper(id2);

			jpaManager.insertpaperdisease(paper.getID(), disease.getID());

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available images...");

		showImage("all");

		System.out.println("Please, select the id of the images you want to relate this disease with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Image image = jpaManager.readImage(id2);

			jpaManager.insertimagedisease(image.getID(), disease.getID());

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available procedures...");

		showProcedure("all");

		System.out.println("Please, select the id of the procedures you want to relate this disease with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Procedure procedure = jpaManager.readProcedure(id2);

			jpaManager.insertproceduredisease(procedure.getID(), disease.getID());

		}
		// jpaManager.disconnect();
		/*
		 * ArrayList<BodyPart> list = dbManager.selectBodyPart("all");
		 * 
		 * if (list == null) {
		 * 
		 * System.out.println("Error searching for the body part(s).");
		 * 
		 * } else {
		 * 
		 * for (BodyPart bodypart : list) {
		 * 
		 * System.out.println(bodypart.getID() + ": " + bodypart.getName());
		 * 
		 * }
		 * 
		 * System.out.
		 * print("Please, select wich of the listed body parts is affected (�none� for none): "
		 * );
		 * 
		 * try {
		 * 
		 * read = console.readLine();
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * String NAME = read;
		 * 
		 * if (NAME.equals("none")) {
		 * 
		 * Disease disease1 = new Disease(name, description, null);
		 * 
		 * dbManager.insertIntoDisease(disease1);
		 * 
		 * } else {
		 * 
		 * ArrayList<BodyPart> bodyPart = dbManager.selectBodyPart(NAME);
		 * 
		 * Disease disease1 = new Disease(name, description,
		 * bodyPart.get(0).getID());
		 * 
		 * dbManager.insertIntoDisease(disease1);
		 * 
		 * }
		 * 
		 * }
		 */

	}

	public static void showDisease(String name) {
		// dbManager.connect(c);
		ArrayList<Disease> list = dbManager.selectDisease(name);

		if (list == null) {

			System.out.println("Error searching for the disease(s).");

		} else {

			for (Disease disease : list) {

				System.out.println(disease);

			}

		}
		//// dbManager.disconnect();
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
		// dbManager.connect(c);
		byte[] p = dbManager.stringtobyte(photo);

		Image image = new Image(description, type, size, p);

		dbManager.insertIntoImage(image);
		//// dbManager.disconnect();
		System.out.println("\nProceeding to show all available papers...");

		showPaper("all");

		System.out.println("Please, select the id of the papers you want to relate this image with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		ArrayList<Integer> id = new ArrayList<Integer>();

		int opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Paper paper = jpaManager.readPaper(id2);

			paper.addImage(image);

		}
		// jpaManager.disconnect();

		System.out.println("\nProceeding to show all available diseases...");

		showDisease("all");

		System.out.println("Please, select the id of the diseases you want to relate this image with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		opcion = 1;

		while (opcion != 0) {

			try {

				read = console.readLine();

				opcion = Integer.parseInt(read);

				if (opcion != 0) {

					id.add(opcion);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Disease disease = jpaManager.readDisease(id2);

			jpaManager.insertimagedisease(image.getID(), disease.getID());
		}
		// jpaManager.disconnect();
		/*
		 * showPaper("all");
		 * 
		 * System.out.println("Paper name:");
		 * 
		 * try {
		 * 
		 * read = console.readLine();
		 * 
		 * 
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * String name = read;
		 * 
		 * ArrayList<Paper> list = dbManager.selectPaper(name);
		 * 
		 * Paper paper = null;
		 * 
		 * for (Paper pap : list) {
		 * 
		 * paper = pap;
		 * 
		 * }
		 * 
		 * 
		 * 
		 * showDisease("all");
		 * 
		 * System.out.print("Disease name:");
		 * 
		 * try {
		 * 
		 * read = console.readLine();
		 * 
		 * 
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 * 
		 * String disease_name = read;
		 * 
		 * ArrayList<Disease> disease_list =
		 * dbManager.selectDisease(disease_name);
		 * 
		 * Disease disease = null;
		 * 
		 * for (Disease dis : disease_list) {
		 * 
		 * disease = dis;
		 * 
		 * }
		 * 
		 * 
		 * 
		 * // Paper missing. The user must select from all the existent
		 * 
		 * 
		 * 
		 * Image image1 = new Image(description, type, size, p, paper, disease);
		 * 
		 * dbManager.insertIntoImage(image1);
		 * 
		 * String imageAddress = read;
		 */

	}

	public static void showImage(String name) {
		// dbManager.connect(c);
		ArrayList<Image> list = dbManager.selectImage(name);

		if (list == null) {

			System.out.println("Error searching for the image(s).");

		} else {

			for (Image image : list) {

				System.out.println(image);

			}

		}
		//// dbManager.disconnect();

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
		Paper paper = new Paper(title, source);
		// dbManager.connect(c);
		dbManager.insertIntoPaper(paper);
		//// dbManager.disconnect();
		System.out.println("\nProceeding to show all available authors...");
		showAuthor("all");
		System.out.println("Please, select the id of the authors you want to relate this paper with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		ArrayList<Integer> id = new ArrayList<Integer>();
		int option = 1;
		while (option != 0) {
			try {
				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				}

				if (option == 0) {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			jpaManager.insertpaperauthor(id2, paper.getID());

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available diseases...");

		showDisease("all");

		System.out.println("Please, select the id of the diseases you want to relate this paper with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		option = 1;

		while (option != 0) {

			try {

				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Disease disease = jpaManager.readDisease(id2);

			jpaManager.insertpaperdisease(paper.getID(), disease.getID());

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available devices...");

		showDevice("all");

		System.out.println("Please, select the id of the devices you want to relate this paper with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		option = 1;

		while (option != 0) {

			try {

				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Device device = jpaManager.readDevice(id2);

			device.addPaper(paper);

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available procedures...");

		showProcedure("all");

		System.out.println("Please, select the id of the procedures you want to relate this paper with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		option = 1;

		while (option != 0) {

			try {

				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Procedure procedure = jpaManager.readProcedure(id2);

			procedure.addPaper(paper);

		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available images...");

		showImage("all");

		System.out.println("Please, select the id of the images you want to relate this paper with.");

		System.out.println("Select 0 for none or to finish.");

		System.out.print("Option: ");

		id = new ArrayList<Integer>();

		option = 1;

		while (option != 0) {

			try {

				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Image image = jpaManager.readImage(id2);

			paper.addImage(image);

		}
		// jpaManager.disconnect();
	}

	public static void showPaper(String name) {
		// dbManager.connect(c);
		ArrayList<Paper> list = dbManager.selectPaper(name);

		if (list == null) {

			System.out.println("Error searching for the paper(s).");

		} else {

			for (Paper paper : list) {

				System.out.println(paper);

			}

		}
		//// dbManager.disconnect();
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
		// dbManager.connect(c);
		dbManager.insertIntoProcedure(procedure);
		//// dbManager.disconnect();
		System.out.println("\nProceeding to show all available diseases...");
		showDisease("all");
		System.out.println("Please, select the id of the diseases you want to relate this procedure with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		ArrayList<Integer> id = new ArrayList<Integer>();
		int option = 1;
		while (option != 0) {
			try {
				read = console.readLine();
				option = Integer.parseInt(read);
				if (option != 0) {
					id.add(option);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// jpaManager.connect();
		for (Integer id2 : id) {
			Disease disease = jpaManager.readDisease(id2);
			jpaManager.insertproceduredisease(procedure.getID(), disease.getID());
		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available papers...");
		showPaper("all");
		System.out.println("Please, select the id of the papers you want to relate this procedure with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		id = new ArrayList<Integer>();
		option = 1;
		while (option != 0) {
			try {
				read = console.readLine();
				option = Integer.parseInt(read);
				if (option != 0) {
					id.add(option);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// jpaManager.connect();
		for (Integer id2 : id) {
			Paper paper = jpaManager.readPaper(id2);
			procedure.addPaper(paper);
		}
		// jpaManager.disconnect();
		System.out.println("\nProceeding to show all available devices...");
		showDevice("all");
		System.out.println("Please, select the id of the devices you want to relate this procedure with.");
		System.out.println("Select 0 for none or to finish.");
		System.out.print("Option: ");
		id = new ArrayList<Integer>();
		option = 1;
		while (option != 0) {
			try {
				read = console.readLine();
				option = Integer.parseInt(read);
				if (option != 0) {
					id.add(option);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// jpaManager.connect();
		for (Integer id2 : id) {
			Device device = jpaManager.readDevice(id2);
			procedure.addDevice(device);
		}
		// jpaManager.disconnect();
	}

	public static void showProcedure(String name) {
		// dbManager.connect(c);
		ArrayList<Procedure> list = dbManager.selectProcedure(name);
		if (list == null) {
			System.out.println("Error searching for the procedure(s).");
		} else {
			for (Procedure procedure : list) {
				System.out.println(procedure);
			}
		}
		//// dbManager.disconnect();
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
		// dbManager.connect(c);
		dbManager.insertIntoSymptom(symptom);
		//// dbManager.disconnect();
		System.out.println("\nProceeding to show all available diseases...");

		showDisease("all");

		System.out.print("Select the id of the related disease (0 for none): ");

		ArrayList<Integer> id = new ArrayList<Integer>();

		int option = 1;

		while (option != 0) {

			try {

				read = console.readLine();

				option = Integer.parseInt(read);

				if (option != 0) {

					id.add(option);

				} else {

					break;

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}
		// jpaManager.connect();
		for (Integer id2 : id) {

			Disease disease = jpaManager.readDisease(id2);

			jpaManager.insertsymtomdisease(disease.getID(), symptom.getID());

		}
		// jpaManager.disconnect();
	}

	public static void showSymptom(String name) {
		// dbManager.connect(c);
		ArrayList<Symptom> list = dbManager.selectSymptom(name);

		if (list == null) {

			System.out.println("Error searching for the author(s).");
		} else {

			for (Symptom symptom : list) {

				System.out.println(symptom);

			}

		}
		//// dbManager.disconnect();
	}

	private static void deleteAuthor() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteBodyPart() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteDevice() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteDisease() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteImage() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deletePaper() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteProcedure() {
		// dbManager.connect(c);
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
		//// dbManager.disconnect();
	}

	private static void deleteSymptom() {
		// dbManager.connect(c);
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

				jpaManager.deleteSymptomJPA(id);

			}

		}
		//// dbManager.disconnect();
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
		showAuthor("all");
		System.out.println("\nWhich is the author that you want to modify?" + "\nWrite its ID number:");
		try {
			read = console.readLine();
			Integer authorId = Integer.parseInt(read);
			System.out.println("Write the new author's association:");
			String newAssociation;
			newAssociation = console.readLine();
			// dbManager.connect(c);
			dbManager.updateAuthor(authorId, newAssociation);
			//// dbManager.disconnect();
			System.out.println("The author #" + authorId + " has been modified.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyDevice() {

		try {
			showDevice("all");
			System.out.println("\nWhich is the device that you want to modify?" + "\nWrite its ID number:");
			read = console.readLine();
			Integer deviceId = Integer.parseInt(read);
			System.out.println("Write the new devices's price:");
			read = console.readLine();
			Float newPrice = Float.parseFloat(read);
			System.out.println("Write the new device's brand name:");
			String newBrand = console.readLine();
			// dbManager.connect(c);
			dbManager.updateDevice(deviceId, newPrice, newBrand);
			//// dbManager.disconnect();
			System.out.println("The device #" + deviceId + " has been modified.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyDisease() {
		showDisease("all");
		System.out.println("\nWhich is the disease that you want to modify?" + "\nWrite its ID number:");
		try {
			read = console.readLine();
			Integer diseaseId = Integer.parseInt(read);
			showProcedure("all");
			System.out.println("Write the new disease's procedure:");
			String newProcedure = console.readLine();
			showBodyPart("all");
			System.out.println("Which is the body part related to this disease?" + "\nWrite its ID number:");
			read = console.readLine();
			Integer newBodyPart = Integer.parseInt(read);
			// dbManager.connect(c);
			dbManager.updateDisease(diseaseId, newProcedure, newBodyPart);
			//// dbManager.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void modifyImage() {
		try {
			showDisease("all");

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
			// dbManager.connect(c);
			dbManager.updateImage(imageId, newDescription, newPaper);
			//// dbManager.disconnect();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static void modifyProcedure() {
		try {
			showProcedure("all");
			System.out.println("Which is the procedure that you want to modify?" + "\nWrite its ID number:");
			read = console.readLine();
			Integer procedureId = Integer.parseInt(read);
			System.out.println("Write the new procedure's description:");
			String newDescription = console.readLine();
			jpaManager.updateProcedureJPA(procedureId, newDescription);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showRelatedToSymptom(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToProcedure(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToPaper(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToImage(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToDisease(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToDevice(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToBodyPart(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {

		}
	}

	private static void showRelatedToAuthor(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			// dbManager.connect(c);
			Author author = dbManager.selectAuthor(name).get(0);
			//// dbManager.disconnect();
			System.out.println("\nPAPERS:");
			// jpaManager.connect();
			List<Integer> paper_ids = jpaManager.readPapersRelatedToAuthor(author.getID());
			for (Integer paper_id : paper_ids) {
				Paper paper = jpaManager.readPaper(paper_id);
				System.out.println("ID: " + paper.getID() + " Title: " + paper.getTitle());
			}
			// jpaManager.disconnect();
			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static String askIfViewRelated() {
		String answer = null;
		System.out.println("\nWould you like to view a related item? (Y/N)");
		try {
			answer = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	private static void viewRelated(String category, int id) {
		// TODO probar con un switch para las categor�as.
	}
}
