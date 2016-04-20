package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

import org.xml.sax.Attributes;

import es.uniovi.asw.votingAccess.console.Action;

import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;

public class RegisterEVoterAction extends DefaultAction {

	public RegisterEVoterAction() {
		setNextActions(this, new LogInAndVoteAction());
	}
	

	public Object askUser(BufferedReader reader,
			PrintStream writer, PrintStream errorWriter) throws Exception {
		String nif = null;

		writer.println("Voter's NIF:");
		while (!correctNIF(nif)) {
			nif = reader.readLine();
			if (!correctNIF(nif)) {
				errorWriter.println("NIF format is wrong");
				writer.println("Please, re-enter NIF");
			}
		}

		return new RegisterEVoter().registerEVoter(nif);
	}

	private boolean correctNIF(String nif) {
		return nif != null && !nif.isEmpty();
	}


	@Override
	public String getOrder() {
		return "Register yourself as e-voter";
	}

}
