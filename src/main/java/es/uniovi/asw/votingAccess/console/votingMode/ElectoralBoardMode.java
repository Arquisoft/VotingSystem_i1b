package es.uniovi.asw.votingAccess.console.votingMode;

import es.uniovi.asw.votingAccess.console.ConsoleReader;
import es.uniovi.asw.votingAccess.console.VotingMode;
import es.uniovi.asw.votingAccess.console.actions.MarkVoterAction;
import es.uniovi.asw.votingAccess.console.actions.SubmitVoteAction;

public class ElectoralBoardMode implements VotingMode {

	@Override
	public ConsoleReader setUpConsole(Object[] params) {
		int boardCode = (int)params[0];
		return new ConsoleReader(new MarkVoterAction(boardCode), new SubmitVoteAction(boardCode));
	}

}
