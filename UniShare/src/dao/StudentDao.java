package dao;

import java.util.List;

import dto.Student;

public interface StudentDao {
	
	List<Student> getAllStudents();
	Student getStudentyById(int id);
	boolean insertStudent(Student student);
	boolean updateStudent(Student student);
	boolean updateStudentLastTimeActive(Student student);
	Student getStudentByNameAndPassword(String username, String password);
	List<Student> getAllStudentsConnected(Student student);
	List<Student> getAllStudentRequests(Student student);
	List<Student> getAllStudentsByFacultyId(int facultyId);
	
	
}
