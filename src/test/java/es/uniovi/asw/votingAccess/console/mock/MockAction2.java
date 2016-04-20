package es.uniovi.asw.votingAccess.console.mock;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.votingAccess.console.Action;

public class MockAction2 implements Action {

	@Override
	public String getOrder() {
		return "Mock action 2";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		return null;
	}

	@Override
	public List<Action> getNextActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new MockAction1());
		actions.add(this);
		return actions;
	}

}
