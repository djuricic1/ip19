package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.News;

public class NewsDao {
	
	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private String SQL_GET_ALL = "SELECT * FROM news";
	
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
}
