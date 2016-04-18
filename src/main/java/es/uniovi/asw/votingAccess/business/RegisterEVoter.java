package es.uniovi.asw.votingAccess.business;

import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.DatabaseAccess;
import es.uniovi.asw.DBUpdate.DatabaseAccessImpl;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.exception.BusinessException;

public class RegisterEVoter {

	private DatabaseAccess db = new DatabaseAccessImpl();

	public Voter registerEVoter(String nif) throws BusinessException, SQLException {
		Voter voter;

		voter = db.findVoter(nif);
		if (voter == null) {
			throw new BusinessException("The given NIF does not correspond to any voter registered in the census");
		}
		if (voter.isEVoter()) {
			throw new BusinessException("The user is already registered as e-voter");
		}
		voter.setEVoter(true);
		return db.insertEVoter(voter);
	}

}
