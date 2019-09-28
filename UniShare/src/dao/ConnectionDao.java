package dao;

import java.util.List;

public interface ConnectionDao {
	
	boolean insertConnection(int student_id, int student_id1, int typeOfConnection);
	boolean deleteConnection(int student_id, int student_id1);
	List<Integer> getRequestsSent(int studentId);
	List<Integer> getStudentRequests(int studentId);
	boolean acceptConnection(int senderId, int accepterId);
	
}
