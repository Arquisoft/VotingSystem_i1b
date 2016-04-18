package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;

public interface Action {
	public Object askUser(BufferedReader reader) throws Exception;
}
