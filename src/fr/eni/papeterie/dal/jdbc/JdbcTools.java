package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {
	private static Connection connect=null;

	public static Connection getConnection() throws SQLException {
		if(connect==null) {
			connect = DriverManager.getConnection(Settings.getProperty("url"), Settings.getProperty("user"), Settings.getProperty("password"));
		}
		return connect;
	}
	
	public static void closeConnection() throws SQLException {
		if(connect!=null) {
			connect.close();
			connect = null;
		}
	}
	
}
