package es.uniovi.asw.DBUpdate;

import es.uniovi.asw.DBUpdate.modelo.*;

public interface DatabaseAccess { //mas apropiado que Insert
	
	//estos metodos podrian ser las interfaces entre componentes
	public Vote insertVote(Vote vote);
	public Voter updateHasVoted(Voter voter);
	public Voter findVoter(String nif);
	public Voter insertEVoter(Voter vote);
}
