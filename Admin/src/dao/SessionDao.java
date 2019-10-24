package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
}
