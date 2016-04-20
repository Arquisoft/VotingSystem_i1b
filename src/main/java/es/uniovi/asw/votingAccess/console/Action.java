package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

/**
 * Class that represents a service from the application.
 * @author UO238739
 *
 */
public interface Action {
	/**
	 * Gets a brief service definition to be shown to the user.
	 * @return
	 */
	public String getOrder();
	
	/**
	 * Asks the user any data needed to execute the service, verifies that data and executes the service.
	 * @param reader
	 * @param writer
	 * @param errorWriter
	 * @return
	 * @throws Exception
	 */
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception;
	
	/**
	 * Services offered to the user once the current service finishes its execution.
	 * @return
	 */
	public List<Action> getNextActions();
	public void setNextActions(Action...actions);
}
