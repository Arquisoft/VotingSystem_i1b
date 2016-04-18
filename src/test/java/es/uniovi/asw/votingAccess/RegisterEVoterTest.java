package es.uniovi.asw.votingAccess;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.exception.BusinessException;
import es.uniovi.asw.votingAccess.exception.BusinessExceptionMessages;

public class RegisterEVoterTest {

	private static Voter voter1 = new Voter("TEST1", "Test voter 1", "voter1@test.com", 1, "password1", false, false);
	private static Voter alreadyRegisteredVoter = new Voter("TEST2", "Already registered voter", "voter2@test.com", 1, "password2", false, true);
	private static Voter notInCensusVoter = new Voter("TEST3", "Non existing voter", "voter3@test.com", 1, "password3", false, false);
	
	@BeforeClass
	public static void setUp() throws SQLException {
		JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		DatabaseTestHelper.insertVoter(voter1);
		DatabaseTestHelper.insertVoter(alreadyRegisteredVoter);
	}

	@AfterClass
	public static void emptyDB() throws SQLException {
		DatabaseTestHelper.deleteVoters();
	}
	
	@Test
	public void testCorrectRegister() throws BusinessException, SQLException {
		Voter testVoter;
		
		assertFalse(voter1.isEVoter());
		testVoter = new RegisterEVoter().registerEVoter(voter1.getNif());
		assertEquals(voter1, testVoter);
		assertTrue(testVoter.isEVoter());
	}
	
	@Test
	public void testMissingVoterFromCensus() throws SQLException {
		String errorMessage = null;
		
		try{
			new RegisterEVoter().registerEVoter(notInCensusVoter.getNif());
		} catch (BusinessException e){
			errorMessage = e.getMessage();
		}
		
		assertNotNull(errorMessage);
		assertEquals(BusinessExceptionMessages.NOT_IN_CENSUS, errorMessage);
	}
	
	@Test
	public void testAlreadyResgisteredEVoter() throws SQLException {
		String errorMessage = null;
		
		try{
			new RegisterEVoter().registerEVoter(alreadyRegisteredVoter.getNif());
		} catch (BusinessException e){
			errorMessage = e.getMessage();
		}
		
		assertNotNull(errorMessage);
		assertEquals(BusinessExceptionMessages.ALREADY_EVOTER, errorMessage);
	}

}
