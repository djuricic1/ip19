package beans;

import java.io.Serializable;
import java.util.List;

import daoimpl.PostDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Student;
import dto.Post;


public class StudentBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Student student = new Student();
	private boolean isLoggedIn = false;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	private PostDaoImpl pdi = new PostDaoImpl();
	
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

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public boolean add(Student student) {
		return sdi.insertStudent(student);
	}
	
	public boolean update() {
		return sdi.updateStudent(student);
	}
	
	public List<Post> getAllPosts() {		
		return pdi.getAllByStudentId(student.getId());
	}
	
	public List<Student> getAllStudentConnected(){
		return sdi.getAllStudentsConnected(student);
	}
	
	
}
