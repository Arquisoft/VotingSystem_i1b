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
import es.uniovi.asw.votingAccess.exception.BusinessException;
import es.uniovi.asw.votingAccess.exception.BusinessExceptionMessages;

public class LogInEVoterTest {

	private static Voter voter1 = new Voter("TEST1", "Test voter 1", "voter1@test.com", 1, "password1", false, true);
	private static Voter voter2 = new Voter("TEST2", "Test voter 2", "voter2@test.com", 1, "password2", false, true);
	private static Voter notEVoter = new Voter("TEST3", "No e-voter", "voter3@test.com", 1, "password3", false, false);
	private static Voter notInCensusVoter = new Voter("TEST4", "Not in-census voter", "voter4@test.com", 1, "password4", false, true);
	
	@BeforeClass
	public static void setUp() throws SQLException {
		JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		DatabaseTestHelper.insertVoter(voter1);
		DatabaseTestHelper.insertVoter(voter2);
		DatabaseTestHelper.insertVoter(notEVoter);
	}

	@AfterClass
	public static void emptyDB() throws SQLException {
		DatabaseTestHelper.deleteVoters();
	}
	
	@Test
	public void testCorrectLogIn() throws BusinessException, SQLException {
		Voter testVoter;
		
		assertTrue(voter1.isEVoter());
		testVoter = new LogInEVoter().logInEVoter(voter1.getNif(), voter1.getPassword());
		assertEquals(voter1, testVoter);
	}
	
	@Test
	public void testWrongPassword() throws BusinessException, SQLException {
		String errorMessage = null;
		
		assertTrue(voter2.isEVoter());
		try{
			new LogInEVoter().logInEVoter(voter2.getNif(), "wrong_"+voter2.getPassword());
		} catch (BusinessException e){
			errorMessage = e.getMessage();
		}
		
		assertNotNull(errorMessage);
		assertEquals(BusinessExceptionMessages.WRONG_PASSWORD, errorMessage);
	}
	
	@Test
	public void testMissingVoterFromCensus() throws SQLException {
		String errorMessage = null;
		
		assertTrue(notInCensusVoter.isEVoter());
		try{
			new LogInEVoter().logInEVoter(notInCensusVoter.getNif(), notInCensusVoter.getPassword());
		} catch (BusinessException e){
			errorMessage = e.getMessage();
		}
		
		assertNotNull(errorMessage);
		assertEquals(BusinessExceptionMessages.NOT_IN_CENSUS, errorMessage);
	}
	
	@Test
	public void testNotEVoter() throws BusinessException, SQLException {
		String errorMessage = null;
		
		assertFalse(notEVoter.isEVoter());
		try{
			new LogInEVoter().logInEVoter(notEVoter.getNif(), notEVoter.getPassword());
		} catch (BusinessException e){
			errorMessage = e.getMessage();
		}
		
		assertNotNull(errorMessage);
		assertEquals(BusinessExceptionMessages.NOT_EVOTER, errorMessage);
	}
}
