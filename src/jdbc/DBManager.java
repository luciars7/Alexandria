package jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;
import pojos.*;


//�ON DELETE CASCADE? �Tiene sentido para alguna tabla?

public class DBManager {

	static Connection c = null;

	public DBManager() {

	}

	public void connect(Connection c) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db");
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

	public static boolean checkTables() {
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
			// Retrieve data: begin
			list = new ArrayList<Author>();
			Statement stmt = c.createStatement();
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
					//BodyPart bodypart = (BodyPart) selectBodyPart(rs7.getInt("bodypart"));
					//list.add(new Disease(id, name, description, bodypart));
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
					//BodyPart bodypart = (BodyPart) this.selectBodyPart(rs7.getInt("bodyPart"));
					//list.add(new Disease(id, name, description, bodypart));
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
			//BodyPart bodypart = (BodyPart) this.selectBodyPart(rs8.getInt("bodyPart"));
			//disease = new Disease(id3, name, description, bodypart);
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
					//Paper paper = (Paper) this.selectPaper(rs9.getInt("paper"));
					//list.add(new Image(id, description, type, size, image, paper));
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
					//Paper paper = (Paper) this.selectPaper(rs9.getInt("paper"));
					//list.add(new Image(id, description, type, size, image, paper));
					list.add(new Image(id, description, type, size, image));
				
					//El c�digo de abajo podr�a servir para mostrar las im�genes en una ventana nueva.
				    /*if (image!=null) {
					ByteArrayInputStream blobIn = new ByteArrayInputStream(image);

					// Show the photo
					
						ImageWindow window = new ImageWindow();
					    window.showBlob(blobIn);
					
					// Write the photo in a file
					/*else {
						File outFile = new File("./photos/Output.png");
						OutputStream blobOut = new FileOutputStream(outFile);
						byte[] buffer = new byte[blobIn.available()];
						blobIn.read(buffer);
						blobIn.write(buffer);
						blobIn.close();
					}
				}*/
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

	public Image selectImage(Integer id) {
		Image image = null;
		try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM image WHERE ID = '" + id + "'";
			ResultSet rs0 = stmt.executeQuery(sql); // Works as an iterator.
			id = rs0.getInt("ID");
			String description = rs0.getString("description");
			String type = rs0.getString("type");
			String size = rs0.getString("size");
			byte[] imageB = rs0.getBytes("image");
			//Paper paper = (Paper) this.selectPaper(rs0.getInt("paper"));
			//image = new Image(id, description, type, size, imageB, paper);
			image = new Image(id, description, type, size, imageB);
			if (rs0 != null) {
				rs0.close();
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
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
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE paper" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "title TEXT UNIQUE,"
					+ "source TEXT," + "device INTEGER REFERENCES device (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
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
					+ "procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE"
					+ "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE)"; // Brand
																											// is
																											// being
																											// inserted
																											// as
																											// null.
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE image" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "description TEXT UNIQUE,"
					+ "type TEXT," + "size TEXT," + "image BLOOB,"
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
					+ "description TEXT)";
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
			// We don't know if it also has to be done for the N-N tables.
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
					+ device.getType() + "', '" + device.getprice() + "', '" + device.getBrand() + "')";
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

			InputStream streamBlob = new FileInputStream(p);

			byte[] bytesBlob = new byte[streamBlob.available()];

			streamBlob.read(bytesBlob);

			streamBlob.close();

			return bytesBlob;

		} catch (IOException e) {

			System.out.println("Something went wrong... Try using a valid route.");

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

	public void updateDisease(Integer disease_id, String newDescription, Integer newBodyPart) {
		try {
			String sql = "UPDATE disease SET description = ?, BodyPart = ? WHERE ID = ?";
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

	public void updatePaperWithADevice(Integer id, Integer device) {
		try {
			String sql = "UPDATE paper SET device = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, device);
			prep.setInt(2, id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updatePaperWithAProcedure(Integer id, Integer procedure) {
		try {
			String sql = "UPDATE paper SET procedure = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, procedure);
			prep.setInt(2, id);
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateProcedure(Integer procedure_id, String newDescription) {///// Preguntar

		///// a

		///// Luc�a

		///// por

		///// el

		///// name.

		try {

			String sql = "UPDATE procedure SET name = ?, description = ? WHERE ID = ?";

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
