package commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHandler {

	private Connection connection;
	private Statement statement;
	
	public void dbConnection() {
		try {
			 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Driver NOT FOUND!");
			
			System.out.println(e.toString());
			return;
 
		}
 
		connection = null;
 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/Per_DB", "postgres",
					"postgres");
 
		} catch (SQLException e) {
 
			System.out.println(e);
			return;
 
		}
		
	}
	
	public void stat() throws SQLException{
		statement = connection.createStatement();
	}
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}

	
}
