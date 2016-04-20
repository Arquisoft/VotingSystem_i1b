package es.uniovi.asw.votingAccess.console;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.votingAccess.console.mock.MockAction1;
import es.uniovi.asw.votingAccess.console.mock.MockAction2;

public class ConsoleReaderTest {

	private ConsoleReader console;
	private String data = "0";
	
	@Before
	public void setUp(){
		console = new ConsoleReader(new MockAction1(), new MockAction2());
	}
	
	@Test
	public void test() throws IOException {
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		console.setInputStream(System.in);
		console.run();
	}

}
