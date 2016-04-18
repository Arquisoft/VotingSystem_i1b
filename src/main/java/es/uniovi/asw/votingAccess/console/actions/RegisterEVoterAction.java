package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.console.Action;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class RegisterEVoterAction implements Action {

	public Object askUser(BufferedReader reader) throws IOException, BusinessException, SQLException {
		String nif;

		System.out.println("NIF:");
		nif = reader.readLine();

		return new RegisterEVoter().registerEVoter(nif);
	}
}
