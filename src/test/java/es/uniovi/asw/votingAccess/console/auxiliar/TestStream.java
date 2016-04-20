package es.uniovi.asw.votingAccess.console.auxiliar;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestStream extends PrintStream {

	private static final String newLine = System.getProperty("line.separator");

    private final StringBuffer sb = new StringBuffer();
    private final PrintStream original;

    public TestStream(OutputStream out) {
		super(out);
		this.original = new PrintStream(out);
	}

    public void print(double d) {
        sb.append(d);
        original.print(d);
    }

    public void print(String s) {
        sb.append(s);
        original.print(s);
    }

    public void println(String s) {
        sb.append(s).append(newLine);
        original.println(s);
    }

    public void println() {
        sb.append(newLine);
        original.println();
    }
    
    public String getWrittenText(){
    	return sb.toString();
    }
}