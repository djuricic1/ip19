import java.util.Date;

import daoimpl.FacultyDaoImpl;
import daoimpl.PostDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Faculty;
import dto.Post;
import dto.Student;

public class Main {

	public static void main(String[] args) {
		FacultyDaoImpl fdi = new FacultyDaoImpl();
		//Faculty f = fdi.getFacultyById(1);
		PostDaoImpl pdi = new PostDaoImpl();
		Faculty f = fdi.getFacultyById(1);
		System.out.println(f.getName());
		StudentDaoImpl sdi = new StudentDaoImpl();
	
		Student s2 = sdi.getAllStudents().get(0);
		//s2.setLastTimeActive(new Date(System.currentTimeMillis()));
		sdi.updateStudentLastTimeActive(s2);	
	
		Post post = new Post();
		post.setDatePosted(new java.sql.Timestamp(System.currentTimeMillis()));
		post.setNumberOfDislikes(0);
		post.setNumberOfLikes(0);
		post.setStudent(s2);
		post.setDescription("Test opisa");
		
		pdi.insertPost(post);
		
		//System.out.println(new Date().getTime()); 
		
	}

}
