package es.uniovi.asw;

import es.uniovi.asw.votingAccess.console.ArgumentsParser;

public class Application {

	public static void main(String[] args) {
		ArgumentsParser parser = new ArgumentsParser();
		parser.run(args);
	}

}
