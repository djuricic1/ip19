package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.ConnectionDao;

public class ConnectionDaoImpl implements ConnectionDao {

	private static final String SQL_INSERT = "INSERT INTO connection (student_id, student_id1, typeOfConnection) VALUES (?, ?, ?)";
	private static final String SQL_DELETE = "DELETE FROM connection WHERE (student_id = ?) and (student_id1 = ?)";
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
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
