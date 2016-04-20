package es.uniovi.asw.votingAccess.business;

import es.uniovi.asw.DBUpdate.DatabaseAccess;
import es.uniovi.asw.DBUpdate.DatabaseAccessImpl;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.exception.BusinessException;
import es.uniovi.asw.votingAccess.exception.BusinessExceptionMessages;

import java.sql.SQLException;

/**
 * Created by uo237633 on 19/04/2016.
 */
public class MarkVoter {

    private DatabaseAccess db = new DatabaseAccessImpl();
    
    public Voter markVoter(String nif, int electoralBoardCode) throws BusinessException, SQLException {
        Voter voter;

        voter = db.findVoter(nif);
        if (voter == null) {
            throw new BusinessException(BusinessExceptionMessages.NOT_IN_CENSUS);
        }
        if (!voter.getElectoralBoard().equals(electoralBoardCode)) {
            throw new BusinessException(String.format(
            		BusinessExceptionMessages.WRONG_ELECTORAL_BOARD, 
            		electoralBoardCode, voter.getElectoralBoard()));
        }
        if (voter.isEVoter()) {
            throw new BusinessException(BusinessExceptionMessages.IS_EVOTER);
        }
        if (voter.getHasVoted()){
            throw new BusinessException(BusinessExceptionMessages.HAS_ALREADY_VOTED);
        }
        voter.setHasVoted(true);
        return db.updateHasVoted(voter);
    }
}
