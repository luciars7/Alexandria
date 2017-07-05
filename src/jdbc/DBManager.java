package jdbc;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import pojos.*;

public class DBManager {
	JFrame editorFrame; // This is used in a method for showing BLOBs. The
						// method is not used.
	static Connection c = null; // Connection to the database.

	public DBManager() {

	}

	public void connect(Connection c) {
		// The JDBC manager is created with a connection to the database.
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db"); // Location
																				// of
																				// the
																				// database.
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			this.c = c;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JFrame getEditorFrame() {
		return editorFrame;
	}

	public void setEditorFrame(JFrame editorFrame) {
		this.editorFrame = editorFrame;
	}

	public static Connection getC() {
		return c;
	}

	public static void setC(Connection c) {
		DBManager.c = c;
	}

	public static boolean checkTables() {
		// This method was found in the internet. Basically, it tries to access
		// the first table that is created. If it can't, then no table has been
		// created and we proceed to do so.
		DatabaseMetaData dbm;
		try {
			dbm = c.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "paper", null);
			if (tables.next()) {
				tables.close();
				return true;
			} else {
				tables.close();
				createTables();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error encountered when retreiving data.");
			e.printStackTrace();
		}
		return true;
	}

	// SELECTS
	// ------------------------------------------------------------------------------------------------
	public ArrayList<Author> selectAuthor(String NAME) {
		ArrayList<Author> list = null;
		try {
			list = new ArrayList<Author>();
			Statement stmt = c.createStatement();
			// If the user wants to see all authors, we return a list containing
			// all of them. Each attribute is recovered in a specific order.
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM author";
				ResultSet rs1 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs1.next()) {
					int id = rs1.getInt("ID");
					String name = rs1.getString("name");
					String origin = rs1.getString("origin");
					String association = rs1.getString("association");
					list.add(new Author(id, name, origin, association));
				}
				if (rs1 != null) {
					rs1.close();
				}
			} else {
				// In this case we are using a list eventhough, being the name
				// attribute unique, we are only going to recover one single
				// object.
				String sql = "SELECT * FROM author WHERE name = '" + NAME + "'";
				ResultSet rs1 = stmt.executeQuery(sql); // Works as an iterator.
				int id = rs1.getInt("ID");
				String name = rs1.getString("name");
				String origin = rs1.getString("origin");
				String association = rs1.getString("association");
				list.add(new Author(id, name, origin, association));
				if (rs1 != null) {
					rs1.close();
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Author selectAuthor(Integer id) {
		// This method works exactly as the second option of the previous one.
		// In this case we search by the ID. It is useful to be able to search
		// by two unique attributes because we have situations when we can not
		// search by one of them.
		Author author = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM author WHERE ID = '" + id + "'";
			ResultSet rs2 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs2.getInt("ID");
			String name = rs2.getString("name");
			String origin = rs2.getString("origin");
			String association = rs2.getString("association");
			author = new Author(id, name, origin, association);
			if (rs2 != null) {
				rs2.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	public ArrayList<BodyPart> selectBodyPart(String NAME) {
		ArrayList<BodyPart> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<BodyPart>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM bodypart";
				ResultSet rs3 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs3.next()) {
					int id = rs3.getInt("ID");
					String name = rs3.getString("name");
					String location = rs3.getString("location");
					list.add(new BodyPart(id, name, location));
				}
				if (rs3 != null) {
					rs3.close();
				}
			} else {
				String sql = "SELECT * FROM bodypart WHERE name = '" + NAME + "'";
				ResultSet rs3 = stmt.executeQuery(sql); // Works as an iterator.
				int id = rs3.getInt("ID");
				String name = rs3.getString("name");
				String location = rs3.getString("location");
				list.add(new BodyPart(id, name, location));
				if (rs3 != null) {
					rs3.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public BodyPart selectBodyPart(Integer id) {
		BodyPart bodyPart = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM bodypart WHERE ID = '" + id + "'";
			ResultSet rs4 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs4.getInt("ID");
			String name = rs4.getString("name");
			String location = rs4.getString("location");
			bodyPart = new BodyPart(id, name, location);
			if (rs4 != null) {
				rs4.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bodyPart;
	}

	public ArrayList<Device> selectDevice(String NAME) {
		ArrayList<Device> list = null;
		try {
			list = new ArrayList<Device>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM device";
				ResultSet rs5 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs5.next()) {
					int id = rs5.getInt("ID");
					String name = rs5.getString("name");
					String type = rs5.getString("type");
					float price = rs5.getFloat("price");
					String brand = rs5.getString("brand");
					list.add(new Device(id, name, type, price, brand));
				}
				if (rs5 != null) {
					rs5.close();
				}
			} else {
				String sql = "SELECT * FROM device WHERE name = '" + NAME + "'";
				ResultSet rs5 = stmt.executeQuery(sql); // Works as an iterator.
				int id = rs5.getInt("ID");
				String name = rs5.getString("name");
				String type = rs5.getString("type");
				float price = rs5.getFloat("price");
				String brand = rs5.getString("brand");
				list.add(new Device(id, name, type, price, brand));
				if (rs5 != null) {
					rs5.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Device selectDevice(Integer id) {
		Device device = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM device WHERE ID = '" + id + "'";
			ResultSet rs6 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs6.getInt("ID");
			String name = rs6.getString("name");
			String type = rs6.getString("type");
			float price = rs6.getFloat("price");
			String brand = rs6.getString("brand");
			device = new Device(id, name, type, price, brand);
			if (rs6 != null) {
				rs6.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return device;
	}

	public ArrayList<Disease> selectDisease(String NAME) {
		ArrayList<Disease> list = null;
		try {
			list = new ArrayList<Disease>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM disease";
				ResultSet rs7 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs7.next()) {
					int id = rs7.getInt("ID");
					String name = rs7.getString("name");
					String description = rs7.getString("description");
					list.add(new Disease(id, name, description));
				}
				if (rs7 != null) {
					rs7.close();
				}
			} else {
				String sql = "SELECT * FROM disease WHERE name = '" + NAME + "'";
				ResultSet rs7 = stmt.executeQuery(sql); // Works as an iterator.
				int id = rs7.getInt("ID");
				String name = rs7.getString("name");
				String description = rs7.getString("description");
				list.add(new Disease(id, name, description));
				if (rs7 != null) {
					rs7.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Disease selectDisease(Integer id) {
		Disease disease = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM disease WHERE ID = '" + id + "'";
			ResultSet rs8 = stmt.executeQuery(sql); // Works as an iterator.
			int id3 = rs8.getInt("ID");
			String name = rs8.getString("name");
			String description = rs8.getString("description");
			disease = new Disease(id3, name, description);
			if (rs8 != null) {
				rs8.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return disease;
	}

	public ArrayList<Image> selectImage(String NAME) {
		ArrayList<Image> list = null;
		try {
			list = new ArrayList<Image>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM image";
				ResultSet rs9 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs9.next()) {
					int id = rs9.getInt("ID");
					String description = rs9.getString("description");
					String type = rs9.getString("type");
					String size = rs9.getString("size");
					byte[] image = rs9.getBytes("image");
					list.add(new Image(id, description, type, size, image));
				}
				if (rs9 != null) {
					rs9.close();
				}
			} else {
				String sql = "SELECT * FROM image WHERE description = '" + NAME + "'";
				ResultSet rs9 = stmt.executeQuery(sql); // Works as an iterator.
				while (rs9.next()) {
					int id = rs9.getInt("ID");
					String description = rs9.getString("description");
					String type = rs9.getString("type");
					String size = rs9.getString("size");
					byte[] image = rs9.getBytes("image");
					Image i = new Image(id, description, type, size, image);
					list.add(i);
				}

				if (rs9 != null) {
					rs9.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void showBlob(final InputStream stream) {
		// This method attempted to show BLOBs. It was not used in the end.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editorFrame = new JFrame("Image Window");
				editorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				BufferedImage image = null;
				try {
					image = ImageIO.read(stream);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				ImageIcon imageIcon = new ImageIcon(image);
				JLabel jLabel = new JLabel();
				jLabel.setIcon(imageIcon);
				editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

				editorFrame.pack();
				editorFrame.setLocationRelativeTo(null);
				editorFrame.setVisible(true);
			}
		});
	}

	public Image selectImage(Integer id) {
		Image i = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM image WHERE ID = '" + id + "'";
			ResultSet rs0 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs0.getInt("ID");
			String description = rs0.getString("description");
			String type = rs0.getString("type");
			String size = rs0.getString("size");
			byte[] imageB = rs0.getBytes("image");
			i = new Image(id, description, type, size, imageB);
			if (rs0 != null) {
				rs0.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public ArrayList<Image> selectImageFromImageDisease(Integer disease_id) {
		// The concept is similar to the one of the previous methods but in this
		// case we obtain the object from a N-N table.
		ArrayList<Image> list = null;
		try {
			list = new ArrayList<Image>();
			Statement stmt = c.createStatement();

			String sql = "SELECT * FROM image as i INNER JOIN imagedisease as imdis ON i.ID = imdis.image INNER JOIN disease as d ON d.ID = imdis.disease WHERE d.ID = '"
					+ disease_id + "'";
			ResultSet rs9 = stmt.executeQuery(sql);// Works as an iterator.
			// We need to use the number of the columns because the ID is
			// ambiguous with the JOINs.
			while (rs9.next()) {
				int id = rs9.getInt(1);
				String description = rs9.getString(2);
				String type = rs9.getString(3);
				String size = rs9.getString(4);
				byte[] image = rs9.getBytes(5);
				list.add(new Image(id, description, type, size, image));
			}
			if (rs9 != null) {
				rs9.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Paper> selectPaper(String NAME) {
		ArrayList<Paper> list = null;
		try {
			list = new ArrayList<Paper>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM paper";
				ResultSet rs11 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs11.next()) {
					int id = rs11.getInt("ID");
					String title = rs11.getString("title");
					String source = rs11.getString("source");
					list.add(new Paper(id, title, source));
				}
				if (rs11 != null) {
					rs11.close();
				}
			} else {
				String sql = "SELECT * FROM paper WHERE title = '" + NAME + "'";
				ResultSet rs11 = stmt.executeQuery(sql); // Works as an
															// iterator.
				int id = rs11.getInt("ID");
				String title = rs11.getString("title");
				String source = rs11.getString("source");
				list.add(new Paper(id, title, source));
				if (rs11 != null) {
					rs11.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Paper selectPaper(Integer id) {
		Paper paper = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM paper WHERE ID = '" + id + "'";
			ResultSet rs12 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs12.getInt("ID");
			String title = rs12.getString("title");
			String source = rs12.getString("source");
			paper = new Paper(id, title, source);
			if (rs12 != null) {
				rs12.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paper;
	}

	public ArrayList<Procedure> selectProcedure(String NAME) {
		ArrayList<Procedure> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Procedure>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM procedure";
				ResultSet rs13 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs13.next()) {
					int id = rs13.getInt("ID");
					String name = rs13.getString("name");
					String description = rs13.getString("description");
					list.add(new Procedure(id, name, description));
				}
				if (rs13 != null) {
					rs13.close();
				}
			} else {
				String sql = "SELECT * FROM procedure WHERE name = '" + NAME + "'";
				ResultSet rs13 = stmt.executeQuery(sql); // Works as an
															// iterator.
				int id = rs13.getInt("ID");
				String name = rs13.getString("name");
				String description = rs13.getString("description");
				list.add(new Procedure(id, name, description));
				if (rs13 != null) {
					rs13.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Procedure selectProcedure(Integer id) {
		Procedure procedure = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM procedure WHERE ID = '" + id + "'";
			ResultSet rs14 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs14.getInt("ID");
			String name = rs14.getString("name");
			String description = rs14.getString("description");
			procedure = new Procedure(id, name, description);
			if (rs14 != null) {
				rs14.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedure;
	}

	public ArrayList<Symptom> selectSymptom(String NAME) {
		ArrayList<Symptom> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Symptom>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM symptom";
				ResultSet rs15 = stmt.executeQuery(sql);// Works as an iterator.
				while (rs15.next()) {
					int id = rs15.getInt("ID");
					String name = rs15.getString("name");
					String description = rs15.getString("description");
					list.add(new Symptom(id, name, description));
				}
				if (rs15 != null) {
					rs15.close();
				}
			} else {
				String sql = "SELECT * FROM symptom WHERE name = '" + NAME + "'";
				ResultSet rs15 = stmt.executeQuery(sql); // Works as an
															// iterator.
				int id = rs15.getInt("ID");
				String name = rs15.getString("name");
				String description = rs15.getString("description");
				list.add(new Symptom(id, name, description));
				if (rs15 != null) {
					rs15.close();
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Symptom selectSymptom(Integer id) {
		Symptom symptom = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM symptom WHERE ID = '" + id + "'";
			ResultSet rs16 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs16.getInt("ID");
			String name = rs16.getString("name");
			String description = rs16.getString("description");
			symptom = new Symptom(id, name, description);
			if (rs16 != null) {
				rs16.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptom;
	}

	public static void createTables() {
		try {
			// Creates the tables.
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE paper" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "title TEXT UNIQUE,"
					+ "source TEXT)";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE bodypart" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "location TEXT)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE disease" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "description TEXT,"
					+ "bodypart INTEGER REFERENCES bodypart (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE author" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "origin TEXT," + "association TEXT)";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE device" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "type TEXT ," + "price FLOAT," + "brand TEXT,"
					+ "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE image" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "description TEXT UNIQUE,"
					+ "type TEXT," + "size TEXT," + "image BLOB,"
					+ "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE symptom" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "description TEXT)";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE procedure" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT UNIQUE,"
					+ "description TEXT," + "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt8.executeUpdate(sql8);
			stmt8.close();

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE symptomdisease"
					+ "(disease INTEGER REFERENCES disease (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "symptom INTEGER REFERENCES symptom (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (disease, symptom))";
			stmt9.executeUpdate(sql9);
			stmt9.close();

			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE paperauthor"
					+ "(paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "author INTEGER REFERENCES author (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (paper, author))";
			stmt10.executeUpdate(sql10);
			stmt10.close();

			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE paperdisease"
					+ "(paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "disease INTEGER REFERENCES disease (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (paper, disease))";
			stmt11.executeUpdate(sql11);
			stmt11.close();

			Statement stmt12 = c.createStatement();
			String sql12 = "CREATE TABLE imagedisease"
					+ "(image INTEGER REFERENCES image (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "disease INTEGER REFERENCES disease (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (image, disease))";
			stmt12.executeUpdate(sql12);
			stmt12.close();

			Statement stmt13 = c.createStatement();
			String sql13 = "CREATE TABLE proceduredisease"
					+ "(procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "disease INTEGER REFERENCES disease (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (procedure, disease))";
			stmt13.executeUpdate(sql13);
			stmt13.close();

			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('author', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('bodypart', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('device', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('disease', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('image', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('paper', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('procedure', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('symptom', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			// The last block of code is done to avoid problems with JPA.
			// We don't know if it also has to be written for the N-N tables
			// although probably not.
			/*
			 * sqlSeq =
			 * "INSERT INTO sqlite_sequence (name, seq) VALUES ('symptomdisease', 1)"
			 * ; stmtSeq.executeUpdate(sqlSeq); sqlSeq =
			 * "INSERT INTO sqlite_sequence (name, seq) VALUES ('paperauthor', 1)"
			 * ; stmtSeq.executeUpdate(sqlSeq); sqlSeq =
			 * "INSERT INTO sqlite_sequence (name, seq) VALUES ('paperdisease', 1)"
			 * ; stmtSeq.executeUpdate(sqlSeq); sqlSeq =
			 * "INSERT INTO sqlite_sequence (name, seq) VALUES ('imagedisease', 1)"
			 * ; stmtSeq.executeUpdate(sqlSeq); sqlSeq =
			 * "INSERT INTO sqlite_sequence (name, seq) VALUES ('proceduredisease', 1)"
			 * ; stmtSeq.executeUpdate(sqlSeq);
			 */
			stmtSeq.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// INSERTS
	// ------------------------------------------------------------------------------------------------
	public void insertIntoAuthor(Author author) {
		// An object is inserted in the corresponding table.
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO author (name,origin,association) VALUES ('" + author.getName() + "','"
					+ author.getOrigin() + "','" + author.getAssociation() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoBodyPart(BodyPart bodyPart) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO bodyPart (name, location) VALUES ('" + bodyPart.getName() + "', '"
					+ bodyPart.getLocation() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoDevice(Device device) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "";
			sqlSeq = "INSERT INTO device (name, type, price, brand) VALUES ('" + device.getName() + "', '"
					+ device.getType() + "', '" + device.getPrice() + "', '" + device.getBrand() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoDisease(Disease disease) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "";
			sqlSeq = "INSERT INTO disease (name, description) VALUES ('" + disease.getName() + "', '"
					+ disease.getDescription() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoImage(Image image) {
		try {
			Statement stmtSeq = c.createStatement();
			/*
			 * 
			 * prep.setBytes(6, bytesBlob);
			 * 
			 */
			String sqlSeq = "INSERT INTO image (description, type, size, image) VALUES ('" + image.getDescription()
					+ "', '" + image.getType() + "', '" + image.getSize() + "', '" + image.getImage() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public byte[] stringtobyte(File p) {
		try {
			/*
			 * InputStream streamBlob = new FileInputStream(p); byte[] bytesBlob
			 * = new byte[streamBlob.available()]; streamBlob.read(bytesBlob);
			 * streamBlob.close();
			 */
			byte[] bytesBlob = Files.readAllBytes(p.toPath());
			return bytesBlob;
		} catch (IOException e) {
			System.out.println("\nSomething went wrong... Try using a valid route.");
			return null;
		}
	}

	public void insertIntoPaper(Paper paper) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO paper (title, source) VALUES ('" + paper.getTitle() + "', '"
					+ paper.getSource() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoProcedure(Procedure procedure) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO procedure (name, description) VALUES ('" + procedure.getName() + "', '"
					+ procedure.getDescription() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoSymptom(Symptom symptom) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO symptom (name, description) VALUES ('" + symptom.getName() + "', '"
					+ symptom.getDescription() + "')";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// DELETIONS
	// ------------------------------------------------------------------------------------------------
	public void deleteAuthor(int author_id) {
//An object is deleted from the corresponding table. Using a unique attribute makes things easier because we make sure that only the intended object is eliminated.
		try {
			String sql = "DELETE FROM author WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, author_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteBodyPart(int bodypart_id) {
		try {
			String sql = "DELETE FROM bodypart WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, bodypart_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteDisease(int disease_id) {
		try {
			String sql = "DELETE FROM disease WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, disease_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteImage(int image_id) {
		try {
			String sql = "DELETE FROM image WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, image_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deletePaper(int paper_id) {
		try {
			String sql = "DELETE FROM paper WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, paper_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteProcedure(int procedure_id) {
		try {
			String sql = "DELETE FROM procedure WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, procedure_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteDevice(int device_id) {
		try {
			String sql = "DELETE FROM device WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, device_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteSymptom(int symptom_id) {
		try {
			String sql = "DELETE FROM symptom WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, symptom_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// UPDATES
	// ------------------------------------------------------------------------------------------------
	public void updateAuthor(Integer author_id, String newAssociation) {
		//One single object has some attributes modified. Note that we use a unique attribute in order to modify only one object.
		try {
			String sql = "UPDATE author SET association = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newAssociation);
			prep.setInt(2, author_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Entity 'BodyPart' doesn't need an 'update' method as what is going to be
	// changed is the foreign key
	// attribute that refers to it. For example: if a disease (which has
	// 'BodyPart' as a foreign key) goes
	// under research and it is found out that the body part in which it happens
	// is different, in the database
	// what is going to be changed is the attribute in 'BodyPart' foreign key of
	// entity 'disease'.

	public void updateDevice(Integer device_id, Float newprice, String newBrand) {
		try {
			String sql = "UPDATE device SET price = ?, brand = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setFloat(1, newprice);
			prep.setString(2, newBrand);
			prep.setInt(3, device_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateDeviceWithProcedure(Integer device_id, Integer procedure_id) {
		try {
			String sql = "UPDATE device SET procedure = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, procedure_id);
			prep.setInt(2, device_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateDeviceWithPaper(Integer device_id, Integer paper_id) {
		try {
			String sql = "UPDATE device SET paper = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, paper_id);
			prep.setInt(2, device_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateDisease(Integer disease_id, String newDescription, Integer newBodyPart) {
		try {
			String sql = "UPDATE disease SET description = ?, bodypart = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2, newBodyPart);
			prep.setInt(3, disease_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateImage(Integer image_id, String newDescription, Integer newPaper) {
		try {
			String sql = "UPDATE image SET description = ?, paper = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2, newPaper);
			prep.setInt(3, image_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateProcedureWithPaper(Integer procedure_id, Integer paper_id) {
		try {
			String sql = "UPDATE procedure SET paper = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, paper_id);
			prep.setInt(2, procedure_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateProcedure(Integer procedure_id, String newDescription) {
		try {
			String sql = "UPDATE procedure SET description = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2, procedure_id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Symptoms will always be symptoms. If they change the old ones are going
	// to be deleted and new ones
	// will be created. They aren't going to be modified.

	// INSERTIONS INTO N-N TABLES
	// ------------------------------------------------------------------------------------------------
	public void insertpaperauthor(int paper, int author) {
		//Two foreign keys are inserted in a N-N table. 
		if (paper != 0 && author != 0) {
			try {
				String sql = "INSERT INTO paperauthor (paper, author) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, paper);
				prep.setInt(2, author);
				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertsymptomdisease(int symptom, int disease) {
		if (symptom != 0 && disease != 0) {
			try {
				String sql = "INSERT INTO symptomdisease (disease, symptom) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, disease);
				prep.setInt(2, symptom);
				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertpaperdisease(int paper, int disease) {
		if (paper != 0 && disease != 0) {
			try {
				String sql = "INSERT INTO paperdisease (paper, disease) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, paper);
				prep.setInt(2, disease);
				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertimagedisease(int image, int disease) {
		if (image != 0 && disease != 0) {
			try {
				String sql = "INSERT INTO imagedisease (image, disease) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, image);
				prep.setInt(2, disease);
				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertproceduredisease(int procedure, int disease) {
		if (procedure != 0 && disease != 0) {
			try {
				String sql = "INSERT INTO proceduredisease (procedure, disease) " + "VALUES (?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, procedure);
				prep.setInt(2, disease);
				prep.executeUpdate();
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
