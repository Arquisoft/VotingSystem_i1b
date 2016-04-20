package es.uniovi.asw.votingAccess.console;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

public class ArgumentsParserTest {

	private ArgumentsParser argParser;
	
	@Before
	public void setUp(){
		this.argParser = new ArgumentsParser();
	}

	@Test
	public void testEVotingArgument() throws ParseException {
		String[] args = {"-"+ArgumentsParser.EVOTING_MODE};
		CommandLine line = argParser.parse(args);
		assertTrue(line.hasOption(ArgumentsParser.EVOTING_MODE));
	}
	
	@Test
	public void testElectoralBoardArgument() throws ParseException {
		String board = "1";
		String[] args = {"-"+ArgumentsParser.BOARD_MODE, board};
		CommandLine line = argParser.parse(args);
		assertTrue(line.hasOption(ArgumentsParser.BOARD_MODE));
		assertEquals(board, line.getOptionValue(ArgumentsParser.BOARD_MODE));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWrongElectoralBoardParameter() throws ParseException, IOException{
		String[] args = {"-"+ArgumentsParser.BOARD_MODE, "uno"};
		argParser.processArguments(args);
	}
	
	@Test(expected=MissingArgumentException.class)
	public void testMissingElectoralBoardArgument() throws ParseException, IOException{
		String[] args = {"-"+ArgumentsParser.BOARD_MODE};
		argParser.processArguments(args);
	}
	
	@Test(expected=MissingOptionException.class)
	public void testMissingOption() throws ParseException, IOException{
		String[] args = { };
		argParser.processArguments(args);
	}
}
