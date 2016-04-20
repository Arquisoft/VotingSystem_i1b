package es.uniovi.asw.votingAccess;

import java.io.IOException;

public interface VotingAccess {
	public void configParams(Object...params);
	public void vote() throws IOException;
}
