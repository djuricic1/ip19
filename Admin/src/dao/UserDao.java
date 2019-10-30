package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.User;

public class UserDao {
	
ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public int getNumberOfRegisteredUsers() {
		Connection con = null;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = connectionPool.checkOut();
			ps = con.prepareStatement("SELECT count(*) FROM mydb.student");
			
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
	
	public boolean randomlyResetUserPassword(int userId) {
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET password=? WHERE (id = ?)");
			preparedStatement.setString(1, "TEST1234");
			preparedStatement.setInt(2, userId);
			
			preparedStatement.executeUpdate();
			
			isSuccessful = true;		
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO add er handling
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}
	
	public boolean blockUser(int userId, int disabled) {
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET disabled=? WHERE (id = ?)");
			preparedStatement.setInt(1, disabled);
			preparedStatement.setInt(2, userId);
			
			preparedStatement.executeUpdate();
			
			isSuccessful = true;		
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO add er handling
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}
	
	public List<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement("select id, name, surname, username, disabled from student");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setUsername(rs.getString("username"));
				
				user.setDisabled(rs.getInt("disabled"));
				
		
				users.add(user);
			}
			pstmt.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		} 
		
		return users;
	}
	
	
	
}
