package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Class made to ease the database connections and JDBC stuff
 * @author UO238739
 *
 */
public class JdbcHelper {

	private static ResourceBundle connectionConfig;
	private static final String DEFAULT_CONFIG_FILE = "main.resources.database";
	
	private static ResourceBundle getConnectionConfig(){
		if(connectionConfig==null){
			loadConnectionConfig(DEFAULT_CONFIG_FILE);
		} 
		return connectionConfig;
	}
	
	public static void loadConnectionConfig(String path){
		connectionConfig = ResourceBundle.getBundle(path);
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getConnectionConfig().getString("URL"), 
				getConnectionConfig().getString("USER"), 
				getConnectionConfig().getString("PASS"));
	}
	
	public static void close(ResultSet rs, Statement st, Connection c) {
		close(rs);
		close(st);
		close(c);
	}

	public static void close(ResultSet rs, Statement st) {
		close(rs);
		close(st);
	}

	protected static void close(ResultSet rs) {
		if (rs != null) try { rs.close(); } catch(SQLException e) {};
	}

	public static void close(Statement st) {
		if (st != null ) try { st.close(); } catch(SQLException e) {};
	}

	public static void close(Connection c) {
		if (c != null) try { c.close(); } catch(SQLException e) {};
	}
	
}
