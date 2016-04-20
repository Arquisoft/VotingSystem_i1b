package es.uniovi.asw.votingAccess.console.mock;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.votingAccess.console.Action;

public class MockAction1 implements Action {

	@Override
	public String getOrder() {
		return "Mock action 1";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		writer.println(reader.readLine());
		return null;
	}

	@Override
	public List<Action> getNextActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new MockAction2());
		return actions;
	}

}
