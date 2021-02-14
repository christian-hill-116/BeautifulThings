package main;

import java.sql.*;

public class ReadAll {

	public static void main(String[] args) throws SQLException {
		
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
		
	}

}
