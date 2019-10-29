package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.EventLikesDao;
import dto.EventLike;

public class EventLikesDaoImpl implements EventLikesDao{

	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO event_likes (student_id, event_id, type) VALUES (?, ?, ?)";
	
	@Override
	public boolean insert(EventLike eventLike) {
		
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		    preparedStmt.setInt(1, eventLike.getStudentId());
		    preparedStmt.setInt(2, eventLike.getEventId());
		    preparedStmt.setInt(3, eventLike.getType());
		    
		    preparedStmt.execute();
		   
		   	isSuccessful = true;
		    
		   	preparedStmt.close();
		    
		} catch (SQLIntegrityConstraintViolationException e) {
			isSuccessful = false;
		} catch (SQLException e) {
			// TODO add err hadnling for same username 
			e.printStackTrace();
			isSuccessful = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}

}
