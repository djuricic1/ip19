package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Session;
import dto.User;

public class SessionDao {
	
	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public int getNumberOfActiveUsers() {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = connectionPool.checkOut();
			ps = con.prepareStatement("SELECT count(*) FROM mydb.session WHERE timeSignedOut is null;");
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);	
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			//System.out.println("Login error -->" + ex.getMessage());
			//TODO : add err handling
			ex.printStackTrace();
		} finally {
			connectionPool.checkIn(con);
		}
		return result;
	}
	
	public List<Session> getAllInLastDay() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<Session> result = new ArrayList<Session>();
		try {
			con = connectionPool.checkOut();
			ps = con.prepareStatement("SELECT * FROM session WHERE timeSignedOut >= now() - INTERVAL 1 DAY");
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Session session = new Session();
				session.setId(rs.getInt(1));
				session.setTimeLogged(rs.getTimestamp(2).getTime());
				session.setTimeSignedOut(rs.getTimestamp(3).getTime());
				session.setSudentId(rs.getInt(4));
				result.add(session);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			//System.out.println("Login error -->" + ex.getMessage());
			//TODO : add err handling
			ex.printStackTrace();
		} finally {
			connectionPool.checkIn(con);
		}
		return result;		
	}
	
}
