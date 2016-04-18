package es.uniovi.asw.votingAccess.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import es.uniovi.asw.votingAccess.exception.BusinessException;

public interface Action {
	public Object askUser(BufferedReader reader) throws IOException, BusinessException, SQLException;
}
