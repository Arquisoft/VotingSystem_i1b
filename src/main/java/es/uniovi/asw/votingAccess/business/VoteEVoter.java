package es.uniovi.asw.votingAccess.business;

import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.DatabaseAccess;
import es.uniovi.asw.DBUpdate.DatabaseAccessImpl;
import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;

public class VoteEVoter {

	private DatabaseAccess db = new DatabaseAccessImpl();
	
	public void voteEVoter(Vote vote, Voter v) throws SQLException {

		db.insertVote(vote);
		v.setHasVoted(true);
		db.updateHasVoted(v);
	}

}
