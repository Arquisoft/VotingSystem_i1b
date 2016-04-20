package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.votingAccess.console.actions.QuitAction;

/**
 * Class made to output the different services offered to the user and to process what he/she chooses.
 * Each service corresponds to an Action class.
 * @author UO238739
 *
 */
public class ConsoleReader {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private PrintStream writer = System.out;
	private PrintStream errorWriter = System.err;
	private final static String ORDER_TEMPLATE = "(%d) %s";
	private List<Action> initialActions = new ArrayList<Action>();
	private static QuitAction quitAction;
	
	public ConsoleReader(Action... initialActions) {
		this.initialActions.add(new QuitAction());
		for(Action action : initialActions){
			this.initialActions.add(action);
		}
	}
	
	public static QuitAction getQuitAction(){
		if(quitAction == null){
			quitAction = new QuitAction();
		}
		return quitAction;
	}
	
	public void setWriter(PrintStream writer){
		this.writer = writer;
	}
	
	public void setInputStream(InputStream reader) {
		this.reader = new BufferedReader(new InputStreamReader(reader));
	}
	
	public List<Action> getInitialActions(){
		return initialActions;
	}
	
	/**
	 * Shows the different available services, asks the user for the service he/she selects
	 * and then executes that service. 
	 * Once the service is finished, a new set of services will be offered depending of that one service.
	 * If a exception occurs during the execution of a service, it is shown on the console 
	 * and the previous list of available services is shown again.
	 * @throws IOException
	 */
	public void run() throws IOException {
		List<Action> availableActions = initialActions;
		Action selectedAction;
		
		do{
			listActions(availableActions);
			selectedAction = selectAction(availableActions);
			try{
				selectedAction.askUser(reader, writer, errorWriter);
				availableActions = selectedAction.getNextActions();
			} catch (Exception e){
				errorWriter.println("An error has ocurred:");
				errorWriter.println(e.getMessage());
			}
		} while(!(selectedAction instanceof QuitAction));
		writer.close();
		errorWriter.close();
	}
	
	/**
	 * Processes the user's selection.
	 * If the user chooses a wrong option, it will keep asking until obtaining a valid one.
	 * @param actions List of actions from where the user selects
	 */
	private Action selectAction(List<Action> actions) throws IOException {
		Integer selectedAction = null;

		while (selectedAction == null) {
			try {
				selectedAction = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) { 
				errorWriter.println("Wrong format");
				writer.println("Please, re-enter the selected option");
			}
		}

		return actions.get(selectedAction);
	}
	
	/**
	 * Outputs the different available services
	 * @param actions List of services to be shown
	 */
	private void listActions(List<Action> actions) {
		String order;
		Action action;

		assert !actions.isEmpty();
		writer.println("Choose an option (type the corresponding number):");
		for (int i = 0; i < actions.size(); i++) {
			action = actions.get(i);
			order = String.format(ORDER_TEMPLATE, i, action.getOrder());
			writer.println(order);
		}
	}
}
