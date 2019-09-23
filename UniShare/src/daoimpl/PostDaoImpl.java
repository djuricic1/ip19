package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dao.PostDao;
import dto.Post;

public class PostDaoImpl implements PostDao{
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_GET_ALL_BY_STUDENT_ID = "SELECT * FROM post WHERE student_id=?";
	private static final String SQL_INSERT = "INSERT INTO post (numberOfLikes, numberOfDislikes, student_id, datePosted, description) VALUES (?, ?, ?, ?, ?)";
	//private StudentDaoImpl sdi = new StudentDaoImpl();
	
	
	@Override
	public List<Post> getAllByStudentId(int studentId) {
		Connection connection = null;
		ResultSet rs = null;
		List<Post> posts = new ArrayList<>();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_BY_STUDENT_ID);
			pstmt.setInt(1, studentId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Post p = new Post();
				p.setId(rs.getInt("id"));
				p.setDatePosted(rs.getDate("datePosted"));
				p.setDescription(rs.getString("description"));
				p.setNumberOfLikes(rs.getInt("numberOfLikes"));
				p.setNumberOfDislikes(rs.getInt("numberOfDislikes"));				
				p.setStudentId(studentId); 
				posts.add(p);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return posts;
	}

	@Override
	public boolean insertPost(Post post) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		
	
			preparedStmt.setInt(1, post.getNumberOfLikes());
			preparedStmt.setInt(2, post.getNumberOfDislikes());
			preparedStmt.setInt(3, post.getStudentId());
			preparedStmt.setTimestamp(4,  new java.sql.Timestamp(post.getDatePosted().getTime()));
			preparedStmt.setString(5, post.getDescription());	
			
			preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			// TODO add err hadnling for same username 
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;
	}
	
	
	
}
