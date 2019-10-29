package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.NewsLikesDao;
import dto.EventLike;
import dto.NewsLike;

public class NewsLikesDaoImpl implements NewsLikesDao {

	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO news_likes (studentId, eventId, type) VALUES (?, ?, ?)";
	
	@Override
	public boolean insert(NewsLike newsLike) {
		
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		    preparedStmt.setInt(1, newsLike.getStudentId());
		    preparedStmt.setInt(2, newsLike.getNewsId());
		    preparedStmt.setInt(3, newsLike.getType());
		    
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
