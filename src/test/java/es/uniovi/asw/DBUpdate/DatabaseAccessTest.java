package es.uniovi.asw.DBUpdate;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseAccessTest {

	private static final String DB_CONFIG_FILE = "test.resources.database";
	
	@BeforeClass
	public static void config() {
		JdbcHelper.loadConnectionConfig(DB_CONFIG_FILE);
	}
	
	@Test
	public void testInsertVote(){
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsertVoter(){
		fail("Not yet implemented");
	}
	
	@Test
	public void findVoter(){
		fail("Not yet implemented");
	}
	
	@Test
	public void insertEVoter(){
		fail("Not yet implemented");
	}

}
