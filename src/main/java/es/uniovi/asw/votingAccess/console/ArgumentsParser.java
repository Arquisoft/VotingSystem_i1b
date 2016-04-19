package es.uniovi.asw.votingAccess.console;

import org.apache.commons.cli.*;

import es.uniovi.asw.votingAccess.VotingAccess;

/**
 * Class in charge of processing the arguments passed to the application 
 * and configure the rest of the application according to that arguments.
 * @author UO238739
 *
 */
public class ArgumentsParser implements VotingAccess{

	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	
	public void run(String[] args){
		//TODO process args
		//TODO init and call ConsoleReader
	}
	
	@Override
	public void configParams() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean vote() {
		// TODO Auto-generated method stub
		return false;
	}
}
