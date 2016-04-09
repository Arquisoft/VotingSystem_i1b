package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.uniovi.asw.DBUpdate.modelo.Voter;

class DatabaseMethods {

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
	
}
