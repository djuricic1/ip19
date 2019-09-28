package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import daoimpl.BlogDaoImpl;
import daoimpl.ConnectionDaoImpl;
import daoimpl.FileDaoImpl;
import daoimpl.PostDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Student;
import dto.Blog;
import dto.File;
import dto.Post;


public class StudentBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Student student = new Student();
	private boolean isLoggedIn = false;
	private StudentDaoImpl sdi = new StudentDaoImpl();
	private PostDaoImpl pdi = new PostDaoImpl();
	private FileDaoImpl fdi = new FileDaoImpl();
	private BlogDaoImpl bdi = new BlogDaoImpl();
	private ConnectionDaoImpl cdi = new ConnectionDaoImpl();
	
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
	
	// returns all posts that user needs to see
	public List<Post> getAllPosts() {		
		List<Post> posts =  pdi.getAllByStudentId(student.getId());		
		List<Student> studentsConnected = sdi.getAllStudentsConnected(student);
		for(Student s : studentsConnected) {
			posts.addAll(pdi.getAllByStudentId(s.getId()));
		}
		if(posts.size() > 5) {
			Collections.sort(posts, (p1, p2) -> (p2.getNumberOfLikes() - p2.getNumberOfDislikes()) - (p1.getNumberOfLikes() - p1.getNumberOfDislikes()));
			List<Post> firstFive = posts.subList(0, 5);
			List<Post> dateSorted = posts.subList(5, posts.size());
			Collections.sort(dateSorted, (p1, p2) -> p1.getDatePosted().after(p2.getDatePosted()) ? -1 : 1);
			firstFive.addAll(dateSorted);
			
			return firstFive;
		} 
		else return posts;
		
	}
	
	public List<Student> getAllStudentConnected(){
		List<Student> ret = sdi.getAllStudentsConnected(student);
		ret.remove(student);
		//System.out.println(ret.get(0).getUsername());
		//System.out.println(ret.get(1).getUsername());
		return ret;
	}
	
	public Student getStudentById(int studentID) {
		return sdi.getStudentyById(studentID);
	}
	
	public List<File> getAllFiles() {
		List<File> files = fdi.getAllFilesByStudentId(student.getId());
		List<Student> studentsConnected = sdi.getAllStudentsConnected(student);
		for(Student s : studentsConnected) {
			files.addAll(fdi.getAllFilesByStudentId(s.getId()));
		}
		
		Collections.sort(files, (f1, f2) -> f1.getId() - f2.getId());
		
		return files;
	}
	
	public List<Blog> getAllBlogs() {
		List<Blog> blogs = bdi.getAllBlogs();
		return blogs;
	}
	
	public List<Student> getAllByFacultyId(int facultyId) {
		List<Student> ret = sdi.getAllStudentsByFacultyId(facultyId);
		ret.remove(student);
		return ret;
	}
	
	public List<Student> getAllConnectionRequests() {
		List<Student> temp = sdi.getAllStudentRequests(student);
		temp.remove(student);
		return temp;
	}
	
	public List<Integer> getRequestsSent(int studentId) {
		return cdi.getRequestsSent(studentId);
	}
}
