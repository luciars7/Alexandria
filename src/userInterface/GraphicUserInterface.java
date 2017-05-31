package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jdbc.DBManager;
import jpa.JpaManager;
import pojos.Author;
import pojos.BodyPart;
import pojos.Device;
import pojos.Disease;
import pojos.Image;
import pojos.Paper;
import pojos.Procedure;
import pojos.Symptom;
import xml.XmlManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GraphicUserInterface extends JFrame {
	static Connection c = null;
	static DBManager dbManager = null;
	static JpaManager jpaManager = null;
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	static String read = null;

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable mainTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUserInterface frame = new GraphicUserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraphicUserInterface() {
		setTitle("Alexandria: a multidisciplinar library");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nacho\\Desktop\\9TpoMA9Lc.jpg"));
		System.out.println("Establishing a connection with ALEXANDRIA...");
		dbManager = new DBManager();
		dbManager.connect(c);
		checkTables();
		// Needs to be done before JPA creates the tables as it
		// wishes.
		System.out.println("New connection stablished.");
		jpaManager = new JpaManager();
		jpaManager.connect();
		// showMenu();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel buttonPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPane.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		buttonPane.setBounds(10, 11, 99, 244);
		contentPane.add(buttonPane);

		JRadioButton buttonAuthor = new JRadioButton("Authors");
		buttonAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("0");
				String columns[] = { "ID", "name", "origin", "association" };
				DefaultTableModel tableModelAuthor = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModelAuthor);
				ArrayList<Author> authors = dbManager.selectAuthor("all");
				Object[] row = new Object[tableModelAuthor.getColumnCount()];
				for (int i = 0; i < authors.size(); i++) {
					row[0] = authors.get(i).getID();
					row[1] = authors.get(i).getName();
					row[2] = authors.get(i).getOrigin();
					row[3] = authors.get(i).getAssociation();
					tableModelAuthor.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonAuthor.setVerticalAlignment(SwingConstants.TOP);
		buttonAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonAuthor);
		buttonPane.add(buttonAuthor);

		JRadioButton buttonBodyPart = new JRadioButton("Body parts");
		buttonBodyPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("1");
				String columns[] = { "ID", "name", "location" };
				DefaultTableModel tableModelBodyPart = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModelBodyPart);
				ArrayList<BodyPart> bodyParts = dbManager.selectBodyPart("all");
				Object[] row = new Object[tableModelBodyPart.getColumnCount()];
				for (int i = 0; i < bodyParts.size(); i++) {
					row[0] = bodyParts.get(i).getID();
					row[1] = bodyParts.get(i).getName();
					row[2] = bodyParts.get(i).getLocation();
					tableModelBodyPart.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonBodyPart.setVerticalAlignment(SwingConstants.TOP);
		buttonBodyPart.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonBodyPart);
		buttonPane.add(buttonBodyPart);

		JRadioButton buttonDevices = new JRadioButton("Devices");
		buttonDevices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "name", "type", "price", "brand" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Device> devices = dbManager.selectDevice("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < devices.size(); i++) {
					row[0] = devices.get(i).getID();
					row[1] = devices.get(i).getName();
					row[2] = devices.get(i).getType();
					row[3] = devices.get(i).getPrice();
					row[4] = devices.get(i).getBrand();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonDevices.setVerticalAlignment(SwingConstants.TOP);
		buttonDevices.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonDevices);
		buttonPane.add(buttonDevices);

		JRadioButton buttonDisease = new JRadioButton("Diseases");
		buttonDisease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "name", "description" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Disease> diseases = dbManager.selectDisease("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < diseases.size(); i++) {
					row[0] = diseases.get(i).getID();
					row[1] = diseases.get(i).getName();
					row[2] = diseases.get(i).getDescription();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonDisease.setVerticalAlignment(SwingConstants.TOP);
		buttonDisease.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonDisease);
		buttonPane.add(buttonDisease);

		JRadioButton buttonImage = new JRadioButton("Images");
		buttonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "description", "type","size","image" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Image> images = dbManager.selectImage("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < images.size(); i++) {
					row[0] = images.get(i).getID();
					row[1] = images.get(i).getDescription();
					row[2] = images.get(i).getType();
					row[3] = images.get(i).getSize();
					row[4] = images.get(i).getImage();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonImage.setVerticalAlignment(SwingConstants.TOP);
		buttonImage.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonImage);
		buttonPane.add(buttonImage);

		JRadioButton buttonPaper = new JRadioButton("Papers");
		buttonPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "title", "source" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Paper> papers = dbManager.selectPaper("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < papers.size(); i++) {
					row[0] = papers.get(i).getID();
					row[1] = papers.get(i).getTitle();
					row[2] = papers.get(i).getSource();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonPaper.setVerticalAlignment(SwingConstants.TOP);
		buttonPaper.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonPaper);
		buttonPane.add(buttonPaper);

		JRadioButton buttonProcedure = new JRadioButton("Procedures");
		buttonProcedure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "name", "description" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Procedure> procedures = dbManager.selectProcedure("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < procedures.size(); i++) {
					row[0] = procedures.get(i).getID();
					row[1] = procedures.get(i).getName();
					row[2] = procedures.get(i).getDescription();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonProcedure.setVerticalAlignment(SwingConstants.TOP);
		buttonProcedure.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonProcedure);
		buttonPane.add(buttonProcedure);

		JRadioButton buttonSymptom = new JRadioButton("Symptoms");
		buttonSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columns[] = { "ID", "name", "description" };
				DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
				mainTable.setModel(tableModel);
				ArrayList<Symptom> symptoms = dbManager.selectSymptom("all");
				Object[] row = new Object[tableModel.getColumnCount()];
				for (int i = 0; i < symptoms.size(); i++) {
					row[0] = symptoms.get(i).getID();
					row[1] = symptoms.get(i).getName();
					row[2] = symptoms.get(i).getDescription();
					tableModel.addRow(row);
				}
				mainTable.repaint();
			}
		});
		buttonSymptom.setVerticalAlignment(SwingConstants.TOP);
		buttonSymptom.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(buttonSymptom);
		buttonPane.add(buttonSymptom);

		JPanel panel = new JPanel();
		panel.setBounds(119, 266, 496, 88);
		contentPane.add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 11, 491, 244);
		contentPane.add(scrollPane);

		mainTable = new JTable();
		scrollPane.setViewportView(mainTable);
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
			dbManager.disconnect();
			jpaManager.disconnect();
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
			break;
		}
		case 2: {
			addBodyPart();
			break;
		}
		case 3: {
			addDevice();
			break;
		}
		case 4: {
			addDisease();
			break;
		}
		case 5: {
			addImage();
			break;
		}
		case 6: {
			addPaper();
			break;
		}
		case 7: {
			addProcedure();
			break;
		}
		case 8: {
			addSymptom();
			break;
		}
		case 9: {
			break;
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
		System.out.print("Name (must be unique): ");
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
		author = dbManager.selectAuthor(name).get(0); // Name is unique, so we
														// will always retrieve
														// only one. We use name
														// because we can not
														// access the id.
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
		for (Integer id2 : id) {
			dbManager.insertpaperauthor(id2, author.getID());
			Paper paper = dbManager.selectPaper(id2);
			author.addPaper(paper);
			paper.addAuthor(author);
		}
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
		System.out.print("Name (must be unique): ");
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
		bodyPart = dbManager.selectBodyPart(name).get(0); // Name is unique, so
															// it will always
															// retrieve only
															// one.
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
		for (Integer id2 : id) {
			Disease disease = dbManager.selectDisease(id2);
			bodyPart.addDisease(disease);
			disease.setBodypart(bodyPart);
			dbManager.updateDisease(id2, disease.getDescription(), bodyPart.getID());
		}
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
		dbManager.insertIntoDevice(device);
		System.out.println("Device inserted correctly.");
		device = dbManager.selectDevice(name).get(0);

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
		for (Integer id2 : id) {
			Procedure procedure = dbManager.selectProcedure(id2);
			device.setProcedure(procedure);
			procedure.addDevice(device);
			dbManager.updateDeviceWithProcedure(device.getID(), procedure.getID());
		}

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
		for (Integer id2 : id) {
			Paper paper = dbManager.selectPaper(id2);
			device.setPaper(paper);
			paper.addDevice(device);
			dbManager.updateDeviceWithPaper(device.getID(), paper.getID());
		}
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
		Disease disease = new Disease(name, description);
		dbManager.insertIntoDisease(disease);
		System.out.println("Disease inserted correctly.");
		disease = dbManager.selectDisease(name).get(0);

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
		for (Integer id2 : id) {
			BodyPart bodyPart = dbManager.selectBodyPart(id2);
			bodyPart.addDisease(disease);
			disease.setBodyPart(bodyPart);
			dbManager.updateDisease(disease.getID(), disease.getDescription(), bodyPart.getID());
		}
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
		for (Integer id2 : id) {
			Symptom symptom = dbManager.selectSymptom(id2);
			symptom.addDisease(disease);
			disease.addSymptom(symptom);
			dbManager.insertsymptomdisease(symptom.getID(), disease.getID());
		}

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
		for (Integer id2 : id) {
			Paper paper = dbManager.selectPaper(id2);
			paper.addDisease(disease);
			disease.addPaper(paper);
			dbManager.insertpaperdisease(paper.getID(), disease.getID());
		}

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
		for (Integer id2 : id) {
			Image image = dbManager.selectImage(id2);
			image.addDisease(disease);
			disease.addImage(image);
			dbManager.insertimagedisease(image.getID(), disease.getID());
		}

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
		for (Integer id2 : id) {
			Procedure procedure = dbManager.selectProcedure(id2);
			disease.addProcedure(procedure);
			procedure.addDisease(disease);
			dbManager.insertproceduredisease(procedure.getID(), disease.getID());
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

	public static byte[] askForImagePath(byte[] p) {
		while (p == null) {
			try {
				System.out.print("Please, write the address of the file you want to upload: ");
				read = console.readLine();
				String imageAdress = read;
				File photo = new File(imageAdress);
				p = dbManager.stringtobyte(photo);
				askForImagePath(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
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
		/*
		 * .out.
		 * print("Please, write the address of the file you want to upload: ");
		 * try { read = console.readLine(); } catch (IOException e) {
		 * e.printStackTrace(); } String imageAdress = read; File photo = new
		 * File(imageAdress); byte[] p = dbManager.stringtobyte(photo);
		 */
		byte[] p = askForImagePath(null);
		Image image = new Image(description, type, size, p);
		dbManager.insertIntoImage(image);
		System.out.println("Image inserted.");
		image = dbManager.selectImage(description).get(0);

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
		for (Integer id2 : id) {
			Paper paper = dbManager.selectPaper(id2);
			paper.addImage(image);
			image.setPaper(paper);
			dbManager.updateImage(image.getID(), image.getDescription(), paper.getID());
		}

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
		for (Integer id2 : id) {
			Disease disease = dbManager.selectDisease(id2);
			image.addDisease(disease);
			disease.addImage(image);
			dbManager.insertimagedisease(image.getID(), disease.getID());
		}
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
		Paper paper = new Paper(title, source);
		dbManager.insertIntoPaper(paper);
		System.out.println("Paper inserted.");
		paper = dbManager.selectPaper(title).get(0); // We can now access the
														// id.

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
		for (Integer id2 : id) {
			Author author = dbManager.selectAuthor(id2);
			paper.addAuthor(author);
			author.addPaper(paper);
			dbManager.insertpaperauthor(paper.getID(), author.getID());
		}

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
		for (Integer id2 : id) {
			Disease disease = dbManager.selectDisease(id2);
			paper.addDisease(disease);
			disease.addPaper(paper);
			dbManager.insertpaperdisease(paper.getID(), disease.getID());
		}

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
		for (Integer id2 : id) {
			Device device = dbManager.selectDevice(id2);
			device.setPaper(paper);
			paper.addDevice(device);
			dbManager.updateDeviceWithPaper(device.getID(), paper.getID());
		}

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
		for (Integer id2 : id) {
			Procedure procedure = dbManager.selectProcedure(id2);
			procedure.setPaper(paper);
			paper.addProcedure(procedure);
			dbManager.updateProcedureWithPaper(procedure.getID(), paper.getID());
		}

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
		for (Integer id2 : id) {
			Image image = dbManager.selectImage(id2);
			paper.addImage(image);
			image.setPaper(paper);
			dbManager.updateImage(image.getID(), image.getDescription(), paper.getID());
		}
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
		System.out.println("Procedure inserted.");
		procedure = dbManager.selectProcedure(name).get(0);

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
		for (Integer id2 : id) {
			Disease disease = dbManager.selectDisease(id2);
			procedure.addDisease(disease);
			disease.addProcedure(procedure);
			dbManager.insertproceduredisease(procedure.getID(), disease.getID());
		}

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
		for (Integer id2 : id) {
			Paper paper = dbManager.selectPaper(id2);
			procedure.setPaper(paper);
			paper.addProcedure(procedure);
			dbManager.updateProcedureWithPaper(procedure.getID(), paper.getID());
		}

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
		for (Integer id2 : id) {
			Device device = dbManager.selectDevice(id2);
			procedure.addDevice(device);
			device.setProcedure(procedure);
			dbManager.updateDeviceWithProcedure(device.getID(), procedure.getID());
		}

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
		jpaManager.createSymptomJPA(symptom);
		System.out.println("Symptom inserted");
		symptom = jpaManager.readSymptom(name);

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
		for (Integer id2 : id) {
			Disease disease = dbManager.selectDisease(id2);
			symptom.addDisease(disease);
			disease.addSymptom(symptom);
			dbManager.insertsymptomdisease(symptom.getID(), disease.getID());
		}
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
				jpaManager.deleteProcedureJPA(id);
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
			ArrayList<Author> list = dbManager.selectAuthor("all");
			if (list == null) {
				System.out.println("Error searching for the authors.");
			} else {
				for (Author author : list) {
					System.out.println(author);
				}
				showAuthor("all");
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

			if (list.size() == 0) {

				System.out.println("Error searching for the devices.");

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

				dbManager.updateDevice(deviceId, newPrice, newBrand);

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static void modifyDisease() {
		try {
			ArrayList<Disease> listD = dbManager.selectDisease("all");

			if (listD.size() == 0) {

				System.out.println("Error searching for the diseases.");

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

					dbManager.updateDisease(diseaseId, newProcedure, newBodyPart);

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static void modifyImage() {

		try {

			ArrayList<Image> listI = dbManager.selectImage("all");

			if (listI.size() == 0) {

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
				dbManager.updateImage(imageId, newDescription, newPaper);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static void modifyProcedure() {
		try {
			showProcedure("all");
			System.out.println("\nWhich is the procedure that you want to modify?" + "\nWrite its ID number:");
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
			Symptom symptom = jpaManager.readSymptom(name);

			System.out.println("Diseases:");
			List<Disease> listD = jpaManager.readDiseaseFromSymptomDisease(symptom.getID());
			for (Disease disease : listD) {
				if (disease != null) {
					System.out.println(disease.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showRelatedToProcedure(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Procedure procedure = jpaManager.readProcedure(name);

			System.out.println("Diseases:");
			List<Disease> listD = jpaManager.readDiseaseFromProcedureDisease(procedure.getID());
			for (Disease disease : listD) {
				if (disease != null) {
					System.out.println(disease.toString());
				}
			}

			System.out.println("Papers:");
			List<Integer> listI = jpaManager.readPaperRelatedToProcedure(procedure.getID());
			for (Integer id : listI) {
				if (id != null) {
					Paper paper = jpaManager.readPaper(id);
					System.out.println(paper.toString());
				}
			}

			System.out.println("Devices:");
			List<Integer> listDe = jpaManager.readDeviceRelatedToProcedure(procedure.getID());
			for (Integer id : listDe) {
				if (id != null) {
					Device device = jpaManager.readDevice(id);
					System.out.println(device.toString());
				}
			}
			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void showRelatedToPaper(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Paper paper = jpaManager.readPaper(name);

			System.out.println("Authors:");
			List<Author> listA = jpaManager.readAuthorFromPaperAuthor(paper.getID());
			for (Author author : listA) {
				if (author != null) {
					System.out.println(author.toString());
				}
			}

			System.out.println("Images:");
			List<Integer> listI = jpaManager.readImageRelatedToPaper(paper.getID());
			for (Integer id : listI) {
				if (id != null) {
					Image image = dbManager.selectImage(id);
					System.out.println(image.toString());
				}
			}

			System.out.println("Diseases:");
			List<Disease> listD = jpaManager.readDiseaseFromPaperDisease(paper.getID());
			for (Disease disease : listD) {
				if (disease != null) {
					System.out.println(disease.toString());
				}
			}

			System.out.println("Procedures:");
			List<Integer> listPr = jpaManager.readProcedureRelatedToPaper(paper.getID());
			for (Integer id : listPr) {
				if (id != null) {
					Procedure procedure = jpaManager.readProcedure(id);
					System.out.println(procedure.toString());
				}
			}

			System.out.println("Devices:");
			List<Integer> listDe = jpaManager.readDeviceRelatedToPaper(paper.getID());
			for (Integer id : listDe) {
				if (id != null) {
					Device device = jpaManager.readDevice(id);
					System.out.println(device.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showRelatedToImage(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Image image = dbManager.selectImage(name).get(0);

			System.out.println("Papers:");
			List<Integer> listP = jpaManager.readPaperRelatedToImage(image.getID());
			for (Integer id : listP) {
				if (id != null) {
					if (id != null) {
						Paper paper = jpaManager.readPaper(id);
						System.out.println(paper.toString());
					}
				}
			}

			System.out.println("\nDiseases:");
			List<Disease> listD = jpaManager.readDiseaseFromImageDisease(image.getID());
			for (Disease disease : listD) {
				if (disease != null) {
					System.out.println(disease.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showRelatedToDisease(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Disease disease = jpaManager.readDisease(name);

			System.out.println("Body parts:");
			List<Integer> listBP = jpaManager.readBodyPartRelatedToDisease(disease.getID());
			for (Integer id : listBP) {
				if (id != null) {
					BodyPart bodyPart = jpaManager.readBodyPart(id);
					System.out.println(bodyPart.toString());
				}
			}

			System.out.println("\nSymptoms:");
			List<Symptom> listS = jpaManager.readSymptomFromSymptomDisease(disease.getID());
			for (Symptom symptom : listS) {
				if (symptom != null) {
					System.out.println(symptom.toString());
				}
			}

			System.out.println("\nPapers:");
			List<Paper> listP = jpaManager.readPaperFromPaperDisease(disease.getID());
			for (Paper paper : listP) {
				if (paper != null) {
					System.out.println(paper.toString());
				}
			}

			System.out.println("\nImages:");
			List<Image> listI = dbManager.selectImageFromImageDisease(disease.getID());
			for (Image image : listI) {
				if (image != null) {
					System.out.println(image.toString());
				}
			}

			System.out.println("\nProcedures:");
			List<Procedure> listPr = jpaManager.readProcedureFromProcedureDisease(disease.getID());
			for (Procedure procedure : listPr) {
				if (procedure != null) {
					System.out.println(procedure.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void showRelatedToDevice(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Device device = jpaManager.readDevice(name);

			System.out.println("Procedures:");
			List<Integer> listD = jpaManager.readProcedureRelatedToDevice(device.getID());
			for (Integer id : listD) {
				if (id != null) {
					Procedure p = jpaManager.readProcedure(id);
					System.out.println(p.toString());
				}
			}

			System.out.println("\nPapers:");
			List<Integer> listP = jpaManager.readProcedureRelatedToDevice(device.getID());
			for (Integer id : listP) {
				if (id != null) {
					Paper paper = jpaManager.readPaper(id);
					System.out.println(paper.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showRelatedToBodyPart(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			BodyPart bodyPart = jpaManager.readBodyPart(name);

			System.out.println("\nDiseases:");
			List<Disease> listD = jpaManager.readDiseaseRelatedToBodyPart(bodyPart.getID());
			for (Disease disease : listD) {
				if (disease != null) {
					System.out.println(disease.toString());
				}
			}
			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
				System.out.print("ID: ");
				int ID = Integer.parseInt(console.readLine());
				viewRelated(category, ID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showRelatedToAuthor(String name) {
		String proceed = askIfViewRelated();
		if (proceed.equalsIgnoreCase("y")) {
			Author author = jpaManager.readAuthor(name);

			System.out.println("\nPapers:");
			List<Paper> listP = jpaManager.readPaperFromPaperAuthor(author.getID());
			for (Paper paper : listP) {
				if (paper != null) {
					System.out.println(paper.toString());
				}
			}

			try {
				System.out.println(
						"\nPlease, select the category and ID  of the item you want to view (Example: [paper,1]).");
				System.out.println("Write [none,0] to leave.");
				System.out.print("Category: ");
				String category = console.readLine().toLowerCase();
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

		switch (category) {
		case "author": {
			Author author = dbManager.selectAuthor(id);
			showAuthor(author.getName());
			showRelatedToAuthor(author.getName());
			break;
		}
		case "body part": {
			BodyPart bodyPart = dbManager.selectBodyPart(id);
			showBodyPart(bodyPart.getName());
			showRelatedToBodyPart(bodyPart.getName());
			break;
		}
		case "device": {
			Device device = dbManager.selectDevice(id);
			showDevice(device.getName());
			showRelatedToDevice(device.getName());
			break;
		}
		case "disease": {
			Disease disease = dbManager.selectDisease(id);
			showDisease(disease.getName());
			showRelatedToDisease(disease.getName());
			break;
		}
		case "pathology": {
			Disease disease = dbManager.selectDisease(id);
			showDisease(disease.getName());
			showRelatedToDisease(disease.getName());
			break;
		}
		case "image": {
			Image image = dbManager.selectImage(id);
			showImage(image.getDescription());
			showRelatedToImage(image.getDescription());
			break;
		}
		case "paper": {
			Paper paper = dbManager.selectPaper(id);
			showPaper(paper.getTitle());
			showRelatedToPaper(paper.getTitle());
			break;
		}
		case "article": {
			Paper paper = dbManager.selectPaper(id);
			showPaper(paper.getTitle());
			showRelatedToPaper(paper.getTitle());
			break;
		}
		case "procedure": {
			Procedure procedure = dbManager.selectProcedure(id);
			showProcedure(procedure.getName());
			showRelatedToProcedure(procedure.getName());
			break;
		}
		case "treatment": {
			Procedure procedure = dbManager.selectProcedure(id);
			showProcedure(procedure.getName());
			showRelatedToProcedure(procedure.getName());
			break;
		}
		case "symptom": {
			Symptom symptom = dbManager.selectSymptom(id);
			showSymptom(symptom.getName());
			showRelatedToSymptom(symptom.getName());
			break;
		}
		case "none": {
			return;
		}
		}
	}

	private static void convertXMLAuthor() {
		List<Author> authors = dbManager.selectAuthor("all");
		for (Author a : authors) {
			System.out.println(a.getID() + ": " + a.getName());
		}
		String aut = "";
		String fileName = "";
		System.out.print("Write the author's name (none to go back):");
		try {
			aut = console.readLine();
			if (aut.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLAuthor(aut, fileName);
	}
	
	private static void convertXMLBodyPart() {
		List<BodyPart> bodyparts = dbManager.selectBodyPart("all");
		for (BodyPart bp : bodyparts) {
			System.out.println(bp.getID() + ": " + bp.getName());
		}
		String bodyp = "";
		String fileName = "";
		System.out.print("Write the body part's name (none to go back):");
		try {
			bodyp = console.readLine();
			if (bodyp.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLBodyPart(bodyp, fileName);
	}
	
	private static void convertXMLDevice() {
		List<Device> devices = dbManager.selectDevice("all");
		for (Device d : devices) {
			System.out.println(d.getID() + ": " + d.getName());
		}
		String d = "";
		String fileName = "";
		System.out.print("Write the device's name (none to go back):");
		try {
			d = console.readLine();
			if (d.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLDevice(d, fileName);
	}
	
	private static void convertXMLDisease() {
		List<Disease> diseases = dbManager.selectDisease("all");
		for (Disease d : diseases) {
			System.out.println(d.getID() + ": " + d.getName());
		}
		String d = "";
		String fileName = "";
		System.out.print("Write the disease's name (none to go back):");
		try {
			d = console.readLine();
			if (d.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLDisease(d, fileName);
	}
	
	private static void convertXMLImage() {
		List<Image> images = dbManager.selectImage("all");
		for (Image i : images) {
			System.out.println(i.getID() + ": " + i.getDescription());
		}
		String i = "";
		String fileName = "";
		System.out.print("Write the image's description (none to go back):");
		try {
			i = console.readLine();
			if (i.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLImage(i, fileName);
	}
	
	private static void convertXMLPaper() {
		List<Paper> papers = dbManager.selectPaper("all");
		for (Paper p : papers) {
			System.out.println(p.getID() + ": " + p.getTitle());
		}
		String p = "";
		String fileName = "";
		System.out.print("Write the paper's title (none to go back):");
		try {
			p = console.readLine();
			if (p.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLPaper(p, fileName);
	}
	
	private static void convertXMLProcedure() {
		List<Procedure> procedures = dbManager.selectProcedure("all");
		for (Procedure p : procedures) {
			System.out.println(p.getID() + ": " + p.getName());
		}
		String p = "";
		String fileName = "";
		System.out.print("Write the procedure's name (none to go back):");
		try {
			p = console.readLine();
			if (p.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLProcedure(p, fileName);
	}
	
	private static void convertXMLSymptom() {
		List<Symptom> symptoms = dbManager.selectSymptom("all");
		for (Symptom s : symptoms) {
			System.out.println(s.getID() + ": " + s.getName());
		}
		String s = "";
		String fileName = "";
		System.out.print("Write the symptom's name (none to go back):");
		try {
			s = console.readLine();
			if (s.equalsIgnoreCase("none")) {
				return;
			}
			System.out.println("Write the path of the file where it is going to be saved: ");
			fileName = console.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);

		xmlm.marshalToXMLSymptom(s, fileName);
	}
	
	private static void convertJAVA() {
		System.out.print("\nPlease, select the item you want to convert: ");
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
			convertJavaAuthor();
			break;
		}
		case 2: {
			convertJavaBodyPart();
			break;
		}
		case 3: {
			convertJavaDevice();
			break;
		}
		case 4: {
			convertJavaDisease();
			break;
		}
		case 5: {
			convertJavaImage();
			break;
		}
		case 6: {
			convertJavaPaper();
			break;
		}
		case 7: {
			convertJavaProcedure();
			break;
		}
		case 8: {
			convertJavaSymptom();
			break;
		}
		case 9: {
			break;
		}
		}

	}

	private static void convertJavaAuthor() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaAuthor(fileName);
	}
	
	private static void convertJavaBodyPart() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaBodyPart(fileName);
	}
	
	private static void convertJavaDevice() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaDevice(fileName);
	}
	
	private static void convertJavaDisease() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaDisease(fileName);
	}
	
	private static void convertJavaImage() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaImage(fileName);
	}
	
	private static void convertJavaPaper() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaPaper(fileName);
	}
	
	private static void convertJavaProcedure() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaProcedure(fileName);
	}
	
	private static void convertJavaSymptom() {
		String fileName = "";
		System.out.print("Write the path of the XML file: ");
		try {
			fileName = console.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		XmlManager xmlm = new XmlManager(dbManager);
		xmlm.unmarshalToJavaSymptom(fileName);
	}
}