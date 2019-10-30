package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Event;

public class EventDao {

	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();	
	
	private String SQL_GET_ALL = "SELECT * FROM event";

	public ArrayList<Event> getAll() {
		Connection connection = null;
		ResultSet rs = null;
		ArrayList<Event> events = new ArrayList<>();
		try { 
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Event e = new Event();
				e.setId(rs.getInt(1));
				e.setTitle(rs.getString(2));
				e.setStartOn(rs.getTimestamp(3).getTime());
				e.setEndsOn(rs.getTimestamp(4).getTime());
				e.setDescription(rs.getString(5));
				e.setImageUrl(rs.getString(6));
				e.setCategory(rs.getString(7));
				e.setNumberOfLikes(rs.getInt(8));
				e.setNumberOfDislikes(rs.getInt(9));
				events.add(e);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return events;
	}
	
}
