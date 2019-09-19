package dao;

public interface ConnectionDao {
	
	boolean insertConnection(int student_id, int student_id1, int typeOfConnection);
	boolean deleteConnection(int student_id, int student_id1);
	
}
