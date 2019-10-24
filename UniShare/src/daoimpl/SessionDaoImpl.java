package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.SessionDao;
import dto.Session;

public class SessionDaoImpl implements SessionDao {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO session (timeLogged, studentId) VALUES (?, ?)";
	private static final String SQL_UPDATE = "UPDATE session SET timeSignedOut=? WHERE id=?";

	@Override
	public int insertSession(Session session) {
		Connection connection = null;
		boolean isSuccessful = false;

		int key = -1;
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		
		    preparedStmt.setTimestamp(1, new java.sql.Timestamp(session.getTimeLogged()));
			preparedStmt.setInt(2, session.getStudentId());			
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
			
			if (rs.next()){
			    key=rs.getInt(1);
			}
			
			System.out.println(key);
			preparedStmt.close();
			
			
			
		} catch (SQLException e) {
			// TODO add err handling for same user name 
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return key;
	}

	@Override
	public boolean updateSession(Session session) {
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
			
			preparedStatement.setTimestamp(1, new java.sql.Timestamp(session.getTimeSignedOut()));
			preparedStatement.setInt(2, session.getId());
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			isSuccessful = true;
			
			
		} catch (SQLException e) {
			// TODO add er handling
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}
	
}
