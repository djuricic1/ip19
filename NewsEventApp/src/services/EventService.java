package services;

import daoimpl.EventLikesDaoImpl;
import dto.EventLike;

public class EventService {
	
	EventLikesDaoImpl eldi = new EventLikesDaoImpl();

	public boolean addRate(EventLike eventLike) {
		
		return eldi.insert(eventLike);
	}
	
	
}
