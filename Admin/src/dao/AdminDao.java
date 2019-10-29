package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Admin;

public class AdminDao {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	
	public Admin getByUsername(String username) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.checkOut();
			ps = con.prepareStatement("Select * from admin where username=?");
			
			Admin admin = new Admin();
			
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				admin.setId(rs.getInt(1));
				admin.setUsername(rs.getString(2));
				admin.setPassword(rs.getString(3));	
			}
			
			return admin;
			
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
		
		} finally {
			connectionPool.checkIn(con);
		}
		return null;
	}
}
