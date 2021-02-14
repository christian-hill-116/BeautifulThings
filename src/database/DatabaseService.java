package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.BeautifulThing;

public class DatabaseService {
	
	//four crud operations
	public int deleteOne(int id) throws SQLException {
		
		int numberOfRowsAffected = 0;
		
		//connect
				String dbURL = "jdbc:mysql://localhost:3306/beautifulThings";
				String user = "root";
				String password = "root";
				
				Connection c = null;
				PreparedStatement stmt = null;
				int rowsAffected = 0;
				
				try {
					c = DriverManager.getConnection(dbURL, user, password);
					System.out.println("Connection is sucessful " + dbURL + " User= " + user + " pwd= " + password);
					
					//create a SQL statement
					stmt = c.prepareStatement("delete from beautifulThings.thingsTable where id = ?");
					stmt.setInt(1, id);
					//execute the statement
					rowsAffected = stmt.executeUpdate();
					
					//success message
					System.out.println("Rows affected " + rowsAffected);
					
				} catch (SQLException e) {
					System.out.println("Error connection with database");
					e.printStackTrace();
				}finally {
					//close the connection to the db.
					
					stmt.close();
					
					c.close();
					
				}
		
		return numberOfRowsAffected;
	}
	
	public int insertOne(BeautifulThing b) throws SQLException {
		
		int numberOfRowsAffected = 0;
		
			//connect
				String dbURL = "jdbc:mysql://localhost:3306/beautifulThings";
				String user = "root";
				String password = "root";
		
				Connection c = null;
				PreparedStatement stmt = null;
				int rowsAffected = 0;
		
				try {
					c = DriverManager.getConnection(dbURL, user, password);
					System.out.println("Connection is sucessful " + dbURL + " User= " + user + " pwd= " + password);
			
					//create a SQL statement
					stmt = c.prepareStatement("insert into beautifulThings.thingsTable (id, thing_title, thing_description, thing_value) 		values(null, ?, ?, ?)");
					stmt.setString(1, b.getThingTitle());
					stmt.setString(2, b.getThingDescription());
					stmt.setInt(3, b.getRating());
			
					//execute the statement
					rowsAffected = stmt.executeUpdate();
			
					//success message
					System.out.println("Rows inserted " + rowsAffected);
			
				} catch (SQLException e) {
					System.out.println("Error connection with database");
					e.printStackTrace();
				}finally {
					//close the connection to the db.
			
					stmt.close();
			
					c.close();
			
		}
		
		return numberOfRowsAffected;
	}
	
	public ArrayList<BeautifulThing> readAll() throws SQLException{
		ArrayList<BeautifulThing> everyone = new ArrayList<>();
		BeautifulThing b;
		
		
		//connect
		String dbURL = "jdbc:mysql://localhost:3306/beautifulThings";
		String user = "root";
		String password = "root";
		
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connection is sucessful " + dbURL + " User= " + user + " pwd= " + password);
			
			//create a SQL statement
			stmt = c.createStatement();
			
			//execute the statement
			rs = stmt.executeQuery("select * from beautifulThings.thingsTable");
			
			//process the rows in a result set
			while (rs.next()) {
				System.out.println("id= " + rs.getInt("id") + " title= " + rs.getString("thing_title") + " desc= " + rs.getString("thing_description") + " rating= " + rs.getInt("thing_value"));
				
				b = new BeautifulThing(rs.getInt("id"), rs.getString("thing_title"), rs.getString("thing_description"), rs.getInt("thing_value"));
				
				everyone.add(b);
				
			}
		} catch (SQLException e) {
			System.out.println("Error connection with database");
			e.printStackTrace();
		}finally {
			//close the connection to the db.
			rs.close();
			stmt.close();
			c.close();
			
		}
		
		return everyone;
	}
	
	public int updateOne(int id, BeautifulThing b) throws SQLException {

		int numberOfRowsAffected = 0;
		
		//connect
		String dbURL = "jdbc:mysql://localhost:3306/beautifulThings";
		String user = "root";
		String password = "root";
		
		Connection c = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;
		
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connection is sucessful " + dbURL + " User= " + user + " pwd= " + password);
			
			//create a SQL statement
			stmt = c.prepareStatement("update beautifulThings.thingsTable set thing_title = ?, thing_descripton = ?, thing_value = ?, where id = ?");
			stmt.setString(1, b.getThingTitle());
			stmt.setString(2, b.getThingDescription());
			stmt.setInt(3, b.getRating());
			stmt.setInt(4, id);
			//execute the statement
			rowsAffected = stmt.executeUpdate();
			
			//success message
			System.out.println("Rows affected " + rowsAffected);
			
		} catch (SQLException e) {
			System.out.println("Error connection with database");
			e.printStackTrace();
		}finally {
			//close the connection to the db.
			
			stmt.close();
			
			c.close();
			
		}
		
		return numberOfRowsAffected;
	}
	

}
