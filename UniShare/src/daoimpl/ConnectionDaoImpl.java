package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionDao;

public class ConnectionDaoImpl implements ConnectionDao {

	private static final String SQL_INSERT = "INSERT INTO connection (student_id, student_id1, typeOfConnection) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM connection WHERE (student_id = ?) and (student_id1 = ?)";
	private static final String SQL_GET_REQUESTS_SENT = "SELECT * FROM connection WHERE student_id=? and typeOfConnection=1";
	private static final String SQL_GET_STUDENT_REQUESTS = "SELECT * FROM connection WHERE student_id1=? and typeOfConnection=1";
	private static final String SQL_ACCEPT_CONNECTION = "UPDATE connection SET typeOfConnection=0 WHERE student_id=? and student_id1=?";
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	@Override
	public boolean acceptConnection(int senderId, int accepterId) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_ACCEPT_CONNECTION);
		
			preparedStmt.setInt(1, senderId);
			preparedStmt.setInt(2, accepterId);
			
			preparedStmt.executeUpdate();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}
	
	
	
	@Override
	public List<Integer> getStudentRequests(int studentId) {
		Connection connection = null;
		ResultSet rs = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			connection = connectionPool.checkOut();
			PreparedStatement ps = connection.prepareStatement(SQL_GET_STUDENT_REQUESTS);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ids.add(rs.getInt("student_id1"));
			}
			
			ps.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return ids;
	}
	@Override
	public List<Integer> getRequestsSent(int studentId) {
		Connection connection = null;
		ResultSet rs = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			connection = connectionPool.checkOut();
			PreparedStatement ps = connection.prepareStatement(SQL_GET_REQUESTS_SENT);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()) {
				ids.add(rs.getInt("student_id1"));
			}
			
			ps.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return ids;
	}
	
	
	@Override
	public boolean insertConnection(int student_id, int student_id1, int typeOfConnection) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		
			preparedStmt.setInt(1, student_id);
			preparedStmt.setInt(2, student_id1);
			preparedStmt.setInt(3, typeOfConnection);
			
			preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}

	
	
	@Override
	public boolean deleteConnection(int student_id, int student_id1) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_DELETE);
		
			preparedStmt.setInt(1, student_id);
			preparedStmt.setInt(2, student_id1);
			
			preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}

}
