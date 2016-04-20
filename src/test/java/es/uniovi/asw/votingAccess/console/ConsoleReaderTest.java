package es.uniovi.asw.votingAccess.console;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.votingAccess.console.actions.QuitAction;
import es.uniovi.asw.votingAccess.console.auxiliar.MockAction1;
import es.uniovi.asw.votingAccess.console.auxiliar.MockAction2;
import es.uniovi.asw.votingAccess.console.auxiliar.TestStream;

public class ConsoleReaderTest {

	private ConsoleReader console;
	private TestStream output;
	private MockAction1 a1;
	private MockAction2 a2;
	
	@Before
	public void setUp(){
		a2 = new MockAction2();
		a1 = new MockAction1(a2);
		console = new ConsoleReader(a1);
		output = new TestStream(System.out);
		changePrintStream();
	}
	
	@Test
	public void testQuitAction() throws IOException {
		changeInputStream("0");

		console.run();
		
		assertTrue(output.getWrittenText().contains(new QuitAction().getOrder()));
	}
	
	@Test
	public void testNextAction() throws IOException {
		changeInputStream("1\n0"); //first option 1 (mock action 1) and then 0 (quit action)

		console.run();
		
		assertTrue(output.getWrittenText().contains(a1.getOrder()));
		assertTrue(output.getWrittenText().contains(a2.getOrder()));
	}

	private void changeInputStream(String data){
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		console.setInputStream(System.in);
	}
	
	private void changePrintStream(){
		console.setWriter(output);
	}
}
