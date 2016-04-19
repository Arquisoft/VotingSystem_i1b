package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;

public class LogInEVoterAction extends DefaultAction {
	
	public LogInEVoterAction(){
		setNextActions(new RegisterEVoterAction(), this);
	}
	
	@Override
	public String getOrder() {
		return "Identify yourself and vote";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		// TODO 
		// new LogInEVoter().logInEVoter(nif, password);
		// submit vote
		return null;
	}

}
