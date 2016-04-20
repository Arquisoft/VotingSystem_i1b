package es.uniovi.asw.votingAccess.business;

import es.uniovi.asw.DBUpdate.DatabaseAccess;
import es.uniovi.asw.DBUpdate.DatabaseAccessImpl;
import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.votingAccess.exception.BusinessException;
import java.sql.SQLException;

/**
 * Created by uo237633 on 19/04/2016.
 */
public class AddVote {
    private DatabaseAccess db = new DatabaseAccessImpl();

    public Vote addVote(String option) throws BusinessException, SQLException {
        Vote vote = new Vote(option);
        return db.insertVote(vote);
    }
}
