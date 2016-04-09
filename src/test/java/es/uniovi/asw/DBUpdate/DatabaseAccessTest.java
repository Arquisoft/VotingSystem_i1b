package es.uniovi.asw.DBUpdate;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.DBUpdate.modelo.Voter;

public class DatabaseAccessTest {

	private DatabaseAccess db = new DatabaseAccessImpl();
	private Voter v1 = new Voter("TEST1");
	private Voter v2 = new Voter("TEST2");
	private Voter v3 = new Voter("TEST3");
	private Voter v4 = new Voter("TEST4");
	private static final String DB_CONFIG_FILE = "test.resources.database";

	@BeforeClass
	public void setUp() throws SQLException {
		JdbcHelper.loadConnectionConfig(DB_CONFIG_FILE);
		fillDB();
	}

	@AfterClass
	public void restore() throws SQLException {
		emptyDB();
	}

	@Test
	public void testInsertVote() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateHasVoted() {
		fail("Not yet implemented");
	}

	@Test
	public void findVoter() {
		fail("Not yet implemented");
	}

	@Test
	public void insertEVoter() {
		fail("Not yet implemented");
	}

	private void fillDB() throws SQLException {
		DatabaseMethods.insertVoter(v1);
		DatabaseMethods.insertVoter(v2);
		DatabaseMethods.insertVoter(v3);
		DatabaseMethods.insertVoter(v4);
	}

	private void emptyDB() throws SQLException {
		DatabaseMethods.deleteVotes();
		DatabaseMethods.deleteVoters();
	}
}
