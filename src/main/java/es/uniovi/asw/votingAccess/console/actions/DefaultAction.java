package es.uniovi.asw.votingAccess.console.actions;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.votingAccess.console.Action;
import es.uniovi.asw.votingAccess.console.ConsoleReader;

public abstract class DefaultAction implements Action {
	protected List<Action> nextActions = new ArrayList<Action>();
	
	@Override
	public void setNextActions(Action... actions){
		nextActions = new ArrayList<Action>();
		nextActions.add(ConsoleReader.getQuitAction());
		for(Action action : actions){
			nextActions.add(action);
		}
	}
	
	@Override
	public List<Action> getNextActions() {
		return nextActions;
	}
	
	
	protected boolean correctNIF(String nif) {
		return nif != null && !nif.isEmpty();
	}

}
