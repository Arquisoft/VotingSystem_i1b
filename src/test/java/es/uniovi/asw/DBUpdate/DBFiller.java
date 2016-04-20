package es.uniovi.asw.DBUpdate;

import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.Voter;

public class DBFiller {

	public static void main(String[] args) throws SQLException {
		DatabaseTestHelper.deleteVoters();
		DatabaseTestHelper.deleteVotes();
		
		//E_VOTERS
		Voter voter1 = new Voter("test1", "Paco López", "paco@mail.com", 1, "1234", false, true);
		Voter voter2 = new Voter("test2", "Pili Fernández", "pili@mail.com", 1, "1234", false, true);
		
		//ELECTORAL_BOARD
		Voter voter3 = new Voter("test3", "María Martínez", "maria@mail.com", 1, "1234", false, false);
		Voter voter4 = new Voter("test4", "Pepe Pérez", "pepe@mail.com", 1, "1234", false, false);
		
		DatabaseTestHelper.insertVoter(voter1);
		DatabaseTestHelper.insertVoter(voter2);
		DatabaseTestHelper.insertVoter(voter3);
		DatabaseTestHelper.insertVoter(voter4);
		
	}

}
