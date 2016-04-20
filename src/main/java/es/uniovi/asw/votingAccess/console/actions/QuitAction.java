package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

import es.uniovi.asw.votingAccess.console.Action;

public class QuitAction extends DefaultAction {

	@Override
	public String getOrder() {
		return "Quit";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer,
			PrintStream errorWriter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getNextActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
