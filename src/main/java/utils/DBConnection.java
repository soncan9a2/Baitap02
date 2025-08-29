package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private final String serverName = "LAPTOP-GQBHIR0S\\SQLEXPRESS";
	private final String dbName = "LTWeb";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "1012";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
				 + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName 
			+ ";encrypt=true;trustServerCertificate=true;";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
}
