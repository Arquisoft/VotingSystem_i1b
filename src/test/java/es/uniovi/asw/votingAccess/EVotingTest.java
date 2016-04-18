package es.uniovi.asw.votingAccess;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.LogInEVoter;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class EVotingTest {

	private static Voter voter1 = new Voter("TEST1", "Test voter 1", "voter1@test.com", 1, "password1", false, false);
	private static Voter voter2 = new Voter("TEST2", "Test voter 2", "voter2@test.com", 1, "password2", false, true);
	
	@BeforeClass
	public static void setUp() throws SQLException {
		JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		DatabaseTestHelper.insertVoter(voter1);
		DatabaseTestHelper.insertVoter(voter2);
	}

	@AfterClass
	public static void emptyDB() throws SQLException {
		DatabaseTestHelper.deleteVoters();
	}
	
	@Test
	public void testRegisterEVoter() throws BusinessException, SQLException {
		Voter testVoter;
		
		assertFalse(voter1.isEVoter());
		testVoter = new RegisterEVoter().registerEVoter(voter1.getNif());
		assertEquals(voter1, testVoter);
		assertTrue(testVoter.isEVoter());
	}
	
	@Test
	public void testLogInEVoter() throws BusinessException, SQLException {
		Voter testVoter;
		
		assertTrue(voter2.isEVoter());
		testVoter = new LogInEVoter().logInEVoter(voter2.getNif(), voter2.getPassword());
		assertEquals(voter2, testVoter);
	}

}
