package es.uniovi.asw.votingAccess.console.actions;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.votingAccess.console.Action;

public abstract class DefaultAction implements Action {
	protected List<Action> nextActions = new ArrayList<Action>();
	private static Action quitAction = new QuitAction();
	
	protected void setNextActions(Action... actions){
		nextActions.add(quitAction);
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
