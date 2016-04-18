package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import es.uniovi.asw.votingAccess.console.actions.QuitAction;

public class ConsoleReader {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private PrintStream writer = System.out;
	private PrintStream errorWriter = System.err;
	private final static String ORDER_TEMPLATE = "(%d) %s";
	private List<Action> initialActions;
	
	public ConsoleReader() {

	}

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
	}

	private Action selectAction(List<Action> availableActions) throws IOException {
		Integer selectedAction = null;

		while (selectedAction == null) {
			try {
				selectedAction = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) { 
				errorWriter.println("Wrong format");
				writer.println("Please, re-enter the selected option");
			}
		}

		return availableActions.get(selectedAction);
	}

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
