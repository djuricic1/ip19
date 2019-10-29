package dao;

import java.util.ArrayList;

import dto.Event;

public interface EventDao {
	
	ArrayList<Event> getAll();
	boolean addEvent(Event event);

}
