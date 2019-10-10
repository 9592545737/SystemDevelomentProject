package application;

import java.sql.*;

public class CConnection {
	
	public Statement createConnection() {
		Statement stmt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-repair-shop-database","root","");
			stmt=connection.createStatement();
			System.out.println("Connection Build");
		} 
		catch(Exception e) {
			System.out.println(e);
		}
		return stmt;
	}
}
