package es.uniovi.asw;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import es.uniovi.asw.votingAccess.console.ArgumentsParser;

public class Application {

	public static void main(String[] args) throws ParseException, IOException {
		ArgumentsParser parser = new ArgumentsParser();
		parser.processArguments(args);
	}

}
