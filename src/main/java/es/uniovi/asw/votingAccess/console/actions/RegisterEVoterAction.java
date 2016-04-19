package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;

import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.console.Action;

public class RegisterEVoterAction implements Action {

	public Object askUser(BufferedReader reader) throws Exception {
		String nif;

		System.out.println("NIF:");
		nif = reader.readLine();

		return new RegisterEVoter().registerEVoter(nif);
	}
}
