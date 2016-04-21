package es.uniovi.asw.votingAccess.console;

import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.console.actions.MarkVoterAction;
import es.uniovi.asw.votingAccess.console.actions.SubmitVoteAction;
import es.uniovi.asw.votingAccess.console.auxiliar.TestStream;
import es.uniovi.asw.votingAccess.console.votingMode.ElectoralBoardMode;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class ElectoralBoardModeTest {

    private static MarkVoterAction markVoter;
    private static SubmitVoteAction submitVote;
    private static Voter voter1;
    private TestStream output;
    private BufferedReader input;

    @BeforeClass
	public static void setUp() throws SQLException {
		getActions();
		fillDB();
	}

    private static void fillDB() throws SQLException {
        JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
        voter1 = new Voter("Prueba", "Test voter 1", "voter1@test.com", 1, "password1", false, false);
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

    private static void getActions(){
        Object[] params = new Object[1];
        params[0] = "1";
        ConsoleReader console = new ElectoralBoardMode().setUpConsole(params);
        for(Action action : console.getInitialActions()){
            if(action instanceof MarkVoterAction){
                markVoter = (MarkVoterAction)action;
            } else if(action instanceof SubmitVoteAction){
                submitVote = (SubmitVoteAction)action;
            }
        }
    }

	@Test
	public void testMarkVoterAction() throws Exception {
        String dni1 = voter1.getNif();

        changeInputStream(dni1);

        markVoter.askUser(input, output, output);

        assertTrue(output.getWrittenText().contains("The voter has been marked"));
	}

	
	@Test
	public void testSubmitVoteAction(){
		//TODO
	}

    private void changeInputStream(String data){
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        input = new BufferedReader(new InputStreamReader(System.in));
    }

}
