package beans;

import java.io.Serializable;

import daoimpl.StudentDaoImpl;
import dto.Student;

public class StudentBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Student student = new Student();
	private boolean isLoggedIn = false;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	
	public boolean login(String username, String password) {
		if ((student = sdi.getStudentByNameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		student = new Student();
		isLoggedIn = false;
	}

	public Student getUser() {
		return student;
	}
	
	public void setUser(Student student) {
		this.student = student;
	}
	
	public boolean add(Student student) {
		return sdi.insertStudent(student);
	}
	
}
