package dao;

import dto.Session;

public interface SessionDao {
	
	int insertSession(Session session);
	boolean updateSession(Session session); 

}
