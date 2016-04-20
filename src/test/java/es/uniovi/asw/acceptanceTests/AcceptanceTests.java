package es.uniovi.asw.acceptanceTests;

import java.sql.SQLException;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.LogInEVoter;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.business.VoteEVoter;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class AcceptanceTests {
	
	
	private Exception exceptionThrown;
	private Object aux;
	
	
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
		  DatabaseTestHelper.insertVoter(new Voter("58584762G", "Pepe",
				  "pepe@uniovi.es", 2, "passwordPEPE", false, false));
	  }

	  @When("^the voter gives it's non-existing NIF$")
	  public void the_voter_gives_it_s_non_existing_NIF() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  try {
			  new RegisterEVoter().registerEVoter("53781447H");
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
		  try {
			  new RegisterEVoter().registerEVoter("81380579U");
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
		  new RegisterEVoter().registerEVoter("55824978L");

	  }
	  

	  @Then("^the voter is registered for online voting$")
	  public void the_voter_is_registered_for_online_voting() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("55824978L"));
		  org.junit.Assert.assertTrue(
				  DatabaseTestHelper.findVoter("55824978L").isEVoter());
		  DatabaseTestHelper.deleteVoters();
	  }
	  
	  
	  @Given("^the NIF of the voter who tries to vote does not exist$")
	  public void the_NIF_of_the_voter_who_tries_to_vote_does_not_exist() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNull(DatabaseTestHelper.findVoter("12345678U"));
	  }

	  
	  @When("^the voter introduces his NIF and password$")
	  public void the_voter_introduces_his_NIF_and_password() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      try {
	    	  new LogInEVoter().logInEVoter("12345678U", "password");
	      } catch(BusinessException b) {
	    	  exceptionThrown = b;
	      }
	  }

	  
	  @Then("^the application shows a message showing the problem$")
	  public void the_application_shows_a_message_showing_the_problem() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertTrue(exceptionThrown.getMessage().contains(
	    		  "given NIF does not correspond to any voter registered"));
	      DatabaseTestHelper.deleteVoters();
	  }
	  
	  
	  
	  @Given("^the NIF and password of the voter is correct but the voter is not registered to vote electronically$")
	  public void the_NIF_and_password_of_the_voter_is_correct_but_the_voter_is_not_registered_to_vote_electronically() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("58584762G"));
	  }

	  @When("^the voter inputs his NIF and password$")
	  public void the_voter_inputs_his_NIF_and_password() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  try {
	    	  new LogInEVoter().logInEVoter("58584762G", "passwordPEPE");
	      } catch(BusinessException b) {
	    	  exceptionThrown = b;
	      }
	  }

	  @Then("^the application tells the voter he is not registered$")
	  public void the_application_tells_the_voter_he_is_not_registered() throws Throwable {
	      org.junit.Assert.assertTrue(
	    		  exceptionThrown.getMessage().contains(
	    				  "not registered as e-voter"));
	      DatabaseTestHelper.deleteVoters();
	  }
	  
	  
	  @Given("^the NIF introduced by the voter exists$")
	  public void the_NIF_introduced_by_the_voter_exists() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("81380579U"));
	  }

	  @When("^the voter introduces a wrong password$")
	  public void the_voter_introduces_a_wrong_password() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      try {
	    	  new LogInEVoter().logInEVoter("81380579U", "wrongPassword");
	      } catch(BusinessException b) {
	    	  exceptionThrown = b;
	      }
	  }

	  @Then("^the program shows the error to the voter$")
	  public void the_program_shows_the_error_to_the_voter() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertTrue(
	    		  exceptionThrown.getMessage().contains("password is incorrect"));
	      DatabaseTestHelper.deleteVoters();
	  }
	  
	  
	  
	  
	  @Given("^the user exists$")
	  public void the_user_exists() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("81380579U"));
	  }

	  @Given("^it is registered for voting$")
	  public void it_is_registered_for_voting() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertTrue(
	    		  DatabaseTestHelper.findVoter("81380579U").isEVoter());
	  }

	  @Given("^it has not voted yet$")
	  public void it_has_not_voted_yet() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertFalse(
	    		  DatabaseTestHelper.findVoter("81380579U").getHasVoted());
	  }

	  @When("^the voter introduces the correct NIF and password$")
	  public void the_voter_introduces_the_correct_NIF_and_password() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      aux = new LogInEVoter().logInEVoter("81380579U", "soyPerico");
	  }

	  @Then("^the user is logged in$")
	  public void the_user_is_logged_in() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      Voter logged = (Voter) aux;
	      org.junit.Assert.assertEquals("Perico", logged.getName());
	      org.junit.Assert.assertEquals("81380579U", logged.getNif());
	      org.junit.Assert.assertEquals("perico@uniovi.es", logged.getEmail());
	  }
	  
	  
	  
	  @Given("^the user has already logged in$")
	  public void the_user_has_already_logged_in() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("81380579U"));
		  org.junit.Assert.assertFalse(
				  DatabaseTestHelper.findVoter("81380579U").getHasVoted());
		  aux = new LogInEVoter().logInEVoter("81380579U", "soyPerico");
	  }

	  @When("^the user submits his vote$")
	  public void the_user_submits_his_vote() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      new VoteEVoter().voteEVoter(new Vote("Y"), (Voter) aux);
	  }

	  @Then("^the option selected by the user is added to the database$")
	  public void the_option_selected_by_the_user_is_added_to_the_database() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      List<Vote> votes = DatabaseTestHelper.findVotes();
	      org.junit.Assert.assertEquals(1, votes.size());
	      org.junit.Assert.assertEquals("Y", votes.get(0).getOption());
	      
	  }

	  @Then("^the voter who voted is marked as already voted, so he can't vote again$")
	  public void the_voter_who_voted_is_marked_as_already_voted_so_he_can_t_vote_again() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertTrue(
	    		  DatabaseTestHelper.findVoter("81380579U").getHasVoted());
	      DatabaseTestHelper.deleteVotes();
	      DatabaseTestHelper.deleteVoters();
	  }

}
