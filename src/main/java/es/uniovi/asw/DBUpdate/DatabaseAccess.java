package es.uniovi.asw.DBUpdate;

import es.uniovi.asw.DBUpdate.modelo.*;

public interface DatabaseAccess { //mas apropiado que Insert
	public Vote insertVote(Vote voto);
	public Voter insertVote(Voter votante);
	public Voter findVoter(String nif);
	public Voter insertEVoter(Voter votante);
}
