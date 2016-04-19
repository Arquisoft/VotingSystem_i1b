package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.LogInEVoter;
import es.uniovi.asw.votingAccess.console.Action;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class LogInAndVoteAction implements Action {

	
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
		
		
	private boolean correctNIF(String nif) {
		return nif != null && !nif.isEmpty();
	}


	@Override
	public String getOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO: Handle exceptions properly


	@Override
	public Object askUser(BufferedReader reader,
			PrintStream writer, PrintStream errorWriter) throws Exception {
		Voter v = logIn(reader, writer, errorWriter);
		//vote(v, reader, writer, errorWriter);
		return v;
	}


	@Override
	public List<Action> getNextActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
