package es.uniovi.asw.votingAccess.console.auxiliar;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.votingAccess.console.actions.DefaultAction;

public class MockAction2 extends DefaultAction {
	
	@Override
	public String getOrder() {
		return "Mock action 2";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		return null;
	}
}
