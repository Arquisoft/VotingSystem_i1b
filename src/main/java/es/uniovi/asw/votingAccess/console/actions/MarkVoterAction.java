package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.votingAccess.business.MarkVoter;

public class MarkVoterAction extends DefaultAction {

	private int electoralBoardCode;
	
	public MarkVoterAction(int boardCode){
		this.electoralBoardCode = boardCode;
	}
	
	@Override
	public String getOrder() {
		return "Mark that a voter has voted";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		String nif;
		
		writer.println("Introduce the voter's NIF: ");
        nif = reader.readLine();
        new MarkVoter().markVoter(nif, electoralBoardCode);
        writer.println("The voter has been marked");
        
        return null;
	}

}
