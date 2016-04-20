package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.LogInEVoter;
import es.uniovi.asw.votingAccess.business.VoteEVoter;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class LogInAndVoteAction extends DefaultAction {
	
	@Override
	public String getOrder() {
		return "Identify yourself and vote";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer,
			PrintStream errorWriter) throws Exception {
		Voter v = logIn(reader, writer, errorWriter);
		vote(v, reader, writer, errorWriter);
		return v;
	}
	
	
	private void vote(Voter v, BufferedReader reader,
			PrintStream writer, PrintStream errorWriter)
					throws IOException, SQLException {

		String voteInput = null;
		writer.println(
				"Please introduce your vote, write y for yes or n for no: ");
		while (!correctVote(voteInput)) {
			voteInput = reader.readLine();
			if (!correctVote(voteInput)) {
				errorWriter.println("The vote is wrong");
				writer.println("Please re-enter it");
			}
		}
		
		Vote vote = new Vote(voteInput.toUpperCase());
		new VoteEVoter().voteEVoter(vote, v);
		writer.println("Your vote has been submitted succesfully");
		
	}
	
	
	private boolean correctVote(String vote) {
		if (vote == null)
			return false;
		return vote.toLowerCase().equals("y") || vote.toLowerCase().equals("n");
	}

	private Voter logIn(BufferedReader reader,
			PrintStream writer, PrintStream errorWriter) throws IOException,
	BusinessException, SQLException {
		
		String nif = null, password = null;
		writer.println("Voter's NIF:");
		while (!correctNIF(nif)) {
			nif = reader.readLine();
			if (!correctNIF(nif)) {
				errorWriter.println("NIF format is wrong");
				writer.println("Please, re-enter NIF");
			}
		}
		System.out.println("Please introduce your password:");
		password = reader.readLine();
		return new LogInEVoter().logInEVoter(nif, password);
	}
	

}
