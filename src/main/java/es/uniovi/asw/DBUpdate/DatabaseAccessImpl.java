package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			ps.executeUpdate();
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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("FIND_VOTER_BY_NIF"));
			ps.setString(1, nif);
			rs = ps.executeQuery();
			return resultSetToVoter(rs);
		} catch (SQLException e){
			System.err.println(e.getStackTrace());
			return null;
		} finally {
			JdbcHelper.close(rs, ps, con);
		}
	}

	private Voter resultSetToVoter(ResultSet rs) throws SQLException {
		Voter voter;
		rs.next();
		voter = new Voter(rs.getString(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getBoolean(6),
				rs.getBoolean(7));
		assert !rs.next();
		return voter;
	}

	@Override
	public Voter insertEVoter(Voter voter) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("UPDATE_E_VOTER"));
			ps.setBoolean(1, voter.isEVoter());
			ps.setString(2, voter.getNif());
			ps.executeUpdate();
			return voter;
		} catch (SQLException e){
			System.err.println(e.getStackTrace());
			return voter;
		} finally {
			JdbcHelper.close(con, ps);
		}
	}

	@Override
	public Voter updateHasVoted(Voter voter) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("UPDATE_HAS_VOTED"));
			ps.setBoolean(1, voter.getHasVoted());
			ps.setString(2, voter.getNif());
			ps.executeUpdate();
			return voter;
		} catch (SQLException e){
			System.err.println(e.getStackTrace());
			return voter;
		} finally {
			JdbcHelper.close(con, ps);
		}
	}

}
