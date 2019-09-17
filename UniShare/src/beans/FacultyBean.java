package beans;

import java.io.Serializable;
import java.util.List;

import daoimpl.FacultyDaoImpl;
import dto.Faculty;

public class FacultyBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Faculty faculty = new Faculty();
	private FacultyDaoImpl fdi = new FacultyDaoImpl();
	
	public List<Faculty> getAllFaculties() {
		return fdi.getAllFaculties();
	}
}
