package es.uniovi.asw.DBUpdate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;

public class DatabaseAccessTest {

	private DatabaseAccess db = new DatabaseAccessImpl();
	private static Voter voter1 = new Voter("TEST1");
	private static Voter voter2 = new Voter("TEST2");
	private static Voter voter3 = new Voter("TEST3");
	private Vote vote1 = new Vote("test");
	private static final String DB_CONFIG_FILE = "src/test/resources/database.properties";

	@BeforeClass
	public static void setUp() throws SQLException {
		JdbcHelper.setConnectionConfig(DB_CONFIG_FILE);
		fillDB();
	}

	@AfterClass
	public static void restore() throws SQLException {
		emptyDB();
	}

	private static void fillDB() throws SQLException {
		DatabaseAccessTestHelper.insertVoter(voter1);
		DatabaseAccessTestHelper.insertVoter(voter2);
		DatabaseAccessTestHelper.insertVoter(voter3);
	}

	private static void emptyDB() throws SQLException {
		DatabaseAccessTestHelper.deleteVotes();
		DatabaseAccessTestHelper.deleteVoters();
	}
	
	@Test
	public void findVoter() {
		assertEquals(voter1, db.findVoter(voter1.getNif()));
	}
	
	@Test
	public void testInsertVote() throws SQLException {
		db.insertVote(vote1);
		List<Vote> votes = DatabaseAccessTestHelper.findVotes();
		assertEquals(1, votes.size());
		assertEquals(vote1.getOption(), votes.get(0).getOption());
	}

	@Test
	public void testUpdateHasVoted() throws SQLException {
		db.updateHasVoted(voter2);
		assertEquals(voter2, DatabaseAccessTestHelper.findVoter(voter2.getNif()));
	}

	@Test
	public void insertEVoter() throws SQLException {
		db.insertEVoter(voter3);
		assertEquals(voter3, DatabaseAccessTestHelper.findVoter(voter3.getNif()));
	}

}
