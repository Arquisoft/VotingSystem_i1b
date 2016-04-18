package es.uniovi.asw.votingAccess.business;

import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.DatabaseAccess;
import es.uniovi.asw.DBUpdate.DatabaseAccessImpl;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.exception.BusinessException;
import es.uniovi.asw.votingAccess.exception.BusinessExceptionMessages;

public class RegisterEVoter {

	private DatabaseAccess db = new DatabaseAccessImpl();

	public Voter registerEVoter(String nif) throws BusinessException, SQLException {
		Voter voter;

		voter = db.findVoter(nif);
		if (voter == null) {
			throw new BusinessException(BusinessExceptionMessages.NOT_IN_CENSUS);
		}
		if (voter.isEVoter()) {
			throw new BusinessException(BusinessExceptionMessages.ALREADY_EVOTER);
		}
		voter.setEVoter(true);
		return db.insertEVoter(voter);
	}

}
