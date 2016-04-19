package es.uniovi.asw.ejemplo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.console.actions.RegisterEVoterAction;
import es.uniovi.asw.votingAccess.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;
  private Exception exceptionThrown;

  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testLanding() throws Exception {
    mvc.perform(get("/")).andExpect(status().isOk()).andExpect(
    		content().string(containsString("Voting")));
  }
  
  
  @Given("^There is not any voter in the census with the DNI introduced by the voter$")
  public void there_is_not_any_voter_in_the_census_with_the_DNI_introduced_by_the_voter() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
	  DatabaseTestHelper.deleteVoters();
	  insertExampleVoters();
	  org.junit.Assert.assertNull(DatabaseTestHelper.findVoter("53781447H"));
  }

  private void insertExampleVoters() throws SQLException {
	  DatabaseTestHelper.insertVoter(
			  new Voter("81380579U", "Perico",
					  "perico@uniovi.es", 1, "soyPerico", false, true));
	  DatabaseTestHelper.insertVoter(new Voter("55824978L", "Alberto",
			  "alberto@uniovi.es", 2, "albertoPassword", false, false));
  }

  @When("^the voter gives it's non-existing NIF$")
  public void the_voter_gives_it_s_non_existing_NIF() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  BufferedReader reader = new BufferedReader(
			  new FileReader(
					  "src/test/java/es/uniovi/asw/ejemplo/dniInput.txt"));
	  try {
		  new RegisterEVoterAction().askUser(reader, System.out, System.err);
	  } catch(BusinessException b) {
		  exceptionThrown = b;
	  }
  }
  
  

  @Then("^the voter receives a message telling he is not in census$")
  public void the_voter_receives_a_message_telling_he_is_not_in_census() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  org.junit.Assert.assertTrue(
    		  exceptionThrown.getMessage()
    		  .contains("given NIF does not correspond to any voter"));
      DatabaseTestHelper.deleteVoters();
  }

  @Given("^The voter is already registered for the electronic vote$")
  public void the_voter_is_already_registered_for_the_electronic_vote() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
	  DatabaseTestHelper.deleteVoters();
	  insertExampleVoters();
	  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("81380579U"));
  }
  

  @When("^the voter gives it's NIF$")
  public void the_voter_gives_it_s_NIF() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  BufferedReader reader = new BufferedReader(
			  new FileReader(
					  "src/test/java/es/uniovi/asw/ejemplo/dniaInput.txt"));
	  try {
		  new RegisterEVoterAction().askUser(reader, System.out, System.err);
	  } catch(BusinessException b) {
		  exceptionThrown = b;
	  }
  }
  

  @Then("^the voter receives a message telling he is already registered$")
  public void the_voter_receives_a_message_telling_he_is_already_registered() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  org.junit.Assert.assertTrue(
    		  exceptionThrown.getMessage()
    		  .contains("already registered as e-voter"));
      DatabaseTestHelper.deleteVoters();
  }
  
  
  @Given("^the voter exists but not registered for online voting$")
  public void the_voter_exists_but_not_registered_for_online_voting() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
	  DatabaseTestHelper.deleteVoters();
	  insertExampleVoters();
	  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("55824978L"));
	  org.junit.Assert.assertFalse(
			  DatabaseTestHelper.findVoter("55824978L").isEVoter());
  }
  

  @When("^the voter introduces it's NIF$")
  public void the_voter_introduces_it_s_NIF() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  BufferedReader reader = new BufferedReader(
			  new FileReader(
					  "src/test/java/es/uniovi/asw/ejemplo/dnibInput.txt"));
	  new RegisterEVoterAction().askUser(reader, System.out, System.err);

  }
  

  @Then("^the voter is registered for online voting$")
  public void the_voter_is_registered_for_online_voting() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
	  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("55824978L"));
	  org.junit.Assert.assertTrue(
			  DatabaseTestHelper.findVoter("55824978L").isEVoter());
	  DatabaseTestHelper.deleteVoters();
  }

}