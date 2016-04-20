package es.uniovi.asw.votingAccess.console;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.console.actions.LogInAndVoteAction;
import es.uniovi.asw.votingAccess.console.actions.RegisterEVoterAction;
import es.uniovi.asw.votingAccess.console.auxiliar.TestStream;
import es.uniovi.asw.votingAccess.console.votingMode.EVotingMode;

public class EVotingModeTest {

	private static RegisterEVoterAction registerAction;
	private static LogInAndVoteAction logInAction;
	private TestStream output;
	private BufferedReader input;
	
	private static Voter voter1;
	
	@BeforeClass
	public static void setUp() throws SQLException{
		getActions();
		fillDB();
	}
	
	private static void getActions(){
		ConsoleReader console = new EVotingMode().setUpConsole();
		for(Action action : console.getInitialActions()){
			if(action instanceof RegisterEVoterAction){
				registerAction = (RegisterEVoterAction)action;
			} else if(action instanceof LogInAndVoteAction){
				logInAction = (LogInAndVoteAction)action;
			}
		}
	}
	
	private static void fillDB() throws SQLException {
		JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		voter1 = new Voter("TEST1", "Test voter 1", "voter1@test.com", 1, "password1", false, false);
		DatabaseTestHelper.insertVoter(voter1);
	}
	
	@AfterClass
	public static void emptyDB() throws SQLException {
		DatabaseTestHelper.deleteVoters();
	}

	@Before
	public void setUpStreams(){
		this.output = new TestStream(System.out);
	}
	
	@Test
	public void testRegisterEVoterAction() throws Exception {
		String dni = voter1.getNif();
		String empty = "";
		
		changeInputStream(empty + "\n" + dni);
		
		registerAction.askUser(input, output, output);
		
		assertTrue(output.getWrittenText().contains("NIF format is wrong"));
		assertTrue(output.getWrittenText().contains("New e-voter registered"));
	}
		
	
	@Test
	public void testLogInAndVoteAction(){
		//TODO
	}
	
	
	private void changeInputStream(String data){
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		input = new BufferedReader(new InputStreamReader(System.in));
	}
}
