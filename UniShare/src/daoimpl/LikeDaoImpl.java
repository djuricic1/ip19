package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.LikeDao;
import dto.Like;

public class LikeDaoImpl implements LikeDao{
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO likes (student_id, post_id, type) VALUES (?, ?, ?)";
	
	
	@Override
	public boolean insertLike(Like like) {
		
		Connection connection = null;
		boolean isSuccessful = false;
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		    preparedStmt.setInt(1, like.getStudentId());
		    preparedStmt.setInt(2, like.getPostId());
		    preparedStmt.setInt(3, like.getType());
		    
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
