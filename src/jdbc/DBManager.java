package jdbc;

import java.io.*;

import java.sql.*;

import java.util.ArrayList;

import pojos.*;

public class DBManager {

	Connection c = null;

	public DBManager(Connection c) {

		this.c = c;

	}

	public void disconnect() {

		try {

			c.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

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

				String sql = "SELECT * FROM author WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM bodypart";

				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String name = rs.getString("name");

					String location = rs.getString("location");

					list.add(new BodyPart(id, name, location));

				}

				rs.close();

			} else {

				String sql = "SELECT * FROM bodypart WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM device";

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

				String sql = "SELECT * FROM device WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM disease";

				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String name = rs.getString("name");

					String description = rs.getString("description");

					BodyPart bodypart = (BodyPart) rs.getObject("bodyPart");

					list.add(new Disease(id, name, description, bodypart));

				}

				rs.close();

			} else {

				String sql = "SELECT * FROM disease WHERE name = '" + NAME + "'";

				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String name = rs.getString("name");

					String description = rs.getString("description");

					BodyPart bodypart = (BodyPart) rs.getObject("bodyPart");

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

				String sql = "SELECT * FROM image";

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

				String sql = "SELECT * FROM image WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM paper";

				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String title = rs.getString("title");

					String source = rs.getString("source");

					list.add(new Paper(id, title, source));

				}

				rs.close();

			} else {

				String sql = "SELECT * FROM paper WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM procedure";

				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String name = rs.getString("name");

					String description = rs.getString("description");

					list.add(new Procedure(id, name, description));

				}

				rs.close();

			} else {

				String sql = "SELECT * FROM procedure WHERE name = '" + NAME + "'";

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

				String sql = "SELECT * FROM symptom";

				ResultSet rs = stmt.executeQuery(sql);// Works as an iterator.

				while (rs.next()) {

					int id = rs.getInt("ID");

					String name = rs.getString("name");

					String description = rs.getString("description");

					list.add(new Symptom(id, name, description));

				}

				rs.close();

			} else {

				String sql = "SELECT * FROM symptom WHERE name = '" + NAME + "'";

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

	public void createTables() {

		try {

			// Create tables: begin

			Statement stmt1 = c.createStatement();

			String sql1 = "CREATE TABLE paper" + "(ID INTEGER PRIMARY KEY," + "title TEXT," + "source TEXT,"
					+ "device INTEGER REFERENCES device (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE)";

			stmt1.executeUpdate(sql1);

			stmt1.close();

			Statement stmt2 = c.createStatement();

			String sql2 = "CREATE TABLE bodypart" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "location TEXT)";

			stmt2.executeUpdate(sql2);

			stmt2.close();

			Statement stmt3 = c.createStatement();

			String sql3 = "CREATE TABLE disease" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "description TEXT,"

					+ "bodypart INTEGER REFERENCES bodypart (ID) ON UPDATE CASCADE ON DELETE CASCADE)";

			stmt3.executeUpdate(sql3);

			stmt3.close();

			Statement stmt4 = c.createStatement();

			String sql4 = "CREATE TABLE author" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "origin TEXT,"

					+ "association TEXT)";

			stmt4.executeUpdate(sql4);

			stmt4.close();

			Statement stmt5 = c.createStatement();

			String sql5 = "CREATE TABLE device" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "type TEXT ,"

					+ "price FLOAT," + "brand TEXT,"

					+ "procedure INTEGER REFERENCES procedure (ID) ON UPDATE CASCADE ON DELETE CASCADE,"

					+ "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE)";

			stmt5.executeUpdate(sql5);

			stmt5.close();

			Statement stmt6 = c.createStatement();

			String sql6 = "CREATE TABLE image" + "(ID INTEGER PRIMARY KEY," + "description TEXT," + "type TEXT,"

					+ "size TEXT," + "image BLOOB,"

					+ "paper INTEGER REFERENCES paper (ID) ON UPDATE CASCADE ON DELETE CASCADE)";

			stmt6.executeUpdate(sql6);

			stmt6.close();

			Statement stmt7 = c.createStatement();

			String sql7 = "CREATE TABLE symptom" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "description TEXT)";

			stmt7.executeUpdate(sql7);

			stmt7.close();

			Statement stmt8 = c.createStatement();

			String sql8 = "CREATE TABLE procedure" + "(ID INTEGER PRIMARY KEY," + "name TEXT," + "description TEXT)";

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

			String sqlSeq = "INSERT INTO image (description, type, size, image) VALUES ('"

					+ image.getDescription() + "', '" + image.getType() + "', '" + image.getSize() + "', '"

					+ image.getImage() + "')";

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

			String sql = "UPDATE device SET price = ? AND brand = ? WHERE ID = ?";

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

	public void updateDisease(Integer disease_id, String newDescription, Integer newBodyPart) {

		try {

			String sql = "UPDATE disease SET description = ? AND BodyPart = ? WHERE ID = ?";

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

			String sql = "UPDATE image SET description = ? AND paper = ? WHERE ID = ?";

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

	// A paper is always going to be the same, its title is not going to change

	// and the link of the page

	// found is neither going to change. The only thing that could happen to it,

	// it is to be deleted.

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

}
