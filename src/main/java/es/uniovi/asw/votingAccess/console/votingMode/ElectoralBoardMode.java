package es.uniovi.asw.votingAccess.console.votingMode;

import es.uniovi.asw.votingAccess.console.Action;
import es.uniovi.asw.votingAccess.console.ConsoleReader;
import es.uniovi.asw.votingAccess.console.VotingMode;
import es.uniovi.asw.votingAccess.console.actions.MarkVoterAction;
import es.uniovi.asw.votingAccess.console.actions.SubmitVoteAction;

public class ElectoralBoardMode implements VotingMode {

	@Override
	public ConsoleReader setUpConsole(Object[] params) {
		int boardCode = new Integer((String)params[0]);
		
		Action markVoter = new MarkVoterAction(boardCode);
		Action submitVote = new SubmitVoteAction();
		
		markVoter.setNextActions(markVoter, submitVote);
		submitVote.setNextActions(markVoter, submitVote);
		
		return new ConsoleReader(markVoter, submitVote);
	}

}
