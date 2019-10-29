package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.NewsDao;
import dto.Event;
import dto.News;

public class NewsDaoImpl implements NewsDao {
	
	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private String SQL_INSERT = "INSERT INTO news (dateCreated, title, content, numberOfLikes, numberOfDislikes) VALUES (?, ?, ?, ?, ?)";
	private String SQL_GET_ALL = "SELECT * FROM news";
	
	@Override
	public ArrayList<News> getAll() {
		Connection connection = null;
		ResultSet rs = null;
		ArrayList<News> news = new ArrayList<>();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				News e = new News();
				e.setId(rs.getInt(1));
				e.setDateCreated(rs.getTimestamp(2).getTime());
				e.setTitle(rs.getString(3));
				e.setContent(rs.getString(4));
				e.setNumberOfLikes(rs.getInt(5));
				e.setNumberOfDislikes(rs.getInt(6));
				
				news.add(e);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return news;
	}

	@Override
	public boolean addNews(News news) {
		
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);

		    preparedStmt.setTimestamp(1, new java.sql.Timestamp(news.getDateCreated()));
		    preparedStmt.setString(2, news.getTitle());
		    preparedStmt.setString(3, news.getContent());	
		    preparedStmt.setInt(4, news.getNumberOfLikes());
		    preparedStmt.setInt(5, news.getNumberOfDislikes());
		    
		  
			preparedStmt.execute();
			
			preparedStmt.close();
			
			isSuccessful = true;
			
		} catch (SQLException e) {
			// TODO add err hadnling for same username 
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isSuccessful;

	}

}
