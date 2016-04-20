package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.votingAccess.business.AddVote;

public class SubmitVoteAction extends DefaultAction {
	
	@Override
	public String getOrder() {
		return "Submit a vote";
	}

	@Override
	public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
		String voteOption;
		
		writer.println("Choose an option: ");
        writer.println("1. Yes");
        writer.println("2. No");
        voteOption = reader.readLine();
        if(voteOption.equals("1")){
            new AddVote().addVote("Y");
            writer.println("Vote added");
        }
        else if(voteOption.equals("2")){
            new AddVote().addVote("N");
            writer.println("Vote added");
        }
        else{
            writer.println("Incorrect voting option");
        }
        
        return null;
	}

}