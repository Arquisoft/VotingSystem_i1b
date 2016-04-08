package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;

public class DatabaseAccessImpl implements DatabaseAccess {

	@Override
	public Vote insertVote(Vote vote) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("INSERT_VOTE"));
			ps.setString(1, vote.getOption());
			ps.execute();
			return vote;
		} catch (SQLException e) {
			System.err.println(e.getStackTrace());
			return vote;
		} finally {
			JdbcHelper.close(con, ps);
		}
	}

	@Override
	public Voter findVoter(String nif) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voter insertEVoter(Voter voter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voter updateHasVoted(Voter voter) {
		// TODO Auto-generated method stub
		return null;
	}

}
