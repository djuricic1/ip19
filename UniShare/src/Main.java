import java.util.Date;

import daoimpl.FacultyDaoImpl;
import daoimpl.StudentDaoImpl;
import dto.Faculty;
import dto.Student;

public class Main {

	public static void main(String[] args) {
		FacultyDaoImpl fdi = new FacultyDaoImpl();
		Faculty f = fdi.getFacultyById(1);
		
		StudentDaoImpl sdi = new StudentDaoImpl();
	
		Student s2 = sdi.getAllStudents().get(1);
		s2.setLastTimeActive(new Date(System.currentTimeMillis()));
		sdi.updateStudentLastTimeActive(s2);
		
	
		
		System.out.println(new Date().getTime()); 
		
	}

}
