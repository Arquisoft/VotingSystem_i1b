package es.uniovi.asw.votingAccess.console;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.cli.*;

import es.uniovi.asw.votingAccess.VotingAccess;
import es.uniovi.asw.votingAccess.console.votingMode.EVotingMode;
import es.uniovi.asw.votingAccess.console.votingMode.ElectoralBoardMode;

/**
 * Class in charge of processing the arguments passed to the application 
 * and configure the rest of the application according to that arguments.
 * @author UO238739
 *
 */
public class ArgumentsParser implements VotingAccess{

	private PrintStream writer = System.out;
	private CommandLineParser parser = new DefaultParser();
	private Options options = new Options();
	public static final String BOARD_MODE = "b";
	public static final String EVOTING_MODE = "e";
	private ConsoleReader console;
	private VotingMode mode;

	public ArgumentsParser(){
		OptionGroup modeOptions = new OptionGroup();
		Option boardOp = new Option(BOARD_MODE, true, "Sets the Electoral Board mode");
		Option evotingOp = new Option(EVOTING_MODE, false, "Sets the electronic voting mode");
		modeOptions.addOption(boardOp);
		modeOptions.addOption(evotingOp);
		modeOptions.setRequired(true);
		options.addOptionGroup(modeOptions);
	}
	
    public void processArguments(String[] args) throws ParseException, IOException {
        CommandLine line;
        line = parse(args);
            if(line.hasOption(BOARD_MODE)) {
                processBoardMode(line);
            } else if(line.hasOption(EVOTING_MODE)){
            	processEVotingMode();
            }
    }
    
    private void processBoardMode(CommandLine line) throws IOException{
    	mode = new ElectoralBoardMode();
    	String boardCode = line.getOptionValue(BOARD_MODE);
        if(boardCode.matches("\\d+")){
            configParams(boardCode);
            vote();
        } else {
        	throw new IllegalArgumentException("The Electoral Board code must be an integer");
        }
    }
    
    private void processEVotingMode() throws IOException{
    	mode = new EVotingMode();
        configParams();
        vote();
    }
	
	@Override
	public void configParams(Object... params) {
		console = mode.setUpConsole(params);
		console.setWriter(writer);
	}
	
	@Override
	public void vote() throws IOException {
		console.run();
	}
	
	public void showHelp(){
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("VotingSystem", options);
	}
	
	public CommandLine parse(String[] args) throws ParseException {
		return parser.parse(options, args);
	}
}
