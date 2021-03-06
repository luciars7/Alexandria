package userInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import xmls.Xml2HtmlPaper;
import xmls.XmlManager;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JRadioButtonMenuItem;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.ImageFilter;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

public class GraphicUserInterface extends JFrame {
	static Connection c = null;
	static DBManager dbManager = null;
	static JpaManager jpaManager = null;
	static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	static String read = null;
	static File file;

	private static JPanel contentPane;
	private static ButtonGroup buttonGroupPojos = new ButtonGroup();
	private static JTable mainTable;
	static JTextArea textAreaRelated;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUserInterface frame = new GraphicUserInterface();
					paintAuthors();
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
		setBounds(100, 100, 641, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) buttonPane.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		buttonPane.setBounds(10, 11, 104, 244);
		contentPane.add(buttonPane);

		JRadioButton buttonAuthor = new JRadioButton("Authors");
		buttonAuthor.setSelected(true);
		buttonAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintAuthors();
			}
		});
		buttonAuthor.setVerticalAlignment(SwingConstants.TOP);
		buttonAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonAuthor);
		buttonPane.add(buttonAuthor);

		JRadioButton buttonBodyPart = new JRadioButton("Body parts");
		buttonBodyPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintBodyParts();
			}
		});
		buttonBodyPart.setVerticalAlignment(SwingConstants.TOP);
		buttonBodyPart.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonBodyPart);
		buttonPane.add(buttonBodyPart);

		JRadioButton buttonDevices = new JRadioButton("Devices");
		buttonDevices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintDevices();
			}
		});
		buttonDevices.setVerticalAlignment(SwingConstants.TOP);
		buttonDevices.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonDevices);
		buttonPane.add(buttonDevices);

		JRadioButton buttonDisease = new JRadioButton("Diseases");
		buttonDisease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintDiseases();
			}
		});
		buttonDisease.setVerticalAlignment(SwingConstants.TOP);
		buttonDisease.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonDisease);
		buttonPane.add(buttonDisease);

		JRadioButton buttonImage = new JRadioButton("Images");
		buttonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintImages();
			}
		});
		buttonImage.setVerticalAlignment(SwingConstants.TOP);
		buttonImage.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonImage);
		buttonPane.add(buttonImage);

		JRadioButton buttonPaper = new JRadioButton("Papers");
		buttonPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintPapers();
			}
		});
		buttonPaper.setVerticalAlignment(SwingConstants.TOP);
		buttonPaper.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonPaper);
		buttonPane.add(buttonPaper);

		JRadioButton buttonProcedure = new JRadioButton("Procedures");
		buttonProcedure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintProcedures();
			}
		});
		buttonProcedure.setVerticalAlignment(SwingConstants.TOP);
		buttonProcedure.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonProcedure);
		buttonPane.add(buttonProcedure);

		JRadioButton buttonSymptom = new JRadioButton("Symptoms");
		buttonSymptom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintSymptoms();
			}
		});
		buttonSymptom.setVerticalAlignment(SwingConstants.TOP);
		buttonSymptom.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroupPojos.add(buttonSymptom);
		buttonPane.add(buttonSymptom);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(124, 266, 491, 149);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 471, 127);
		panel.add(scrollPane_1);

		textAreaRelated = new JTextArea();
		scrollPane_1.setViewportView(textAreaRelated);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(124, 11, 491, 190);
		contentPane.add(scrollPane);

		mainTable = new JTable();
		mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// System.out.println(mainTable.getValueAt(mainTable.getSelectedRow(),
				// 0).toString());
			}
		});

		scrollPane.setViewportView(mainTable);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(124, 212, 491, 43);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton newEntityButton = new JButton("Add element");
		newEntityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					addAuthor();
					break;
				}
				case "Body parts": {
					addBodyPart();
					break;
				}
				case "Devices": {
					addDevice();
					break;
				}
				case "Diseases": {
					addDisease();
					break;
				}
				case "Images": {
					addImage();
					break;
				}
				case "Papers": {
					addPaper();
					break;
				}
				case "Procedures": {
					addProcedure();
					break;
				}
				case "Symptoms": {
					addSymptom();
					break;
				}
				}

			}
		});
		newEntityButton.setBounds(10, 11, 153, 23);
		panel_1.add(newEntityButton);

		JButton modifyElementButton = new JButton("Modify element");
		modifyElementButton.setBounds(173, 11, 153, 23);
		panel_1.add(modifyElementButton);

		JButton deleteElementButton = new JButton("Delete element");
		deleteElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					deleteAuthor();
					break;
				}
				case "Body parts": {
					deleteBodyPart();
					break;
				}
				case "Devices": {
					deleteDevice();
					break;
				}
				case "Diseases": {
					deleteDisease();
					break;
				}
				case "Images": {
					deleteImage();
					break;
				}
				case "Papers": {
					deletePaper();
					break;
				}
				case "Procedures": {
					deleteProcedure();
					break;
				}
				case "Symptoms": {
					deleteSymptom();
					break;
				}
				}
			}
		});
		deleteElementButton.setBounds(336, 11, 145, 23);
		panel_1.add(deleteElementButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 266, 104, 149);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnToXml = new JButton("To XML");
		btnToXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					convertXMLAuthor();
					break;
				}
				case "Body parts": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Devices": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Diseases": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Images": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Papers": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Procedures": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Symptoms": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				}

			}
		});
		btnToXml.setBounds(10, 11, 84, 23);
		panel_2.add(btnToXml);

		JButton btnToJava = new JButton("To Java");
		btnToJava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					convertJavaAuthor();
					break;
				}
				case "Body parts": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Devices": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Diseases": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Images": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Papers": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Procedures": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				case "Symptoms": {
					JOptionPane.showMessageDialog(contentPane,
							"This option is only available for authors. Please, select one.");
					break;
				}
				}
			}
		});
		btnToJava.setBounds(10, 45, 84, 23);
		panel_2.add(btnToJava);

		JButton btnToHtml = new JButton("To HTML");
		btnToHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Body parts": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Devices": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Diseases": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Images": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Papers": {
					showHTML();
					break;
				}
				case "Procedures": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				case "Symptoms": {
					JOptionPane.showMessageDialog(contentPane, "This option is only available for papers.");
					break;
				}
				}

			}
		});
		btnToHtml.setBounds(10, 79, 84, 23);
		panel_2.add(btnToHtml);

		JButton btnRelated = new JButton("Related");
		btnRelated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					showRelatedToAuthor();
					break;
				}
				case "Body parts": {
					showRelatedToBodyPart();
					break;
				}
				case "Devices": {
					showRelatedToDevice();
					break;
				}
				case "Diseases": {
					showRelatedToDisease();
					break;
				}
				case "Images": {
					showRelatedToImage();
					break;
				}
				case "Papers": {
					showRelatedToPaper();
					break;
				}
				case "Procedures": {
					showRelatedToProcedure();
					break;
				}
				case "Symptoms": {
					showRelatedToSymptom();
					break;
				}

				}
			}
		});
		btnRelated.setBounds(10, 113, 84, 23);
		panel_2.add(btnRelated);
		modifyElementButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String selectedButton = getSelectedButtonText();
				switch (selectedButton) {
				case "Authors": {
					modifyAuthor();
					break;
				}
				case "Body parts": {
					JOptionPane.showMessageDialog(contentPane, "Body parts can not be modified.");
					break;
				}
				case "Devices": {
					modifyDevice();
					break;
				}
				case "Diseases": {
					modifyDisease();
					break;
				}
				case "Images": {
					modifyImage();
					break;
				}
				case "Papers": {
					JOptionPane.showMessageDialog(contentPane, "Papers can not be modified.");
					break;
				}
				case "Procedures": {
					modifyProcedure();
					break;
				}
				case "Symptoms": {
					JOptionPane.showMessageDialog(contentPane, "Symptoms can not be modified.");
					break;
				}
				}

			}

		});
	}

	public String getSelectedButtonText() {
		for (Enumeration<AbstractButton> buttons = buttonGroupPojos.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	public static void checkTables() {
		System.out.println("Checking for the tables...");
		boolean result = dbManager.checkTables();
		if (result == true) {
			System.out.println("Tables already exist.");
		} else {
			JOptionPane.showMessageDialog(contentPane, "Tables didn't exist. They have been created.");
		}
	}

	public static void paintAuthors() {
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

	public static void paintBodyParts() {

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

	public static void paintDevices() {

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

	public static void paintDiseases() {

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

	public static void paintImages() {

		String columns[] = { "ID", "description", "type", "size", "image" };
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

	public static void paintPapers() {

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

	public static void paintProcedures() {

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

	public static void paintSymptoms() {
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
		JPanel contentPane;
		JTextField textFieldName;
		JTextField textFieldOrigin;
		JTextField textFieldAssociation;
		JButton btnCancel;
		JLabel lblRelatedElemts;
		JLabel lblPapers;
		JList list;
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("New author");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel labelOrigin = new JLabel("Origin");
		GridBagConstraints gbc_labelOrigin = new GridBagConstraints();
		gbc_labelOrigin.anchor = GridBagConstraints.EAST;
		gbc_labelOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_labelOrigin.gridx = 0;
		gbc_labelOrigin.gridy = 1;
		contentPane.add(labelOrigin, gbc_labelOrigin);

		textFieldOrigin = new JTextField();
		GridBagConstraints gbc_textFieldOrigin = new GridBagConstraints();
		gbc_textFieldOrigin.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldOrigin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOrigin.gridx = 1;
		gbc_textFieldOrigin.gridy = 1;
		contentPane.add(textFieldOrigin, gbc_textFieldOrigin);
		textFieldOrigin.setColumns(10);

		JLabel labelAssociation = new JLabel("Association");
		GridBagConstraints gbc_labelAssociation = new GridBagConstraints();
		gbc_labelAssociation.anchor = GridBagConstraints.EAST;
		gbc_labelAssociation.insets = new Insets(0, 0, 5, 5);
		gbc_labelAssociation.gridx = 0;
		gbc_labelAssociation.gridy = 2;
		contentPane.add(labelAssociation, gbc_labelAssociation);

		textFieldAssociation = new JTextField();
		GridBagConstraints gbc_textFieldAssociation = new GridBagConstraints();
		gbc_textFieldAssociation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAssociation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAssociation.gridx = 1;
		gbc_textFieldAssociation.gridy = 2;
		contentPane.add(textFieldAssociation, gbc_textFieldAssociation);
		textFieldAssociation.setColumns(10);

		lblRelatedElemts = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElemts = new GridBagConstraints();
		gbc_lblRelatedElemts.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElemts.gridx = 0;
		gbc_lblRelatedElemts.gridy = 4;
		contentPane.add(lblRelatedElemts, gbc_lblRelatedElemts);

		lblPapers = new JLabel("Papers");
		GridBagConstraints gbc_lblPapers = new GridBagConstraints();
		gbc_lblPapers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPapers.gridx = 0;
		gbc_lblPapers.gridy = 5;
		contentPane.add(lblPapers, gbc_lblPapers);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		list = new JList(papers.toArray());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 5;
		contentPane.add(list, gbc_list);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String association = textFieldOrigin.getText();
				String origin = textFieldAssociation.getText();
				Author author = new Author(name, origin, association);
				dbManager.insertIntoAuthor(author);
				author = dbManager.selectAuthor(name).get(0);
				int[] selected = list.getSelectedIndices();
				for (int i : selected) {
					Paper paper = (Paper) list.getModel().getElementAt(i);
					dbManager.insertpaperauthor(paper.getID(), author.getID());
				}
				frame.setVisible(false);
				frame.dispose();
				paintAuthors();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 7;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 8;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

	public static void addBodyPart() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		JPanel contentPane;
		JTextField textFieldName;
		JTextField textFieldLocation;
		JButton btnCancel;
		JLabel lblRelatedElemts;
		JLabel lblDiseases;
		JList listDiseases;
		frame.setTitle("New Disease");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel labelLocation = new JLabel("Location");
		GridBagConstraints gbc_labelLocation = new GridBagConstraints();
		gbc_labelLocation.anchor = GridBagConstraints.EAST;
		gbc_labelLocation.insets = new Insets(0, 0, 5, 5);
		gbc_labelLocation.gridx = 0;
		gbc_labelLocation.gridy = 1;
		contentPane.add(labelLocation, gbc_labelLocation);

		textFieldLocation = new JTextField();
		GridBagConstraints gbc_textFieldLocation = new GridBagConstraints();
		gbc_textFieldLocation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLocation.gridx = 1;
		gbc_textFieldLocation.gridy = 1;
		contentPane.add(textFieldLocation, gbc_textFieldLocation);
		textFieldLocation.setColumns(10);

		lblRelatedElemts = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElemts = new GridBagConstraints();
		gbc_lblRelatedElemts.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElemts.gridx = 0;
		gbc_lblRelatedElemts.gridy = 4;
		contentPane.add(lblRelatedElemts, gbc_lblRelatedElemts);

		lblDiseases = new JLabel("Diseases");
		GridBagConstraints gbc_lblDiseases = new GridBagConstraints();
		gbc_lblDiseases.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiseases.gridx = 0;
		gbc_lblDiseases.gridy = 5;
		contentPane.add(lblDiseases, gbc_lblDiseases);

		ArrayList<Disease> diseases = dbManager.selectDisease("all");
		listDiseases = new JList(diseases.toArray());
		listDiseases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listDiseases = new GridBagConstraints();
		gbc_listDiseases.insets = new Insets(0, 0, 5, 0);
		gbc_listDiseases.fill = GridBagConstraints.BOTH;
		gbc_listDiseases.gridx = 1;
		gbc_listDiseases.gridy = 5;
		contentPane.add(listDiseases, gbc_listDiseases);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String location = textFieldLocation.getText();
				BodyPart bodyPart = new BodyPart(name, location);
				dbManager.insertIntoBodyPart(bodyPart);
				bodyPart = dbManager.selectBodyPart(name).get(0);
				int[] selected = listDiseases.getSelectedIndices();
				for (int i : selected) {
					Disease disease = (Disease) listDiseases.getModel().getElementAt(i);
					dbManager.updateDisease(disease.getID(), disease.getDescription(), bodyPart.getID());
				}
				frame.setVisible(false);
				frame.dispose();
				paintBodyParts();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 7;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 8;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

	public static void addDevice() {
		JPanel contentPane;
		JTextField textFieldName;
		JTextField textFieldType;
		JButton btnCancel;
		JLabel lblPrice;
		JTextField textFieldPrice;
		JLabel lblBrand;
		JTextField textFieldBrand;
		JLabel lblRelatedElements;
		JLabel lblProcedures;
		JList listProcedures;
		JLabel lblPapers;
		JList listPapers;
		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setTitle("New Device");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel labelType = new JLabel("Type");
		GridBagConstraints gbc_labelType = new GridBagConstraints();
		gbc_labelType.anchor = GridBagConstraints.EAST;
		gbc_labelType.insets = new Insets(0, 0, 5, 5);
		gbc_labelType.gridx = 0;
		gbc_labelType.gridy = 1;
		contentPane.add(labelType, gbc_labelType);

		textFieldType = new JTextField();
		GridBagConstraints gbc_textFieldType = new GridBagConstraints();
		gbc_textFieldType.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldType.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldType.gridx = 1;
		gbc_textFieldType.gridy = 1;
		contentPane.add(textFieldType, gbc_textFieldType);
		textFieldType.setColumns(10);

		lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 2;
		contentPane.add(lblPrice, gbc_lblPrice);

		textFieldPrice = new JTextField();
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrice.gridx = 1;
		gbc_textFieldPrice.gridy = 2;
		contentPane.add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(10);

		lblBrand = new JLabel("Brand");
		GridBagConstraints gbc_lblBrand = new GridBagConstraints();
		gbc_lblBrand.anchor = GridBagConstraints.EAST;
		gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrand.gridx = 0;
		gbc_lblBrand.gridy = 3;
		contentPane.add(lblBrand, gbc_lblBrand);

		textFieldBrand = new JTextField();
		GridBagConstraints gbc_textFieldBrand = new GridBagConstraints();
		gbc_textFieldBrand.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldBrand.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBrand.gridx = 1;
		gbc_textFieldBrand.gridy = 3;
		contentPane.add(textFieldBrand, gbc_textFieldBrand);
		textFieldBrand.setColumns(10);

		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 6;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);

		lblProcedures = new JLabel("Procedures");
		GridBagConstraints gbc_lblProcedures = new GridBagConstraints();
		gbc_lblProcedures.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcedures.gridx = 0;
		gbc_lblProcedures.gridy = 7;
		contentPane.add(lblProcedures, gbc_lblProcedures);

		ArrayList<Procedure> procedure = dbManager.selectProcedure("all");
		listProcedures = new JList(procedure.toArray());
		listProcedures.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listProcedures = new GridBagConstraints();
		gbc_listProcedures.insets = new Insets(0, 0, 5, 0);
		gbc_listProcedures.fill = GridBagConstraints.BOTH;
		gbc_listProcedures.gridx = 1;
		gbc_listProcedures.gridy = 7;
		contentPane.add(listProcedures, gbc_listProcedures);

		lblPapers = new JLabel("Papers");
		GridBagConstraints gbc_lblPapers = new GridBagConstraints();
		gbc_lblPapers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPapers.gridx = 0;
		gbc_lblPapers.gridy = 8;
		contentPane.add(lblPapers, gbc_lblPapers);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		listPapers = new JList(papers.toArray());
		listPapers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listPapers = new GridBagConstraints();
		gbc_listPapers.insets = new Insets(0, 0, 5, 0);
		gbc_listPapers.fill = GridBagConstraints.BOTH;
		gbc_listPapers.gridx = 1;
		gbc_listPapers.gridy = 8;
		contentPane.add(listPapers, gbc_listPapers);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String type = textFieldType.getText();
				Float price = Float.parseFloat(textFieldPrice.getText());
				String brand = textFieldBrand.getText();
				Device device = new Device(name, type, price, brand);
				dbManager.insertIntoDevice(device);
				device = dbManager.selectDevice(name).get(0);
				int[] selected = listProcedures.getSelectedIndices();
				for (int i : selected) {
					Procedure procedure = (Procedure) listProcedures.getModel().getElementAt(i);
					device.setProcedure(procedure);
					procedure.addDevice(device);
					dbManager.updateDeviceWithProcedure(device.getID(), procedure.getID());
				}
				selected = listPapers.getSelectedIndices();
				for (int i : selected) {
					Paper paper = (Paper) listPapers.getModel().getElementAt(i);
					device.setPaper(paper);
					paper.addDevice(device);
					dbManager.updateDeviceWithPaper(device.getID(), paper.getID());
				}
				frame.setVisible(false);
				frame.dispose();
				paintDevices();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 11;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 12;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

	public static void addDisease() {
		JPanel contentPane;
		JTextField textFieldName;
		JTextField textFieldDescription;
		JButton btnCancel;
		JLabel lblRelatedElements;
		JLabel lblBodyParts;
		JList listBodyParts;
		JLabel lblSymptoms;
		JList listSymptoms;
		JLabel lblProcedures;
		JList listProcedures;
		JLabel lblPapers;
		JList listPapers;
		JLabel lblImages;
		JList listImages;

		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setTitle("New Disease");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel labelDescription = new JLabel("Description");
		GridBagConstraints gbc_labelDescription = new GridBagConstraints();
		gbc_labelDescription.anchor = GridBagConstraints.EAST;
		gbc_labelDescription.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescription.gridx = 0;
		gbc_labelDescription.gridy = 1;
		contentPane.add(labelDescription, gbc_labelDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		contentPane.add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 5;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);

		lblBodyParts = new JLabel("Body parts");
		GridBagConstraints gbc_lblBodyParts = new GridBagConstraints();
		gbc_lblBodyParts.insets = new Insets(0, 0, 5, 5);
		gbc_lblBodyParts.gridx = 0;
		gbc_lblBodyParts.gridy = 7;
		contentPane.add(lblBodyParts, gbc_lblBodyParts);

		ArrayList<BodyPart> bodyParts = dbManager.selectBodyPart("all");
		listBodyParts = new JList(bodyParts.toArray());
		listBodyParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listBodyParts = new GridBagConstraints();
		gbc_listBodyParts.insets = new Insets(0, 0, 5, 0);
		gbc_listBodyParts.fill = GridBagConstraints.BOTH;
		gbc_listBodyParts.gridx = 1;
		gbc_listBodyParts.gridy = 7;
		contentPane.add(listBodyParts, gbc_listBodyParts);

		lblSymptoms = new JLabel("Symptoms");
		GridBagConstraints gbc_lblSymptoms = new GridBagConstraints();
		gbc_lblSymptoms.insets = new Insets(0, 0, 5, 5);
		gbc_lblSymptoms.gridx = 0;
		gbc_lblSymptoms.gridy = 8;
		contentPane.add(lblSymptoms, gbc_lblSymptoms);

		ArrayList<Symptom> symptoms = dbManager.selectSymptom("all");
		listSymptoms = new JList(symptoms.toArray());
		listSymptoms.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listSymptoms = new GridBagConstraints();
		gbc_listSymptoms.insets = new Insets(0, 0, 5, 0);
		gbc_listSymptoms.fill = GridBagConstraints.BOTH;
		gbc_listSymptoms.gridx = 1;
		gbc_listSymptoms.gridy = 8;
		contentPane.add(listSymptoms, gbc_listSymptoms);

		lblProcedures = new JLabel("Procedures");
		GridBagConstraints gbc_lblProcedures = new GridBagConstraints();
		gbc_lblProcedures.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcedures.gridx = 0;
		gbc_lblProcedures.gridy = 9;
		contentPane.add(lblProcedures, gbc_lblProcedures);

		ArrayList<Procedure> procedures = dbManager.selectProcedure("all");
		listProcedures = new JList(procedures.toArray());
		listProcedures.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listProcedures = new GridBagConstraints();
		gbc_listProcedures.insets = new Insets(0, 0, 5, 0);
		gbc_listProcedures.fill = GridBagConstraints.BOTH;
		gbc_listProcedures.gridx = 1;
		gbc_listProcedures.gridy = 9;
		contentPane.add(listProcedures, gbc_listProcedures);

		lblPapers = new JLabel("Papers");
		GridBagConstraints gbc_lblPapers = new GridBagConstraints();
		gbc_lblPapers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPapers.gridx = 0;
		gbc_lblPapers.gridy = 10;
		contentPane.add(lblPapers, gbc_lblPapers);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		listPapers = new JList(papers.toArray());
		listPapers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listPapers = new GridBagConstraints();
		gbc_listPapers.insets = new Insets(0, 0, 5, 0);
		gbc_listPapers.fill = GridBagConstraints.BOTH;
		gbc_listPapers.gridx = 1;
		gbc_listPapers.gridy = 10;
		contentPane.add(listPapers, gbc_listPapers);

		lblImages = new JLabel("Images");
		GridBagConstraints gbc_lblImages = new GridBagConstraints();
		gbc_lblImages.insets = new Insets(0, 0, 5, 5);
		gbc_lblImages.gridx = 0;
		gbc_lblImages.gridy = 11;
		contentPane.add(lblImages, gbc_lblImages);

		ArrayList<Image> images = dbManager.selectImage("all");
		listImages = new JList(images.toArray());
		listImages.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listImages = new GridBagConstraints();
		gbc_listImages.insets = new Insets(0, 0, 5, 0);
		gbc_listImages.fill = GridBagConstraints.BOTH;
		gbc_listImages.gridx = 1;
		gbc_listImages.gridy = 11;
		contentPane.add(listImages, gbc_listImages);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String description = textFieldDescription.getText();
				Disease disease = new Disease(name, description);
				dbManager.insertIntoDisease(disease);
				disease = dbManager.selectDisease(name).get(0);
				int[] selected = listBodyParts.getSelectedIndices();
				for (int i : selected) {
					BodyPart bodyPart = dbManager.selectBodyPart(i);
					bodyPart.addDisease(disease);
					disease.setBodyPart(bodyPart);
					dbManager.updateDisease(disease.getID(), disease.getDescription(), bodyPart.getID());
				}
				selected = listSymptoms.getSelectedIndices();
				for (int i : selected) {
					Symptom symptom = dbManager.selectSymptom(i);
					symptom.addDisease(disease);
					disease.addSymptom(symptom);
					dbManager.insertsymptomdisease(symptom.getID(), disease.getID());
				}
				selected = listProcedures.getSelectedIndices();
				for (int i : selected) {
					Procedure procedure = dbManager.selectProcedure(i);
					disease.addProcedure(procedure);
					procedure.addDisease(disease);
					dbManager.insertproceduredisease(procedure.getID(), disease.getID());
				}
				selected = listPapers.getSelectedIndices();
				for (int i : selected) {
					Paper paper = dbManager.selectPaper(i);
					paper.addDisease(disease);
					disease.addPaper(paper);
					dbManager.insertpaperdisease(paper.getID(), disease.getID());
				}
				selected = listImages.getSelectedIndices();
				for (int i : selected) {
					Image image = dbManager.selectImage(i);
					image.addDisease(disease);
					disease.addImage(image);
					dbManager.insertimagedisease(image.getID(), disease.getID());
				}

				frame.setVisible(false);
				frame.dispose();
				paintDiseases();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 13;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 14;
		contentPane.add(btnCancel, gbc_btnCancel);
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
		final JPanel contentPanel = new JPanel();
		JTextField textFieldDescription;
		JTextField textFieldType;
		JTextField textFieldSize;
		JTextField textFieldLocation;

		JFrame dialog = new JFrame();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		dialog.setBounds(100, 100, 270, 423);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDescription = new JLabel("Description:");
			lblDescription.setBounds(10, 13, 81, 14);
			contentPanel.add(lblDescription);
		}
		{
			textFieldDescription = new JTextField();
			textFieldDescription.setBounds(121, 10, 123, 20);
			contentPanel.add(textFieldDescription);
			textFieldDescription.setColumns(10);
		}

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 38, 46, 14);
		contentPanel.add(lblType);

		textFieldType = new JTextField();
		textFieldType.setBounds(121, 35, 123, 20);
		contentPanel.add(textFieldType);
		textFieldType.setColumns(10);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(10, 63, 46, 14);
		contentPanel.add(lblSize);

		textFieldSize = new JTextField();
		textFieldSize.setBounds(121, 60, 123, 20);
		contentPanel.add(textFieldSize);
		textFieldSize.setColumns(10);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(10, 88, 70, 14);
		contentPanel.add(lblLocation);

		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(121, 85, 123, 20);
		contentPanel.add(textFieldLocation);
		textFieldLocation.setColumns(10);

		JLabel lblRelatedElements = new JLabel("Related elements");
		lblRelatedElements.setBounds(10, 130, 123, 14);
		contentPanel.add(lblRelatedElements);

		JLabel lblPapers = new JLabel("Papers:");
		lblPapers.setBounds(10, 155, 81, 14);
		contentPanel.add(lblPapers);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(121, 154, 123, 80);
		contentPanel.add(scrollPane);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		JList listPapers = new JList(papers.toArray());
		listPapers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listPapers);

		JLabel lblDiseases = new JLabel("Diseases:");
		lblDiseases.setBounds(10, 251, 93, 14);
		contentPanel.add(lblDiseases);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(120, 251, 124, 78);
		contentPanel.add(scrollPane_1);

		ArrayList<Disease> diseases = dbManager.selectDisease("all");
		JList listDiseases = new JList(diseases.toArray());
		listDiseases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_1.setViewportView(listDiseases);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String description = textFieldDescription.getText();
						String type = textFieldType.getText();
						String size = textFieldSize.getText();
						String location = textFieldLocation.getText();
						File file = new File(location);
						byte[] p = dbManager.stringtobyte(file);
						Image image  = new Image(description,type,size,p);
						dbManager.insertIntoImage(image);
						image = dbManager.selectImage(description).get(0);
						int[] selected = listPapers.getSelectedIndices();
						for (int i : selected) {
							Paper paper = dbManager.selectPaper(i);
							paper.addImage(image);
							image.setPaper(paper);
							dbManager.updateImage(image.getID(), image.getDescription(), paper.getID());
						}
						selected = listDiseases.getSelectedIndices();
						for (int i : selected) {
							Disease disease = dbManager.selectDisease(i);
							image.addDisease(disease);
							disease.addImage(image);
							dbManager.insertimagedisease(image.getID(), disease.getID());
						}
						
						dialog.setVisible(false);
						dialog.dispose();
						paintImages();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				dialog.getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void addPaper() {
		JPanel contentPane;
		JTextField textFieldTitle;
		JButton btnCancel;
		JLabel lblRelatedElements;
		JLabel lblSource;
		JTextField textFieldSource;
		JLabel lblDiseases;
		JList listDiseases;
		JScrollPane scrollPane;
		JLabel lblProcedures;
		JList listProcedures;
		JScrollPane scrollPane_1;
		JLabel lblDevices;
		JList listDevices;
		JScrollPane scrollPane_2;
		JLabel lblAuthors;
		JList listAuthors;
		JScrollPane scrollPane_3;
		JLabel lblImages;
		JList listImages;
		JScrollPane scrollPane_4;

		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setTitle("New Paper");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelTitle = new JLabel("Title");
		GridBagConstraints gbc_labelTitle = new GridBagConstraints();
		gbc_labelTitle.anchor = GridBagConstraints.EAST;
		gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
		gbc_labelTitle.gridx = 0;
		gbc_labelTitle.gridy = 0;
		contentPane.add(labelTitle, gbc_labelTitle);

		textFieldTitle = new JTextField();
		GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
		gbc_textFieldTitle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitle.gridx = 1;
		gbc_textFieldTitle.gridy = 0;
		contentPane.add(textFieldTitle, gbc_textFieldTitle);
		textFieldTitle.setColumns(10);

		lblSource = new JLabel("Source");
		GridBagConstraints gbc_lblSource = new GridBagConstraints();
		gbc_lblSource.anchor = GridBagConstraints.EAST;
		gbc_lblSource.insets = new Insets(0, 0, 5, 5);
		gbc_lblSource.gridx = 0;
		gbc_lblSource.gridy = 1;
		contentPane.add(lblSource, gbc_lblSource);

		textFieldSource = new JTextField();
		GridBagConstraints gbc_textFieldSource = new GridBagConstraints();
		gbc_textFieldSource.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSource.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSource.gridx = 1;
		gbc_textFieldSource.gridy = 1;
		contentPane.add(textFieldSource, gbc_textFieldSource);
		textFieldSource.setColumns(10);

		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 2;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);

		lblDiseases = new JLabel("Diseases");
		GridBagConstraints gbc_lblDiseases = new GridBagConstraints();
		gbc_lblDiseases.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiseases.gridx = 0;
		gbc_lblDiseases.gridy = 3;
		contentPane.add(lblDiseases, gbc_lblDiseases);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);

		ArrayList<Disease> diseases = dbManager.selectDisease("all");
		listDiseases = new JList(diseases.toArray());
		listDiseases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(listDiseases);

		lblProcedures = new JLabel("Procedures");
		GridBagConstraints gbc_lblProcedures = new GridBagConstraints();
		gbc_lblProcedures.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcedures.gridx = 0;
		gbc_lblProcedures.gridy = 4;
		contentPane.add(lblProcedures, gbc_lblProcedures);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		ArrayList<Procedure> procedures = dbManager.selectProcedure("all");
		listProcedures = new JList(procedures.toArray());
		listProcedures.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_1.setViewportView(listProcedures);

		lblDevices = new JLabel("Devices");
		GridBagConstraints gbc_lblDevices = new GridBagConstraints();
		gbc_lblDevices.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevices.gridx = 0;
		gbc_lblDevices.gridy = 5;
		contentPane.add(lblDevices, gbc_lblDevices);

		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 5;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);

		ArrayList<Device> devices = dbManager.selectDevice("all");
		listDevices = new JList(devices.toArray());
		listDevices.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_2.setViewportView(listDevices);

		lblAuthors = new JLabel("Authors");
		GridBagConstraints gbc_lblAuthors = new GridBagConstraints();
		gbc_lblAuthors.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthors.gridx = 0;
		gbc_lblAuthors.gridy = 6;
		contentPane.add(lblAuthors, gbc_lblAuthors);

		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 6;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);

		ArrayList<Author> authors = dbManager.selectAuthor("all");
		listAuthors = new JList(authors.toArray());
		listAuthors.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_3.setViewportView(listAuthors);

		lblImages = new JLabel("Images");
		GridBagConstraints gbc_lblImages = new GridBagConstraints();
		gbc_lblImages.insets = new Insets(0, 0, 5, 5);
		gbc_lblImages.gridx = 0;
		gbc_lblImages.gridy = 7;
		contentPane.add(lblImages, gbc_lblImages);

		scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 7;
		contentPane.add(scrollPane_4, gbc_scrollPane_4);

		ArrayList<Image> images = dbManager.selectImage("all");
		listImages = new JList(images.toArray());
		listImages.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_4.setViewportView(listImages);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = textFieldTitle.getText();
				String source = textFieldSource.getText();
				Paper paper = new Paper(title, source);
				dbManager.insertIntoPaper(paper);
				paper = dbManager.selectPaper(title).get(0);
				int[] selected = listDiseases.getSelectedIndices();
				for (int i : selected) {
					Disease disease = dbManager.selectDisease(i);
					paper.addDisease(disease);
					disease.addPaper(paper);
					dbManager.insertpaperdisease(paper.getID(), disease.getID());
				}
				selected = listProcedures.getSelectedIndices();
				for (int i : selected) {
					Procedure procedure = dbManager.selectProcedure(i);
					procedure.setPaper(paper);
					paper.addProcedure(procedure);
					dbManager.updateProcedureWithPaper(procedure.getID(), paper.getID());
				}
				selected = listDevices.getSelectedIndices();
				for (int i : selected) {
					Device device = dbManager.selectDevice(i);
					device.setPaper(paper);
					paper.addDevice(device);
					dbManager.updateDeviceWithPaper(device.getID(), paper.getID());
				}
				selected = listAuthors.getSelectedIndices();
				for (int i : selected) {
					Author author = dbManager.selectAuthor(i);
					paper.addAuthor(author);
					author.addPaper(paper);
					dbManager.insertpaperauthor(paper.getID(), author.getID());
				}
				selected = listImages.getSelectedIndices();
				for (int i : selected) {
					Image image = dbManager.selectImage(i);
					paper.addImage(image);
					image.setPaper(paper);
					dbManager.updateImage(image.getID(), image.getDescription(), paper.getID());
				}
				frame.setVisible(false);
				frame.dispose();
				paintPapers();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 8;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		contentPane.add(btnCancel, gbc_btnCancel);

	}

	public static void addProcedure() {

		JPanel contentPane;
		JTextField textFieldName;
		JButton btnCancel;
		JLabel lblRelatedElements;
		JLabel lblName;
		JTextField textFieldDescription;
		JLabel lblDiseases;
		JLabel lblDevices;
		JList listDiseases;
		JList listDevices;
		JLabel lblPapers;
		JList listPapers;
		JLabel lblDescription;

		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setTitle("New Procedure");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		contentPane.add(lblDescription, gbc_lblDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		contentPane.add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 2;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);

		lblDiseases = new JLabel("Diseases");
		GridBagConstraints gbc_lblDiseases = new GridBagConstraints();
		gbc_lblDiseases.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiseases.gridx = 0;
		gbc_lblDiseases.gridy = 3;
		contentPane.add(lblDiseases, gbc_lblDiseases);

		ArrayList<Disease> diseases = dbManager.selectDisease("all");
		listDiseases = new JList(diseases.toArray());
		listDiseases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listDiseases = new GridBagConstraints();
		gbc_listDiseases.insets = new Insets(0, 0, 5, 0);
		gbc_listDiseases.fill = GridBagConstraints.BOTH;
		gbc_listDiseases.gridx = 1;
		gbc_listDiseases.gridy = 3;
		contentPane.add(listDiseases, gbc_listDiseases);

		lblDevices = new JLabel("Devices");
		GridBagConstraints gbc_lblDevices = new GridBagConstraints();
		gbc_lblDevices.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevices.gridx = 0;
		gbc_lblDevices.gridy = 4;
		contentPane.add(lblDevices, gbc_lblDevices);

		ArrayList<Device> devices = dbManager.selectDevice("all");
		listDevices = new JList(devices.toArray());
		listDevices.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listDevices = new GridBagConstraints();
		gbc_listDevices.insets = new Insets(0, 0, 5, 0);
		gbc_listDevices.fill = GridBagConstraints.BOTH;
		gbc_listDevices.gridx = 1;
		gbc_listDevices.gridy = 4;
		contentPane.add(listDevices, gbc_listDevices);

		lblPapers = new JLabel("Papers");
		GridBagConstraints gbc_lblPapers = new GridBagConstraints();
		gbc_lblPapers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPapers.gridx = 0;
		gbc_lblPapers.gridy = 5;
		contentPane.add(lblPapers, gbc_lblPapers);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		listPapers = new JList(papers.toArray());
		listPapers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listPapers = new GridBagConstraints();
		gbc_listPapers.insets = new Insets(0, 0, 5, 0);
		gbc_listPapers.fill = GridBagConstraints.BOTH;
		gbc_listPapers.gridx = 1;
		gbc_listPapers.gridy = 5;
		contentPane.add(listPapers, gbc_listPapers);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String description = textFieldDescription.getText();
				Procedure procedure = new Procedure(name, description);
				dbManager.insertIntoProcedure(procedure);
				procedure = dbManager.selectProcedure(name).get(0);
				int[] selected = listDiseases.getSelectedIndices();
				for (int i : selected) {
					Disease disease = dbManager.selectDisease(i);
					procedure.addDisease(disease);
					disease.addProcedure(procedure);
					dbManager.insertproceduredisease(procedure.getID(), disease.getID());
				}
				selected = listDevices.getSelectedIndices();
				for (int i : selected) {
					Device device = dbManager.selectDevice(i);
					procedure.addDevice(device);
					device.setProcedure(procedure);
					dbManager.updateDeviceWithProcedure(device.getID(), procedure.getID());
				}
				selected = listPapers.getSelectedIndices();
				for (int i : selected) {
					Paper paper = dbManager.selectPaper(i);
					procedure.setPaper(paper);
					paper.addProcedure(procedure);
					dbManager.updateProcedureWithPaper(procedure.getID(), paper.getID());
				}

				frame.setVisible(false);
				frame.dispose();
				paintProcedures();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 8;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

	public static void addSymptom() {
		JPanel contentPane;
		JTextField textFieldName;
		JButton btnCancel;
		JLabel lblRelatedElements;
		JLabel lblName;
		JTextField textFieldDescription;
		JLabel lblDiseases;
		JList listDiseases;
		JLabel lblDescription;

		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setTitle("New Symptom");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 309, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelName = new JLabel("Name");
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.EAST;
		gbc_labelName.insets = new Insets(0, 0, 5, 5);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		contentPane.add(labelName, gbc_labelName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		contentPane.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		lblDescription = new JLabel("Description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		contentPane.add(lblDescription, gbc_lblDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		contentPane.add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

		lblRelatedElements = new JLabel("Related elements");
		GridBagConstraints gbc_lblRelatedElements = new GridBagConstraints();
		gbc_lblRelatedElements.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelatedElements.gridx = 0;
		gbc_lblRelatedElements.gridy = 2;
		contentPane.add(lblRelatedElements, gbc_lblRelatedElements);

		lblDiseases = new JLabel("Diseases");
		GridBagConstraints gbc_lblDiseases = new GridBagConstraints();
		gbc_lblDiseases.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiseases.gridx = 0;
		gbc_lblDiseases.gridy = 3;
		contentPane.add(lblDiseases, gbc_lblDiseases);

		ArrayList<Disease> diseases = dbManager.selectDisease("all");
		listDiseases = new JList(diseases.toArray());
		listDiseases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_listDiseases = new GridBagConstraints();
		gbc_listDiseases.insets = new Insets(0, 0, 5, 0);
		gbc_listDiseases.fill = GridBagConstraints.BOTH;
		gbc_listDiseases.gridx = 1;
		gbc_listDiseases.gridy = 3;
		contentPane.add(listDiseases, gbc_listDiseases);

		JButton addButton = new JButton("Add");
		addButton.setHorizontalAlignment(SwingConstants.LEFT);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				String description = textFieldDescription.getText();
				Symptom symptom = new Symptom(name, description);
				dbManager.insertIntoSymptom(symptom);
				symptom = dbManager.selectSymptom(name).get(0);
				int[] selected = listDiseases.getSelectedIndices();
				for (int i : selected) {
					Disease disease = dbManager.selectDisease(i);
					symptom.addDisease(disease);
					disease.addSymptom(symptom);
					dbManager.insertsymptomdisease(symptom.getID(), disease.getID());
				}

				frame.setVisible(false);
				frame.dispose();
				paintSymptoms();
			}
		});
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 8;
		contentPane.add(addButton, gbc_addButton);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 9;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

	private static void deleteAuthor() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteAuthor(id);
		paintAuthors();
	}

	private static void deleteBodyPart() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteBodyPart(id);
		paintBodyParts();
	}

	private static void deleteDevice() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteDevice(id);
		paintDevices();
	}

	private static void deleteDisease() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteDisease(id);
		paintDiseases();
	}

	private static void deleteImage() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteImage(id);
		paintImages();
	}

	private static void deletePaper() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deletePaper(id);
		paintPapers();
	}

	private static void deleteProcedure() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteProcedure(id);
		paintProcedures();
	}

	private static void deleteSymptom() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		dbManager.deleteSymptom(id);
		paintSymptoms();
	}

	private static void modifyAuthor() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		JTextField textFieldNewAssociation;
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 308, 168);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewAssociation = new JLabel("New association");
		GridBagConstraints gbc_lblNewAssociation = new GridBagConstraints();
		gbc_lblNewAssociation.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewAssociation.anchor = GridBagConstraints.EAST;
		gbc_lblNewAssociation.gridx = 0;
		gbc_lblNewAssociation.gridy = 0;
		frame.getContentPane().add(lblNewAssociation, gbc_lblNewAssociation);

		textFieldNewAssociation = new JTextField();
		GridBagConstraints gbc_textFieldNewAssociation = new GridBagConstraints();
		gbc_textFieldNewAssociation.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNewAssociation.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNewAssociation.gridx = 1;
		gbc_textFieldNewAssociation.gridy = 0;
		frame.getContentPane().add(textFieldNewAssociation, gbc_textFieldNewAssociation);
		textFieldNewAssociation.setColumns(10);

		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String association = textFieldNewAssociation.getText();
				dbManager.updateAuthor(id, association);
				frame.setVisible(false);
				frame.dispose();
				paintAuthors();
			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		frame.getContentPane().add(btnAddChanges, gbc_btnAddChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
	}

	private static void modifyDevice() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		JTextField textFieldNewPrice;
		JTextField textFieldNewBrand;

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 308, 180);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewPrice = new JLabel("New price");
		GridBagConstraints gbc_lblNewPrice = new GridBagConstraints();
		gbc_lblNewPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPrice.anchor = GridBagConstraints.EAST;
		gbc_lblNewPrice.gridx = 0;
		gbc_lblNewPrice.gridy = 0;
		frame.getContentPane().add(lblNewPrice, gbc_lblNewPrice);

		textFieldNewPrice = new JTextField();
		GridBagConstraints gbc_textFieldNewPrice = new GridBagConstraints();
		gbc_textFieldNewPrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNewPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNewPrice.gridx = 1;
		gbc_textFieldNewPrice.gridy = 0;
		frame.getContentPane().add(textFieldNewPrice, gbc_textFieldNewPrice);
		textFieldNewPrice.setColumns(10);

		JLabel lblNewBrand = new JLabel("New brand");
		GridBagConstraints gbc_lblNewBrand = new GridBagConstraints();
		gbc_lblNewBrand.anchor = GridBagConstraints.EAST;
		gbc_lblNewBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBrand.gridx = 0;
		gbc_lblNewBrand.gridy = 1;
		frame.getContentPane().add(lblNewBrand, gbc_lblNewBrand);

		textFieldNewBrand = new JTextField();
		GridBagConstraints gbc_textFieldNewBrand = new GridBagConstraints();
		gbc_textFieldNewBrand.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNewBrand.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNewBrand.gridx = 1;
		gbc_textFieldNewBrand.gridy = 1;
		frame.getContentPane().add(textFieldNewBrand, gbc_textFieldNewBrand);
		textFieldNewBrand.setColumns(10);

		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Float price = Float.parseFloat(textFieldNewPrice.getText());
				String brand = textFieldNewBrand.getText();
				dbManager.updateDevice(id, price, brand);
				frame.setVisible(false);
				frame.dispose();
				paintDevices();
			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		frame.getContentPane().add(btnAddChanges, gbc_btnAddChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
	}

	private static void modifyDisease() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);

		JFrame frame = new JFrame();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 308, 375);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewBodyPart = new JLabel("New body part");
		GridBagConstraints gbc_lblNewBodyPart = new GridBagConstraints();
		gbc_lblNewBodyPart.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBodyPart.gridx = 0;
		gbc_lblNewBodyPart.gridy = 1;
		frame.getContentPane().add(lblNewBodyPart, gbc_lblNewBodyPart);

		ArrayList<BodyPart> bodyParts = dbManager.selectBodyPart("all");
		JList listBodyParts = new JList(bodyParts.toArray());
		listBodyParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listBodyParts = new GridBagConstraints();
		gbc_listBodyParts.insets = new Insets(0, 0, 5, 0);
		gbc_listBodyParts.fill = GridBagConstraints.BOTH;
		gbc_listBodyParts.gridx = 1;
		gbc_listBodyParts.gridy = 1;
		frame.getContentPane().add(listBodyParts, gbc_listBodyParts);

		JLabel lblNewProcedure = new JLabel("New procedure");
		GridBagConstraints gbc_lblNewProcedure = new GridBagConstraints();
		gbc_lblNewProcedure.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewProcedure.gridx = 0;
		gbc_lblNewProcedure.gridy = 2;
		frame.getContentPane().add(lblNewProcedure, gbc_lblNewProcedure);

		ArrayList<Procedure> procedures = dbManager.selectProcedure("all");
		JList listProcedure = new JList(procedures.toArray());
		listProcedure.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listProcedure = new GridBagConstraints();
		gbc_listProcedure.insets = new Insets(0, 0, 5, 0);
		gbc_listProcedure.fill = GridBagConstraints.BOTH;
		gbc_listProcedure.gridx = 1;
		gbc_listProcedure.gridy = 2;
		frame.getContentPane().add(listProcedure, gbc_listProcedure);

		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disease disease = dbManager.selectDisease(id);
				int[] selected = listBodyParts.getSelectedIndices();
				for (int i : selected) {
					BodyPart bodyPart = dbManager.selectBodyPart(i);
					bodyPart.addDisease(disease);
					disease.setBodyPart(bodyPart);
					dbManager.updateDisease(disease.getID(), disease.getDescription(), bodyPart.getID());
				}

				selected = listProcedure.getSelectedIndices();
				for (int i : selected) {
					Procedure procedure = dbManager.selectProcedure(i);
					disease.addProcedure(procedure);
					procedure.addDisease(disease);
					dbManager.insertproceduredisease(procedure.getID(), disease.getID());
				}

				frame.setVisible(false);
				frame.dispose();
				paintDiseases();
			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		frame.getContentPane().add(btnAddChanges, gbc_btnAddChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);

	}

	private static void modifyImage() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);

		JTextField textFieldDescription;
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 308, 238);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewDescription = new JLabel("New description");
		GridBagConstraints gbc_lblNewDescription = new GridBagConstraints();
		gbc_lblNewDescription.anchor = GridBagConstraints.EAST;
		gbc_lblNewDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDescription.gridx = 0;
		gbc_lblNewDescription.gridy = 1;
		frame.getContentPane().add(lblNewDescription, gbc_lblNewDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		frame.getContentPane().add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

		JLabel lblNewPaper = new JLabel("New paper");
		GridBagConstraints gbc_lblNewPaper = new GridBagConstraints();
		gbc_lblNewPaper.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPaper.gridx = 0;
		gbc_lblNewPaper.gridy = 3;
		frame.getContentPane().add(lblNewPaper, gbc_lblNewPaper);

		ArrayList<Paper> papers = dbManager.selectPaper("all");
		JList listPaper = new JList(papers.toArray());
		listPaper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listPaper = new GridBagConstraints();
		gbc_listPaper.insets = new Insets(0, 0, 5, 0);
		gbc_listPaper.fill = GridBagConstraints.BOTH;
		gbc_listPaper.gridx = 1;
		gbc_listPaper.gridy = 3;
		frame.getContentPane().add(listPaper, gbc_listPaper);

		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image image = dbManager.selectImage(id);
				String description = textFieldDescription.getText();

				int[] selected = listPaper.getSelectedIndices();
				for (int i : selected) {
					Paper paper = dbManager.selectPaper(i);
					dbManager.updateImage(id, description, paper.getID());
				}
				frame.setVisible(false);
				frame.dispose();
				paintImages();

			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		frame.getContentPane().add(btnAddChanges, gbc_btnAddChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
	}

	private static void modifyProcedure() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);

		JTextField textFieldDescription;

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 308, 154);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewDescription = new JLabel("New description");
		GridBagConstraints gbc_lblNewDescription = new GridBagConstraints();
		gbc_lblNewDescription.anchor = GridBagConstraints.EAST;
		gbc_lblNewDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDescription.gridx = 0;
		gbc_lblNewDescription.gridy = 1;
		frame.getContentPane().add(lblNewDescription, gbc_lblNewDescription);

		textFieldDescription = new JTextField();
		GridBagConstraints gbc_textFieldDescription = new GridBagConstraints();
		gbc_textFieldDescription.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescription.gridx = 1;
		gbc_textFieldDescription.gridy = 1;
		frame.getContentPane().add(textFieldDescription, gbc_textFieldDescription);
		textFieldDescription.setColumns(10);

		JButton btnAddChanges = new JButton("Add changes");
		btnAddChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String description = textFieldDescription.getText();
				dbManager.updateProcedure(id, description);
				frame.setVisible(false);
				frame.dispose();
				paintProcedures();
			}
		});
		GridBagConstraints gbc_btnAddChanges = new GridBagConstraints();
		gbc_btnAddChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddChanges.gridx = 0;
		gbc_btnAddChanges.gridy = 6;
		frame.getContentPane().add(btnAddChanges, gbc_btnAddChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
	}

	private static void showRelatedToSymptom() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Symptom symptom = dbManager.selectSymptom(id);
		relatedElements = relatedElements + "Elements related to symptom " + symptom.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Diseases:";
		List<Disease> listD = jpaManager.readDiseaseFromSymptomDisease(symptom.getID());
		for (Disease disease : listD) {
			if (disease != null) {
				relatedElements = relatedElements + "\n" + disease.toString();
			}
		}

		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToProcedure() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Procedure procedure = dbManager.selectProcedure(id);
		relatedElements = relatedElements + "Elements related to procedure " + procedure.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Diseases:";
		List<Disease> listD = jpaManager.readDiseaseFromProcedureDisease(procedure.getID());
		for (Disease disease : listD) {
			if (disease != null) {
				relatedElements = relatedElements + "\n" + disease.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Images:";
		List<Integer> listI = jpaManager.readPaperRelatedToProcedure(procedure.getID());
		for (Integer i : listI) {
			if (i != null) {
				Paper paper = jpaManager.readPaper(i);
				relatedElements = relatedElements + "\n" + paper.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Devices:";
		List<Integer> listDe = jpaManager.readDeviceRelatedToProcedure(procedure.getID());
		for (Integer i : listDe) {
			if (i != null) {
				Device device = jpaManager.readDevice(i);
				relatedElements = relatedElements + "\n" + device.toString();
			}
		}

		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToPaper() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Paper paper = dbManager.selectPaper(id);
		relatedElements = relatedElements + "Elements related to paper " + paper.getTitle() + "\n";
		relatedElements = relatedElements + "\n" + "Authors:";
		List<Author> listA = jpaManager.readAuthorFromPaperAuthor(paper.getID());
		for (Author author : listA) {
			if (author != null) {
				relatedElements = relatedElements + "\n" + author.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Images:";
		List<Integer> listI = jpaManager.readImageRelatedToPaper(paper.getID());
		for (Integer i : listI) {
			if (i != null) {
				Image image = dbManager.selectImage(i);
				relatedElements = relatedElements + "\n" + image.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Diseases:";
		List<Disease> listD = jpaManager.readDiseaseFromPaperDisease(paper.getID());
		for (Disease disease : listD) {
			if (disease != null) {
				relatedElements = relatedElements + "\n" + disease.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Procedures:";
		List<Integer> listPr = jpaManager.readProcedureRelatedToPaper(paper.getID());
		for (Integer i : listPr) {
			if (i != null) {
				Procedure procedure = jpaManager.readProcedure(i);
				relatedElements = relatedElements + "\n" + procedure.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Devices:";
		List<Integer> listDe = jpaManager.readDeviceRelatedToPaper(paper.getID());
		for (Integer i : listDe) {
			if (i != null) {
				Device device = jpaManager.readDevice(i);
				relatedElements = relatedElements + "\n" + device.toString();
			}
		}

		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToImage() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Image image = dbManager.selectImage(id);
		relatedElements = relatedElements + "Elements related to image " + image.getDescription() + "\n";
		relatedElements = relatedElements + "\n" + "Papers:";
		List<Integer> listP = jpaManager.readPaperRelatedToImage(image.getID());
		for (Integer i : listP) {
			if (i != null) {
				Paper paper = jpaManager.readPaper(i);
				relatedElements = relatedElements + "\n" + paper.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Diseases:";
		List<Disease> listD = jpaManager.readDiseaseFromImageDisease(image.getID());
		for (Disease disease : listD) {
			if (disease != null) {
				relatedElements = relatedElements + "\n" + disease.toString();
			}
		}
		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToDisease() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Disease disease = dbManager.selectDisease(id);
		relatedElements = relatedElements + "Elements related to disease " + disease.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Body parts:";
		List<Integer> listBP = jpaManager.readBodyPartRelatedToDisease(disease.getID());
		for (Integer i : listBP) {
			if (i != null) {
				BodyPart bodyPart = jpaManager.readBodyPart(i);
				relatedElements = relatedElements + "\n" + bodyPart.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Symptoms:";
		List<Symptom> listS = jpaManager.readSymptomFromSymptomDisease(disease.getID());
		for (Symptom symptom : listS) {
			if (symptom != null) {
				relatedElements = relatedElements + "\n" + symptom.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Papers:";
		List<Paper> listP = jpaManager.readPaperFromPaperDisease(disease.getID());
		for (Paper paper : listP) {
			if (paper != null) {
				relatedElements = relatedElements + "\n" + paper.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Images:";
		List<Image> listI = dbManager.selectImageFromImageDisease(disease.getID());
		for (Image image : listI) {
			if (image != null) {
				relatedElements = relatedElements + "\n" + image.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Procedures:";
		List<Procedure> listPr = jpaManager.readProcedureFromProcedureDisease(disease.getID());
		for (Procedure procedure : listPr) {
			if (procedure != null) {
				relatedElements = relatedElements + "\n" + procedure.toString();
			}
		}

		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToDevice() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Device device = dbManager.selectDevice(id);
		relatedElements = relatedElements + "Elements related to device " + device.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Procedures:";
		List<Integer> listD = jpaManager.readProcedureRelatedToDevice(device.getID());
		for (Integer i : listD) {
			if (i != null) {
				Procedure p = jpaManager.readProcedure(i);
				relatedElements = relatedElements + "\n" + p.toString();
			}
		}
		relatedElements = relatedElements + "\n" + "Papers:";
		List<Integer> listPr = jpaManager.readPaperRelatedToDevice(device.getID());
		for (Integer i : listPr) {
			if (i != null) {
				Paper paper = jpaManager.readPaper(i);
				relatedElements = relatedElements + "\n" + paper.toString();
			}
		}
		textAreaRelated.setText(relatedElements);

	}

	private static void showRelatedToBodyPart() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		BodyPart bodyPart = dbManager.selectBodyPart(id);
		relatedElements = relatedElements + "Elements related to body part " + bodyPart.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Diseases:";
		List<Disease> listD = jpaManager.readDiseaseRelatedToBodyPart(bodyPart.getID());
		for (Disease disease : listD) {
			if (disease != null) {
				relatedElements = relatedElements + "\n" + disease.toString();
			}
		}
		textAreaRelated.setText(relatedElements);
	}

	private static void showRelatedToAuthor() {
		String relatedElements = "";
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Author author = dbManager.selectAuthor(id);
		relatedElements = relatedElements + "Elements related to author " + author.getName() + "\n";
		relatedElements = relatedElements + "\n" + "Papers:";
		List<Paper> listP = jpaManager.readPaperFromPaperAuthor(author.getID());
		for (Paper paper : listP) {
			if (paper != null) {
				relatedElements = relatedElements + "\n" + (paper.toString());
			}
		}
		textAreaRelated.setText(relatedElements);

	}

	private static void convertXMLAuthor() {
		int row = mainTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please, select an element.");
			return;
		}
		int id = (Integer) mainTable.getValueAt(row, 0);
		Author author = dbManager.selectAuthor(id);

		final JPanel contentPanel = new JPanel();
		JTextField textFieldFile;

		JFrame dialog = new JFrame();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		dialog.setBounds(100, 100, 379, 124);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFilesLocation = new JLabel("File's location:");
			lblFilesLocation.setBounds(10, 13, 95, 14);
			contentPanel.add(lblFilesLocation);
		}
		{
			textFieldFile = new JTextField();
			textFieldFile.setBounds(108, 10, 245, 20);
			contentPanel.add(textFieldFile);
			textFieldFile.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fileName = textFieldFile.getText();
						XmlManager xmlm = new XmlManager(dbManager);
						xmlm.marshalToXMLAuthor(author.getName(), fileName);
						dialog.setVisible(false);
						dialog.dispose();
						JOptionPane.showMessageDialog(contentPane, "XML created.");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				dialog.getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

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

	private static void convertJavaAuthor() {
		final JPanel contentPanel = new JPanel();
		JTextField textFieldFile;

		JFrame dialog = new JFrame();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		dialog.setBounds(100, 100, 379, 124);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFilesLocation = new JLabel("File's location:");
			lblFilesLocation.setBounds(10, 13, 95, 14);
			contentPanel.add(lblFilesLocation);
		}
		{
			textFieldFile = new JTextField();
			textFieldFile.setBounds(108, 10, 245, 20);
			contentPanel.add(textFieldFile);
			textFieldFile.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fileName = textFieldFile.getText();
						XmlManager xmlm = new XmlManager(dbManager);
						Author author = xmlm.unmarshalToJavaAuthor(fileName);
						dialog.setVisible(false);
						dialog.dispose();
						JOptionPane.showMessageDialog(contentPane, "Java author created: " + author.toString());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				dialog.getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

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

	public static void showHTML() {
		Xml2HtmlPaper.simpleTransform("./xml/XmlDocument.xml", "./xml/PaperStyle.xslt", "./xml/htmlPaper.html");
		JOptionPane.showMessageDialog(contentPane, "The file has been created. It should be in the xml folder.");
	}
}
