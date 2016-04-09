package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;

class DatabaseAccessTestHelper {

	static void deleteVotes() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getString("DELETE_VOTES"));
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
	}

	static void deleteVoters() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getString("DELETE_VOTERS"));
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
	}

	static Voter insertVoter(Voter voter) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getString("INSERT_VOTER"));
		ps.setString(1, voter.getNif());
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
		return voter;
	}

	public static Voter findVoter(String nif) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Voter voter;
		
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("FIND_VOTER_BY_NIF"));
			ps.setString(1, nif);
			rs = ps.executeQuery();
			
			rs.next();
			voter = new Voter(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getBoolean(6),
					rs.getBoolean(7));
			assert !rs.next();
			
			JdbcHelper.close(rs, ps, con);
			
			return voter;
	}

	public static Vote findOneVote() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vote vote;
		
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getString("SELECT_ALL_VOTES"));
			rs = ps.executeQuery();
			
			rs.next();
			vote = new Vote(rs.getLong(1),
					rs.getString(2));
			assert !rs.next();
			
			JdbcHelper.close(rs, ps, con);
			
			return vote;
	}
	
}
