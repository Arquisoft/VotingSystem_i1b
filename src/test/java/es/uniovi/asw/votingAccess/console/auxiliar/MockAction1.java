package es.uniovi.asw.votingAccess.console.auxiliar;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.votingAccess.console.actions.DefaultAction;

public class MockAction1 extends DefaultAction {

	public MockAction1(MockAction2 action){
		setNextActions(action);
	}
	
	@Override
	public String getOrder() {
		return "Mock action 1";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		return null;
	}

}
