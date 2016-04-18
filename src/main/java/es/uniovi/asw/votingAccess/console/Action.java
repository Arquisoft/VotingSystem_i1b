package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

public interface Action {
	public String getOrder();
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception;
	public List<Action> getNextActions();
}
