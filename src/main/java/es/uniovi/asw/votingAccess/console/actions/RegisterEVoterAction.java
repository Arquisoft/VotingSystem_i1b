package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;

public class RegisterEVoterAction extends DefaultAction {

	public Object askUser(BufferedReader reader,
			PrintStream writer, PrintStream errorWriter) throws Exception {
		String nif = null;
		Voter voter;
		
		writer.println("Voter's NIF:");
		while (!correctNIF(nif)) {
			nif = reader.readLine();
			if (!correctNIF(nif)) {
				errorWriter.println("NIF format is wrong");
				writer.println("Please, re-enter NIF");
			}
		}

		voter = new RegisterEVoter().registerEVoter(nif);
		writer.println("New e-voter registered");
		
		return voter;
	}


	@Override
	public String getOrder() {
		return "Register yourself as e-voter";
	}

}
