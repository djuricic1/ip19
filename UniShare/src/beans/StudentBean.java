package beans;

import java.io.Serializable;

import daoimpl.StudentDaoImpl;
import dto.Student;

public class StudentBean implements Serializable {
	
	private Student student = new Student();
	private boolean isLoggedIn = false;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	
	public boolean login(String username, String password) {
		return false;
	}
	
	
	
}
