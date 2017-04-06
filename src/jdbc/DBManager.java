package jdbc;

import java.sql.*;
import java.util.ArrayList;
import pojos.*;


public class DBManager {
	Connection c = null;

	public DBManager(){
		connect();
	}

	public ArrayList<Author> selectAuthor(String NAME) {
		ArrayList<Author> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Author>();
			Statement stmt = c.createStatement();
			if(NAME.equalsIgnoreCase("all")){
	 			String sql = "SELECT * FROM authors";
	 			ResultSet rs = stmt.executeQuery(sql);//Works as an iterator.
	 			while (rs.next()) {
	 				int id = rs.getInt("ID");
	 				String name = rs.getString("name");
	 				String origin = rs.getString("origin");
	 				String association = rs.getString("association");
	 				list.add(new Author(id, name, origin, association));
			}rs.close();
	 			}
	 			else{
	 				
	 				String sql = "SELECT * FROM authors WHERE name = '"+NAME+"'";
	 				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
	 				while (rs.next()) {
	 					int id = rs.getInt("ID");
	 					String name = rs.getString("name");
	 					String origin = rs.getString("origin");
	 					String association = rs.getString("association");
	 					list.add(new Author(id, name, origin, association));
	 				
	 			}rs.close();
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
			if(NAME.equalsIgnoreCase("all")){
	 			String sql = "SELECT * FROM bodyparts";
	 			ResultSet rs = stmt.executeQuery(sql);//Works as an iterator.
	 			while (rs.next()) {
	 				int id = rs.getInt("ID");
	 				String name = rs.getString("name");
	 				String location = rs.getString("location");
	 				list.add(new BodyPart(id, name, location));
			}rs.close();
	 			}
	 			else{
	 				
	 				String sql = "SELECT * FROM bodyparts WHERE name = '"+NAME+"'";
	 				ResultSet rs = stmt.executeQuery(sql); // Works as an iterator.
	 				while (rs.next()) {
	 					int id = rs.getInt("ID");
		 				String name = rs.getString("name");
		 				String location = rs.getString("location");
		 				list.add(new BodyPart(id, name, location));
	 				
	 			}rs.close();
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

	public void createTables() {
		try {
			// Open database connection
			connect();
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE papers"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "title TEXT,"
					+ "source TEXT)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE bodyparts"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "location TEXT)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE diseases"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "description TEXT,"
					+ "bodyparts TEXT REFERENCES bodyparts (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE authors"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "origin TEXT,"
					+ "association TEXT)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			
			Statement stmt5 = c.createStatement();

			String sql5 = "CREATE TABLE devices"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "type TEXT ,"
					+ "price$ FLOAT,"
					+ "brand TEXT,"
			
					+ "medprocedures INTEGER REFERENCES procedures (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE images"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "description TEXT,"
					+ "type TEXT,"
					+ "size TEXT,"
					+ "image BLOOB,"
					+ "paper INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			
			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE symptoms"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "description TEXT)";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			
			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE procedures"
					+ "(ID INTEGER PRIMARY KEY,"
					+ "name TEXT,"
					+ "description TEXT)";
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
			e.printStackTrace();}
		}


	// INSERTS ------------------------------------------------------------------------------------------------
	public void insertIntoAuthor (String name, String origin, String association) {
		try {
			Statement stmtSeq = c.createStatement();
			 String sqlSeq = "INSERT INTO authors (name,origin,association) VALUES ('" + name + "','" + origin + "','" + association + "')";
		stmtSeq.executeUpdate(sqlSeq);
			c.close();
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

	public void insertIntoDevice (String name, String type, float price, String brand) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Device (name, type, price, brand) VALUES ('" + name + "', '" + type + "', '" + price
					+ "', '" + brand + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoDisease (String name, String description, BodyPart bodyParts) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Disease (name, description, bodyParts) VALUES ('" + name + "', '" + description
					+ "', '" + bodyParts + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoImage (String description, String type, String size, String link, Paper paper) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Image (description, type, size, link, paper) VALUES ('" + description + "', '"
					+ type + "', '" + size + "', '" + link + "', '" + paper + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertIntoPaper (String title, String source) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Paper (title, source) VALUES ('" + title + "', '" + source + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertIntoProcedure (String name, String description) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Procedure (name, description) VALUES ('" + name + "', '" + description + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertIntoSymptom (String name, String description) {
		try {
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO Symptom (name, description) VALUES ('" + name + "', '" + description + "')";
			stmtSeq.executeUpdate(sqlSeq);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	 /*
	try {
		// Open database connection
		Class.forName("org.sqlite.JDBC");
		// Note that we are using the class' connection
		c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		connect();
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
		Statement stmtSeq = c.createStatement();
		 String sqlSeq = "INSERT INTO images-diseases (name,origin,association) VALUES (" + name + "," + origin + "," + association + ")";
		stmtSeq.executeUpdate(sqlSeq);
	}
		catch (SQLException ex){
			ex.printStackTrace();
=======
*/
// DELETIONS ------------------------------------------------------------------------------------------------
	public void deleteAuthor (int author_id) {
		try {
			String sql = "DELETE FROM Author WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, author_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteBodyPart (int bodypart_id) {
		try {
			String sql = "DELETE FROM BodyPart WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, bodypart_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}