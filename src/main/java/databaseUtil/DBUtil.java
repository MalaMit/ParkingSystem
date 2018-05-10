package databaseUtil;

import java.sql.*;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/parking?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123";


	// init connection object
	private static Connection connection;
	// init properties object
	private static Properties properties;

	// create properties
	private static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
		}
		return properties;
	}

	// connect database
	public static Connection dbConnect() {
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	// disconnect database
	public static void dbDisconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// this is for insert/delete/update operation
	public static void dbExcecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		try {
			dbConnect();
			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			System.out.println("Problem occured at dbExcecuteQuery operation" + e);
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			dbDisconnect();
		}
	}

	//this is for retrieving the records from the DB
	public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		CachedRowSet crs = null;

		try {
			dbConnect();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(rs);

		} catch (SQLException e) {
			System.out.println("Error occured in dbExecute operation" + e);
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			dbDisconnect();
		}
		return crs;
	}

}
