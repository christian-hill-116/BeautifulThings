package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOneItem {

public static void main(String[] args) throws SQLException {
		
		//connect
		String dbURL = "jdbc:mysql://localhost:3306/beautifulThings";
		String user = "root";
		String password = "root";
		
		Connection c = null;
		Statement stmt = null;
		int rowsAffected = 0;
		
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connection is sucessful " + dbURL + " User= " + user + " pwd= " + password);
			
			//create a SQL statement
			stmt = c.createStatement();
			
			//execute the statement
			rowsAffected = stmt.executeUpdate("delete from beautifulThings.thingsTable where id = 6");
			
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

	}
}
