package es.uniovi.asw.votingAccess.console.actions;

import java.io.BufferedReader;
import java.io.PrintStream;

import es.uniovi.asw.votingAccess.business.AddVote;

public class SubmitVoteAction extends DefaultAction {

	private int electoralBoardCode;
	
	public SubmitVoteAction(int boardCode){
		this.electoralBoardCode = boardCode;
		setNextActions(this, new MarkVoterAction(boardCode));
	}
	
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
        if(voteOption == "1"){
            new AddVote().addVote("Yes");
            writer.println("Vote added");
        }
        else if(voteOption == "2"){
            new AddVote().addVote("No");
            writer.println("Vote added");
        }
        else{
            writer.println("Incorrect voting option");
        }
        
        return null;
	}

}
