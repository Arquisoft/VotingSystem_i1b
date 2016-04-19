package es.uniovi.asw.DBUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;

public class DatabaseTestHelper {

	public static final String DB_CONFIG_FILE = "src/test/resources/database.properties";
	
	public static void deleteVotes() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("DELETE_VOTES"));
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
	}

	public static void deleteVoters() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("DELETE_VOTERS"));
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
	}

	public static Voter insertVoter(String nif) throws SQLException {
		Voter voter = new Voter(nif);
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("INSERT_VOTER"));
		ps.setString(1, voter.getNif());
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
		return voter;
	}

	public static Voter insertVoter(Voter voter) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		con = JdbcHelper.getConnection();
		ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("INSERT_COMPLETE_VOTER"));
		ps.setString(1, voter.getNif());
		ps.setString(2, voter.getName());
		ps.setString(3, voter.getEmail());
		ps.setInt(4, voter.getElectoralBoard());
		ps.setString(5, voter.getPassword());
		ps.setBoolean(6, voter.getHasVoted());
		ps.setBoolean(7, voter.isEVoter());
		ps.executeUpdate();
		JdbcHelper.close(con, ps);
		return voter;
	}
	
	public static Voter findVoter(String nif) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Voter voter = null;
		
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("FIND_VOTER_BY_NIF"));
			ps.setString(1, nif);
			rs = ps.executeQuery();
			
			if (rs.next()) {
			voter = new Voter(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getBoolean(6),
					rs.getBoolean(7));
			}
			assert !rs.next();
			
			JdbcHelper.close(rs, ps, con);
			
			return voter;
	}

	public static List<Vote> findVotes() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Vote> votes = new ArrayList<Vote>();
		Vote vote;
		
			con = JdbcHelper.getConnection();
			ps = con.prepareStatement(JdbcHelper.getQueries().getProperty("SELECT_ALL_VOTES"));
			rs = ps.executeQuery();
			
			while(rs.next()){
			vote = new Vote(rs.getLong(1),
					rs.getString(2));
			votes.add(vote);
			}
			
			JdbcHelper.close(rs, ps, con);
			
			return votes;
	}
	
}
