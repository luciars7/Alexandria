package jdbc;

import java.sql.*;
import java.util.ArrayList;
import pojos.*;

public class DBManager {
	Connection c = null;

	public DBManager() {
		connect();
	}

	// SELECTS ------------------------------------------------------------------------------------------------
	public ArrayList<Author> selectAuthor(String NAME) {
		ArrayList<Author> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Author>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM authors";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String origin = rs.getString("origin");
					String association = rs.getString("association");
					list.add(new Author(id, name, origin, association));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM authors WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String origin = rs.getString("origin");
					String association = rs.getString("association");
					list.add(new Author(id, name, origin, association));

				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<BodyPart> selectBodyPart(String NAME) {
		ArrayList<BodyPart> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<BodyPart>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM bodyparts";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String location = rs.getString("location");
					list.add(new BodyPart(id, name, location));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM bodyparts WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String location = rs.getString("location");
					list.add(new BodyPart(id, name, location));

				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Device> selectDevice(String NAME) {
		ArrayList<Device> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Device>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM devices";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String type = rs.getString("type");
					float price = rs.getFloat("price");
					String brand = rs.getString("brand");
					list.add(new Device(id, name, type, price, brand));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM devices WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String type = rs.getString("type");
					float price = rs.getFloat("price");
					String brand = rs.getString("brand");
					list.add(new Device(id, name, type, price, brand));

				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Disease> selectDisease(String NAME) {
		ArrayList<Disease> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Disease>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM diseases";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					BodyPart bodypart = (BodyPart) rs.getObject("bodyParts");
					list.add(new Disease(id, name, description, bodypart));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM diseases WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					BodyPart bodypart = (BodyPart) rs.getObject("bodyParts");
					list.add(new Disease(id, name, description, bodypart));
				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Image> selectImage(String NAME) {
		ArrayList<Image> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Image>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM images";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String description = rs.getString("description");
					String type = rs.getString("type");
					String size = rs.getString("size");
					byte[] image = rs.getBytes("image");
					Paper paper = (Paper) rs.getObject("paper");
					list.add(new Image(id, description, type, size, image, paper));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM images WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String description = rs.getString("description");
					String type = rs.getString("type");
					String size = rs.getString("size");
					byte[] image = rs.getBytes("image");
					Paper paper = (Paper) rs.getObject("paper");
					list.add(new Image(id, description, type, size, image, paper));
				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Paper> selectPaper(String NAME) {
		ArrayList<Paper> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Paper>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM papers";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String title = rs.getString("title");
					String source = rs.getString("source");
					list.add(new Paper(id, title, source));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM papers WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String title = rs.getString("title");
					String source = rs.getString("source");
					list.add(new Paper(id, title, source));
				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Procedure> selectProcedure(String NAME) {
		ArrayList<Procedure> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Procedure>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM procedures";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					list.add(new Procedure(id, name, description));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM procedures WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					list.add(new Procedure(id, name, description));
				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Symptom> selectSymptom(String NAME) {
		ArrayList<Symptom> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Symptom>();
			Statement stmt = c.createStatement();
			if (NAME.equalsIgnoreCase("all")) {
				String sql = "SELECT * FROM symptoms";
				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					list.add(new Symptom(id, name, description));
				}
				rs.close();
			} else {

				String sql = "SELECT * FROM symptoms WHERE name = '" + NAME + "'";
				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
				while (rs.next()) {
					int id = rs.getInt("ID");
					String name = rs.getString("name");
					String description = rs.getString("description");
					list.add(new Symptom(id, name, description));
				}
				rs.close();
			}
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connect() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/alexandria.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTables() {
		try {
			// Open database connection
			connect();
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE papers" + "(ID INTEGER PRIMARY KEY," + "title TEXT," + "source TEXT)";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE bodyparts" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "location TEXT)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE diseases"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "description TEXT,"
					+ "bodyparts INTEGER REFERENCES bodyparts (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE authors" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "origin TEXT,"
					+ "association TEXT)";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();

			String sql5 = "CREATE TABLE devices" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "type TEXT ,"
					+ "price$ FLOAT," + "brand TEXT,"

					+ "medprocedures INTEGER REFERENCES procedures (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE images" + "(ID INTEGER PRIMARY KEY," + "description TEXT," + "type TEXT,"
					+ "size TEXT," + "image BLOOB,"
					+ "paper INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE symptoms" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "description TEXT)";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE procedures" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "description TEXT)";
			stmt8.executeUpdate(sql8);
			stmt8.close();

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE symptomsdiseases"
					+ "(diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "symptoms INTEGER REFERENCES symptoms (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (diseases,symptoms))";
			stmt9.executeUpdate(sql9);
			stmt9.close();

			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE papersauthors"
					+ "(papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "authors INTEGER REFERENCES authors (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (papers,authors))";
			stmt10.executeUpdate(sql10);
			stmt10.close();

			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE papersdiseases"
					+ "(papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (papers,diseases))";
			stmt11.executeUpdate(sql11);
			stmt11.close();

			Statement stmt12 = c.createStatement();
			String sql12 = "CREATE TABLE imagesdiseases"
					+ "(images INTEGER REFERENCES images (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (images,diseases))";
			stmt12.executeUpdate(sql12);
			stmt12.close();

			Statement stmt13 = c.createStatement();
			String sql13 = "CREATE TABLE proceduresdiseases"
					+ "(procedures INTEGER REFERENCES procedures (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (procedures,diseases))";
			stmt13.executeUpdate(sql13);
			stmt13.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// INSERTS ------------------------------------------------------------------------------------------------
	public void insertIntoAuthor (String name, String origin, String association) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO authors (name,origin,association) VALUES ('" + name + "','" + origin +
							"','" + association + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertIntoBodyPart (String name, String location) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO bodyPart (name, location) VALUES ('" + name + "', '" + location + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoDevice(String name, String type, float price, String brand) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO devices (name, type, price, brand) VALUES ('" + name + "', '" + type + "', '"
							+ price + "', '" + brand + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoDisease(String name, String description, BodyPart bodyParts) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO diseases (name, description, bodyParts) VALUES ('" + name + "', '"
							+ description + "', '" + bodyParts + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoImage(String description, String type, String size, String link, Paper paper) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO images (description, type, size, link, paper) VALUES ('" + description + "', '"
							+ type + "', '" + size + "', '" + link + "', '" + paper + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoPaper(String title, String source) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO papers (title, source) VALUES ('" + title + "', '" + source + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoProcedure(String name, String description) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO procedures (name, description) VALUES ('" + name + "', '" + description +
							"')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoSymptom(String name, String description) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO symptoms (name, description) VALUES ('" + name + "', '" + description + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertIntoSymptomDisease(String symp, String disease){
		try {
			Statement stmtSeq = c.createStatement();
			int s = 
			String sqlSeq = "INSERT INTO symptomsdiseases (diseases, symptoms) VALUES ('" + symp + "', '" + disease + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	// DELETIONS ------------------------------------------------------------------------------------------------
	public void deleteAuthor(int author_id) {
		try {
			String sql = "DELETE FROM authors WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, author_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteBodyPart(int bodypart_id) {
		try {
			String sql = "DELETE FROM bodyparts WHERE id=?";
 			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, bodypart_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
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
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteImage(int image_id) {
		try {
			String sql = "DELETE FROM images WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, image_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deletePaper(int paper_id) {
		try {
			String sql = "DELETE FROM papers WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, paper_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteProcedure(int procedure_id) {
		try {
			String sql = "DELETE FROM procedures WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, procedure_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
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
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteSymptom (int symptom_id) {
		try {
			String sql = "DELETE FROM symptoms WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, symptom_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// UPDATES ------------------------------------------------------------------------------------------------
	public void updateAuthor (Integer author_id, String newAssociation) {
		try {
			String sql = "UPDATE Author SET association = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newAssociation);
			prep.setInt(2, author_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Entity 'BodyPart' doesn't need an 'update' method as what is going to be changed is the foreign key
	// attribute that refers to it. For example: if a disease (which has 'BodyPart' as a foreign key) goes
	// under research and it is found out that the body part in which it happens is different, in the database
	// what is going to be changed is the attribute in 'BodyPart' foreign key of entity 'disease'.
	
	public void updateDevice (Integer device_id, Float newPrice, String newBrand) {
		try {
			String sql = "UPDATE Device SET price = ? AND brand = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setFloat(1, newPrice);
			prep.setString(2, newBrand);
			prep.setInt(3, device_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateDisease (Integer disease_id, String newDescription, Integer newBodyPart) {
		try {
			String sql = "UPDATE Disease SET description = ? AND BodyPart = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2, newBodyPart);
			prep.setInt(3, disease_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateImage (Integer image_id, String newDescription, Integer newPaper) {
		try {
			String sql = "UPDATE Image SET description = ? AND paper = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2,newPaper);
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// A paper is always going to be the same, its title is not going to change and the link of the page
	// found is neither going to change. The only thing that could happen to it, it is to be deleted. 
	
	public void updateProcedure (Integer procedure_id, String newDescription) {
		try {
			String sql = "UPDATE Procedure SET description = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, newDescription);
			prep.setInt(2, procedure_id);
			prep.executeUpdate();
			System.out.println("Update finished.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Symptoms will always be symptoms. If they change the old ones are going to be deleted and new ones
	// will be created. They aren't going to be modified.
}