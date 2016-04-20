package es.uniovi.asw.DBUpdate;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Class made to ease the database connections and JDBC stuff
 * 
 * @author UO238739
 *
 */
public class JdbcHelper {

	private static Properties connectionConfig;
	private static Properties queries;
	private static final String DEFAULT_CONFIG_FILE = "src/main/resources/database.properties";
	private static final String QUERIES_FILE = "src/main/resources/queries.properties";

	public static Properties getQueries() {
		if (queries == null) {
			queries = loadProperties(QUERIES_FILE);
		}
		return queries;
	}

	private static Properties getConnectionConfig() {
		if (connectionConfig == null) {
			setConnectionConfig(DEFAULT_CONFIG_FILE);
		}
		return connectionConfig;
	}

	public static void setConnectionConfig(String path) {
		connectionConfig = loadProperties(path);
		try {
			Class.forName(connectionConfig.getProperty("DRIVER"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Properties loadProperties(String path) {
		Properties result = new Properties();
		try {
			result.load(new FileInputStream(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getConnectionConfig().getProperty("URL"),
				getConnectionConfig().getProperty("USER"), getConnectionConfig().getProperty("PASS"));
	}

	public static void close(ResultSet rs, Statement st, Connection c) {
		close(rs);
		close(st);
		close(c);
	}

	public static void close(Connection c, Statement st) {
		close(c);
		close(st);
	}

	public static void close(ResultSet rs, Statement st) {
		close(rs);
		close(st);
	}

	protected static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
			}
		}
	}

}
