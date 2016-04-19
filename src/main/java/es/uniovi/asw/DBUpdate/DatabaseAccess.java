package es.uniovi.asw.DBUpdate;

import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.*;

public interface DatabaseAccess { //*mas apropiado que Insert*
	
	//*estos metodos podrian ser las interfaces entre componentes*
	public Vote insertVote(Vote vote) throws SQLException;
	public Voter updateHasVoted(Voter voter) throws SQLException;
	public Voter findVoter(String nif) throws SQLException;
	public Voter insertEVoter(Voter voter) throws SQLException;
}
