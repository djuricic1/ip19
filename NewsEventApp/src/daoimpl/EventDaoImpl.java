package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.EventDao;
import dto.Event;


public class EventDaoImpl implements EventDao{
	
	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private String SQL_INSERT = "INSERT INTO event (title, startsOn, endsOn, description, imageUrl, category, "
			+ "numberOfLikes, numberOfDislikes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private String SQL_GET_ALL = "SELECT * FROM event";
	
	@Override
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

	@Override
	public boolean addEvent(Event event) {
		Connection connection = null;
		boolean isSuccessful = false;
		
		try {
			connection = connectionPool.checkOut();
		    PreparedStatement preparedStmt = connection.prepareStatement(SQL_INSERT);
		
		    preparedStmt.setString(1, event.getTitle());
		    preparedStmt.setTimestamp(2, new java.sql.Timestamp(event.getStartOn()));
		    preparedStmt.setTimestamp(3, new java.sql.Timestamp(event.getEndsOn()));
		    preparedStmt.setString(4, event.getDescription());	
		    preparedStmt.setString(5, event.getImageUrl());
		    preparedStmt.setString(6, event.getCategory());
		    preparedStmt.setInt(7, event.getNumberOfLikes());
		    preparedStmt.setInt(8, event.getNumberOfDislikes());
		    
		  
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
