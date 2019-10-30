package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Event;
import dto.News;
import dto.NewsEvent;

public class NewsEventDao {
	
	ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public ArrayList<NewsEvent> getAll() {
		
		ArrayList<NewsEvent> result = new ArrayList<>();
		
		NewsDao newsDao = new NewsDao();
		EventDao eventDao = new EventDao();
		
		ArrayList<News> news = newsDao.getAll();
		ArrayList<Event> events = eventDao.getAll();
		
		for(News n : news) {
			NewsEvent toAdd = new NewsEvent();
			toAdd.setId(n.getId());
			toAdd.setTitle(n.getTitle());
			toAdd.setType("news");
			result.add(toAdd);
		}
		
		for(Event e : events) {
			NewsEvent toAdd = new NewsEvent();
			toAdd.setId(e.getId());
			toAdd.setTitle(e.getCategory() + " " + e.getTitle());
			toAdd.setType("event");
			result.add(toAdd);
		}
		return result;
	}
	
	public boolean remove(NewsEvent ne) {
		Connection connection = null;
		try {
			connection  = connectionPool.checkOut();
			if("news".equals(ne.getType())) {
				String q = "delete from news where id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(q);
				preparedStatement.setInt(1, ne.getId());
				return preparedStatement.executeUpdate() > 0;
				
			}else if("event".equals(ne.getType())) {
				String query = "delete from event where id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, ne.getId());
				
				return preparedStatement.executeUpdate() > 0;
							}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null)
				connectionPool.checkIn(connection);
		}
		return false;
	}
	
}
