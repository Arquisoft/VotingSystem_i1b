package es.uniovi.asw.votingAccess.console.votingMode;

import es.uniovi.asw.votingAccess.console.ConsoleReader;
import es.uniovi.asw.votingAccess.console.VotingMode;
import es.uniovi.asw.votingAccess.console.actions.LogInAndVoteAction;
import es.uniovi.asw.votingAccess.console.actions.RegisterEVoterAction;

public class EVotingMode implements VotingMode {

	@Override
	public ConsoleReader setUpConsole(Object[] params) {
		return new ConsoleReader(new RegisterEVoterAction(), new LogInAndVoteAction());
	}
}
