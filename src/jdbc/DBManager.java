package jdbc;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;
=======
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
import java.sql.Statement;
import java.util.ArrayList;

import pojos.*;

public class DBManager {
	Connection c = null;

<<<<<<< HEAD
    public void DBManager(){
    	connect();
    }
	
	public  void connect() {
=======
	public void connect() {
>>>>>>> branch 'master' of https://github.com/luciars7/Alexandria.git
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/company.db"); // Indicates
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
			System.out.println("Database connection opened.");

			// Here is where I do things with the database

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTables() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Create tables: begin
           
		
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE papers "
					+ "(ID INTEGER PRIMARY KEY,"
					+ " title  TEXT,"
					+ "source TEXT)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			System.out.println("Tables created.");
			
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE body parts "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " name  TEXT,"
					+ "location TEXT)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE diseases "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " name  TEXT,"
					+ "description TEXT,"
					+ "body parts TEXT REFERENCES body parts (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE authors "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " name  TEXT,"
					+ "origin TEXT,"
					+ "association TEXT)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE devices "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " name  TEXT,"
					+ "type TEXT ,"
					+ "price($) FLOAT,"
					+ "brand TEXT,"
					+ "med procedures INTEGER REFERENCES procedures (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE images "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " description,"
					+ "type TEXT,"
					+ "size TEXT,"
					+ "image BLOOB,"
					+ "paper INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			
			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE symptoms "
					+ "(ID     INTEGER PRIMARY KEY,"
					+ " name TEXT,"
					+ "description TEXT)";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			
			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE procedures"
					+ "(ID INTEGER PRIMARY KEY,"
					+ " name TEXT,"
					+ "description TEXT)";
			stmt8.executeUpdate(sql8);
			stmt8.close();
			
			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE symptoms-diseases"
					+ "(diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " symptoms INTEGER REFERENCES symptoms (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (diseases,symptoms))";
			stmt9.executeUpdate(sql9);
			stmt9.close();
			
			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE papers-authors"
					+ "(papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " authors INTEGER REFERENCES authors (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (papers,authors))";
			stmt10.executeUpdate(sql10);
			stmt10.close();
			
			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE papers-diseases"
					+ "(papers INTEGER REFERENCES papers (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (papers,diseases))";
			stmt11.executeUpdate(sql11);
			stmt11.close();
			
			Statement stmt12 = c.createStatement();
			String sql12 = "CREATE TABLE images-diseases"
					+ "(images INTEGER REFERENCES images (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (images,diseases))";
			stmt12.executeUpdate(sql12);
			stmt12.close();
			
			Statement stmt13 = c.createStatement();
			String sql13 = "CREATE TABLE procedures-diseases"
					+ "(procedures INTEGER REFERENCES procedures (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " diseases INTEGER REFERENCES diseases (ID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "PRIMARY KEY (procedures,diseases))";
			stmt13.executeUpdate(sql13);
			stmt13.close();
			// Create table: end

			//
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO authors (ID,name,origin,association) VALUES (1, 'Christian Nordqvist', 'UK' ,'Medical News Today')";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO authors (ID,name,origin,association) VALUES (2, 'Pradeep Arora', 'USA' ,'Arora Psychiatric Consultation')";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO symptoms-diseases (diseases,symptoms) VALUES (1,1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO symptoms-diseases (diseases,symptoms) VALUES (1,5)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO symptoms-diseases (diseases,symptoms) VALUES (2,7)";
			stmtSeq.executeUpdate(sqlSeq);
			
			stmtSeq.close();
			
			
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<Author> selectAuthor(String NAME) {
		ArrayList<Author> list = null;
		try {
			// Retrieve data: begin
			list = new ArrayList<Author>();
			Statement stmt = c.createStatement();
			String sql = "SELECT *  FROM authors WHERE name IN "+NAME;
			ResultSet rs = stmt.executeQuery(sql); //Works as an iterator.
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				String origin = rs.getString("origin");
				String association = rs.getString("association");
				list.add(new Author(id, name, origin, association));
			}
			rs.close();
			stmt.close();
			System.out.println("Search finished.");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
}
	
